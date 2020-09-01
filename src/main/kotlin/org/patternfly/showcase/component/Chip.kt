@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import org.patternfly.Notification
import org.patternfly.Severity
import org.patternfly.pfBadge
import org.patternfly.pfChip
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.w3c.dom.HTMLElement

object ChipComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Chip",
                key = "Chips",
                text = " are used to communicate a value, a tag, or a set of attribute-value pairs within workflows that involve filtering or tagging a set of objects. Related design guidelines: ",
                link = ("filters" to "Filters")
            )
        })
        yield(render {
            pfSection("sc-component__chips") {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", ChipCode.BASIC) {
                    pfChip("Chip") {
                        closes.map { Notification(Severity.INFO, "Chip closed") } handledBy Notification.store.add
                    }
                    br {}
                    pfChip("Really long chip that goes on and on")
                    br {}
                    pfChip("Chip") {
                        pfBadge { +"23" }
                    }
                    br {}
                    pfChip("Read-only chip", readOnly = true)
                    br {}
                    pfChip("Read-only chip", readOnly = true) {
                        pfBadge { +"42" }
                    }
                }
            }
        })
    }
}

internal object ChipCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pfChip("Chip") {
            closes.map { Notification(Severity.INFO, "Chip closed") } handledBy Notification.store.add
        }
        pfChip("Really long chip that goes on and on")
        pfChip("Chip") {
            pfBadge { +"23" }
        }
        pfChip("Read-only chip", readOnly = true)
        pfChip("Read-only chip", readOnly = true) {
            pfBadge { +"42" }
        }
    }
}
"""
}
