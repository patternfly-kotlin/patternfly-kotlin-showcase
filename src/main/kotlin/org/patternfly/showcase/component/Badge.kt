package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.states
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Elements
import org.patternfly.Switch
import org.patternfly.classes
import org.patternfly.elements
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfBadge
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.patternfly.pfSwitch
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
class BadgeComponent : Elements {
    private lateinit var range: Input
    private lateinit var state: Switch
    override val elements = elements {
        intro(
            title = "Badge",
            prefix = "A ",
            key = "badge",
            text = " is used to annotate other information like a label or an object name. Badges are typically used to reflect a count, e.g. number of object, number of events, number of unread, etc."
        )
        pfSection(baseClass = "sc-component__badges") {
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
            snippet("Reactive", BadgeCode.REACTIVE) {
                div(baseClass = classes {
                    +"flex".layout()
                    +"align-items-center".modifier()
                    +"mb-md".util()
                }) {
                    label(`for` = "range") { +"Value: " }
                    range = input(id = "range", baseClass = "w-50".util()) {
                        type = const("range")
                        min = const("0")
                        max = const("500")
                        value = const("240")
                    }
                    state = pfSwitch("ml-md".util()) {
                        label = const("Read")
                        labelOff = const("Unread")
                        input.checked = const(true)
                    }
                }
                pfBadge(min = 100, max = 400) {
                    read = state.input.changes.states()
                    range.inputs.events
                        .map { it.target.unsafeCast<HTMLInputElement>().valueAsNumber.toInt() }
                        .bind()
                }
            }
        }
    }

    init {
        MainScope().launch {
            delay(EVENT_DELAY)
            range.domNode.dispatchEvent(Event(Events.input.name))
        }
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
    const val REACTIVE: String = """fun main() {
    render {
        lateinit var range: Input
        lateinit var state: Switch
        div(baseClass = classes {
            +"flex".layout()
            +"align-items-center".modifier()
            +"mb-md".util()
        }) {
            label(`for` = "range") { +"Value: " }
            range = input(id = "range", baseClass = "w-25".util()) {
                type = const("range")
                min = const("0")
                max = const("500")
                value = const("240")
            }
            state = pfSwitch("ml-md".util()) {
                label = const("Read")
                labelOff = const("Unread")
                input.checked = const(true)
            }
        }
        pfBadge(min = 100, max = 400) {
            read = state.input.changes.states()
            range.changes.valuesAsNumber().map { it.toInt() }.bind()
        }
    }
}
"""
}
