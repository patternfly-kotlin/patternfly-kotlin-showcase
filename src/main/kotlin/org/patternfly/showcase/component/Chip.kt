package org.patternfly.showcase.component

import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.patternfly.Notification
import org.patternfly.badge
import org.patternfly.chip
import org.patternfly.component
import org.patternfly.pageSection
import org.patternfly.showcase.EVENT_DELAY
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

object ChipComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Chip",
            key = "Chips",
            text = " are used to communicate a value, a tag, or a set of attribute-value pairs within workflows that involve filtering or tagging a set of objects. Related design guidelines: ",
            link = ("filters" to "Filters")
        )
        pageSection(baseClass = "sc-component__chips") {
            h2 { +"Examples" }
            snippet("Basic", ChipCode.BASIC) {
                chip { +"Chip" }
                br {}
                chip { +"Really long chip that goes on and on" }
                br {}
                chip {
                    +"Chip"
                    badge { +"23" }
                }
                br {}
                chip(readOnly = true) { +"Read-only chip" }
                br {}
                chip(readOnly = true) {
                    +"Read-only chip"
                    badge { +"42" }
                }
                chip {
                    +"Cloe me"
                    closes handledBy Notification.info("Chip closed")
                }
            }
        }
        snippet("Reactive", ChipCode.REACTIVE) {
            fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

            val text: Input = input(baseClass = "form-control".component()) {
                type("text")
                value("Chip")
                placeholder("Chip text")
            }
            br {}
            br {}
            chip(readOnly = true) {
                text.keyups.map { currentValue(it) }.asText()
            }
            br {}
            chip {
                text.keyups.map { currentValue(it) }.asText()
            }
            br {}
            chip(readOnly = true) {
                text.keyups.map { currentValue(it) }.asText()
                badge {
                    value(text.keyups.map { currentValue(it).length })
                }
            }
            br {}
            chip {
                text.keyups.map { currentValue(it) }.asText()
                badge {
                    value(text.keyups.map { currentValue(it).length })
                }
            }

            MainScope().launch {
                delay(EVENT_DELAY)
                text.domNode.dispatchEvent(Event(Events.keyup.name))
            }
        }
    }
}

object ChipCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        chip { +"Chip" }
        br {}
        chip { +"Really long chip that goes on and on" }
        br {}
        chip {
            +"Chip"
            badge { +"23" }
        }
        br {}
        chip(readOnly = true) { +"Read-only chip" }
        br {}
        chip(readOnly = true) {
            +"Read-only chip"
            badge { +"42" }
        }
        chip {
            +"Cloe me"
            closes.map { Notification(Severity.INFO, "Chip closed") } handledBy Notification.store.add
        }
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

        val text: Input = input(baseClass = "form-control".component()) {
            type = const("text")
            value = const("Chip")
            placeholder = const("Chip text")
        }
        br {}
        br {}
        chip(readOnly = true) {
            text.keyups.map { currentValue(it) }.bind()
        }
        br {}
        chip {
            text.keyups.map { currentValue(it) }.bind()
        }
        br {}
        chip(readOnly = true) {
            text.keyups.map { currentValue(it) }.bind()
            badge {
                text.keyups.map { currentValue(it).length }.bind()
            }
        }
        br {}
        chip {
            text.keyups.map { currentValue(it) }.bind()
            badge {
                text.keyups.map { currentValue(it).length }.bind()
            }
        }

        MainScope().launch {
            delay(EVENT_DELAY)
            text.domNode.dispatchEvent(Event(Events.keyup.name))
        }
    }
}
"""
}
