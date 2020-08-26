@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
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
import org.patternfly.Notification
import org.patternfly.Severity
import org.patternfly.Size
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfBadge
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.patternfly.pfSwitch
import org.patternfly.pfTitle
import org.patternfly.showcase.Places
import org.patternfly.util
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event

object SwitchComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            pfSection("pb-0".util()) {
                pfContent {
                    pfTitle("Switch", size = Size.XL_3)
                    p {
                        +"Use a "
                        strong { +"switch" }
                        +" to toggle the state of a setting (between on and off). Switches and checkboxes can often be used interchangeably, but the switch provides a more explicit, visible representation on a setting. Related design guidelines: "
                        a {
                            href = const(Places.behaviour("selection-controls"))
                            target = const("pf4")
                            +"Selection controls"
                        }
                    }
                }
            }
        })
        yield(render {
            pfSection {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", SwitchCode.BASIC) {
                    pfSwitch {
                        label = const("Message when on")
                        labelOff = const("Message when off")
                    }
                }
                snippet("Without label", SwitchCode.WITHOUT_LABEL) {
                    pfSwitch()
                }
                snippet("Disabled", SwitchCode.DISABLED) {
                    pfSwitch {
                        label = const("Message when on")
                        labelOff = const("Message when off")
                        disabled = const(true)
                    }
                }
                snippet("Event", SwitchCode.EVENT) {
                    pfSwitch {
                        label = const("Message when on")
                        labelOff = const("Message when off")
                        input.changes.states().map {
                            Notification(Severity.INFO, "Switch is ${if (it) "" else "not"} checked")
                        } handledBy Notification.store.add
                    }
                }
            }
        })
    }
}

internal object SwitchCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pfSwitch {
            label = const("Message when on")
            labelOff = const("Message when off")
        }
    }
}
"""

    //language=kotlin
    const val WITHOUT_LABEL: String = """fun main() {
    render {
        pfSwitch()
    }
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        pfSwitch {
            label = const("Message when on")
            labelOff = const("Message when off")
            disabled = const(true)
        }
    }
}
"""

    //language=kotlin
    const val EVENT: String = """fun main() {
    render {
        pfSwitch {
            label = const("Message when on")
            labelOff = const("Message when off")
            input.changes.states().map {
                Notification(Severity.INFO, "Switch is ${'$'}{if (it) "" else "not"} checked")
            } handledBy Notification.store.add
        }
    }
}
"""
}
