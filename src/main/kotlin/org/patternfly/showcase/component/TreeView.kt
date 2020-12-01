package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.ColExIcon
import org.patternfly.SingleIcon
import org.patternfly.TreeBuilder
import org.patternfly.children
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.pageSection
import org.patternfly.showcase.data.Place
import org.patternfly.showcase.data.placeId
import org.patternfly.showcase.data.world
import org.patternfly.tree
import org.patternfly.treeItem
import org.patternfly.treeView

@Suppress("DuplicatedCode")
object TreeViewComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Tree",
            prefix = "A ",
            key = "tree",
            text = " is a structure that displays data in a hierarchical view."
        )
        pageSection {
            h2 { +"Examples" }
            snippet("Default", TreeViewCode.DEFAULT) {
                treeView(placeId) {
                    tree {
                        world.places.forEach { place(it) }
                    }
                }
            }
            snippet("Checkboxes", TreeViewCode.CHECKBOXES) {
                treeView(placeId, checkboxes = true) {
                    tree {
                        world.places.forEach { place(it) }
                    }
                }
            }
            snippet("Icons", TreeViewCode.ICONS) {
                treeView(placeId) {
                    icons = { place ->
                        when {
                            place.id.contains("fa-") -> {
                                SingleIcon { icon(place.id.substringAfter("fa-").fas()) }
                            }
                            place.id.matches("[a-z]{2}".toRegex()) -> {
                                SingleIcon {
                                    img {
                                        domNode.style.verticalAlign = "middle"
                                        domNode.src = "https://www.countryflags.io/${place.id}/flat/16.png"
                                    }
                                }
                            }
                            else -> {
                                ColExIcon(
                                    collapsed = { icon("folder".fas()) },
                                    expanded = { icon("folder-open".fas()) }
                                )
                            }
                        }

                    }
                    tree {
                        world.places.forEach { place(it) }
                    }
                }
            }
            snippet("Badges", TreeViewCode.BADGES) {
                treeView(placeId, badges = true) {
                    tree {
                        world.places.forEach { place(it) }
                    }
                }
            }
        }
    }
}

private fun TreeBuilder<Place>.place(place: Place) {
    treeItem(place) {
        if (place.places.isNotEmpty()) {
            children {
                place.places.forEach { place(it) }
            }
        }
    }
}

object TreeViewCode {

    //language=kotlin
    const val DEFAULT: String = """fun main() {
    render {
    }
}
"""

    //language=kotlin
    const val CHECKBOXES: String = """fun main() {
    render {
    }
}
"""

    //language=kotlin
    const val ICONS: String = """fun main() {
    render {
    }
}
"""

    //language=kotlin
    const val BADGES: String = """fun main() {
    render {
    }
}
"""
}
