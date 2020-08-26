@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.render
import dev.fritz2.dom.states
import dev.fritz2.dom.values
import dev.fritz2.dom.valuesAsNumber
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Size
import org.patternfly.Switch
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfBadge
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.patternfly.pfSwitch
import org.patternfly.pfTitle
import org.patternfly.plusAssign
import org.patternfly.util
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event

object BadgeComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            pfSection("pb-0".util()) {
                pfContent {
                    pfTitle("Badge", size = Size.XL_3)
                    p {
                        +"A "
                        strong { +"badge" }
                        +"  is used to annotate other information like a label or an object name. Badges are typically used to reflect a count, e.g. number of object, number of events, number of unread, etc."
                    }
                }
            }
        })
        yield(render {
            pfSection {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Read", BadgeCode.READ) {
                    pfBadge { +"7" }
                    pfBadge { +"24" }
                    pfBadge { +"240" }
                    pfBadge { +"999+" }
                }
                snippet("Unread", BadgeCode.UNREAD) {
                    pfBadge {
                        +"7"
                        read = const(false)
                    }
                    pfBadge {
                        +"24"
                        read = const(false)
                    }
                    pfBadge {
                        +"240"
                        read = const(false)
                    }
                    pfBadge {
                        +"999+"
                        read = const(false)
                    }
                }
                snippet("Limit", BadgeCode.LIMIT) {
                    lateinit var range: Input
                    lateinit var state: Switch
                    div {
                        classList = flowOf(listOf("flex".layout(), "align-items-center".modifier(), "mb-md".util()))
                        label(`for` = "range") {
                            +"Value: "
                        }
                        range = input(id = "range", baseClass = "w-25".util()) {
                            type = const("range")
                            min = const("0")
                            max = const("500")
                            value = const("240")
                        }
                        state = pfSwitch {
                            domNode.classList += "ml-md".util()
                            label = const("Read")
                            labelOff = const("Unread")
                            input.checked = const(true)
                        }
                    }
                    pfBadge(min = 100, max = 400) {
                        read = state.input.changes.states()
                        range.changes.valuesAsNumber().map { it.toInt() }.bind()
                    }
                    MainScope().launch {
                        delay(333)
                        range.domNode.dispatchEvent(Event("change"))
                    }
                }
            }
        })
    }
}

internal object BadgeCode {

    //language=kotlin
    const val READ: String = """fun main() {
    render {
        pfBadge { +"7" }
        pfBadge { +"24" }
        pfBadge { +"240" }
        pfBadge { +"999+" }
    }
}
"""

    //language=kotlin
    const val UNREAD: String = """fun main() {
    render {
        pfBadge {
            +"7"
            read = const(false)
        }
        pfBadge {
            +"24"
            read = const(false)
        }
        pfBadge {
            +"240"
            read = const(false)
        }
        pfBadge {
            +"999+"
            read = const(false)
        }
    }
}
"""

    //language=kotlin
    const val LIMIT: String = """fun main() {
    render {
        lateinit var range: Input
        lateinit var state: Input
        div {
            label(`for` = "range") {
                +"Value: "
            }
            range = input(id = "range") {
                type = const("range")
                min = const("0")
                max = const("500")
                value = const("240")
            }
            state = pfSwitch {
                label = const("Read")
                labelOff = const("Unread")
                input.checked = const(true)
            }
        }
        pfBadge(min = 100, max = 400) {
            read = state.changes.states()
            range.changes.valuesAsNumber().map { it.toInt() }.bind()
        }
    }
}
"""
}
