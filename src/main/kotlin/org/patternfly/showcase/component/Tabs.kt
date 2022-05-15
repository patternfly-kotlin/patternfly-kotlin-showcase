@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.lenses.IdProvider
import kotlinx.coroutines.flow.map
import org.patternfly.classes
import org.patternfly.dom.Id
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.slider
import org.patternfly.tabs
import org.patternfly.toggleGroup

object TabsComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Tabs",
            text = "A tab component creates a set of tabs to organize content on a page.",
            designGuidelines = "https://www.patternfly.org/v4/components/tabs/design-guidelines"
        )
        pageSection {
            snippet("Default", TabsCode.DEFAULT) {
                tabs {
                    item {
                        tab("Users")
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab("Containers")
                        content { +"Containers" }
                    }
                    item {
                        tab("Database")
                        content { +"Database" }
                    }
                    item {
                        tab("Server")
                        content { +"Server" }
                    }
                    item {
                        tab("System")
                        content { +"System" }
                        disabled(true)
                    }
                    item {
                        tab("Network")
                        content { +"Network" }
                        disabled(true)
                    }
                }
            }
            snippet("Box", TabsCode.BOX) {
                tabs(box = true) {
                    item {
                        tab("Users")
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab("Containers")
                        content { +"Containers" }
                    }
                    item {
                        tab("Database")
                        content { +"Database" }
                    }
                    item {
                        tab("Server")
                        content { +"Server" }
                    }
                    item {
                        tab("System")
                        content { +"System" }
                        disabled(true)
                    }
                    item {
                        tab("Network")
                        content { +"Network" }
                        disabled(true)
                    }
                }
            }
            snippet("Box light", TabsCode.BOX_LIGHT) {
                tabs(box = true, baseClass = "color-scheme--light-300".modifier()) {
                    item {
                        tab("Users")
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab("Containers")
                        content { +"Containers" }
                    }
                    item {
                        tab("Database")
                        content { +"Database" }
                    }
                    item {
                        tab("Server")
                        content { +"Server" }
                    }
                    item {
                        tab("System")
                        content { +"System" }
                        disabled(true)
                    }
                    item {
                        tab("Network")
                        content { +"Network" }
                        disabled(true)
                    }
                }
            }
            snippet("Overflow", TabsCode.OVERFLOW) {
                tabs {
                    item {
                        tab("Users")
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab("Containers")
                        content { +"Containers" }
                    }
                    item {
                        tab("Database")
                        content { +"Database" }
                    }
                    item {
                        tab("Server")
                        content { +"Server" }
                    }
                    item {
                        tab("System")
                        content { +"System" }
                        disabled(true)
                    }
                    item {
                        tab("Network")
                        content { +"Network" }
                        disabled(true)
                    }
                    for (i in 1..10) {
                        item {
                            tab("Tab item $i")
                            content { +"Tab content $i" }
                        }
                    }
                }
            }
            snippet("Vertical", TabsCode.VERTICAL) {
                tabs(vertical = true) {
                    item {
                        tab("Users")
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab("Containers")
                        content { +"Containers" }
                    }
                    item {
                        tab("Database")
                        content { +"Database" }
                    }
                    item {
                        tab("Server")
                        content { +"Server" }
                    }
                    item {
                        tab("System")
                        content { +"System" }
                        disabled(true)
                    }
                    item {
                        tab("Network")
                        content { +"Network" }
                        disabled(true)
                    }
                }
            }
            snippet("Vertical box", TabsCode.VERTICAL_BOX) {
                tabs(vertical = true, box = true) {
                    item {
                        tab("Users")
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab("Containers")
                        content { +"Containers" }
                    }
                    item {
                        tab("Database")
                        content { +"Database" }
                    }
                    item {
                        tab("Server")
                        content { +"Server" }
                    }
                    item {
                        tab("System")
                        content { +"System" }
                        disabled(true)
                    }
                    item {
                        tab("Network")
                        content { +"Network" }
                        disabled(true)
                    }
                }
            }
            snippet("Inset", TabsCode.INSET) {
                tabs(baseClass = classes {
                    +"inset-sm-on-md".modifier()
                    +"inset-lg-on-lg".modifier()
                    +"inset-2xl-on-xl".modifier()
                }) {
                    item {
                        tab("Users")
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab("Containers")
                        content { +"Containers" }
                    }
                    item {
                        tab("Database")
                        content { +"Database" }
                    }
                    item {
                        tab("Server")
                        content { +"Server" }
                    }
                    item {
                        tab("System")
                        content { +"System" }
                        disabled(true)
                    }
                    item {
                        tab("Network")
                        content { +"Network" }
                        disabled(true)
                    }
                }
            }
            snippet("Icons", TabsCode.ICONS) {
                tabs {
                    item {
                        tab {
                            +"Users"
                            icon("users".fas())
                        }
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab {
                            +"Containers"
                            icon("box".fas())
                        }
                        content { +"Containers" }
                    }
                    item {
                        tab {
                            +"Database"
                            icon("database".fas())
                        }
                        content { +"Database" }
                    }
                    item {
                        tab {
                            +"Server"
                            icon("server".fas())
                        }
                        content { +"Server" }
                    }
                    item {
                        tab {
                            +"System"
                            icon("laptop".fas())
                        }
                        content { +"System" }
                        disabled(true)
                    }
                    item {
                        tab {
                            +"Network"
                            icon("project-diagram".fas())
                        }
                        content { +"Network" }
                        disabled(true)
                    }
                }
            }
            snippet("Filled", TabsCode.FILLED) {
                tabs(filled = true) {
                    item {
                        tab("Users")
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab("Containers")
                        content { +"Containers" }
                    }
                    item {
                        tab("Database")
                        content { +"Database" }
                    }
                }
            }
            snippet("Filled", TabsCode.FILLED_ICONS) {
                tabs(filled = true) {
                    item {
                        tab("Users") { icon("users".fas()) }
                        content { +"Users" }
                        selected(true)
                    }
                    item {
                        tab("Containers") { icon("box".fas()) }
                        content { +"Containers" }
                    }
                    item {
                        tab("Database") { icon("database".fas()) }
                        content { +"Database" }
                    }
                }
            }
            snippet("Store", TabsCode.STORE) {
                data class Demo(val id: String, val name: String)

                val demos = storeOf(
                    listOf(
                        Demo("foo", "Foo"),
                        Demo("bar", "Bar")
                    )
                )
                val idProvider: IdProvider<Demo, String> = { Id.build(it.id, "store") }

                tabs {
                    items(demos, idProvider) { demo ->
                        item {
                            tab(demo.name)
                            content { +demo.name }
                        }
                    }
                }
            }
            snippet("Reactive", TabsCode.REACTIVE) {
                val range = 2..20
                val numberOfTabs = storeOf(5)
                val values = storeOf(range.toList())
                val selectedTab = storeOf<Int?>(null)
                val disabledTabs = storeOf<List<Int>>(listOf())
                val idProvider: IdProvider<Int, String> = { Id.build(it.toString(), "reactive") }

                numberOfTabs.data.map { tabs -> (1..tabs).toList() } handledBy values.update

                div(baseClass = classes("grid".layout(), "gutter".modifier())) {
                    inlineStyle("align-items: center;")
                    div(baseClass = classes("grid".layout("item"), "2-col".modifier())) {
                        numberOfTabs.data.renderText()
                        +" tabs"
                    }
                    div(baseClass = classes("grid".layout("item"), "10-col".modifier())) {
                        slider(numberOfTabs, range) {
                            leftActions { decrease() }
                            showTicks()
                            rightActions { increase() }
                        }
                    }
                    div(baseClass = classes("grid".layout("item"), "2-col".modifier())) {
                        +"Selected tab"
                    }
                    div(baseClass = classes("grid".layout("item"), "10-col".modifier())) {
                        toggleGroup(singleSelect = true) {
                            items(values, idProvider, singleSelection = selectedTab) {
                                item { +"#$it" }
                            }
                        }
                    }
                    div(baseClass = classes("grid".layout("item"), "2-col".modifier())) {
                        +"Disabled tabs"
                    }
                    div(baseClass = classes("grid".layout("item"), "10-col".modifier())) {
                        toggleGroup {
                            items(values, idProvider, multiSelection = disabledTabs) {
                                item { +"#$it" }
                            }
                        }
                    }
                    div(baseClass = classes("grid".layout("item"), "12-col".modifier())) {
                        tabs {
                            items(values, idProvider, selectedTab, disabledTabs) { number ->
                                item {
                                    tab("Tab item $number")
                                    content { +"Tab content $number" }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

object TabsCode {

    //language=kotlin
    const val DEFAULT: String = """fun main() {
    render {
        tabs {
            item(id = "users-dft", selected = true) { +"Users" }
            item(id = "containers-dft") { +"Containers" }
            item(id = "database-dft") { +"Database" }
            item(id = "server-dft") { +"Server" }
            item(id = "system-dft", disabled = true) { +"System" }
            item(id = "network-dft", disabled = true) { +"Network" }
        }
        tabContent("users-dft") { +"Users" }
        tabContent("containers-dft") { +"Containers" }
        tabContent("database-dft") { +"Database" }
        tabContent("server-dft") { +"Server" }
        tabContent("system-dft") { +"System" }
        tabContent("network-dft") { +"Network" }
    }
}
"""

    //language=kotlin
    const val BOX: String = """fun main() {
    render {
        tabs(box = true) {
            item(id = "users-box", selected = true) { +"Users" }
            item(id = "containers-box") { +"Containers" }
            item(id = "database-box") { +"Database" }
            item(id = "server-box") { +"Server" }
            item(id = "system-box", disabled = true) { +"System" }
            item(id = "network-box", disabled = true) { +"Network" }
        }
        tabContent("users-box") { +"Users" }
        tabContent("containers-box") { +"Containers" }
        tabContent("database-box") { +"Database" }
        tabContent("server-box") { +"Server" }
        tabContent("system-box") { +"System" }
        tabContent("network-box") { +"Network" }
    }
}
"""

    //language=kotlin
    const val BOX_LIGHT: String = """fun main() {
    render {
        tabs(box = true, baseClass = "color-scheme--light-300".modifier()) {
            item(id = "users-bl", selected = true) { +"Users" }
            item(id = "containers-bl") { +"Containers" }
            item(id = "database-bl") { +"Database" }
            item(id = "server-bl") { +"Server" }
            item(id = "system-bl", disabled = true) { +"System" }
            item(id = "network-bl", disabled = true) { +"Network" }
        }
        tabContent("users-bl") { +"Users" }
        tabContent("containers-bl") { +"Containers" }
        tabContent("database-bl") { +"Database" }
        tabContent("server-bl") { +"Server" }
        tabContent("system-bl") { +"System" }
        tabContent("network-bl") { +"Network" }
    }
}
"""

    //language=kotlin
    const val OVERFLOW: String = """fun main() {
    render {
        tabs(box = true, baseClass = "color-scheme--light-300".modifier()) {
            item(id = "users-ofl", selected = true) { +"Users" }
            item(id = "containers-ofl") { +"Containers" }
            item(id = "database-ofl") { +"Database" }
            item(id = "server-ofl") { +"Server" }
            item(id = "system-ofl", disabled = true) { +"System" }
            item(id = "network-ofl", disabled = true) { +"Network" }
            for (i in 1..10) {
                item(id = "tab-${'$'}i-ofl", title = "Tab item ${'$'}i")
            }
        }
        tabContent("users-ofl") { +"Users" }
        tabContent("containers-ofl") { +"Containers" }
        tabContent("database-ofl") { +"Database" }
        tabContent("server-ofl") { +"Server" }
        tabContent("system-ofl") { +"System" }
        tabContent("network-ofl") { +"Network" }
        for (i in 1..10) {
            tabContent("tab-${'$'}i-ofl") { +"Tab content ${'$'}i" }
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
    const val FILLED_ICONS: String = """fun main() {
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
        tabs(vertical = true) {
            item(id = "users-vert", selected = true) { +"Users" }
            item(id = "containers-vert") { +"Containers" }
            item(id = "database-vert") { +"Database" }
            item(id = "server-vert") { +"Server" }
            item(id = "system-vert", disabled = true) { +"System" }
            item(id = "network-vert", disabled = true) { +"Network" }
        }
        tabContent("users-vert") { +"Users" }
        tabContent("containers-vert") { +"Containers" }
        tabContent("database-vert") { +"Database" }
        tabContent("server-vert") { +"Server" }
        tabContent("system-vert") { +"System" }
        tabContent("network-vert") { +"Network" }
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
    const val STORE: String = """fun main() {
    render {
    
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        val range = 2..20
        val numberOfTabs = storeOf(5)
        val values = storeOf(range.toList())
        val selectedTab = storeOf<Int?>(null)
        val disabledTabs = storeOf<List<Int>>(listOf())
        val idProvider: IdProvider<Int, String> = { Id.build(it.toString(), "reactive") }

        numberOfTabs.data.map { tabs -> (1..tabs).toList() } handledBy values.update

        div(baseClass = classes("grid".layout(), "gutter".modifier())) {
            inlineStyle("align-items: center;")
            div(baseClass = classes("grid".layout("item"), "2-col".modifier())) {
                numberOfTabs.data.renderText()
                +" tabs"
            }
            div(baseClass = classes("grid".layout("item"), "10-col".modifier())) {
                slider(numberOfTabs, range) {
                    leftActions { decrease() }
                    showTicks()
                    rightActions { increase() }
                }
            }
            div(baseClass = classes("grid".layout("item"), "2-col".modifier())) {
                +"Selected tab"
            }
            div(baseClass = classes("grid".layout("item"), "10-col".modifier())) {
                toggleGroup(singleSelect = true) {
                    items(values, idProvider, singleSelection = selectedTab) {
                        item { +"#${'$'}it" }
                    }
                }
            }
            div(baseClass = classes("grid".layout("item"), "2-col".modifier())) {
                +"Disabled tabs"
            }
            div(baseClass = classes("grid".layout("item"), "10-col".modifier())) {
                toggleGroup {
                    items(values, idProvider, multiSelection = disabledTabs) {
                        item { +"#${'$'}it" }
                    }
                }
            }
            div(baseClass = classes("grid".layout("item"), "12-col".modifier())) {
                tabs {
                    items(values, idProvider, selectedTab, disabledTabs) { number ->
                        item { +"Tab item ${'$'}number" }
                    }
                }
                values.data.renderEach(idProvider) { number ->
                    tabContent(number, idProvider) {
                        +"Tab content ${'$'}number"
                    }
                }
            }
        }
    }
}
"""
}
