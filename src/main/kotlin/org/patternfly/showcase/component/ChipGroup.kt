package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.ButtonVariant.link
import org.patternfly.ChipGroupStore
import org.patternfly.chip
import org.patternfly.chipGroup
import org.patternfly.chips
import org.patternfly.clickButton
import org.patternfly.fas
import org.patternfly.modifier
import org.patternfly.pageSection
import kotlin.random.Random

object ChipGroupComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Chip group",
            text = "A chip group represents an attribute that has been assigned one or more values.",
            designGuidelines = "https://www.patternfly.org/v4/components/chip-group/design-guidelines"
        )
        pageSection(baseClass = "sc-component__chip-groups") {
            snippet("Inline", ChipGroupCode.INLINE) {
                chipGroup<String> {
                    chips {
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
                chipGroup<String> {
                    +"Category"
                    chips {
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
                chipGroup<String>(closable = true) {
                    +"Category"
//                    closes handledBy notification(INFO, "Chip group closed")
                    chips {
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
                store.addAll(
                    listOf(
                        Word("Chip one"),
                        Word("Really long chip that goes on and on"),
                        Word("Chip three"),
                        Word("Chip four"),
                        Word("Chip five")
                    )
                )

                chipGroup(store) {
                    +"Letters"
                    display { word ->
                        chip {
                            +word.text
                            badge {
                                count(word.letters)
                            }
                        }
                    }
                }
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
                    clickButton(link, baseClass =  "small".modifier()) {
                        icon("plus-circle".fas())
                        +"Add chip"
                    }.map { randomString(Random.nextInt(10)) } handledBy store.add
                    chipGroup(store, limit) { +"Max $limit" }
                    br {}
                }
            }
        }
    }
}

object ChipGroupCode {

    //language=kotlin
    const val INLINE: String = """fun main() {
    render {
        chipGroup<String> {
            chips {
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
        chipGroup<String> {
            +"Category"
            chips {
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
        chipGroup<String>(closable = true) {
            +"Category"
            closes handledBy Notification.info("Chip group closed")
            chips {
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
        data class Word(val text: String, val letters: Int = text.length)

        val store = ChipGroupStore<Word>()
        store.addAll(
            listOf(
                Word("Chip one"),
                Word("Really long chip that goes on and on"),
                Word("Chip three"),
                Word("Chip four"),
                Word("Chip five")
            )
        )

        chipGroup(store) {
            +"Letters"
            display { word ->
                chip {
                    +word.text
                    badge {
                        value(word.letters)
                    }
                }
            }
        }
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
            clickButton(link, baseClass =  "small".modifier()) {
                buttonIcon(ICON_FIRST, "plus-circle".fas())
                +"Add chip"
            }.map { randomString(Random.nextInt(10)) } handledBy store.add
            chipGroup(store, limit) { +"Max ${'$'}limit" }
            br {}
        }
    }
}
"""
}
