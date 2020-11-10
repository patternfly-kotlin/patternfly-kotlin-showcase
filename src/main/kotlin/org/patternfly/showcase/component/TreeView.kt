package org.patternfly.showcase.component

import dev.fritz2.elemento.Id
import dev.fritz2.elemento.elements
import org.patternfly.TreeStore
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.patternfly.pfTreeItem
import org.patternfly.pfTreeItems
import org.patternfly.pfTreeView

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
                val store = TreeStore<String> { Id.build(it) }
                pfTreeView(store) {
                    pfTreeItems {
                        pfTreeItem("Application Launcher") {
                            expanded = true
                            pfTreeItems {
                                pfTreeItem("Application 1") {
                                    pfTreeItems {
                                        pfTreeItem("Settings")
                                        pfTreeItem("Current")
                                    }
                                }
                                pfTreeItem("Application 2") {
                                    pfTreeItems {
                                        pfTreeItem("Settings")
                                        pfTreeItem("Loader") {
                                            pfTreeItems {
                                                pfTreeItem("Loading App 1")
                                                pfTreeItem("Loading App 2")
                                                pfTreeItem("Loading App 3")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        pfTreeItem("Cost Management") {
                            pfTreeItems {
                                pfTreeItem("Application 3") {
                                    pfTreeItems {
                                        pfTreeItem("Settings")
                                        pfTreeItem("Current")
                                    }
                                }
                            }
                        }
                        pfTreeItem("Sources") {
                            pfTreeItems {
                                pfTreeItem("Application 4") {
                                    pfTreeItems {
                                        pfTreeItem("Settings")
                                    }
                                }
                            }
                        }
                        pfTreeItem("Long folder name that overflows the container it is in") {
                            pfTreeItems {
                                pfTreeItem("Application 5")
                            }
                        }
                    }
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
}
