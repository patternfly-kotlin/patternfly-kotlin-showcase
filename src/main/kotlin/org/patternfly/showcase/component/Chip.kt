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
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
object ChipComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Chip",
            text = "A chip is used to communicate a value or a set of attribute-value pairs within workflows that involve filtering a set of objects.",
            designGuidelines = "https://www.patternfly.org/v4/components/chip/design-guidelines"
        )
        pageSection(baseClass = "sc-component__chips") {
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
                br {}
                chip {
                    +"Cloe me"
                    closes handledBy Notification.info("Chip closed")
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
        br {}
        chip {
            +"Cloe me"
            closes handledBy Notification.info("Chip closed")
        }
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render { 
        val text: Input
        fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value
    
        text = input(baseClass = "form-control".component()) {
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
"""
}
