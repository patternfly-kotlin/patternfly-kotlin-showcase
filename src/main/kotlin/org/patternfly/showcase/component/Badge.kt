package org.patternfly.showcase.component

import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.states
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Switch
import org.patternfly.badge
import org.patternfly.classes
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.showcase.EVENT_DELAY
import org.patternfly.switch
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

object BadgeComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Badge",
            prefix = "A ",
            key = "badge",
            text = " is used to annotate other information like a label or an object name. Badges are typically used to reflect a count, e.g. number of object, number of events, number of unread, etc."
        )
        pageSection(baseClass = "sc-component__badges") {
            h2 { +"Examples" }
            snippet("Read", BadgeCode.READ) {
                badge { +"7" }
                badge { +"24" }
                badge { +"240" }
                badge { +"999+" }
            }
            snippet("Unread", BadgeCode.UNREAD) {
                badge {
                    +"7"
                    read(false)
                }
                badge {
                    +"24"
                    read(false)
                }
                badge {
                    +"240"
                    read(false)
                }
                badge {
                    +"999+"
                    read(false)
                }
            }
            snippet("Reactive", BadgeCode.REACTIVE) {
                lateinit var range: Input
                lateinit var state: Switch

                div(baseClass = classes {
                    +"flex".layout()
                    +"align-items-center".modifier()
                    +"mb-md".util()
                }) {
                    label {
                        `for`("range")
                        +"Value: "
                    }
                    range = input(id = "range", baseClass = "w-50".util()) {
                        type("range")
                        min("0")
                        max("500")
                        value("240")
                    }
                    state = switch("ml-md".util()) {
                        label("Read")
                        labelOff("Unread")
                        input.checked(true)
                    }
                }
                badge(min = 100, max = 400) {
                    read(state.input.changes.states())
                    value(range.inputs.events.map { it.target.unsafeCast<HTMLInputElement>().valueAsNumber.toInt() })
                }

                MainScope().launch {
                    delay(EVENT_DELAY)
                    range.domNode.dispatchEvent(Event(Events.input.name))
                }
            }
        }
    }
}

object BadgeCode {

    //language=kotlin
    const val READ: String = """fun main() {
    render {
        badge { +"7" }
        badge { +"24" }
        badge { +"240" }
        badge { +"999+" }
    }
}
"""

    //language=kotlin
    const val UNREAD: String = """fun main() {
    render {
        badge {
            +"7"
            read(false)
        }
        badge {
            +"24"
            read(false)
        }
        badge {
            +"240"
            read(false)
        }
        badge {
            +"999+"
            read(false)
        }
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        lateinit var range: Input
        lateinit var state: Switch
        
        div(baseClass = classes {
            +"flex".layout()
            +"align-items-center".modifier()
            +"mb-md".util()
        }) {
            label {
                `for`("range")
                +"Value: "
            }
            range = input(id = "range", baseClass = "w-50".util()) {
                type("range")
                min("0")
                max("500")
                value("240")
            }
            state = switch("ml-md".util()) {
                label("Read")
                labelOff("Unread")
                input.checked(true)
            }
        }
        badge(min = 100, max = 400) {
            read(state.input.changes.states())
            value(range.inputs.events.map { it.target.unsafeCast<HTMLInputElement>().valueAsNumber.toInt() })
        }
    
        MainScope().launch {
            delay(EVENT_DELAY)
            range.domNode.dispatchEvent(Event(Events.input.name))
        }
    }
}
"""
}
