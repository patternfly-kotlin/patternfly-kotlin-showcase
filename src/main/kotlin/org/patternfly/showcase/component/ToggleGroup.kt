package org.patternfly.showcase.component

import dev.fritz2.binding.RootStore
import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.lenses.IdProvider
import kotlinx.coroutines.flow.map
import org.patternfly.ButtonVariant.primary
import org.patternfly.clickButton
import org.patternfly.dom.Id
import org.patternfly.fas
import org.patternfly.pageSection
import org.patternfly.toggleGroup
import org.patternfly.util

object ToggleGroupComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Title",
            text = "A title component applies top and bottom margins, font-weight, font-size, and line-height to titles.",
            designGuidelines = "https://www.patternfly.org/v4/components/title/design-guidelines"
        )
        pageSection {
            snippet("Default with multiple selectable", ToggleGroupCode.DEFAULT_MULTIPLE) {
                val disabled = object : RootStore<Boolean>(false) {
                    val toggle = handle { value -> !value }
                }

                clickButton(primary, baseClass = "mb-md".util()) {
                    disabled.data.map { if (it) "Enable back" else "Disable all" }.renderText()
                } handledBy disabled.toggle
                br { }
                toggleGroup {
                    item(id = "opt1-tgl") {
                        +"Option 1"
                        disabled(disabled.data)
                    }
                    item(id = "opt2-tgl") {
                        +"Option 2"
                        disabled(disabled.data)
                    }
                    item {
                        +"Option 3"
                        disabled(true)
                    }
                }
            }
            snippet("Default with single selectable", ToggleGroupCode.DEFAULT_SINGLE) {
                toggleGroup(singleSelect = true) {
                    item { +"Option 1" }
                    item { +"Option 2" }
                    item { +"Option 3" }
                }
            }
            snippet("Icons", ToggleGroupCode.ICONS) {
                toggleGroup {
                    item { icon("copy".fas()) }
                    item { icon("undo".fas()) }
                    item { icon("share-square".fas()) }
                }
            }
            snippet("Text and icons", ToggleGroupCode.TEXT_ICONS) {
                data class IconAndText(val icon: String, val text: String)

                val share = IconAndText("share-square".fas(), "Share")
                val values = storeOf(
                    listOf(
                        IconAndText("copy".fas(), "Copy"),
                        IconAndText("undo".fas(), "Undo"),
                        share,
                    )
                )
                val idProvider: IdProvider<IconAndText, String> = { Id.build(it.text, "tgl") }
                val selection = storeOf(listOf(share))

                // use the same selection store to sync selections
                toggleGroup {
                    items(values, idProvider, multiSelection = selection) {
                        item {
                            icon(it.icon)
                            +it.text
                        }
                    }
                }
                br { }
                toggleGroup {
                    items(values, idProvider, multiSelection = selection) {
                        item {
                            +it.text
                            icon(it.icon)
                        }
                    }
                }
            }
            snippet("Compact variant", ToggleGroupCode.COMPACT) {
                toggleGroup(compact = true) {
                    item { +"Option 1" }
                    item { +"Option 2" }
                    item {
                        +"Option 3"
                        disabled(true)
                    }
                }
            }
        }
    }
}

object ToggleGroupCode {

    //language=kotlin
    const val DEFAULT_MULTIPLE: String = """fun main() {
    render {
        val disabled = object : RootStore<Boolean>(false) {
            val toggle = handle { value -> !value }
        }

        clickButton(primary, baseClass = "mb-md".util()) {
            disabled.data.map { if (it) "Enable back" else "Disable all" }.renderText()
        } handledBy disabled.toggle
        br { }
        toggleGroup {
            item(id = "opt1-tgl") {
                +"Option 1"
                disabled(disabled.data)
            }
            item(id = "opt2-tgl") {
                +"Option 2"
                disabled(disabled.data)
            }
            item {
                +"Option 3"
                disabled(true)
            }
        }
    }
}
"""

    //language=kotlin
    const val DEFAULT_SINGLE: String = """fun main() {
    render {
        toggleGroup(singleSelect = true) {
            item { +"Option 1" }
            item { +"Option 2" }
            item { +"Option 3" }
        }
    }
}
"""

    //language=kotlin
    const val ICONS: String = """fun main() {
    render {
        toggleGroup {
            item { icon("copy".fas()) }
            item { icon("undo".fas()) }
            item { icon("share-square".fas()) }
        }
    }
}
"""

    //language=kotlin
    const val TEXT_ICONS: String = """fun main() {
    render {
        data class IconAndText(val icon: String, val text: String)

        val share = IconAndText("share-square".fas(), "Share")
        val values = storeOf(
            listOf(
                IconAndText("copy".fas(), "Copy"),
                IconAndText("undo".fas(), "Undo"),
                share,
            )
        )
        val idProvider: IdProvider<IconAndText, String> = { Id.build(it.text, "tgl") }
        val selection = storeOf(listOf(share))

        // use the same selection store to sync selections
        toggleGroup {
            items(values, idProvider, multiSelection = selection) {
                item {
                    icon(it.icon)
                    +it.text
                }
            }
        }
        br { }
        toggleGroup {
            items(values, idProvider, multiSelection = selection) {
                item {
                    +it.text
                    icon(it.icon)
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val COMPACT: String = """fun main() {
    render {
        toggleGroup(compact = true) {
            item { +"Option 1" }
            item { +"Option 2" }
            item {
                +"Option 3"
                disabled(true)
            }
        }
    }
}
"""
}
