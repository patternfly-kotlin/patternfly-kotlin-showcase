package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.values
import dev.fritz2.dom.valuesAsNumber
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import org.patternfly.badge
import org.patternfly.classes
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.slider
import org.patternfly.switch
import org.patternfly.util

object BadgeComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Badge",
            text = "A badge is used to annotate other information like a label or an object name.",
            designGuidelines = "https://www.patternfly.org/v4/components/badge/design-guidelines"
        )
        pageSection(baseClass = "sc-component__badges") {
            snippet("Read", BadgeCode.READ) {
                badge {
                    count(7)
                    read(true)
                }
                badge {
                    count(24)
                    read(true)
                }
                badge {
                    count(240)
                    read(true)
                }
                badge {
                    count(999)
                    read(true)
                }
            }
            snippet("Unread", BadgeCode.UNREAD) {
                badge { count(7) }
                badge { count(24) }
                badge { count(240) }
                badge { count(999) }
            }
            snippet("Reactive", BadgeCode.REACTIVE) {
                val range = storeOf(240)
                val state = storeOf(true)

                div(baseClass = classes {
                    +"flex".layout()
                    +"align-items-center".modifier()
                    +"mb-md".util()
                }) {
                    slider(range, 0..500 step 25, baseClass = "w-50".util()) {
                        showSteps() { it % 100 == 0 }
                        showTicks()
                    }
                    switch(state, baseClass = "ml-md".util()) {
                        label { +"Read" }
                        labelOff { +"Unread" }
                    }
                }
                badge {
                    bounds(100..400)
                    read(state.data)
                    count(range.data)
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
