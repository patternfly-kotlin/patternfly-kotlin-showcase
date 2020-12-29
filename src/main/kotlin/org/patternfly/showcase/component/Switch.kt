package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.states
import org.patternfly.Notification
import org.patternfly.pageSection
import org.patternfly.switch

object SwitchComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Switch",
            text = "A switch toggles the state of a setting (between on and off).",
            designGuidelines = "https://www.patternfly.org/v4/components/switch/design-guidelines"
        )
        pageSection {
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
                    input.changes.states() handledBy Notification.add {
                        info("Switch is ${if (it) "" else "not"} checked")
                    }
                }
            }
        }
    }
}

object SwitchCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        switch {
            label("Message when on")
            labelOff("Message when off")
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
            label("Message when on")
            labelOff("Message when off")
            disabled(true)
        }
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        switch {
            label("Message when on")
            labelOff("Message when off")
            input.changes.states() handledBy Notification.add {
                info("Switch is ${'$'}{if (it) "" else "not"} checked")
            }
        }
    }
}
"""
}
