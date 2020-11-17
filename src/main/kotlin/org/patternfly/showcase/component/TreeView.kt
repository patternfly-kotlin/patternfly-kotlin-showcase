package org.patternfly.showcase.component

import dev.fritz2.elemento.elements
import org.patternfly.ColExIcon
import org.patternfly.SingleIcon
import org.patternfly.TreeBuilder
import org.patternfly.TreeItemBuilder
import org.patternfly.TreeView
import org.patternfly.fas
import org.patternfly.pfChildren
import org.patternfly.pfContent
import org.patternfly.pfIcon
import org.patternfly.pfSection
import org.patternfly.pfTree
import org.patternfly.pfTreeItem
import org.patternfly.pfTreeView
import org.patternfly.showcase.data.Place
import org.patternfly.showcase.data.placeId
import org.patternfly.showcase.data.world

@Suppress("DuplicatedCode")
class TreeViewComponent {
    val elements = elements {
        intro(
            title = "Tree",
            prefix = "A ",
            key = "tree",
            text = " is a structure that displays data in a hierarchical view."
        )
        pfSection {
            pfContent {
                h2 { +"Examples" }
            }
            snippet("Default", TreeViewCode.DEFAULT) {
                pfTreeView(placeId) {
                    makeWorld()
                }
            }
            snippet("Checkboxes", TreeViewCode.CHECKBOXES) {
                pfTreeView(placeId, checkboxes = true) {
                    makeWorld()
                }
            }
            snippet("Icons", TreeViewCode.ICONS) {
                pfTreeView(placeId) {
                    icons = { place ->
                        when {
                            place.id.contains("fa-") -> {
                                SingleIcon { pfIcon(place.id.substringAfter("fa-").fas()) }
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
                                    collapsed = { pfIcon("folder".fas()) },
                                    expanded = { pfIcon("folder-open".fas()) }
                                )
                            }
                        }

                    }
                    makeWorld()
                }
            }
            snippet("Badges", TreeViewCode.BADGES) {
                pfTreeView(placeId, badges = true) {
                    makeWorld()
                }
            }
        }
    }
}

private fun TreeView<Place>.makeWorld() {
    pfTree {
        for (place in world.places) {
            place(place)
        }
    }
}

private fun TreeBuilder<Place>.place(place: Place) {
    pfTreeItem(place) {
        if (place.places.isNotEmpty()) {
            pfChildren {
                for (child in place.places) {
                    place(child)
                }
            }
        }
    }
}



internal object TreeViewCode {

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
