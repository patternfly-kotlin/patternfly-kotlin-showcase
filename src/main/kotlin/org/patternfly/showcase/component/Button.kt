package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.states
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Elements
import org.patternfly.Notification
import org.patternfly.Position.END
import org.patternfly.Position.START
import org.patternfly.Severity
import org.patternfly.Switch
import org.patternfly.aria
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.elements
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfButton
import org.patternfly.pfContent
import org.patternfly.pfIcon
import org.patternfly.pfLinkButton
import org.patternfly.pfSection
import org.patternfly.pfSwitch
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
class ButtonComponent : Elements {
    override val elements = elements {
        intro(
            title = "Button",
            key = "Buttons",
            text = " communicate and trigger actions a user can take in an application or website. Related design guidelines: ",
            link = ("buttons-and-links" to "Buttons and links")
        )
        pfSection(baseClass = "sc-component__buttons") {
            pfContent {
                h2 { +"Examples" }
            }
            snippet("Variations", ButtonCode.VARIATIONS) {
                pfButton(baseClass = "primary".modifier()) { +"Primary" }
                pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
                pfButton(baseClass = "tertiary".modifier()) { +"Tertiary" }
                pfButton(baseClass = "danger".modifier()) { +"Danger" }
                br {}
                br {}
                pfButton(baseClass = "link".modifier()) {
                    pfIcon(START, "plus-circle".fas())
                    +"Link"
                }
                pfButton(baseClass = "link".modifier()) {
                    +"Link"
                    pfIcon(END, "plus-circle".fas())
                }
                pfButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Inline link" }
                br {}
                br {}
                pfButton(baseClass = "plain".modifier()) { pfIcon("times".fas()) }
                br {}
                br {}
                pfButton(baseClass = "control".modifier()) { +"Control" }
                pfButton(baseClass = "control".modifier()) { pfIcon("copy".fas()) }
                br {}
                br {}
                pfButton(baseClass = classes("primary".modifier(), "small".modifier())) { +"Primary" }
                pfButton(baseClass = classes("secondary".modifier(), "small".modifier())) { +"Secondary" }
                pfButton(baseClass = classes("tertiary".modifier(), "small".modifier())) { +"Tertiary" }
                pfButton(baseClass = classes("danger".modifier(), "small".modifier())) { +"Danger" }
                pfButton(baseClass = classes("link".modifier(), "small".modifier())) {
                    pfIcon(START, "plus-circle".fas())
                    +"Link"
                }
                pfButton(baseClass = classes("link".modifier(), "inline".modifier(), "small".modifier())) { +"Inline link" }
                pfButton(baseClass = classes("control".modifier(), "small".modifier())) { +"Control" }
            }
            snippet("Disabled", ButtonCode.DISABLED) {
                pfButton(baseClass = "primary".modifier()) {
                    disabled = const(false)
                    +"Primary disabled"
                }
                pfButton(baseClass = "secondary".modifier()) {
                    disabled = const(false)
                    +"Secondary disabled"
                }
                pfButton(baseClass = "tertiary".modifier()) {
                    disabled = const(false)
                    +"Tertiary disabled"
                }
                pfButton(baseClass = "danger".modifier()) {
                    disabled = const(false)
                    +"Danger disabled"
                }
                pfButton(baseClass = "link".modifier()) {
                    disabled = const(false)
                    pfIcon(START, "plus-circle".fas())
                    +"Link disabled"
                }
                pfButton(baseClass = classes("link".modifier(), "inline".modifier())) {
                    disabled = const(false)
                    +"Inline link disabled"
                }
                pfButton(baseClass = "plain".modifier()) {
                    disabled = const(false)
                    pfIcon("times".fas())
                }
                pfButton(baseClass = "control".modifier()) {
                    disabled = const(false)
                    +"Control disabled"
                }
            }
            snippet("Aria-disabled", ButtonCode.ARIA_DISABLED) {
                pfButton(baseClass = classes("primary".modifier(), "aria-disabled".modifier())) {
                    aria["disabled"] = true
                    +"Primary disabled"
                }
                pfButton(baseClass = classes("secondary".modifier(), "aria-disabled".modifier())) {
                    aria["disabled"] = true
                    +"Secondary disabled"
                }
                pfButton(baseClass = classes("tertiary".modifier(), "aria-disabled".modifier())) {
                    aria["disabled"] = true
                    +"Tertiary disabled"
                }
                pfButton(baseClass = classes("danger".modifier(), "aria-disabled".modifier())) {
                    aria["disabled"] = true
                    +"Danger disabled"
                }
                pfButton(baseClass = classes("link".modifier(), "aria-disabled".modifier())) {
                    aria["disabled"] = true
                    pfIcon(START, "plus-circle".fas())
                    +"Link disabled"
                }
                pfButton(baseClass = classes("link".modifier(), "aria-disabled".modifier(), "inline".modifier())) {
                    aria["disabled"] = true
                    +"Inline link disabled"
                }
                pfButton(baseClass = classes("plain".modifier(), "aria-disabled".modifier())) {
                    aria["disabled"] = true
                    pfIcon("times".fas())
                }
                pfButton(baseClass = classes("control".modifier(), "aria-disabled".modifier())) {
                    aria["disabled"] = true
                    +"Control disabled"
                }
            }
            snippet("Links as buttons", ButtonCode.LINKS) {
                pfLinkButton("primary".modifier()) {
                    href = const("https://www.w3.org/TR/WCAG20-TECHS/ARIA8.html#ARIA8-examples")
                    target = const("_blank")
                    +"Primary link to W3.org"
                }
                pfLinkButton("secondary".modifier()) {
                    href = const("https://www.patternfly.org")
                    target = const("_blank")
                    +"Secondary link to PatternFly"
                }
                pfLinkButton(classes("tertiary".modifier(), "disabled".modifier())) {
                    aria["disabled"] = true
                    domNode.tabIndex = -1
                    href = const("https://www.patternfly.org")
                    target = const("_blank")
                    +"Tertiary link to W3.org"
                }
            }
            snippet("Block level", ButtonCode.BLOCK_LEVEL) {
                pfButton(baseClass = classes("primary".modifier(), "block".modifier())) { +"Block level button" }
            }
            snippet("Types", ButtonCode.TYPES) {
                pfButton(baseClass = "primary".modifier()) {
                    type = const("submit")
                    +"Submit"
                }
                pfButton(baseClass = "primary".modifier()) {
                    type = const("reset")
                    +"Reset"
                }
                pfButton(baseClass = "primary".modifier()) {
                    +"Default"
                }
            }
            snippet("Call to action", ButtonCode.CALL_TO_ACTION) {
                pfButton(baseClass = classes("primary".modifier(), "display-lg".modifier())) { +"Call to action" }
                pfButton(baseClass = classes("secondary".modifier(), "display-lg".modifier())) { +"Call to action" }
                pfButton(baseClass = classes("tertiary".modifier(), "display-lg".modifier())) { +"Call to action" }
                pfButton(baseClass = classes("link".modifier(), "display-lg".modifier())) {
                    +"Call to action"
                    pfIcon(END, "arrow-right".fas())
                }
                pfButton(baseClass = classes("link".modifier(), "inline".modifier(), "display-lg".modifier())) {
                    +"Call to action"
                    pfIcon(END, "arrow-right".fas())
                }
            }
            snippet("Reactive", ButtonCode.REACTIVE) {
                lateinit var text: Input
                lateinit var enabled: Switch

                fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

                div(baseClass = classes {
                    +"flex".layout()
                    +"align-items-center".modifier()
                    +"mb-md".util()
                }) {
                    text = input(baseClass = classes("form-control".component(), "w-50".util())) {
                        type = const("text")
                        value = const("Click me")
                        placeholder = const("Button text")
                    }
                    enabled = pfSwitch("ml-md".util()) {
                        label = const("Enabled")
                        labelOff = const("Disabled")
                        input.checked = const(true)
                    }
                }
                br {}
                pfButton(baseClass = "primary".modifier()) {
                    disabled = enabled.input.changes.states().map { !it }
                    text.keyups.map { currentValue(it) }.bind()
                    clicks.map {
                        Notification(Severity.INFO, "Button clicked")
                    } handledBy Notification.store.add
                }

                MainScope().launch {
                    delay(EVENT_DELAY)
                    text.domNode.dispatchEvent(Event(Events.keyup.name))
                }
            }
        }
    }
}

internal object ButtonCode {

    //language=kotlin
    const val VARIATIONS: String = """fun main() {
    render {
        pfButton(baseClass = "primary".modifier()) { +"Primary" }
        pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
        pfButton(baseClass = "tertiary".modifier()) { +"Tertiary" }
        pfButton(baseClass = "danger".modifier()) { +"Danger" }
        br {}
        br {}
        pfButton(baseClass = "link".modifier()) {
            pfIcon(START, "plus-circle".fas())
            +"Link"
        }
        pfButton(baseClass = "link".modifier()) {
            +"Link"
            pfIcon(END, "plus-circle".fas())
        }
        pfButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Inline link" }
        br {}
        br {}
        pfButton(baseClass = "plain".modifier()) { pfIcon("times".fas()) }
        br {}
        br {}
        pfButton(baseClass = "control".modifier()) { +"Control" }
        pfButton(baseClass = "control".modifier()) { pfIcon("copy".fas()) }
        br {}
        br {}
        pfButton(baseClass = classes("primary".modifier(), "small".modifier())) { +"Primary" }
        pfButton(baseClass = classes("secondary".modifier(), "small".modifier())) { +"Secondary" }
        pfButton(baseClass = classes("tertiary".modifier(), "small".modifier())) { +"Tertiary" }
        pfButton(baseClass = classes("danger".modifier(), "small".modifier())) { +"Danger" }
        pfButton(baseClass = classes("link".modifier(), "small".modifier())) {
            pfIcon(START, "plus-circle".fas())
            +"Link"
        }
        pfButton(baseClass = classes("link".modifier(), "inline".modifier(), "small".modifier())) { +"Inline link" }
        pfButton(baseClass = classes("control".modifier(), "small".modifier())) { +"Control" }
    }
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        pfButton(baseClass = "primary".modifier()) {
            disabled = const(false)
            +"Primary disabled"
        }
        pfButton(baseClass = "secondary".modifier()) {
            disabled = const(false)
            +"Secondary disabled"
        }
        pfButton(baseClass = "tertiary".modifier()) {
            disabled = const(false)
            +"Tertiary disabled"
        }
        pfButton(baseClass = "danger".modifier()) {
            disabled = const(false)
            +"Danger disabled"
        }
        pfButton(baseClass = "link".modifier()) {
            disabled = const(false)
            pfIcon(START, "plus-circle".fas())
            +"Link disabled"
        }
        pfButton(baseClass = classes("link".modifier(), "inline".modifier())) {
            disabled = const(false)
            +"Inline link disabled"
        }
        pfButton(baseClass = "plain".modifier()) {
            disabled = const(false)
            pfIcon("times".fas())
        }
        pfButton(baseClass = "control".modifier()) {
            disabled = const(false)
            +"Control disabled"
        }
    }
}
"""

    //language=kotlin
    const val ARIA_DISABLED: String = """fun main() {
    render {
        pfButton(baseClass = classes("primary".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Primary disabled"
        }
        pfButton(baseClass = classes("secondary".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Secondary disabled"
        }
        pfButton(baseClass = classes("tertiary".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Tertiary disabled"
        }
        pfButton(baseClass = classes("danger".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Danger disabled"
        }
        pfButton(baseClass = classes("link".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            pfIcon(START, "plus-circle".fas())
            +"Link disabled"
        }
        pfButton(baseClass = classes("link".modifier(), "aria-disabled".modifier(), "inline".modifier())) {
            aria["disabled"] = true
            +"Inline link disabled"
        }
        pfButton(baseClass = classes("plain".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            pfIcon("times".fas())
        }
        pfButton(baseClass = classes("control".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Control disabled"
        }
    }
}
"""

    //language=kotlin
    const val LINKS: String = """fun main() {
    render {
        pfLinkButton("primary".modifier()) {
            href = const("https://www.w3.org/TR/WCAG20-TECHS/ARIA8.html#ARIA8-examples")
            target = const("_blank")
            +"Primary link to W3.org"
        }
        pfLinkButton("secondary".modifier()) {
            href = const("https://www.patternfly.org")
            target = const("_blank")
            +"Secondary link to PatternFly"
        }
        pfLinkButton(classes("tertiary".modifier(), "disabled".modifier())) {
            aria["disabled"] = true
            domNode.tabIndex = -1
            href = const("https://www.patternfly.org")
            target = const("_blank")
            +"Tertiary link to W3.org"
        }
    } 
}
"""

    //language=kotlin
    const val BLOCK_LEVEL: String = """fun main() {
    render {
        pfButton(baseClass = classes("primary".modifier(), "block".modifier())) { +"Block level button" }
    } 
}
"""

    //language=kotlin
    const val TYPES: String = """fun main() {
    render {
        pfButton(baseClass = "primary".modifier()) {
            type = const("submit")
            +"Submit"
        }
        pfButton(baseClass = "primary".modifier()) {
            type = const("reset")
            +"Reset"
        }
        pfButton(baseClass = "primary".modifier()) {
            +"Default"
        }
    } 
}
"""

    //language=kotlin
    const val CALL_TO_ACTION: String = """fun main() {
    render {
        pfButton(baseClass = classes("primary".modifier(), "display-lg".modifier())) { +"Call to action" }
        pfButton(baseClass = classes("secondary".modifier(), "display-lg".modifier())) { +"Call to action" }
        pfButton(baseClass = classes("tertiary".modifier(), "display-lg".modifier())) { +"Call to action" }
        pfButton(baseClass = classes("link".modifier(), "display-lg".modifier())) {
            +"Call to action"
            pfIcon(END, "arrow-right".fas())
        }
        pfButton(baseClass = classes("link".modifier(), "inline".modifier(), "display-lg".modifier())) {
            +"Call to action"
            pfIcon(END, "arrow-right".fas())
        }
    } 
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        lateinit var text: Input
        lateinit var enabled: Switch

        fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

        div(baseClass = classes {
            +"flex".layout()
            +"align-items-center".modifier()
            +"mb-md".util()
        }) {
            text = input(baseClass = classes("form-control".component(), "w-50".util())) {
                type = const("text")
                value = const("Click me")
                placeholder = const("Button text")
            }
            enabled = pfSwitch("ml-md".util()) {
                label = const("Enabled")
                labelOff = const("Disabled")
                input.checked = const(true)
            }
        }
        br {}
        pfButton(baseClass = "primary".modifier()) {
            disabled = enabled.input.changes.states().map { !it }
            text.keyups.map { currentValue(it) }.bind()
            clicks.map {
                Notification(Severity.INFO, "Button clicked")
            } handledBy Notification.store.add
        }

        MainScope().launch {
            delay(EVENT_DELAY)
            text.domNode.dispatchEvent(Event(Events.keyup.name))
        }
    }
}
"""
}
