@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.valuesAsNumber
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Elements
import org.patternfly.TabItem
import org.patternfly.TabStore
import org.patternfly.classes
import org.patternfly.elements
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfContent
import org.patternfly.pfIcon
import org.patternfly.pfSection
import org.patternfly.pfTabItem
import org.patternfly.pfTabs
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
class TabsComponent : Elements {
    override val elements = elements {
        intro(
            title = "Tabs",
            key = "Tabs",
            text = " are used to present a set on tabs for organizing content on a page."
        )
        pfSection {
            pfContent {
                h2 { +"Examples" }
            }
            snippet("Default", TabsCode.DEFAULT) {
                pfTabs<String> {
                    pfTabItem("Users") { +"Users" }
                    pfTabItem("Containers") { +"Containers" }
                    pfTabItem("Database") { +"Database" }
                    pfTabItem("Server") { +"Server" }
                    pfTabItem("System") { +"System" }
                    pfTabItem("Network") { +"Network" }
                }
            }
            snippet("Box", TabsCode.BOX) {
                pfTabs<String>(box = true) {
                    pfTabItem("Users") { +"Users" }
                    pfTabItem("Containers") { +"Containers" }
                    pfTabItem("Database") { +"Database" }
                    pfTabItem("Server") { +"Server" }
                    pfTabItem("System") { +"System" }
                    pfTabItem("Network") { +"Network" }
                }
            }
            snippet("Box light", TabsCode.BOX_LIGHT) {
                pfTabs<String>(box = true, baseClass = "color-scheme--light-300".modifier()) {
                    contentDisplay = {
                        {
                            domNode.style.backgroundColor = "var(--pf-global--BackgroundColor--light-300)"
                        }
                    }
                    pfTabItem("Users") {
                        +"Users"
                    }
                    pfTabItem("Containers") {
                        +"Containers"
                    }
                    pfTabItem("Database") {
                        +"Database"
                    }
                    pfTabItem("Server") {
                        +"Server"
                    }
                    pfTabItem("System") {
                        +"System"
                    }
                    pfTabItem("Network") {
                        +"Network"
                    }
                }
            }
            snippet("Overflow", TabsCode.OVERFLOW) {
                pfTabs<String> {
                    pfTabItem("Users") { +"Users" }
                    pfTabItem("Containers") { +"Containers" }
                    pfTabItem("Database") { +"Database" }
                    pfTabItem("Server") { +"Server" }
                    pfTabItem("System") { +"System" }
                    pfTabItem("Network") { +"Network" }
                    pfTabItem("Tab item 7") { +"Tab content 7" }
                    pfTabItem("Tab item 8") { +"Tab content 8" }
                    pfTabItem("Tab item 9") { +"Tab content 9" }
                    pfTabItem("Tab item 10") { +"Tab content 10" }
                    pfTabItem("Tab item 11") { +"Tab content 11" }
                }
            }
            snippet("Inset", TabsCode.INSET) {
                pfTabs<String>(baseClass = classes {
                    +"inset-sm-on-md".modifier()
                    +"inset-lg-on-lg".modifier()
                    +"inset-2xl-on-xl".modifier()
                }) {
                    pfTabItem("Users") { +"Users" }
                    pfTabItem("Containers") { +"Containers" }
                    pfTabItem("Database") { +"Database" }
                    pfTabItem("Server") { +"Server" }
                    pfTabItem("System") { +"System" }
                    pfTabItem("Network") { +"Network" }
                }
            }
            snippet("Filled", TabsCode.FILLED) {
                pfTabs<String>(filled = true) {
                    pfTabItem("Users") { +"Users" }
                    pfTabItem("Containers") { +"Containers" }
                    pfTabItem("Database") { +"Database" }
                }
            }
            snippet("Vertical", TabsCode.VERTICAL) {
                pfTabs<String>(vertical = true) {
                    pfTabItem("Users") { +"Users" }
                    pfTabItem("Containers") { +"Containers" }
                    pfTabItem("Database") { +"Database" }
                    pfTabItem("Server") { +"Server" }
                    pfTabItem("System") { +"System" }
                    pfTabItem("Network") { +"Network" }
                }
            }
            snippet("Vertical box", TabsCode.VERTICAL_BOX) {
                pfTabs<String>(vertical = true, box = true) {
                    pfTabItem("Users") { +"Users" }
                    pfTabItem("Containers") { +"Containers" }
                    pfTabItem("Database") { +"Database" }
                    pfTabItem("Server") { +"Server" }
                    pfTabItem("System") { +"System" }
                    pfTabItem("Network") { +"Network" }
                }
            }
            snippet("Icons", TabsCode.ICONS) {
                pfTabs<String> {
                    pfTabItem("Users", icon = { pfIcon("users".fas()) }) { +"Users" }
                    pfTabItem("Containers", icon = { pfIcon("box".fas()) }) { +"Containers" }
                    pfTabItem("Database", icon = { pfIcon("database".fas()) }) { +"Database" }
                    pfTabItem("Server", icon = { pfIcon("server".fas()) }) { +"Server" }
                    pfTabItem("System", icon = { pfIcon("laptop".fas()) }) { +"System" }
                    pfTabItem("Network", icon = { pfIcon("project-diagram".fas()) }) { +"Network" }
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
                    label(`for` = "range") { +"Tabs: " }
                    range = input(id = "range", baseClass = "w-50".util()) {
                        type = const("range")
                        min = const("1")
                        max = const("12")
                        value = const("5")
                    }
                    span {
                        range.inputs.events
                            .map { it.target.unsafeCast<HTMLInputElement>().value }
                            .bind()
                    }
                }
                pfTabs(store) {
                    itemDisplay = {
                        { +"Tab item $it" }
                    }
                    contentDisplay = {
                        { +"Content $it" }
                    }
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

internal object TabsCode {

    //language=kotlin
    const val DEFAULT: String = """fun main() {
    render {
        pfTabs<String> {
            pfTabItem("Users") { +"Users" }
            pfTabItem("Containers") { +"Containers" }
            pfTabItem("Database") { +"Database" }
            pfTabItem("Server") { +"Server" }
            pfTabItem("System") { +"System" }
            pfTabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val BOX: String = """fun main() {
    render {
        pfTabs<String>(box = true) {
            pfTabItem("Users") { +"Users" }
            pfTabItem("Containers") { +"Containers" }
            pfTabItem("Database") { +"Database" }
            pfTabItem("Server") { +"Server" }
            pfTabItem("System") { +"System" }
            pfTabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val BOX_LIGHT: String = """fun main() {
    render {
        pfTabs<String>(box = true, baseClass = "color-scheme--light-300".modifier()) {
            contentDisplay = {
                {
                    domNode.style.backgroundColor = "var(--pf-global--BackgroundColor--light-300)"
                }
            }
            pfTabItem("Users") {
                +"Users"
            }
            pfTabItem("Containers") {
                +"Containers"
            }
            pfTabItem("Database") {
                +"Database"
            }
            pfTabItem("Server") {
                +"Server"
            }
            pfTabItem("System") {
                +"System"
            }
            pfTabItem("Network") {
                +"Network"
            }
        }
    }
}
"""

    //language=kotlin
    const val OVERFLOW: String = """fun main() {
    render {
        pfTabs<String> {
            pfTabItem("Users") { +"Users" }
            pfTabItem("Containers") { +"Containers" }
            pfTabItem("Database") { +"Database" }
            pfTabItem("Server") { +"Server" }
            pfTabItem("System") { +"System" }
            pfTabItem("Network") { +"Network" }
            pfTabItem("Tab item 7") { +"Tab content 7" }
            pfTabItem("Tab item 8") { +"Tab content 8" }
            pfTabItem("Tab item 9") { +"Tab content 9" }
            pfTabItem("Tab item 10") { +"Tab content 10" }
            pfTabItem("Tab item 11") { +"Tab content 11" }
        }
    }
}
"""

    //language=kotlin
    const val INSET: String = """fun main() {
    render {
        pfTabs<String>(baseClass = classes {
            +"inset-sm-on-md".modifier()
            +"inset-lg-on-lg".modifier()
            +"inset-2xl-on-xl".modifier()
        }) {
            pfTabItem("Users") { +"Users" }
            pfTabItem("Containers") { +"Containers" }
            pfTabItem("Database") { +"Database" }
            pfTabItem("Server") { +"Server" }
            pfTabItem("System") { +"System" }
            pfTabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val FILLED: String = """fun main() {
    render {
        pfTabs<String>(filled = true) {
            pfTabItem("Users") { +"Users" }
            pfTabItem("Containers") { +"Containers" }
            pfTabItem("Database") { +"Database" }
        }
    }
}
"""

    //language=kotlin
    const val VERTICAL: String = """fun main() {
    render {
        pfTabs<String>(vertical = true) {
            pfTabItem("Users") { +"Users" }
            pfTabItem("Containers") { +"Containers" }
            pfTabItem("Database") { +"Database" }
            pfTabItem("Server") { +"Server" }
            pfTabItem("System") { +"System" }
            pfTabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val VERTICAL_BOX: String = """fun main() {
    render {
        pfTabs<String>(vertical = true, box = true) {
            pfTabItem("Users") { +"Users" }
            pfTabItem("Containers") { +"Containers" }
            pfTabItem("Database") { +"Database" }
            pfTabItem("Server") { +"Server" }
            pfTabItem("System") { +"System" }
            pfTabItem("Network") { +"Network" }
        }
    }
}
"""

    //language=kotlin
    const val ICONS: String = """fun main() {
    render {
        pfTabs<String> {
            pfTabItem("Users", icon = { pfIcon("users".fas()) }) { +"Users" }
            pfTabItem("Containers", icon = { pfIcon("box".fas()) }) { +"Containers" }
            pfTabItem("Database", icon = { pfIcon("database".fas()) }) { +"Database" }
            pfTabItem("Server", icon = { pfIcon("server".fas()) }) { +"Server" }
            pfTabItem("System", icon = { pfIcon("laptop".fas()) }) { +"System" }
            pfTabItem("Network", icon = { pfIcon("project-diagram".fas()) }) { +"Network" }
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
            label(`for` = "range") { +"Tabs: " }
            range = input(id = "range", baseClass = "w-50".util()) {
                type = const("range")
                min = const("1")
                max = const("12")
                value = const("5")
            }
            span {
                range.inputs.events
                    .map { it.target.unsafeCast<HTMLInputElement>().value }
                    .bind()
            }
        }
        pfTabs(store) {
            itemDisplay = {
                { +"Tab item ${'$'}it" }
            }
            contentDisplay = {
                { +"Content ${'$'}it" }
            }
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
"""
}
