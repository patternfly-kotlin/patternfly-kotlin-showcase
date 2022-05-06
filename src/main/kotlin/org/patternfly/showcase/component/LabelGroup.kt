@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.Handler
import dev.fritz2.binding.RootStore
import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.patternfly.ButtonVariant.link
import org.patternfly.Color
import org.patternfly.Severity
import org.patternfly.clickButton
import org.patternfly.fas
import org.patternfly.labelGroup
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
        pageSection {
            snippet("Basic", LabelGroupCode.BASIC) {
                labelGroup {
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Overflow", LabelGroupCode.OVERFLOW) {
                labelGroup {
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                    label(nextColor()) { +"Label 3" }
                    label(nextColor()) { +"Label 4" }
                }
            }
            snippet("Category", LabelGroupCode.CATEGORY) {
                labelGroup {
                    +"Category"
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Category removable", LabelGroupCode.CATEGORY_REMOVABLE) {
                labelGroup {
                    +"Category"
                    closable(true)
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Vertical", LabelGroupCode.VERTICAL) {
                labelGroup(vertical = true) {
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Vertical overflow", LabelGroupCode.VERTICAL_OVERFLOW) {
                labelGroup(vertical = true) {
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                    label(nextColor()) { +"Label 3" }
                    label(nextColor()) { +"Label 4" }
                }
            }
            snippet("Vertical category", LabelGroupCode.VERTICAL_CATEGORY) {
                labelGroup(vertical = true) {
                    +"Category"
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Vertical category removable", LabelGroupCode.VERTICAL_CATEGORY_REMOVABLE) {
                labelGroup(vertical = true) {
                    +"Category"
                    closable(true)
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Compact basic", LabelGroupCode.COMPACT_BASIC) {
                labelGroup(compact = true) {
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Compact overflow", LabelGroupCode.COMPACT_OVERFLOW) {
                labelGroup(compact = true) {
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                    label(nextColor()) { +"Label 3" }
                    label(nextColor()) { +"Label 4" }
                }
            }
            snippet("Compact category", LabelGroupCode.COMPACT_CATEGORY) {
                labelGroup(compact = true) {
                    +"Category"
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Compact category removable", LabelGroupCode.COMPACT_CATEGORY_REMOVABLE) {
                labelGroup(compact = true) {
                    +"Category"
                    closable(true)
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Compact vertical", LabelGroupCode.COMPACT_VERTICAL) {
                labelGroup(vertical = true, compact = true) {
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Compact vertical overflow", LabelGroupCode.COMPACT_VERTICAL_OVERFLOW) {
                labelGroup(vertical = true, compact = true) {
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                    label(nextColor()) { +"Label 3" }
                    label(nextColor()) { +"Label 4" }
                }
            }
            snippet("Compact vertical category", LabelGroupCode.COMPACT_VERTICAL_CATEGORY) {
                labelGroup(vertical = true, compact = true) {
                    +"Category"
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
                }
            }
            snippet("Compact vertical category removable", LabelGroupCode.COMPACT_VERTICAL_CATEGORY_REMOVABLE) {
                labelGroup(vertical = true, compact = true) {
                    +"Category"
                    closable(true)
                    label(nextColor()) { +"Label 1" }
                    label(nextColor()) { +"Really long label that goes on and on" }
                    label(nextColor()) { +"Label 2" }
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

                labelGroup {
                    +"Letters"
                    label(nextColor(), "First")
                    labels(store) { word ->
                        label(nextColor(), word.text)
                    }
                    label(nextColor(), "Last")
                }
            }
            snippet("Reactive", LabelGroupCode.REACTIVE) {
                class LabelStore : RootStore<List<Pair<Color, String>>>(emptyList()) {
                    val add: Handler<Pair<Color, String>> = handle { data, (color, title) ->
                        data + (color to title)
                    }
                    val remove: Handler<Pair<Color, String>> = handle { data, (color, title) ->
                        data - (color to title)
                    }
                }

                val stores: Array<Pair<Int, LabelStore>> = arrayOf(
                    3 to LabelStore(),
                    4 to LabelStore(),
                    20 to LabelStore()
                )

                fun randomString(length: Int) =
                    (1..(3 + length)).map { ('a'..'z').random() }.joinToString("")

                stores.forEach { (limit, store) ->
                    clickButton(link, baseClass = "small".modifier()) {
                        icon("plus-circle".fas())
                        +"Add label"
                    }.map { nextColor() to randomString(Random.nextInt(10)) } handledBy store.add
                    labelGroup(limit) {
                        +"Max $limit"
                        labels(store) { data ->
                            label(data.first, data.second) {
                                closable(true)
                                closes.map { data } handledBy store.remove
                                closes handledBy notification(Severity.INFO, "${data.second} closed")
                            }
                        }
                    }
                    br {}
                }
            }
        }
    }

    private fun nextColor(): Color = Color.values().random()
}

object LabelGroupCode {

    //language=kotlin
    const val BASIC: String = """"""

    //language=kotlin
    const val OVERFLOW: String = """"""

    //language=kotlin
    const val CATEGORY: String = """"""

    //language=kotlin
    const val CATEGORY_REMOVABLE: String = """"""

    //language=kotlin
    const val VERTICAL: String = """"""

    //language=kotlin
    const val VERTICAL_OVERFLOW: String = """"""

    //language=kotlin
    const val VERTICAL_CATEGORY: String = """"""

    //language=kotlin
    const val VERTICAL_CATEGORY_REMOVABLE: String = """"""

    //language=kotlin
    const val COMPACT_BASIC: String = """"""

    //language=kotlin
    const val COMPACT_OVERFLOW: String = """"""

    //language=kotlin
    const val COMPACT_CATEGORY: String = """"""

    //language=kotlin
    const val COMPACT_CATEGORY_REMOVABLE: String = """"""

    //language=kotlin
    const val COMPACT_VERTICAL: String = """"""

    //language=kotlin
    const val COMPACT_VERTICAL_OVERFLOW: String = """"""

    //language=kotlin
    const val COMPACT_VERTICAL_CATEGORY: String = """"""

    //language=kotlin
    const val COMPACT_VERTICAL_CATEGORY_REMOVABLE: String = """"""

    //language=kotlin
    const val CUSTOM_TYPE: String = """"""

    //language=kotlin
    const val REACTIVE: String = """"""
}
