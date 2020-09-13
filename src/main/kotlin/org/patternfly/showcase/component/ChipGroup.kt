@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.action
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import org.patternfly.ChipGroupStore
import org.patternfly.Notification
import org.patternfly.Position.START
import org.patternfly.Severity
import org.patternfly.classes
import org.patternfly.fas
import org.patternfly.modifier
import org.patternfly.pfBadge
import org.patternfly.pfButton
import org.patternfly.pfChipGroup
import org.patternfly.pfChips
import org.patternfly.pfContent
import org.patternfly.pfIcon
import org.patternfly.pfSection
import org.w3c.dom.HTMLElement
import kotlin.random.Random

object ChipGroupComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Chip group",
                prefix = "A ",
                key = "chip group",
                text = " is used to represent an attribute that has been assigned one or more values. An OR relationship is implied between values in the group. Chip groups are useful to express complex filters to a data set, for example. Related design guidelines: ",
                link = ("filters" to "Filters")
            )
        })
        yield(render {
            pfSection("sc-component__chip-groups") {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Inline", ChipGroupCode.INLINE) {
                    pfChipGroup<String> {
                        pfChips {
                            +"Chip one"
                            +listOf(
                                "Really long chip that goes on and on",
                                "Chip three",
                                "Chip four",
                                "Chip five"
                            )
                        }
                    }
                }
                snippet("With category", ChipGroupCode.CATEGORY) {
                    pfChipGroup<String>(text = "Category") {
                        pfChips {
                            +"Chip one"
                            +listOf(
                                "Really long chip that goes on and on",
                                "Chip three",
                                "Chip four",
                                "Chip five"
                            )
                        }
                    }
                }
                snippet("Closable", ChipGroupCode.CLOSABLE) {
                    pfChipGroup<String>(text = "Category", closable = true) {
                        closes.map {
                            Notification(Severity.INFO, "Chip group closed")
                        } handledBy Notification.store.add
                        pfChips {
                            +"Chip one"
                            +listOf(
                                "Really long chip that goes on and on",
                                "Chip three",
                                "Chip four",
                                "Chip five"
                            )
                        }
                    }
                }
                snippet("Custom Type", ChipGroupCode.CUSTOM_TYPE) {
                    data class Word(val text: String, val letters: Int = text.length)

                    val store = ChipGroupStore<Word>()
                    pfChipGroup(store, "Letters") {
                        display = {
                            {
                                +it.text
                                pfBadge {
                                    +it.letters.toString()
                                }
                            }
                        }
                    }
                    action(
                        listOf(
                            Word("Chip one"),
                            Word("Really long chip that goes on and on"),
                            Word("Chip three"),
                            Word("Chip four"),
                            Word("Chip five")
                        )
                    ) handledBy store.update
                }
                snippet("Reactive", ChipGroupCode.REACTIVE) {
                    fun randomString(length: Int) =
                        (1..(3 + length)).map { ('a'..'z').random() }.joinToString("")

                    val stores: Array<Pair<Int, ChipGroupStore<String>>> = arrayOf(
                        3 to ChipGroupStore(),
                        4 to ChipGroupStore(),
                        20 to ChipGroupStore()
                    )

                    stores.forEach { (limit, store) ->
                        pfButton(classes("link".modifier(), "small".modifier())) {
                            pfIcon(START, "plus-circle".fas())
                            +"Add chip"
                            clicks.map { randomString(Random.nextInt(10)) } handledBy store.add
                        }
                        pfChipGroup(store, "Max $limit", limit)
                        br {}
                    }
                }
            }
        })
    }
}

internal object ChipGroupCode {

    //language=kotlin
    const val INLINE: String = """fun main() {
    render {
        pfChipGroup<String> {
            pfChips {
                +"Chip one"
                +listOf(
                    "Really long chip that goes on and on",
                    "Chip three",
                    "Chip four",
                    "Chip five"
                )
            }
        }
    }
}
"""

    //language=kotlin
    const val CATEGORY: String = """fun main() {
    render {
        pfChipGroup<String>(text = "Category") {
            pfChips {
                +"Chip one"
                +listOf(
                    "Really long chip that goes on and on",
                    "Chip three",
                    "Chip four",
                    "Chip five"
                )
            }
        }
    }
}
"""

    //language=kotlin
    const val CLOSABLE: String = """fun main() {
    render {
        pfChipGroup<String>(text = "Category", closable = true) {
            pfChips {
                +"Chip one"
                +listOf(
                    "Really long chip that goes on and on",
                    "Chip three",
                    "Chip four",
                    "Chip five"
                )
            }
        }
    }
}
"""

    //language=kotlin
    const val CUSTOM_TYPE: String = """fun main() {
    render {
        data class Word(val text: String) {
            val letters = text.length
        }
        val store = ChipGroupStore<Word>()
        pfChipGroup(store, "Letters") {
            display = { // it: Word
                { // this: Chip
                    +it.text
                    pfBadge {
                        +it.letters.toString()
                    }
                }
            }
        }
        action(
            listOf(
                Word("Chip one"),
                Word("Really long chip that goes on and on"),
                Word("Chip three"),
                Word("Chip four"),
                Word("Chip five")
            )
        ) handledBy store.update
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        fun randomString(length: Int) =
            (1..(3 + length)).map { ('a'..'z').random() }.joinToString("")

        val stores: Array<Pair<Int, ChipGroupStore<String>>> = arrayOf(
            3 to ChipGroupStore(),
            4 to ChipGroupStore(),
            20 to ChipGroupStore()
        )

        stores.forEach { (limit, store) ->
            pfButton(classes(link, small)) {
                pfIcon(START, "plus-circle".fas())
                +"Add chip"
                clicks.map { randomString(Random.nextInt(10)) } handledBy store.add
            }
            pfChipGroup(store, "Max ${'$'}limit", limit)
            br {}
        }
    }
}
"""
}
