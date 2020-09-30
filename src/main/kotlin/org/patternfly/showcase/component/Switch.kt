package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.states
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import org.patternfly.Elements
import org.patternfly.Notification
import org.patternfly.Severity
import org.patternfly.elements
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.patternfly.pfSwitch
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
class SwitchComponent : Elements {
    override val elements = elements {
        intro(
            title = "Switch",
            prefix = "Use a ",
            key = "switch",
            text = " to toggle the state of a setting (between on and off). Switches and checkboxes can often be used interchangeably, but the switch provides a more explicit, visible representation on a setting. Related design guidelines: ",
            ("selection-controls" to "Selection controls")
        )
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
            snippet("Reactive", SwitchCode.REACTIVE) {
                pfSwitch {
                    label = const("Message when on")
                    labelOff = const("Message when off")
                    input.changes.states().map {
                        Notification(Severity.INFO, "Switch is ${if (it) "" else "not"} checked")
                    } handledBy Notification.store.add
                }
            }
        }
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
    const val REACTIVE: String = """fun main() {
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
