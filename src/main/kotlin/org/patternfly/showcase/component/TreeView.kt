package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.DoubleIcon
import org.patternfly.SingleIcon
import org.patternfly.TreeBuilder
import org.patternfly.children
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.pageSection
import org.patternfly.showcase.data.Place
import org.patternfly.showcase.data.world
import org.patternfly.tree
import org.patternfly.treeItem
import org.patternfly.treeView

@Suppress("DuplicatedCode")
object TreeViewComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Tree view",
            text = "A tree view is a structure that displays data in a hierarchical view. ",
            designGuidelines = "https://www.patternfly.org/v4/components/tree-view/design-guidelines"
        ) {
            p {
                +"The demos here use the data class "
                code { +"Place()" }
                +" and static data from "
                a {
                    href("https://github.com/patternfly-kotlin/patternfly-fritz2-showcase/blob/master/src/main/kotlin/org/patternfly/showcase/data/Place.kt")
                    +"Place.kt"
                }
                +"."
            }
        }
        pageSection {
            snippet("Default", TreeViewCode.DEFAULT) {
                treeView<Place> {
                    tree {
                        world.places.forEach { place(it) }
                    }
                }
            }
            snippet("Checkboxes", TreeViewCode.CHECKBOXES) {
                treeView<Place>(checkboxes = true) {
                    tree {
                        world.places.forEach { place(it) }
                    }
                }
            }
            snippet("Icons", TreeViewCode.ICONS) {
                treeView<Place> {
                    iconProvider { place ->
                        when {
                            place.id.contains("fa-") -> {
                                SingleIcon { icon(place.id.substringAfter("fa-").fas()) }
                            }
                            place.id.matches("[a-z]{2}".toRegex()) -> {
                                SingleIcon {
                                    img {
                                        with(domNode) {
                                            style.width = "16px"
                                            style.verticalAlign = "middle"
                                            src = "https://lipis.github.io/flag-icon-css/flags/4x3/${place.id}.svg"
                                        }
                                    }
                                }
                            }
                            else -> {
                                DoubleIcon(
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
                treeView<Place>(badges = true) {
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
        treeView<Place> {
            tree {
                world.places.forEach { place(it) }
            }
        }
    }
}
"""

    //language=kotlin
    const val CHECKBOXES: String = """fun main() {
    render {
        treeView<Place>(checkboxes = true) {
            tree {
                world.places.forEach { place(it) }
            }
        }
    }
}
"""

    //language=kotlin
    const val ICONS: String = """fun main() {
    render {
        treeView<Place> {
            iconProvider { place ->
                when {
                    place.id.contains("fa-") -> {
                        SingleIcon { icon(place.id.substringAfter("fa-").fas()) }
                    }
                    place.id.matches("[a-z]{2}".toRegex()) -> {
                        SingleIcon {
                            img {
                                with(domNode) {
                                    style.width = "16px"
                                    style.verticalAlign = "middle"
                                    src = "https://lipis.github.io/flag-icon-css/flags/4x3/${'$'}{place.id}.svg"
                                }
                            }
                        }
                    }
                    else -> {
                        DoubleIcon(
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
}
"""

    //language=kotlin
    const val BADGES: String = """fun main() {
    render {
        treeView<Place>(badges = true) {
            tree {
                world.places.forEach { place(it) }
            }
        }
    }
}
"""
}
