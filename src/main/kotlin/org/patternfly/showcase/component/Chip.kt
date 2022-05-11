package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.patternfly.Severity.INFO
import org.patternfly.chip
import org.patternfly.component
import org.patternfly.notification
import org.patternfly.pageSection
import org.w3c.dom.HTMLInputElement

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
                    badge { count(23) }
                }
                br {}
                chip { +"Read-only chip"; readOnly(true) }
                br {}
                chip {
                    +"Read-only chip"
                    badge { count(42) }

                }
                br {}
                chip {
                    +"Cloe me"
                    closable(true)
                    events {
                        closes handledBy notification(INFO, "Chip closed")
                    }
                }
            }
            snippet("Reactive", ChipCode.REACTIVE) {
                val title = storeOf("Chip text")

                input(baseClass = "form-control".component()) {
                    type("text")
                    value(title.data)
                    keyups.map { it.target.unsafeCast<HTMLInputElement>().value } handledBy title.update
                }
                br {}
                br {}
                chip {
                    readOnly(true)
                    title(title.data)
                }
                br {}
                chip {
                    closable(true)
                    title(title.data)
                }
                br {}
                chip {
                    readOnly(true)
                    title(title.data)
                    badge {
                        count(title.data.map { it.length })
                    }
                }
                br {}
                chip {
                    closable(true)
                    title(title.data)
                    badge {
                        count(title.data.map { it.length })
                    }
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
            badge { count(23) }
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
