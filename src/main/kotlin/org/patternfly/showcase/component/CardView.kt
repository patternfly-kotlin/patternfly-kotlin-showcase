@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.lenses.IdProvider
import kotlinx.coroutines.flow.map
import org.patternfly.ButtonVariant.link
import org.patternfly.CardVariant.selectable
import org.patternfly.SelectionMode
import org.patternfly.actionList
import org.patternfly.cardView
import org.patternfly.classes
import org.patternfly.clickButton
import org.patternfly.dom.Id
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.showcase.fixture.DropdownFixture.defaultDropdown
import org.patternfly.util

object CardViewComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Card view",
            text = "A card view is a grid of cards in a gallery to facilitate browsing.",
            designGuidelines = "https://www.patternfly.org/v4/demos/card-view/design-guidelines"
        )
        pageSection {
            snippet("Single select", CardViewCode.SINGLE_SELECT) {
                data class Demo(val id: String, val title: String)

                val foo = Demo("foo", "Foo")
                val bar = Demo("bar", "Bar")
                val demos = listOf(foo, bar)
                val values = storeOf(demos)
                val selection = storeOf<Demo?>(foo)
                val idProvider: IdProvider<Demo, String> = { Id.build(it.id, "single", "select", "demo") }

                cardView(SelectionMode.SINGLE) {
                    items(values, idProvider, singleSelection = selection) { demo ->
                        card(selectable) {
                            title { +demo.title }
                            body { +loremIpsum(3) }
                        }
                    }
                }
                section(baseClass = classes("flex".layout(), "mt-lg".util())) {
                    actionList {
                        for (demo in demos) {
                            item {
                                clickButton(link) { +"Select ${demo.title}" }.map { demo } handledBy selection.update
                            }
                        }
                        item {
                            clickButton(link) { +"Select none" }.map { null } handledBy selection.update
                        }
                    }
                    div(baseClass = "align-right".modifier()) {
                        +"Selected item: "
                        selection.data.map { it?.title ?: "Nothing" }.renderText()
                    }
                }
            }
            snippet("Multi select", CardViewCode.MULTI_SELECT) {
                data class Demo(val id: String, val title: String)

                val foo = Demo("foo", "Foo")
                val bar = Demo("bar", "Bar")
                val demos = listOf(foo, bar)
                val values = storeOf(demos)
                val selection = storeOf(listOf(foo))
                val idProvider: IdProvider<Demo, String> = { Id.build(it.id, "multi", "select", "demo") }

                cardView(SelectionMode.MULTI) {
                    card {
                        title { +"Static Card" }
                        body { +loremIpsum(3) }
                    }
                    items(values, idProvider, multiSelection = selection) { demo ->
                        card(selectable) {
                            header {
                                title { +demo.title }
                                actions {
                                    defaultDropdown()
                                }
                                check()
                            }
                            body { +loremIpsum(3) }
                        }
                    }
                }
                section(baseClass = classes("flex".layout(), "mt-lg".util())) {
                    actionList {
                        for (demo in demos) {
                            item {
                                clickButton(link) { +"Select ${demo.title}" }.map { selection.current + demo } handledBy selection.update
                            }
                        }
                        item {
                            clickButton(link) { +"Select all" }.map { demos } handledBy selection.update
                        }
                        item {
                            clickButton(link) { +"Select none" }.map { emptyList<Demo>() } handledBy selection.update
                        }
                    }
                    div(baseClass = "align-right".modifier()) {
                        +"Selected items: "
                        selection.data.map { demos -> demos.joinToString { it.title }.ifEmpty { "None" } }.renderText()
                    }
                }
            }
        }
    }
}

object CardViewCode {

    //language=kotlin
    const val SINGLE_SELECT: String = """fun main() {
    render {
    }
}
"""

    //language=kotlin
    const val MULTI_SELECT: String = """fun main() {
    render {
    }
}
"""
}
