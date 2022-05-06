package org.patternfly.showcase.component

import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.states
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.PageInfo
import org.patternfly.Switch
import org.patternfly.classes
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.pagination
import org.patternfly.showcase.EVENT_DELAY
import org.patternfly.switch
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

object PaginationComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Pagination",
            text = "A pagination component gives users more navigational capability on pages with content views.",
            designGuidelines = "https://www.patternfly.org/v4/components/pagination/design-guidelines"
        )
        pageSection {
            snippet("Basic", PaginationCode.BASIC) {
                pagination(PageInfo(10, 0, 73))
            }
            snippet("Disabled", PaginationCode.DISABLED) {
                pagination(PageInfo(10, 0, 73)) {
                    disabled(true)
                }
            }
            snippet("No items", PaginationCode.NO_ITEMS) {
                pagination(PageInfo())
            }
            snippet("One page", PaginationCode.ONE_PAGE) {
                pagination(PageInfo(10, 0, 8))
            }
            snippet("Compact", PaginationCode.COMPACT) {
                pagination(PageInfo(10, 0, 73), compact = true)
            }
            snippet("Reactive", PaginationCode.REACTIVE) {
                lateinit var range: Input
                lateinit var enabled: Switch
                val total = 123
                val pageInfo = PageInfo(total = total)

                div(baseClass = classes {
                    +"flex".layout()
                    +"align-items-center".modifier()
                    +"mb-md".util()
                }) {
                    label {
                        `for`("total")
                        +"Total: "
                    }
                    range = input(id = "total", baseClass = "w-50".util()) {
                        type("range")
                        min("0")
                        max("200")
                        value(total.toString())
                    }
                    enabled = switch("ml-md".util()) {
                        label("Enabled")
                        labelOff("Disabled")
                        input.checked(true)
                    }
                }
                pagination(pageInfo) {
                    disabled(enabled.input.changes.states().map { !it })
                    range.inputs.events
                        .map { it.target.unsafeCast<HTMLInputElement>().valueAsNumber.toInt() }
                        .handledBy(pageInfoHandler.total)
                }
                pagination(pageInfo = pageInfo, compact = true, baseClass = "mt-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    range.inputs.events
                        .map { it.target.unsafeCast<HTMLInputElement>().valueAsNumber.toInt() }
                        .handledBy(pageInfoHandler.total)
                }

                MainScope().launch {
                    delay(EVENT_DELAY)
                    range.domNode.dispatchEvent(Event(Events.input.name))
                }
            }
        }
    }
}

object PaginationCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pagination(PageInfo(10, 0, 73))
    }
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        pagination(PageInfo(10, 0, 73)) {
            disabled(true)
        }
    }
}
"""

    //language=kotlin
    const val NO_ITEMS: String = """fun main() {
    render {
        pagination()
    }
}
"""

    //language=kotlin
    const val ONE_PAGE: String = """fun main() {
    render {
        pagination(PageInfo(10, 0, 8))
    }
}
"""

    //language=kotlin
    const val COMPACT: String = """fun main() {
    render {
        pagination(PageInfo(10, 0, 73), compact = true)
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        lateinit var range: Input
        lateinit var enabled: Switch

        div(baseClass = classes {
            +"flex".layout()
            +"align-items-center".modifier()
            +"mb-md".util()
        }) {
            label {
                `for`("total")
                +"Total: "
            }
            range = input(id = "total", baseClass = "w-50".util()) {
                type("range")
                min("0")
                max("200")
                value("123")
            }
            enabled = switch("ml-md".util()) {
                label("Enabled")
                labelOff("Disabled")
                input.checked(true)
            }
        }
        pagination {
            disabled(enabled.input.changes.states().map { !it })
            range.inputs.events
                .map { it.target.unsafeCast<HTMLInputElement>().valueAsNumber.toInt() }
                .handledBy(pageInfoHandler.total)
        }
        pagination(compact = true, baseClass = "mt-sm".util()) {
            disabled(enabled.input.changes.states().map { !it })
            range.inputs.events
                .map { it.target.unsafeCast<HTMLInputElement>().valueAsNumber.toInt() }
                .handledBy(pageInfoHandler.total)
        }

        MainScope().launch {
            delay(EVENT_DELAY)
            range.domNode.dispatchEvent(Event(Events.input.name))
        }
    }
}
"""
}
