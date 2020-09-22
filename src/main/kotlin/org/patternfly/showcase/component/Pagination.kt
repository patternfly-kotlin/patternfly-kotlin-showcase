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
import org.patternfly.PageInfo
import org.patternfly.Switch
import org.patternfly.classes
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfContent
import org.patternfly.pfPagination
import org.patternfly.pfSection
import org.patternfly.pfSwitch
import org.patternfly.util
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

object PaginationComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Pagination",
                prefix = "The ",
                key = "pagination",
                text = " component is used to navigate pages in lists, tables, and other content views. Related design guidelines: ",
                link = ("lists-and-tables" to "Lists and tables")
            )
        })
        yield(render {
            pfSection {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", PaginationCode.BASIC) {
                    pfPagination(PageInfo(10, 0, 73))
                }
                snippet("Disabled", PaginationCode.DISABLED) {
                    pfPagination(PageInfo(10, 0, 73)) {
                        disabled = const(true)
                    }
                }
                snippet("No items", PaginationCode.NO_ITEMS) {
                    pfPagination()
                }
                snippet("One page", PaginationCode.ONE_PAGE) {
                    pfPagination(PageInfo(10, 0, 8))
                }
                snippet("Compact", PaginationCode.COMPACT) {
                    pfPagination(PageInfo(10, 0, 73), compact = true)
                }
                snippet("Reactive", PaginationCode.REACTIVE) {
                    lateinit var range: Input
                    lateinit var enabled: Switch
                    div(baseClass = classes {
                        +"flex".layout()
                        +"align-items-center".modifier()
                        +"mb-md".util()
                    }) {
                        label(`for` = "total") { +"Total: " }
                        range = input(id = "total", baseClass = "w-50".util()) {
                            type = const("range")
                            min = const("0")
                            max = const("200")
                            value = const("123")
                        }
                        enabled = pfSwitch("ml-md".util()) {
                            label = const("Enabled")
                            labelOff = const("Disabled")
                            input.checked = const(true)
                        }
                    }
                    pfPagination {
                        disabled = enabled.input.changes.states().map { !it }
                        range.inputs.events
                            .map { it.target.unsafeCast<HTMLInputElement>().valueAsNumber.toInt() }
                            .handledBy(pageInfoHandler.total)
                    }
                    pfPagination(compact = true, classes = "mt-sm".util()) {
                        disabled = enabled.input.changes.states().map { !it }
                        range.inputs.events
                            .map { it.target.unsafeCast<HTMLInputElement>().valueAsNumber.toInt() }
                            .handledBy(pageInfoHandler.total)
                    }
                    MainScope().launch {
                        delay(333)
                        range.domNode.dispatchEvent(Event(Events.input.name))
                    }
                }
            }
        })
    }
}

internal object PaginationCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
    }
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
    }
}
"""

    //language=kotlin
    const val NO_ITEMS: String = """fun main() {
    render {
    }
}
"""

    //language=kotlin
    const val ONE_PAGE: String = """fun main() {
    render {
    }
}
"""

    //language=kotlin
    const val COMPACT: String = """fun main() {
    render {
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
    }
}
"""
}
