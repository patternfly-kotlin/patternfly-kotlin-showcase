package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.states
import kotlinx.coroutines.flow.map
import org.patternfly.Notification
import org.patternfly.NotificationStore
import org.patternfly.Severity
import org.patternfly.pageSection
import org.patternfly.switch

class SwitchComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Switch",
            prefix = "Use a ",
            key = "switch",
            text = " to toggle the state of a setting (between on and off). Switches and checkboxes can often be used interchangeably, but the switch provides a more explicit, visible representation on a setting. Related design guidelines: ",
            ("selection-controls" to "Selection controls")
        )
        pageSection {
            h2 { +"Examples" }
            snippet("Basic", SwitchCode.BASIC) {
                switch {
                    label("Message when on")
                    labelOff("Message when off")
                }
            }
            snippet("Without label", SwitchCode.WITHOUT_LABEL) {
                switch()
            }
            snippet("Disabled", SwitchCode.DISABLED) {
                switch {
                    label("Message when on")
                    labelOff("Message when off")
                    disabled(true)
                }
            }
            snippet("Reactive", SwitchCode.REACTIVE) {
                switch {
                    label("Message when on")
                    labelOff("Message when off")
                    input.changes.states().map {
                        Notification(Severity.INFO, "Switch is ${if (it) "" else "not"} checked")
                    } handledBy NotificationStore.add
                }
            }
        }
    }
}

internal object SwitchCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        switch {
            label = const("Message when on")
            labelOff = const("Message when off")
        }
    }
}
"""

    //language=kotlin
    const val WITHOUT_LABEL: String = """fun main() {
    render {
            switch()
    }
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        switch {
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
        switch {
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
