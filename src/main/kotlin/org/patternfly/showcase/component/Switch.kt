package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.states
import org.patternfly.Severity
import org.patternfly.notification
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
                switch(storeOf(true)) {
                    label { +"Message when on" }
                    labelOff { +"Message when off" }
                }
            }
            snippet("Reversed layout", SwitchCode.REVERSED) {
                switch(storeOf(true), reversed = true) {
                    label { +"Message when on" }
                    labelOff { +"Message when off" }
                }
            }
            snippet("Without label", SwitchCode.WITHOUT_LABEL) {
                switch(storeOf(true), withCheckIcon = true)
            }
            snippet("Disabled", SwitchCode.DISABLED) {
                switch(storeOf(true)) {
                    label { +"Message when on" }
                    labelOff { +"Message when off" }
                    disabled(true)
                }
                br { }
                switch(storeOf(false)) {
                    label { +"Message when on" }
                    labelOff { +"Message when off" }
                    disabled(true)
                }
                br { }
                switch(storeOf(true), withCheckIcon = true) {
                    disabled(true)
                }
                br { }
                switch(storeOf(false), withCheckIcon = true) {
                    disabled(true)
                }
            }
            snippet("Reactive", SwitchCode.REACTIVE) {
                switch(storeOf(true)) {
                    label { +"Message when on" }
                    labelOff { +"Message when off" }
                    input {
                        changes.states() handledBy notification {
                            severity(Severity.INFO)
                            title("Switch is ${if (it) "" else "not"} checked")
                        }
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
        switch(storeOf(true)) {
            label { +"Message when on" }
            labelOff { +"Message when off" }
        }
    }
}
"""

    //language=kotlin
    const val REVERSED: String = """fun main() {
    render {
        switch(storeOf(true), reversed = true) {
            label { +"Message when on" }
            labelOff { +"Message when off" }
        }
    }
}
"""

    //language=kotlin
    const val WITHOUT_LABEL: String = """fun main() {
    render {
        switch(storeOf(true), withCheckIcon = true)
    }
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        switch(storeOf(true)) {
            label { +"Message when on" }
            labelOff { +"Message when off" }
            disabled(true)
        }
        br { }
        switch(storeOf(false)) {
            label { +"Message when on" }
            labelOff { +"Message when off" }
            disabled(true)
        }
        br { }
        switch(storeOf(true), withCheckIcon = true) {
            disabled(true)
        }
        br { }
        switch(storeOf(false), withCheckIcon = true) {
            disabled(true)
        }
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        switch(storeOf(true)) {
            label("Message when on")
            labelOff("Message when off")
            input {
                changes.states() handledBy notification {
                    severity(Severity.INFO)
                    title("Switch is ${'$'}{if (it) "" else "not"} checked")
                }
            }
        }
    }
}
"""
}
