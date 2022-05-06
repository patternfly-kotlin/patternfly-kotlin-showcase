@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package org.patternfly.showcase.component

import dev.fritz2.binding.RootStore
import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.states
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.ButtonVariant.control
import org.patternfly.ButtonVariant.danger
import org.patternfly.ButtonVariant.inline
import org.patternfly.ButtonVariant.link
import org.patternfly.ButtonVariant.plain
import org.patternfly.ButtonVariant.primary
import org.patternfly.ButtonVariant.secondary
import org.patternfly.ButtonVariant.tertiary
import org.patternfly.Severity.INFO
import org.patternfly.Switch
import org.patternfly.aria
import org.patternfly.classes
import org.patternfly.clickButton
import org.patternfly.component
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.linkButton
import org.patternfly.modifier
import org.patternfly.notification
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
            text = "A button is a box area or text that communicates and triggers user actions when clicked or selected.",
            designGuidelines = "https://www.patternfly.org/v4/components/button/design-guidelines"
        )
        pageSection(baseClass = "sc-component__buttons") {
            snippet("Variations", ButtonCode.VARIATIONS) {
                pushButton(primary) { +"Primary" }
                pushButton(secondary) { +"Secondary" }
                pushButton(tertiary) { +"Tertiary" }
                pushButton(danger) { +"Danger" }
                br {}
                br {}
                pushButton(link) {
                    icon("plus-circle".fas())
                    +"Link"
                }
                pushButton(link) {
                    +"Link"
                    icon("plus-circle".fas())
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
                    icon("plus-circle".fas())
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
                    icon("plus-circle".fas())
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
                    element { aria["disabled"] = true }
                    +"Primary disabled"
                }
                pushButton(secondary, baseClass = "aria-disabled".modifier()) {
                    element { aria["disabled"] = true }
                    +"Secondary disabled"
                }
                pushButton(tertiary, baseClass = "aria-disabled".modifier()) {
                    element { aria["disabled"] = true }
                    +"Tertiary disabled"
                }
                pushButton(danger, baseClass = "aria-disabled".modifier()) {
                    element { aria["disabled"] = true }
                    +"Danger disabled"
                }
                pushButton(link, baseClass = "aria-disabled".modifier()) {
                    element { aria["disabled"] = true }
                    icon("plus-circle".fas())
                    +"Link disabled"
                }
                pushButton(link, inline, baseClass = "aria-disabled".modifier()) {
                    element { aria["disabled"] = true }
                    +"Inline link disabled"
                }
                pushButton(plain, baseClass = "aria-disabled".modifier()) {
                    element { aria["disabled"] = true }
                    icon("times".fas())
                }
                pushButton(control, baseClass = "aria-disabled".modifier()) {
                    element { aria["disabled"] = true }
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
                    element {
                        domNode.tabIndex = -1
                    }
                    element { aria["disabled"] = true }
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
                    icon("arrow-right".fas())
                    +"Call to action"
                }
                pushButton(link, inline, baseClass = "display-lg".modifier()) {
                    +"Call to action"
                    icon("arrow-right".fas())
                }
            }
            snippet("Progress", "n/a") {
                class OnOff : RootStore<Boolean>(false) {
                    val toggle = handle { !it }
                }

                class Download : RootStore<Unit>(Unit) {
                    val state = MutableStateFlow(0)
                    val start = handle {
                        state.value = 1
                        for (i in 2..11) {
                            delay(750)
                            state.value = i
                        }
                    }
                    val inProgress = state.map { it in 1..10 }
                    val percentage = state.map { "${it * 10}%" }
                }

                pushButton(primary) {
                    val onOff = OnOff()
                    +"Click to start loading"
                    loading(onOff.data, "Click to stop loading")
                    events {
                        clicks handledBy onOff.toggle
                    }
                }
                pushButton(secondary) {
                    val onOff = OnOff()
                    +"Click to start loading"
                    loading(onOff.data, "Click to stop loading")
                    events {
                        clicks handledBy onOff.toggle
                    }
                }
                br {}
                br {}
                pushButton(secondary) {
                    val download = Download()
                    +"Download"
                    loading(download.inProgress)
                    events {
                        clicks handledBy download.start
                    }
                }
                pushButton(secondary) {
                    val download = Download()
                    icon("download".fas())
                    loading(download.inProgress)
                    events {
                        clicks handledBy download.start
                    }
                }
                pushButton(secondary) {
                    val download = Download()
                    icon("download".fas())
                    +"Download"
                    loading(download.inProgress)
                    events {
                        clicks handledBy download.start
                    }
                }
                pushButton(secondary) {
                    val download = Download()
                    +"Download"
                    icon("download".fas())
                    loading(download.inProgress)
                    events {
                        clicks handledBy download.start
                    }
                }
                br {}
                br {}
                pushButton(secondary) {
                    val download = Download()
                    +"Download"
                    loading(download.inProgress, download.percentage)
                    events {
                        clicks handledBy download.start
                    }
                }
                pushButton(secondary) {
                    val download = Download()
                    icon("download".fas())
                    loading(download.inProgress, download.percentage)
                    events {
                        clicks handledBy download.start
                    }
                }
                pushButton(secondary) {
                    val download = Download()
                    icon("download".fas())
                    +"Download"
                    loading(download.inProgress, download.percentage)
                    events {
                        clicks handledBy download.start
                    }
                }
                pushButton(secondary) {
                    val download = Download()
                    +"Download"
                    icon("download".fas())
                    loading(download.inProgress, download.percentage)
                    events {
                        clicks handledBy download.start
                    }
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
                } handledBy notification(INFO, "Button clicked")

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
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
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
}
"""

    //language=kotlin
    const val ARIA_DISABLED: String = """fun main() {
    render {
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
}
"""

    //language=kotlin
    const val LINKS: String = """fun main() {
    render {
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
}
"""

    //language=kotlin
    const val BLOCK_LEVEL: String = """fun main() {
    render {
        pushButton(primary, baseClass = "block".modifier()) { +"Block level button" }
    } 
}
"""

    //language=kotlin
    const val TYPES: String = """fun main() {
    render {
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
}
"""

    //language=kotlin
    const val CALL_TO_ACTION: String = """fun main() {
    render {
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
"""
}
