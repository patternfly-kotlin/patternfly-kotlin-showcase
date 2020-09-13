@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.render
import dev.fritz2.dom.states
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Notification
import org.patternfly.Position.END
import org.patternfly.Position.START
import org.patternfly.Severity
import org.patternfly.Switch
import org.patternfly.aria
import org.patternfly.classes
import org.patternfly.component
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
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

object ButtonComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Button",
                key = "Buttons",
                text = " communicate and trigger actions a user can take in an application or website. Related design guidelines: ",
                link = ("buttons-and-links" to "Buttons and links")
            )
        })
        yield(render {
            pfSection("sc-component__buttons") {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Variations", ButtonCode.VARIATIONS) {
                    pfButton("primary".modifier()) { +"Primary" }
                    pfButton("secondary".modifier()) { +"Secondary" }
                    pfButton("tertiary".modifier()) { +"Tertiary" }
                    pfButton("danger".modifier()) { +"Danger" }
                    br {}
                    br {}
                    pfButton("link".modifier()) {
                        pfIcon(START, "plus-circle".fas())
                        +"Link"
                    }
                    pfButton("link".modifier()) {
                        +"Link"
                        pfIcon(END, "plus-circle".fas())
                    }
                    pfButton(classes("link".modifier(), "inline".modifier())) { +"Inline link" }
                    br {}
                    br {}
                    pfButton("plain".modifier()) { pfIcon("times".fas()) }
                    br {}
                    br {}
                    pfButton("control".modifier()) { +"Control" }
                    pfButton("control".modifier()) { pfIcon("copy".fas()) }
                    br {}
                    br {}
                    pfButton(classes("primary".modifier(), "small".modifier())) { +"Primary" }
                    pfButton(classes("secondary".modifier(), "small".modifier())) { +"Secondary" }
                    pfButton(classes("tertiary".modifier(), "small".modifier())) { +"Tertiary" }
                    pfButton(classes("danger".modifier(), "small".modifier())) { +"Danger" }
                    pfButton(classes("link".modifier(), "small".modifier())) {
                        pfIcon(START, "plus-circle".fas())
                        +"Link"
                    }
                    pfButton(classes("link".modifier(), "inline".modifier(), "small".modifier())) { +"Inline link" }
                    pfButton(classes("control".modifier(), "small".modifier())) { +"Control" }
                }
                snippet("Disabled", ButtonCode.DISABLED) {
                    pfButton("primary".modifier()) {
                        disabled = const(false)
                        +"Primary disabled"
                    }
                    pfButton("secondary".modifier()) {
                        disabled = const(false)
                        +"Secondary disabled"
                    }
                    pfButton("tertiary".modifier()) {
                        disabled = const(false)
                        +"Tertiary disabled"
                    }
                    pfButton("danger".modifier()) {
                        disabled = const(false)
                        +"Danger disabled"
                    }
                    pfButton("link".modifier()) {
                        disabled = const(false)
                        pfIcon(START, "plus-circle".fas())
                        +"Link disabled"
                    }
                    pfButton(classes("link".modifier(), "inline".modifier())) {
                        disabled = const(false)
                        +"Inline link disabled"
                    }
                    pfButton("plain".modifier()) {
                        disabled = const(false)
                        pfIcon("times".fas())
                    }
                    pfButton("control".modifier()) {
                        disabled = const(false)
                        +"Control disabled"
                    }
                }
                snippet("Aria-disabled", ButtonCode.ARIA_DISABLED) {
                    pfButton(classes("primary".modifier(), "aria-disabled".modifier())) {
                        aria["disabled"] = true
                        +"Primary disabled"
                    }
                    pfButton(classes("secondary".modifier(), "aria-disabled".modifier())) {
                        aria["disabled"] = true
                        +"Secondary disabled"
                    }
                    pfButton(classes("tertiary".modifier(), "aria-disabled".modifier())) {
                        aria["disabled"] = true
                        +"Tertiary disabled"
                    }
                    pfButton(classes("danger".modifier(), "aria-disabled".modifier())) {
                        aria["disabled"] = true
                        +"Danger disabled"
                    }
                    pfButton(classes("link".modifier(), "aria-disabled".modifier())) {
                        aria["disabled"] = true
                        pfIcon(START, "plus-circle".fas())
                        +"Link disabled"
                    }
                    pfButton(classes("link".modifier(), "aria-disabled".modifier(), "inline".modifier())) {
                        aria["disabled"] = true
                        +"Inline link disabled"
                    }
                    pfButton(classes("plain".modifier(), "aria-disabled".modifier())) {
                        aria["disabled"] = true
                        pfIcon("times".fas())
                    }
                    pfButton(classes("control".modifier(), "aria-disabled".modifier())) {
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
                    pfButton(classes("primary".modifier(), "block".modifier())) { +"Block level button" }
                }
                snippet("Types", ButtonCode.TYPES) {
                    pfButton("primary".modifier()) {
                        type = const("submit")
                        +"Submit"
                    }
                    pfButton("primary".modifier()) {
                        type = const("reset")
                        +"Reset"
                    }
                    pfButton("primary".modifier()) {
                        +"Default"
                    }
                }
                snippet("Call to action", ButtonCode.CALL_TO_ACTION) {
                    pfButton(classes("primary".modifier(), "display-lg".modifier())) { +"Call to action" }
                    pfButton(classes("secondary".modifier(), "display-lg".modifier())) { +"Call to action" }
                    pfButton(classes("tertiary".modifier(), "display-lg".modifier())) { +"Call to action" }
                    pfButton(classes("link".modifier(), "display-lg".modifier())) {
                        +"Call to action"
                        pfIcon(END, "arrow-right".fas())
                    }
                    pfButton(classes("link".modifier(), "inline".modifier(), "display-lg".modifier())) {
                        +"Call to action"
                        pfIcon(END, "arrow-right".fas())
                    }
                }
                snippet("Reactive", ButtonCode.REACTIVE) {
                    fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

                    lateinit var text: Input
                    lateinit var enabled: Switch
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
                    pfButton("primary".modifier()) {
                        disabled = enabled.input.changes.states().map { !it }
                        text.keyups.map { currentValue(it) }.bind()
                        clicks.map {
                            Notification(Severity.INFO, "Button clicked")
                        } handledBy Notification.store.add
                    }
                    MainScope().launch {
                        delay(333)
                        text.domNode.dispatchEvent(Event(Events.keyup.name))
                    }
                }
            }
        })
    }
}

internal object ButtonCode {

    //language=kotlin
    const val VARIATIONS: String = """fun main() {
    render {
        pfButton(primary) { +"Primary" }
        pfButton(secondary) { +"Secondary" }
        pfButton(tertiary) { +"Tertiary" }
        pfButton(danger) { +"Danger" }

        pfButton(link) {
            pfIcon(START, "plus-circle".fas())
            +"Link"
        }
        pfButton(link) {
            +"Link"
            pfIcon(END, "plus-circle".fas())
        }
        pfButton(classes(link, inline)) { +"Inline link" }

        pfButton(plain) { pfIcon("times".fas()) }

        pfButton(control) { +"Control" }
        pfButton(control) { pfIcon("copy".fas()) }

        pfButton(classes(primary, small)) { +"Primary" }
        pfButton(classes(secondary, small)) { +"Secondary" }
        pfButton(classes(tertiary, small)) { +"Tertiary" }
        pfButton(classes(danger, small)) { +"Danger" }
        pfButton(classes(link, small)) {
            pfIcon(START, "plus-circle".fas())
            +"Link"
        }
        pfButton(classes(link, inline, small)) { +"Inline link" }
        pfButton(classes(control, small)) { +"Control" }
    }
}"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        pfButton(primary) {
            disabled = const(false)
            +"Primary disabled"
        }
        pfButton(secondary) {
            disabled = const(false)
            +"Secondary disabled"
        }
        pfButton(tertiary) {
            disabled = const(false)
            +"Tertiary disabled"
        }
        pfButton(danger) {
            disabled = const(false)
            +"Danger disabled"
        }
        pfButton(link) {
            disabled = const(false)
            pfIcon(START, "plus-circle".fas())
            +"Link disabled"
        }
        pfButton(classes(link, inline)) {
            disabled = const(false)
            +"Inline link disabled"
        }
        pfButton(plain) {
            disabled = const(false)
            pfIcon("times".fas())
        }
        pfButton(control) {
            disabled = const(false)
            +"Control disabled"
        }
    }
}"""

    //language=kotlin
    const val ARIA_DISABLED: String = """fun main() {
    render {
        pfButton(classes(primary, ariaDisabled)) {
            aria["disabled"] = true
            +"Primary disabled"
        }
        pfButton(classes(secondary, ariaDisabled)) {
            aria["disabled"] = true
            +"Secondary disabled"
        }
        pfButton(classes(tertiary, ariaDisabled)) {
            aria["disabled"] = true
            +"Tertiary disabled"
        }
        pfButton(classes(danger, ariaDisabled)) {
            aria["disabled"] = true
            +"Danger disabled"
        }
        pfButton(classes(link, ariaDisabled)) {
            aria["disabled"] = true
            pfIcon(START, "plus-circle".fas())
            +"Link disabled"
        }
        pfButton(classes(link, ariaDisabled, inline)) {
            aria["disabled"] = true
            +"Inline link disabled"
        }
        pfButton(classes(plain, ariaDisabled)) {
            aria["disabled"] = true
            pfIcon("times".fas())
        }
        pfButton(classes(control, ariaDisabled)) {
            aria["disabled"] = true
            +"Control disabled"
        }
    }
}"""

    //language=kotlin
    const val LINKS: String = """fun main() {
    render {
        pfLinkButton(primary) {
            href = const("https://www.w3.org/TR/WCAG20-TECHS/ARIA8.html#ARIA8-examples")
            target = const("_blank")
            +"Primary link to W3.org"
        }
        pfLinkButton(secondary) {
            href = const("https://www.patternfly.org")
            target = const("_blank")
            +"Secondary link to PatternFly"
        }
        pfLinkButton(classes(tertiary, disabled)) {
            aria["disabled"] = true
            domNode.tabIndex = -1
            href = const("https://www.patternfly.org")
            target = const("_blank")
            +"Tertiary link to W3.org"
        }
    } 
}"""

    //language=kotlin
    const val BLOCK_LEVEL: String = """fun main() {
    render {
        pfButton(classes(primary, block)) { +"Block level button" }
    } 
}"""

    //language=kotlin
    const val TYPES: String = """fun main() {
    render {
        pfButton(primary) {
            type = const("submit")
            +"Submit"
        }
        pfButton(primary) {
            type = const("reset")
            +"Reset"
        }
        pfButton(primary) {
            +"Default"
        }
    } 
}"""

    //language=kotlin
    const val CALL_TO_ACTION: String = """fun main() {
    render {
        pfButton(classes(primary, displayLg)) { +"Call to action" }
        pfButton(classes(secondary, displayLg)) { +"Call to action" }
        pfButton(classes(tertiary, displayLg)) { +"Call to action" }
        pfButton(classes(link, displayLg)) {
            +"Call to action"
            pfIcon(END, "arrow-right".fas())
        }
        pfButton(classes(link, inline, displayLg)) {
            +"Call to action"
            pfIcon(END, "arrow-right".fas())
        }
    } 
}"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
    } 
}"""
}
