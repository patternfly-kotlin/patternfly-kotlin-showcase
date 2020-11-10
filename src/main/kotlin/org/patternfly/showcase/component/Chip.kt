package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.elemento.elements
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.patternfly.Notification
import org.patternfly.Severity
import org.patternfly.component
import org.patternfly.pfBadge
import org.patternfly.pfChip
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class ChipComponent {
    val elements = elements {
        intro(
            title = "Chip",
            key = "Chips",
            text = " are used to communicate a value, a tag, or a set of attribute-value pairs within workflows that involve filtering or tagging a set of objects. Related design guidelines: ",
            link = ("filters" to "Filters")
        )
        pfSection(baseClass = "sc-component__chips") {
            pfContent {
                h2 { +"Examples" }
            }
            snippet("Basic", ChipCode.BASIC) {
                pfChip { +"Chip" }
                br {}
                pfChip { +"Really long chip that goes on and on" }
                br {}
                pfChip {
                    +"Chip"
                    pfBadge { +"23" }
                }
                br {}
                pfChip(readOnly = true) { +"Read-only chip" }
                br {}
                pfChip(readOnly = true) {
                    +"Read-only chip"
                    pfBadge { +"42" }
                }
                pfChip {
                    +"Cloe me"
                    closes.map { Notification(Severity.INFO, "Chip closed") } handledBy Notification.store.add
                }
            }
            snippet("Reactive", ChipCode.REACTIVE) {
                fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

                val text: Input = input(baseClass = "form-control".component()) {
                    type = const("text")
                    value = const("Chip")
                    placeholder = const("Chip text")
                }
                br {}
                br {}
                pfChip(readOnly = true) {
                    text.keyups.map { currentValue(it) }.bind()
                }
                br {}
                pfChip {
                    text.keyups.map { currentValue(it) }.bind()
                }
                br {}
                pfChip(readOnly = true) {
                    text.keyups.map { currentValue(it) }.bind()
                    pfBadge {
                        text.keyups.map { currentValue(it).length }.bind()
                    }
                }
                br {}
                pfChip {
                    text.keyups.map { currentValue(it) }.bind()
                    pfBadge {
                        text.keyups.map { currentValue(it).length }.bind()
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

internal object ChipCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pfChip { +"Chip" }
        br {}
        pfChip { +"Really long chip that goes on and on" }
        br {}
        pfChip {
            +"Chip"
            pfBadge { +"23" }
        }
        br {}
        pfChip(readOnly = true) { +"Read-only chip" }
        br {}
        pfChip(readOnly = true) {
            +"Read-only chip"
            pfBadge { +"42" }
        }
        pfChip {
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
        pfChip(readOnly = true) {
            text.keyups.map { currentValue(it) }.bind()
        }
        br {}
        pfChip {
            text.keyups.map { currentValue(it) }.bind()
        }
        br {}
        pfChip(readOnly = true) {
            text.keyups.map { currentValue(it) }.bind()
            pfBadge {
                text.keyups.map { currentValue(it).length }.bind()
            }
        }
        br {}
        pfChip {
            text.keyups.map { currentValue(it) }.bind()
            pfBadge {
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
