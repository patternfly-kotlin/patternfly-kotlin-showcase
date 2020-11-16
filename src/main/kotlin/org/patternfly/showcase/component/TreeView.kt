package org.patternfly.showcase.component

import dev.fritz2.binding.action
import dev.fritz2.binding.handledBy
import dev.fritz2.elemento.elements
import org.patternfly.TreeItemBuilder
import org.patternfly.TreeStore
import org.patternfly.fas
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
            key = "Tree",
            text = " is under development."
        )
        pfSection {
            pfContent {
                h2 { +"Examples" }
            }
            snippet("Default", TreeViewCode.DEFAULT) {
                val store = TreeStore(placeId)
                pfTreeView(store) {
                    fetchItems = { treeItem ->
                        treeItem.item.places.map { place ->
                            pfTreeItem(place) {
                                finishPlace(place, false)
                            }
                        }
                    }
                }
                action(pfTree<Place> {
                    for (place in world.places) {
                        pfTreeItem(place) {
                            finishPlace(place, false)
                        }
                    }
                }) handledBy store.update
            }
            snippet("Checkboxes", TreeViewCode.CHECKBOXES) {
                val store = TreeStore(placeId)
                pfTreeView(store, checkboxes = true) {
                    fetchItems = { treeItem ->
                        treeItem.item.places.map { place ->
                            pfTreeItem(place) {
                                finishPlace(place, false)
                            }
                        }
                    }
                }
                action(pfTree<Place> {
                    for (place in world.places) {
                        pfTreeItem(place) {
                            finishPlace(place, false)
                        }
                    }
                }) handledBy store.update
            }
            snippet("Icons", TreeViewCode.ICONS) {
                val store = TreeStore(placeId)
                pfTreeView(store) {
                    fetchItems = { treeItem ->
                        treeItem.item.places.map { place ->
                            pfTreeItem(place) {
                                finishPlace(place, true)
                            }
                        }
                    }
                }
                action(pfTree<Place> {
                    for (place in world.places) {
                        pfTreeItem(place) {
                            finishPlace(place, true)
                        }
                    }
                }) handledBy store.update
            }
            snippet("Badges", TreeViewCode.BADGES) {
                val store = TreeStore(placeId)
                pfTreeView(store, badges = true) {
                    fetchItems = { treeItem ->
                        treeItem.item.places.map { place ->
                            pfTreeItem(place) {
                                finishPlace(place, false)
                            }
                        }
                    }
                }
                action(pfTree<Place> {
                    for (place in world.places) {
                        pfTreeItem(place) {
                            finishPlace(place, false)
                        }
                    }
                }) handledBy store.update
            }
        }
    }
}

private fun TreeItemBuilder<Place>.finishPlace(place: Place, withIcons: Boolean) {
    if (withIcons) {
        // c-       Continent
        // sc-      Subcontinent
        // [a-z]{2} Country
        // co-      County
        // ci-      City
        // di-      District
        when {
            place.id.startsWith("c-") -> {
                icon = { pfIcon(place.id.substringAfter("c-").fas()) }
            }
            place.id.startsWith("co-") -> {
                icon = { pfIcon("landmark".fas()) }
            }
            place.id.startsWith("ci-") -> {
                icon = { pfIcon("building".fas()) }
            }
            place.id.startsWith("di-") -> {
                icon = { pfIcon("map-marked-alt".fas()) }
            }
            place.id.matches("[a-z]{2}".toRegex()) -> {
                icon = {
                    img {
                        domNode.style.verticalAlign = "middle"
                        domNode.src = "https://www.countryflags.io/${place.id}/flat/16.png"
                    }
                }
            }
            else -> {
                icon = { pfIcon("folder".fas()) }
                expandedIcon = { pfIcon("folder-open".fas()) }
            }
        }
    }
    mightHaveChildren = place.places.isNotEmpty()
    childCount = place.places.size
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
