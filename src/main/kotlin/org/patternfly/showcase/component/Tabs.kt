@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.patternfly.TabItem
import org.patternfly.TabStore
import org.patternfly.classes
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.item
import org.patternfly.items
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.slider
import org.patternfly.tabs
import org.patternfly.util

object TabsComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Tabs",
            text = "A tab component creates a set of tabs to organize content on a page.",
            designGuidelines = "https://www.patternfly.org/v4/components/tabs/design-guidelines"
        )
        pageSection {
            snippet("Default", TabsCode.DEFAULT) {
                tabs<String> {
                    items {
                        item("Users") { +"Users" }
                        item("Containers") { +"Containers" }
                        item("Database") { +"Database" }
                        item("Server") { +"Server" }
                        item("System") { +"System" }
                        item("Network") { +"Network" }
                    }
                }
            }
            snippet("Box", TabsCode.BOX) {
                tabs<String>(box = true) {
                    items {
                        item("Users") { +"Users" }
                        item("Containers") { +"Containers" }
                        item("Database") { +"Database" }
                        item("Server") { +"Server" }
                        item("System") { +"System" }
                        item("Network") { +"Network" }
                    }
                }
            }
            snippet("Box light", TabsCode.BOX_LIGHT) {
                tabs<String>(box = true, baseClass = "color-scheme--light-300".modifier()) {
                    contentDisplay {
                        domNode.style.backgroundColor = "var(--pf-global--BackgroundColor--light-300)"
                    }
                    items {
                        item("Users") { +"Users" }
                        item("Containers") { +"Containers" }
                        item("Database") { +"Database" }
                        item("Server") { +"Server" }
                        item("System") { +"System" }
                        item("Network") { +"Network" }
                    }
                }
            }
            snippet("Overflow", TabsCode.OVERFLOW) {
                tabs<String> {
                    items {
                        item("Users") { +"Users" }
                        item("Containers") { +"Containers" }
                        item("Database") { +"Database" }
                        item("Server") { +"Server" }
                        item("System") { +"System" }
                        item("Network") { +"Network" }
                        item("Tab item 7") { +"Tab content 7" }
                        item("Tab item 8") { +"Tab content 8" }
                        item("Tab item 9") { +"Tab content 9" }
                        item("Tab item 10") { +"Tab content 10" }
                        item("Tab item 11") { +"Tab content 11" }
                    }
                }
            }
            snippet("Inset", TabsCode.INSET) {
                tabs<String>(baseClass = classes {
                    +"inset-sm-on-md".modifier()
                    +"inset-lg-on-lg".modifier()
                    +"inset-2xl-on-xl".modifier()
                }) {
                    items {
                        item("Users") { +"Users" }
                        item("Containers") { +"Containers" }
                        item("Database") { +"Database" }
                        item("Server") { +"Server" }
                        item("System") { +"System" }
                        item("Network") { +"Network" }
                    }
                }
            }
            snippet("Filled", TabsCode.FILLED) {
                tabs<String>(filled = true) {
                    items {
                        item("Users") { +"Users" }
                        item("Containers") { +"Containers" }
                        item("Database") { +"Database" }
                    }
                }
            }
            snippet("Vertical", TabsCode.VERTICAL) {
                tabs<String>(vertical = true) {
                    items {
                        item("Users") { +"Users" }
                        item("Containers") { +"Containers" }
                        item("Database") { +"Database" }
                        item("Server") { +"Server" }
                        item("System") { +"System" }
                        item("Network") { +"Network" }
                    }
                }
            }
            snippet("Vertical box", TabsCode.VERTICAL_BOX) {
                tabs<String>(vertical = true, box = true) {
                    items {
                        item("Users") { +"Users" }
                        item("Containers") { +"Containers" }
                        item("Database") { +"Database" }
                        item("Server") { +"Server" }
                        item("System") { +"System" }
                        item("Network") { +"Network" }
                    }
                }
            }
            snippet("Icons", TabsCode.ICONS) {
                tabs<String> {
                    items {
                        item("Users", icon = { icon("users".fas()) }) { +"Users" }
                        item("Containers", icon = { icon("box".fas()) }) { +"Containers" }
                        item("Database", icon = { icon("database".fas()) }) { +"Database" }
                        item("Server", icon = { icon("server".fas()) }) { +"Server" }
                        item("System", icon = { icon("laptop".fas()) }) { +"System" }
                        item("Network", icon = { icon("project-diagram".fas()) }) { +"Network" }
                    }
                }
            }
            snippet("Reactive", TabsCode.REACTIVE) {
                val range = storeOf(5)
                val store = TabStore<Int>()

                div(baseClass = classes {
                    +"flex".layout()
                    +"align-items-center".modifier()
                    +"mb-md".util()
                }) {
                    slider(range, 2..12, baseClass = "w-50".util()) {
                        leftActions { decrease() }
                        showTicks()
                        rightActions { increase() }
                    }
                }
                tabs(store) {
                    tabDisplay { +"Tab item $it" }
                    contentDisplay { +"Content $it" }
                }
                range.data.map { tabs ->
                    (1..tabs).map { number -> TabItem(number, selected = number == 1) }
                } handledBy store.update
            }
        }
    }
}

object TabsCode {

    //language=kotlin
    const val DEFAULT: String = """fun main() {
    render {
        tabs<String> {
            item("Users") { +"Users" }
            item("Containers") { +"Containers" }
            item("Database") { +"Database" }
            item("Server") { +"Server" }
            item("System") { +"System" }
            item("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val BOX: String = """fun main() {
    render {
        tabs<String>(box = true) {
            item("Users") { +"Users" }
            item("Containers") { +"Containers" }
            item("Database") { +"Database" }
            item("Server") { +"Server" }
            item("System") { +"System" }
            item("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val BOX_LIGHT: String = """fun main() {
    render {
        tabs<String>(box = true, baseClass = "color-scheme--light-300".modifier()) {
            contentDisplay = {
                domNode.style.backgroundColor = "var(--pf-global--BackgroundColor--light-300)"
            }
            item("Users") { +"Users" }
            item("Containers") { +"Containers" }
            item("Database") { +"Database" }
            item("Server") { +"Server" }
            item("System") { +"System" }
            item("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val OVERFLOW: String = """fun main() {
    render {
        tabs<String> {
            item("Users") { +"Users" }
            item("Containers") { +"Containers" }
            item("Database") { +"Database" }
            item("Server") { +"Server" }
            item("System") { +"System" }
            item("Network") { +"Network" }
            item("Tab item 7") { +"Tab content 7" }
            item("Tab item 8") { +"Tab content 8" }
            item("Tab item 9") { +"Tab content 9" }
            item("Tab item 10") { +"Tab content 10" }
            item("Tab item 11") { +"Tab content 11" }
        }
    }
}
"""

    //language=kotlin
    const val INSET: String = """fun main() {
    render {
        tabs<String>(baseClass = classes {
            +"inset-sm-on-md".modifier()
            +"inset-lg-on-lg".modifier()
            +"inset-2xl-on-xl".modifier()
        }) {
            item("Users") { +"Users" }
            item("Containers") { +"Containers" }
            item("Database") { +"Database" }
            item("Server") { +"Server" }
            item("System") { +"System" }
            item("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val FILLED: String = """fun main() {
    render {
        tabs<String>(filled = true) {
            item("Users") { +"Users" }
            item("Containers") { +"Containers" }
            item("Database") { +"Database" }
        }
    }
}
"""

    //language=kotlin
    const val VERTICAL: String = """fun main() {
    render {
        tabs<String>(vertical = true) {
            item("Users") { +"Users" }
            item("Containers") { +"Containers" }
            item("Database") { +"Database" }
            item("Server") { +"Server" }
            item("System") { +"System" }
            item("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val VERTICAL_BOX: String = """fun main() {
    render {
        tabs<String>(vertical = true, box = true) {
            item("Users") { +"Users" }
            item("Containers") { +"Containers" }
            item("Database") { +"Database" }
            item("Server") { +"Server" }
            item("System") { +"System" }
            item("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val ICONS: String = """fun main() {
    render {
        tabs<String> {
            item("Users", icon = { icon("users".fas()) }) { +"Users" }
            item("Containers", icon = { icon("box".fas()) }) { +"Containers" }
            item("Database", icon = { icon("database".fas()) }) { +"Database" }
            item("Server", icon = { icon("server".fas()) }) { +"Server" }
            item("System", icon = { icon("laptop".fas()) }) { +"System" }
            item("Network", icon = { icon("project-diagram".fas()) }) { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        lateinit var range: Input
        val store = TabStore<Int>()

        div(baseClass = classes {
            +"flex".layout()
            +"align-items-center".modifier()
            +"mb-md".util()
        }) {
            label {
                +"Tabs: "
                `for`("range")
            }
            range = input(id = "range", baseClass = "w-50".util()) {
                type("range")
                min("1")
                max("12")
                value("5")
            }
            span {
                range.inputs.events
                    .map { it.target.unsafeCast<HTMLInputElement>().value }.asText()
            }
        }
        tabs(store) {
            itemDisplay = { +"Tab item ${'$'}it" }
            contentDisplay = { +"Content ${'$'}it" }
        }

        range.changes.valuesAsNumber()
            .map { it.toInt() }
            .map { tabs ->
                (1..tabs).map { number -> item(number, selected = number == 1) }
            } handledBy store.update

        MainScope().launch {
            delay(EVENT_DELAY)
            range.domNode.dispatchEvent(Event(Events.input.name))
            range.domNode.dispatchEvent(Event(Events.change.name))
        }
    }
}
"""
}
