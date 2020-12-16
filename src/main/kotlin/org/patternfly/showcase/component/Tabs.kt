@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.valuesAsNumber
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.TabItem
import org.patternfly.TabStore
import org.patternfly.classes
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.showcase.EVENT_DELAY
import org.patternfly.tabItem
import org.patternfly.tabs
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

object TabsComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Tabs",
            key = "Tabs",
            text = " are used to present a set on tabs for organizing content on a page."
        )
        pageSection {
            snippet("Default", TabsCode.DEFAULT) {
                tabs<String> {
                    tabItem("Users") { +"Users" }
                    tabItem("Containers") { +"Containers" }
                    tabItem("Database") { +"Database" }
                    tabItem("Server") { +"Server" }
                    tabItem("System") { +"System" }
                    tabItem("Network") { +"Network" }
                }
            }
            snippet("Box", TabsCode.BOX) {
                tabs<String>(box = true) {
                    tabItem("Users") { +"Users" }
                    tabItem("Containers") { +"Containers" }
                    tabItem("Database") { +"Database" }
                    tabItem("Server") { +"Server" }
                    tabItem("System") { +"System" }
                    tabItem("Network") { +"Network" }
                }
            }
            snippet("Box light", TabsCode.BOX_LIGHT) {
                tabs<String>(box = true, baseClass = "color-scheme--light-300".modifier()) {
                    contentDisplay = {
                        domNode.style.backgroundColor = "var(--pf-global--BackgroundColor--light-300)"
                    }
                    tabItem("Users") { +"Users" }
                    tabItem("Containers") { +"Containers" }
                    tabItem("Database") { +"Database" }
                    tabItem("Server") { +"Server" }
                    tabItem("System") { +"System" }
                    tabItem("Network") { +"Network" }
                }
            }
            snippet("Overflow", TabsCode.OVERFLOW) {
                tabs<String> {
                    tabItem("Users") { +"Users" }
                    tabItem("Containers") { +"Containers" }
                    tabItem("Database") { +"Database" }
                    tabItem("Server") { +"Server" }
                    tabItem("System") { +"System" }
                    tabItem("Network") { +"Network" }
                    tabItem("Tab item 7") { +"Tab content 7" }
                    tabItem("Tab item 8") { +"Tab content 8" }
                    tabItem("Tab item 9") { +"Tab content 9" }
                    tabItem("Tab item 10") { +"Tab content 10" }
                    tabItem("Tab item 11") { +"Tab content 11" }
                }
            }
            snippet("Inset", TabsCode.INSET) {
                tabs<String>(baseClass = classes {
                    +"inset-sm-on-md".modifier()
                    +"inset-lg-on-lg".modifier()
                    +"inset-2xl-on-xl".modifier()
                }) {
                    tabItem("Users") { +"Users" }
                    tabItem("Containers") { +"Containers" }
                    tabItem("Database") { +"Database" }
                    tabItem("Server") { +"Server" }
                    tabItem("System") { +"System" }
                    tabItem("Network") { +"Network" }
                }
            }
            snippet("Filled", TabsCode.FILLED) {
                tabs<String>(filled = true) {
                    tabItem("Users") { +"Users" }
                    tabItem("Containers") { +"Containers" }
                    tabItem("Database") { +"Database" }
                }
            }
            snippet("Vertical", TabsCode.VERTICAL) {
                tabs<String>(vertical = true) {
                    tabItem("Users") { +"Users" }
                    tabItem("Containers") { +"Containers" }
                    tabItem("Database") { +"Database" }
                    tabItem("Server") { +"Server" }
                    tabItem("System") { +"System" }
                    tabItem("Network") { +"Network" }
                }
            }
            snippet("Vertical box", TabsCode.VERTICAL_BOX) {
                tabs<String>(vertical = true, box = true) {
                    tabItem("Users") { +"Users" }
                    tabItem("Containers") { +"Containers" }
                    tabItem("Database") { +"Database" }
                    tabItem("Server") { +"Server" }
                    tabItem("System") { +"System" }
                    tabItem("Network") { +"Network" }
                }
            }
            snippet("Icons", TabsCode.ICONS) {
                tabs<String> {
                    tabItem("Users", icon = { icon("users".fas()) }) { +"Users" }
                    tabItem("Containers", icon = { icon("box".fas()) }) { +"Containers" }
                    tabItem("Database", icon = { icon("database".fas()) }) { +"Database" }
                    tabItem("Server", icon = { icon("server".fas()) }) { +"Server" }
                    tabItem("System", icon = { icon("laptop".fas()) }) { +"System" }
                    tabItem("Network", icon = { icon("project-diagram".fas()) }) { +"Network" }
                }
            }
            snippet("Reactive", TabsCode.REACTIVE) {
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
                    itemDisplay = { +"Tab item $it" }
                    contentDisplay = { +"Content $it" }
                }

                range.changes.valuesAsNumber()
                    .map { it.toInt() }
                    .map { tabs ->
                        (1..tabs).map { number -> TabItem(number, selected = number == 1) }
                    } handledBy store.update

                MainScope().launch {
                    delay(EVENT_DELAY)
                    range.domNode.dispatchEvent(Event(Events.input.name))
                    range.domNode.dispatchEvent(Event(Events.change.name))
                }
            }
        }
    }
}

object TabsCode {

    //language=kotlin
    const val DEFAULT: String = """fun main() {
    render {
        tabs<String> {
            tabItem("Users") { +"Users" }
            tabItem("Containers") { +"Containers" }
            tabItem("Database") { +"Database" }
            tabItem("Server") { +"Server" }
            tabItem("System") { +"System" }
            tabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val BOX: String = """fun main() {
    render {
        tabs<String>(box = true) {
            tabItem("Users") { +"Users" }
            tabItem("Containers") { +"Containers" }
            tabItem("Database") { +"Database" }
            tabItem("Server") { +"Server" }
            tabItem("System") { +"System" }
            tabItem("Network") { +"Network" }
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
            tabItem("Users") { +"Users" }
            tabItem("Containers") { +"Containers" }
            tabItem("Database") { +"Database" }
            tabItem("Server") { +"Server" }
            tabItem("System") { +"System" }
            tabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val OVERFLOW: String = """fun main() {
    render {
        tabs<String> {
            tabItem("Users") { +"Users" }
            tabItem("Containers") { +"Containers" }
            tabItem("Database") { +"Database" }
            tabItem("Server") { +"Server" }
            tabItem("System") { +"System" }
            tabItem("Network") { +"Network" }
            tabItem("Tab item 7") { +"Tab content 7" }
            tabItem("Tab item 8") { +"Tab content 8" }
            tabItem("Tab item 9") { +"Tab content 9" }
            tabItem("Tab item 10") { +"Tab content 10" }
            tabItem("Tab item 11") { +"Tab content 11" }
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
            tabItem("Users") { +"Users" }
            tabItem("Containers") { +"Containers" }
            tabItem("Database") { +"Database" }
            tabItem("Server") { +"Server" }
            tabItem("System") { +"System" }
            tabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val FILLED: String = """fun main() {
    render {
        tabs<String>(filled = true) {
            tabItem("Users") { +"Users" }
            tabItem("Containers") { +"Containers" }
            tabItem("Database") { +"Database" }
        }
    }
}
"""

    //language=kotlin
    const val VERTICAL: String = """fun main() {
    render {
        tabs<String>(vertical = true) {
            tabItem("Users") { +"Users" }
            tabItem("Containers") { +"Containers" }
            tabItem("Database") { +"Database" }
            tabItem("Server") { +"Server" }
            tabItem("System") { +"System" }
            tabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val VERTICAL_BOX: String = """fun main() {
    render {
        tabs<String>(vertical = true, box = true) {
            tabItem("Users") { +"Users" }
            tabItem("Containers") { +"Containers" }
            tabItem("Database") { +"Database" }
            tabItem("Server") { +"Server" }
            tabItem("System") { +"System" }
            tabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val ICONS: String = """fun main() {
    render {
        tabs<String> {
            tabItem("Users", icon = { icon("users".fas()) }) { +"Users" }
            tabItem("Containers", icon = { icon("box".fas()) }) { +"Containers" }
            tabItem("Database", icon = { icon("database".fas()) }) { +"Database" }
            tabItem("Server", icon = { icon("server".fas()) }) { +"Server" }
            tabItem("System", icon = { icon("laptop".fas()) }) { +"System" }
            tabItem("Network", icon = { icon("project-diagram".fas()) }) { +"Network" }
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
                (1..tabs).map { number -> TabItem(number, selected = number == 1) }
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
