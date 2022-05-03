@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.Handler
import dev.fritz2.binding.RootStore
import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import org.patternfly.ButtonVariant.link
import org.patternfly.Severity.INFO
import org.patternfly.chipGroup
import org.patternfly.clickButton
import org.patternfly.fas
import org.patternfly.modifier
import org.patternfly.notification
import org.patternfly.pageSection
import kotlin.random.Random

object LabelGroupComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Label group",
            text = "Use a label group when you have multiple labels to display at once. Label groups can be oriented either horizontally or vertically and can optionally be named and dismissable.",
            designGuidelines = "https://www.patternfly.org/v4/components/label-group/design-guidelines"
        )
        pageSection(baseClass = "sc-component__chip-groups") {
            snippet("Inline", LabelGroupCode.INLINE) {
                chipGroup {
                    chip { +"Chip one" }
                    chip { +"Really long chip that goes on and on" }
                    chip { +"Chip three" }
                    chip { +"Chip four" }
                    chip { +"Chip five" }
                }
            }
            snippet("With category", LabelGroupCode.CATEGORY) {
                chipGroup {
                    +"Category"
                    chip { +"Chip one" }
                    chip { +"Really long chip that goes on and on" }
                    chip { +"Chip three" }
                    chip { +"Chip four" }
                    chip { +"Chip five" }
                }
            }
            snippet("Closable", LabelGroupCode.CLOSABLE) {
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
            snippet("Custom Type", LabelGroupCode.CUSTOM_TYPE) {
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
            snippet("Reactive", LabelGroupCode.REACTIVE) {
                class Strings : RootStore<List<String>>(emptyList()) {
                    val add: Handler<String> = handle { strings, string -> strings + string }
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
                        chips(store) { chip(it) }
                    }
                    br {}
                }
            }
        }
    }
}

object LabelGroupCode {

    //language=kotlin
    const val INLINE: String = """"""

    //language=kotlin
    const val CATEGORY: String = """"""

    //language=kotlin
    const val CLOSABLE: String = """"""

    //language=kotlin
    const val CUSTOM_TYPE: String = """"""

    //language=kotlin
    const val REACTIVE: String = """"""
}
