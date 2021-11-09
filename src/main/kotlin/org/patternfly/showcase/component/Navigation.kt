@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.routing.router
import org.patternfly.Severity.INFO
import org.patternfly.navigation
import org.patternfly.notification
import org.patternfly.page
import org.patternfly.pageSection
import org.patternfly.pageSubNav

object NavigationComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Navigation",
            text = "A navigation organizes an application’s structure and content, making it easy to find information and accomplish tasks. Navigation communicates relationships, context, and actions a user can take within an application.",
            designGuidelines = "https://www.patternfly.org/v4/components/navigation/design-guidelines"
        )
        pageSection {
            snippet("Default", NavigationCode.DEFAULT) {
                page {
                    sidebar {
                        navigation(router("home")) {
                            item("link1", "Link 1") {
                                events {
                                    clicks handledBy notification(INFO, "Custom navigation!")
                                }
                            }
                            item("link2", "Link 2")
                            item("link3", "Link 3")
                            item("link4", "Link 4")
                        }
                    }
                }
            }
            snippet("Grouped", NavigationCode.GROUPED) {
                page {
                    sidebar {
                        navigation(router("home")) {
                            group("Group 1") {
                                item("link11", "Link 1")
                                item("link12", "Link 2")
                                item("link13", "Link 3")
                            }
                            group("Group 2") {
                                item("link121", "Link 1")
                                item("link122", "Link 2")
                                item("link123", "Link 3")
                            }
                        }
                    }
                }
            }
            snippet("Expandable", NavigationCode.EXPANDABLE) {
                page {
                    sidebar {
                        navigation(router("home"), expandable = true) {
                            group("Group 1") {
                                item("link11", "Link 1")
                                separator()
                                item("link12", "Link 2")
                                item("link13", "Link 3")
                            }
                            group("Group 2") {
                                events {
                                    coexs handledBy notification(INFO) { expanded ->
                                        title("Group 2 expanded: $expanded")
                                    }
                                }
                                item("link121", "Link 1")
                                item("link122", "Link 2")
                                item("link123", "Link 3")
                            }
                        }
                    }
                }
            }
            snippet("Mixed", NavigationCode.MIXED) {
                page {
                    sidebar {
                        navigation(router("home"), expandable = true) {
                            item("link1", "Link 1")
                            item("link2", "Link 2")
                            item("link3", "Link 3")
                            group("Group 1") {
                                item("link11", "Link 1")
                                item("link12", "Link 2")
                                item("link13", "Link 3")
                            }
                            group("Group 2") {
                                item("link121", "Link 1")
                                item("link122", "Link 2")
                                item("link123", "Link 3")
                            }
                        }
                    }
                }
            }
        }
        snippet("Horizontal", NavigationCode.HORIZONTAL) {
            page {
                masthead {
                    content {
                        navigation(router("home")) {
                            item("link1", "Link 1")
                            item("link2", "Link 2")
                            item("link3", "Link 3")
                            item("link4", "Link 4")
                        }
                    }
                }
            }
        }
        snippet("Subnav", NavigationCode.TERTIARY) {
            page {
                main {
                    pageSubNav {
                        navigation(router("home")) {
                            item("link1", "Link 1")
                            item("link2", "Link 2")
                            item("link3", "Link 3")
                            item("link4", "Link 4")
                            item("link5", "Link 5")
                            item("link6", "Link 6")
                            item("link7", "Link 7")
                            item("link8", "Link 8")
                            item("link9", "Link 9")
                            item("link10", "Link 10")
                            item("link11", "Link 11")
                            item("link12", "Link 12")
                            item("link13", "Link 13")
                            item("link14", "Link 14")
                        }
                    }
                }
            }
        }
    }
}


object NavigationCode {

    //language=kotlin
    const val DEFAULT: String = """fun main() {
    render {
        div(baseClass = classes("background-color-dark-300".util(), "p-md".util())) {
            navigation(router("home")) {
                items {
                    item("link1", "Link 1")
                    item("link2", "Link 2")
                    item("link3", "Link 3")
                    item("link4", "Link 4")
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val GROUPED: String = """fun main() {
    render {
        div(baseClass = classes("background-color-dark-300".util(), "p-md".util())) {
            navigation(router("home")) {
                groups {
                    group("Group 1") {
                        item("link11", "Link 1")
                        item("link12", "Link 2")
                        item("link13", "Link 3")
                    }
                    group("Group 2") {
                        item("link121", "Link 1")
                        item("link122", "Link 2")
                        item("link123", "Link 3")
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val EXPANDABLE: String = """fun main() {
    render {
        div(baseClass = classes("background-color-dark-300".util(), "p-md".util())) {
            navigation(router("home"), expandable = true) {
                groups {
                    group("Group 1") {
                        item("link11", "Link 1")
                        separator()
                        item("link12", "Link 2")
                        item("link13", "Link 3")
                    }
                    group("Group 2") {
                        item("link121", "Link 1")
                        item("link122", "Link 2")
                        item("link123", "Link 3")
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val MIXED: String = """fun main() {
    render {
        div(baseClass = classes("background-color-dark-300".util(), "p-md".util())) {
            navigation(router("home"), expandable = true) {
                groups {
                    group() {
                        item("link1", "Link 1")
                        item("link2", "Link 2")
                        item("link3", "Link 3")
                    }
                    group("Group 1") {
                        item("link11", "Link 1")
                        item("link12", "Link 2")
                        item("link13", "Link 3")
                    }
                    group("Group 2") {
                        item("link121", "Link 1")
                        item("link122", "Link 2")
                        item("link123", "Link 3")
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val HORIZONTAL: String = """fun main() {
    render {
        div(baseClass = classes("background-color-dark-300".util(), "p-md".util())) {
            horizontalNavigation(router("home")) {
                items {
                    item("link1", "Link 1")
                    item("link2", "Link 2")
                    item("link3", "Link 3")
                    item("link4", "Link 4")
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val TERTIARY: String = """fun main() {
    render {
        tertiaryNavigation(router("home")) {
            items {
                item("link1", "Link 1")
                item("link2", "Link 2")
                item("link3", "Link 3")
                item("link4", "Link 4")
                item("link5", "Link 5")
                item("link6", "Link 6")
                item("link7", "Link 7")
                item("link8", "Link 8")
                item("link9", "Link 9")
                item("link10", "Link 10")
                item("link11", "Link 11")
                item("link12", "Link 12")
                item("link13", "Link 13")
                item("link14", "Link 14")
            }
        }
    }
}
"""
}
