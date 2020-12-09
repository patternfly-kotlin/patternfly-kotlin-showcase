package org.patternfly.showcase.component

import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.states
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.ButtonVariation.control
import org.patternfly.ButtonVariation.danger
import org.patternfly.ButtonVariation.inline
import org.patternfly.ButtonVariation.link
import org.patternfly.ButtonVariation.plain
import org.patternfly.ButtonVariation.primary
import org.patternfly.ButtonVariation.secondary
import org.patternfly.ButtonVariation.tertiary
import org.patternfly.IconPosition.ICON_FIRST
import org.patternfly.IconPosition.ICON_LAST
import org.patternfly.Notification
import org.patternfly.Switch
import org.patternfly.buttonIcon
import org.patternfly.classes
import org.patternfly.clickButton
import org.patternfly.component
import org.patternfly.dom.aria
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.layout
import org.patternfly.linkButton
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.pushButton
import org.patternfly.showcase.EVENT_DELAY
import org.patternfly.switch
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

object ButtonComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Button",
            key = "Buttons",
            text = " communicate and trigger actions a user can take in an application or website. Related design guidelines: ",
            link = ("buttons-and-links" to "Buttons and links")
        )
        pageSection(baseClass = "sc-component__buttons") {
            h2 { +"Examples" }
            snippet("Variations", ButtonCode.VARIATIONS) {
                pushButton(primary) { +"Primary" }
                pushButton(secondary) { +"Secondary" }
                pushButton(tertiary) { +"Tertiary" }
                pushButton(danger) { +"Danger" }
                br {}
                br {}
                pushButton(link) {
                    buttonIcon(ICON_FIRST, "plus-circle".fas())
                    +"Link"
                }
                pushButton(link) {
                    +"Link"
                    buttonIcon(ICON_LAST, "plus-circle".fas())
                }
                pushButton(link, inline) { +"Inline link" }
                br {}
                br {}
                pushButton(plain) { icon("times".fas()) }
                br {}
                br {}
                pushButton(control) { +"Control" }
                pushButton(control) { icon("copy".fas()) }
                br {}
                br {}
                pushButton(primary, baseClass = "small".modifier()) { +"Primary" }
                pushButton(secondary, baseClass = "small".modifier()) { +"Secondary" }
                pushButton(tertiary, baseClass = "small".modifier()) { +"Tertiary" }
                pushButton(danger, baseClass = "small".modifier()) { +"Danger" }
                pushButton(link, baseClass = "small".modifier()) {
                    buttonIcon(ICON_FIRST, "plus-circle".fas())
                    +"Link"
                }
                pushButton(link, inline, baseClass = "small".modifier()) { +"Inline link" }
                pushButton(baseClass = classes("control".modifier(), "small".modifier())) { +"Control" }
            }
            snippet("Disabled", ButtonCode.DISABLED) {
                pushButton(primary) {
                    disabled(true)
                    +"Primary disabled"
                }
                pushButton(secondary) {
                    disabled(true)
                    +"Secondary disabled"
                }
                pushButton(tertiary) {
                    disabled(true)
                    +"Tertiary disabled"
                }
                pushButton(danger) {
                    disabled(true)
                    +"Danger disabled"
                }
                pushButton(link) {
                    disabled(true)
                    buttonIcon(ICON_FIRST, "plus-circle".fas())
                    +"Link disabled"
                }
                pushButton(link, inline) {
                    disabled(true)
                    +"Inline link disabled"
                }
                pushButton(plain) {
                    disabled(true)
                    icon("times".fas())
                }
                pushButton(control) {
                    disabled(true)
                    +"Control disabled"
                }
            }
            snippet("Aria-disabled", ButtonCode.ARIA_DISABLED) {
                pushButton(primary, baseClass = "aria-disabled".modifier()) {
                    aria["disabled"] = true
                    +"Primary disabled"
                }
                pushButton(secondary, baseClass = "aria-disabled".modifier()) {
                    aria["disabled"] = true
                    +"Secondary disabled"
                }
                pushButton(tertiary, baseClass = "aria-disabled".modifier()) {
                    aria["disabled"] = true
                    +"Tertiary disabled"
                }
                pushButton(danger, baseClass = "aria-disabled".modifier()) {
                    aria["disabled"] = true
                    +"Danger disabled"
                }
                pushButton(link, baseClass = "aria-disabled".modifier()) {
                    aria["disabled"] = true
                    buttonIcon(ICON_FIRST, "plus-circle".fas())
                    +"Link disabled"
                }
                pushButton(link, inline, baseClass = "aria-disabled".modifier()) {
                    aria["disabled"] = true
                    +"Inline link disabled"
                }
                pushButton(plain, baseClass = "aria-disabled".modifier()) {
                    aria["disabled"] = true
                    icon("times".fas())
                }
                pushButton(control, baseClass = "aria-disabled".modifier()) {
                    aria["disabled"] = true
                    +"Control disabled"
                }
            }
            snippet("Links as buttons", ButtonCode.LINKS) {
                linkButton(primary) {
                    href("https://www.w3.org/TR/WCAG20-TECHS/ARIA8.html#ARIA8-examples")
                    target("_blank")
                    +"Primary link to W3.org"
                }
                linkButton(secondary) {
                    href("https://www.patternfly.org")
                    target("_blank")
                    +"Secondary link to PatternFly"
                }
                linkButton(tertiary, baseClass = "disabled".modifier()) {
                    aria["disabled"] = true
                    domNode.tabIndex = -1
                    href("https://www.patternfly.org")
                    target("_blank")
                    +"Tertiary link to W3.org"
                }
            }
            snippet("Block level", ButtonCode.BLOCK_LEVEL) {
                pushButton(primary, baseClass = "block".modifier()) { +"Block level button" }
            }
            snippet("Types", ButtonCode.TYPES) {
                pushButton(primary) {
                    type("submit")
                    +"Submit"
                }
                pushButton(primary) {
                    type("reset")
                    +"Reset"
                }
                pushButton(primary) {
                    +"Default"
                }
            }
            snippet("Call to action", ButtonCode.CALL_TO_ACTION) {
                pushButton(primary, baseClass = "display-lg".modifier()) { +"Call to action" }
                pushButton(secondary, baseClass = "display-lg".modifier()) { +"Call to action" }
                pushButton(tertiary, baseClass = "display-lg".modifier()) { +"Call to action" }
                pushButton(link, baseClass = "display-lg".modifier()) {
                    +"Call to action"
                    buttonIcon(ICON_LAST, "arrow-right".fas())
                }
                pushButton(link, inline, baseClass = "display-lg".modifier()) {
                    +"Call to action"
                    buttonIcon(ICON_LAST, "arrow-right".fas())
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
                        type("text")
                        value("Click me")
                        placeholder("Button text")
                    }
                    enabled = switch("ml-md".util()) {
                        label("Enabled")
                        labelOff("Disabled")
                        input.checked(true)
                    }
                }
                br {}
                clickButton(baseClass = "primary".modifier()) {
                    disabled(enabled.input.changes.states().map { !it })
                    text.keyups.map { currentValue(it) }.asText()
                } handledBy Notification.info("Button clicked")

                MainScope().launch {
                    delay(EVENT_DELAY)
                    text.domNode.dispatchEvent(Event(Events.keyup.name))
                }
            }
        }
    }
}

object ButtonCode {

    //language=kotlin
    const val VARIATIONS: String = """fun main() {
    render {
        pushButton(baseClass = "primary".modifier()) { +"Primary" }
        pushButton(baseClass = "secondary".modifier()) { +"Secondary" }
        pushButton(baseClass = "tertiary".modifier()) { +"Tertiary" }
        pushButton(baseClass = "danger".modifier()) { +"Danger" }
        br {}
        br {}
        pushButton(baseClass = "link".modifier()) {
            icon(START, "plus-circle".fas())
            +"Link"
        }
        pushButton(baseClass = "link".modifier()) {
            +"Link"
            icon(END, "plus-circle".fas())
        }
        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Inline link" }
        br {}
        br {}
        pushButton(baseClass = "plain".modifier()) { icon("times".fas()) }
        br {}
        br {}
        pushButton(baseClass = "control".modifier()) { +"Control" }
        pushButton(baseClass = "control".modifier()) { icon("copy".fas()) }
        br {}
        br {}
        pushButton(baseClass = classes("primary".modifier(), "small".modifier())) { +"Primary" }
        pushButton(baseClass = classes("secondary".modifier(), "small".modifier())) { +"Secondary" }
        pushButton(baseClass = classes("tertiary".modifier(), "small".modifier())) { +"Tertiary" }
        pushButton(baseClass = classes("danger".modifier(), "small".modifier())) { +"Danger" }
        pushButton(baseClass = classes("link".modifier(), "small".modifier())) {
            icon(START, "plus-circle".fas())
            +"Link"
        }
        pushButton(baseClass = classes("link".modifier(), "inline".modifier(), "small".modifier())) { +"Inline link" }
        pushButton(baseClass = classes("control".modifier(), "small".modifier())) { +"Control" }
    }
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        pushButton(baseClass = "primary".modifier()) {
            disabled = const(true)
            +"Primary disabled"
        }
        pushButton(baseClass = "secondary".modifier()) {
            disabled = const(true)
            +"Secondary disabled"
        }
        pushButton(baseClass = "tertiary".modifier()) {
            disabled = const(true)
            +"Tertiary disabled"
        }
        pushButton(baseClass = "danger".modifier()) {
            disabled = const(true)
            +"Danger disabled"
        }
        pushButton(baseClass = "link".modifier()) {
            disabled = const(true)
            icon(START, "plus-circle".fas())
            +"Link disabled"
        }
        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) {
            disabled = const(true)
            +"Inline link disabled"
        }
        pushButton(baseClass = "plain".modifier()) {
            disabled = const(true)
            icon("times".fas())
        }
        pushButton(baseClass = "control".modifier()) {
            disabled = const(true)
            +"Control disabled"
        }
    }
}
"""

    //language=kotlin
    const val ARIA_DISABLED: String = """fun main() {
    render {
        pushButton(baseClass = classes("primary".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Primary disabled"
        }
        pushButton(baseClass = classes("secondary".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Secondary disabled"
        }
        pushButton(baseClass = classes("tertiary".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Tertiary disabled"
        }
        pushButton(baseClass = classes("danger".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Danger disabled"
        }
        pushButton(baseClass = classes("link".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            icon(START, "plus-circle".fas())
            +"Link disabled"
        }
        pushButton(baseClass = classes("link".modifier(), "aria-disabled".modifier(), "inline".modifier())) {
            aria["disabled"] = true
            +"Inline link disabled"
        }
        pushButton(baseClass = classes("plain".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            icon("times".fas())
        }
        pushButton(baseClass = classes("control".modifier(), "aria-disabled".modifier())) {
            aria["disabled"] = true
            +"Control disabled"
        }
    }
}
"""

    //language=kotlin
    const val LINKS: String = """fun main() {
    render {
        linkButton("primary".modifier()) {
            href = const("https://www.w3.org/TR/WCAG20-TECHS/ARIA8.html#ARIA8-examples")
            target = const("_blank")
            +"Primary link to W3.org"
        }
        linkButton("secondary".modifier()) {
            href = const("https://www.patternfly.org")
            target = const("_blank")
            +"Secondary link to PatternFly"
        }
        linkButton(classes("tertiary".modifier(), "disabled".modifier())) {
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
        pushButton(baseClass = classes("primary".modifier(), "block".modifier())) { +"Block level button" }
    } 
}
"""

    //language=kotlin
    const val TYPES: String = """fun main() {
    render {
        pushButton(baseClass = "primary".modifier()) {
            type = const("submit")
            +"Submit"
        }
        pushButton(baseClass = "primary".modifier()) {
            type = const("reset")
            +"Reset"
        }
        pushButton(baseClass = "primary".modifier()) {
            +"Default"
        }
    } 
}
"""

    //language=kotlin
    const val CALL_TO_ACTION: String = """fun main() {
    render {
        pushButton(baseClass = classes("primary".modifier(), "display-lg".modifier())) { +"Call to action" }
        pushButton(baseClass = classes("secondary".modifier(), "display-lg".modifier())) { +"Call to action" }
        pushButton(baseClass = classes("tertiary".modifier(), "display-lg".modifier())) { +"Call to action" }
        pushButton(baseClass = classes("link".modifier(), "display-lg".modifier())) {
            +"Call to action"
            icon(END, "arrow-right".fas())
        }
        pushButton(baseClass = classes("link".modifier(), "inline".modifier(), "display-lg".modifier())) {
            +"Call to action"
            icon(END, "arrow-right".fas())
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
            enabled = switch("ml-md".util()) {
                label = const("Enabled")
                labelOff = const("Disabled")
                input.checked = const(true)
            }
        }
        br {}
        pushButton(baseClass = "primary".modifier()) {
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
