@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.Handler
import dev.fritz2.binding.RootStore
import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.patternfly.ButtonVariant.link
import org.patternfly.Severity.INFO
import org.patternfly.chipGroup
import org.patternfly.clickButton
import org.patternfly.fas
import org.patternfly.modifier
import org.patternfly.notification
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
                chipGroup {
                    chip { +"Chip one" }
                    chip { +"Really long chip that goes on and on" }
                    chip { +"Chip three" }
                    chip { +"Chip four" }
                    chip { +"Chip five" }
                }
            }
            snippet("With category", ChipGroupCode.CATEGORY) {
                chipGroup {
                    +"Category"
                    chip { +"Chip one" }
                    chip { +"Really long chip that goes on and on" }
                    chip { +"Chip three" }
                    chip { +"Chip four" }
                    chip { +"Chip five" }
                }
            }
            snippet("Closable", ChipGroupCode.CLOSABLE) {
                chipGroup {
                    +"Category"
                    chip { +"Chip one" }
                    chip { +"Really long chip that goes on and on" }
                    chip { +"Chip three" }
                    chip { +"Chip four" }
                    chip { +"Chip five" }
                    closable(true)
                    closes handledBy notification(INFO, "Chip group closed")
                }
            }
            snippet("Custom Type", ChipGroupCode.CUSTOM_TYPE) {
                data class Word(val text: String, val letters: Int = text.length)

                val store = storeOf(
                    listOf(
                        Word("Chip one"),
                        Word("Really long chip that goes on and on"),
                        Word("Chip three"),
                        Word("Chip four"),
                        Word("Chip five")
                    )
                )

                chipGroup {
                    +"Letters"
                    chip("First")
                    chips(store) { word ->
                        chip(word.text) {
                            badge(word.letters)
                        }
                    }
                    chip("Last")
                }
            }
            snippet("Reactive", ChipGroupCode.REACTIVE) {
                class Strings : RootStore<List<String>>(emptyList()) {
                    val add: Handler<String> = handle { strings, string -> strings + string }
                    val remove: Handler<String> = handle { strings, string -> strings - string }
                }

                val stores: Array<Pair<Int, Strings>> = arrayOf(
                    3 to Strings(),
                    4 to Strings(),
                    20 to Strings()
                )

                fun randomString(length: Int) =
                    (1..(3 + length)).map { ('a'..'z').random() }.joinToString("")

                stores.forEach { (limit, store) ->
                    clickButton(link, baseClass = "small".modifier()) {
                        icon("plus-circle".fas())
                        +"Add chip"
                    }.map { randomString(Random.nextInt(10)) } handledBy store.add
                    chipGroup(limit) {
                        +"Max $limit"
                        chips(store) { title ->
                            chip(title) {
                                closes.map { title } handledBy store.remove
                            }
                        }
                    }
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
