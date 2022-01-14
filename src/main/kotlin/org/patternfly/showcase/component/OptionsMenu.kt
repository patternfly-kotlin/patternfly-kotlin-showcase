@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.patternfly.Align.RIGHT
import org.patternfly.ButtonVariant.plain
import org.patternfly.fas
import org.patternfly.optionsMenu
import org.patternfly.pageSection
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
object OptionsMenuComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Options menu",
            text = "An options menu is similar to a dropdown, but provides a way to select among a set of optional settings rather than trigger an action.",
            designGuidelines = "https://www.patternfly.org/v4/components/options-menu/design-guidelines"
        )
        pageSection {
            snippet("Single option", OptionsMenuCode.SINGLE_OPTION) {
                val options = listOf("Option 1", "Option 2", "Option 3")
                val selection = storeOf(options[0])
                optionsMenu {
                    toggle { text("Options menu") }
                    options.forEach { option ->
                        item(option) {
                            selected(selection.data.map { it == option })
                            events {
                                clicks.map { option } handledBy selection.update
                            }
                        }
                    }
                }
            }
            snippet("Disabled", OptionsMenuCode.DISABLED) {
                val options = listOf("Option 1", "Option 2", "Option 3")
                val selection = storeOf(options[0])
                optionsMenu {
                    disabled(true)
                    toggle { text("Disabled options menu") }
                    options.forEach { option ->
                        item(option) {
                            selected(selection.data.map { it == option })
                            events {
                                clicks.map { option } handledBy selection.update
                            }
                        }
                    }
                }
            }
            snippet("Multiple options", "n/a") {
                val columns = listOf("Name", "Date", "Size")
                val sortColumn = storeOf(columns[0])
                val sortAscending = storeOf(true)

                optionsMenu {
                    toggle { text("Sort By") }
                    columns.forEach { name ->
                        item(name) {
                            selected(sortColumn.data.map { it == name })
                            events {
                                clicks.map { name } handledBy sortColumn.update
                            }
                        }
                    }
                    separator()
                    item("Ascending") {
                        selected(sortAscending.data)
                        events {
                            clicks.map { true } handledBy sortAscending.update
                        }
                    }
                    item("Descending") {
                        selected(sortAscending.data.map { !it })
                        events {
                            clicks.map { false } handledBy sortAscending.update
                        }
                    }
                }
            }
            snippet("Grouped", "n/a") {
                val groups = mapOf(
                    "" to listOf("Option 1", "Option 2", "Option 3"),
                    "Named Group" to listOf("Option 4", "Option 5"),
                )
                val selection = storeOf(groups.getValue("Named Group")[0])
                optionsMenu {
                    toggle { text("Options menu") }
                    groups.forEach { (group, items) ->
                        group(group) {
                            items.forEach { item ->
                                item(item) {
                                    selected(selection.data.map { it == item })
                                    events {
                                        clicks.map { item } handledBy selection.update
                                    }
                                }
                            }
                        }
                    }
                }
            }
            snippet("Plain", OptionsMenuCode.ICON) {
                val options = listOf("Option 1", "Option 2", "Option 3")
                val selection = storeOf(options[0])
                optionsMenu {
                    toggle { icon("sort-amount-down".fas()) }
                    options.forEach { option ->
                        item(option) {
                            selected(selection.data.map { it == option })
                            events {
                                clicks.map { option } handledBy selection.update
                            }
                        }
                    }
                }
            }
            snippet("Align top", OptionsMenuCode.UP) {
                val options = listOf("Option 1", "Option 2", "Option 3")
                val selection = storeOf(options[0])
                optionsMenu(up = true) {
                    toggle { text("Options menu") }
                    options.forEach { option ->
                        item(option) {
                            selected(selection.data.map { it == option })
                            events {
                                clicks.map { option } handledBy selection.update
                            }
                        }
                    }
                }
            }
            snippet("Align right", OptionsMenuCode.RIGHT) {
                val options = listOf("Right option 1", "Right option 2", "Right option 3")
                val selection = storeOf(options[0])
                optionsMenu(align = RIGHT) {
                    toggle { text("Options menu") }
                    options.forEach { option ->
                        item(option) {
                            selected(selection.data.map { it == option })
                            events {
                                clicks.map { option } handledBy selection.update
                            }
                        }
                    }
                }
            }
            snippet("Plain with text", OptionsMenuCode.PLAIN) {
                val options = listOf("Option 1", "Option 2", "Option 3")
                val selection = storeOf(options[0])
                optionsMenu {
                    toggle { text("Options menu", plain) }
                    options.forEach { option ->
                        item(option) {
                            selected(selection.data.map { it == option })
                            events {
                                clicks.map { option } handledBy selection.update
                            }
                        }
                    }
                }
            }
            snippet("Store", "n/a") {
                data class Demo(val id: String, val name: String)

                val demos = listOf(
                    Demo("foo", "Foo"),
                    Demo("bar", "Bar")
                )
                val store = storeOf(demos)
                val selection = storeOf(demos[0])

                optionsMenu {
                    toggle { text("Choose one") }
                    items(store, { it.id }) { demo ->
                        item(demo.name) {
                            selected(selection.data.map { it == demo })
                            events {
                                clicks.map { demo } handledBy selection.update
                            }
                        }
                    }
                }
            }
        }
    }
}

object OptionsMenuCode {

    //language=kotlin
    const val SINGLE_OPTION: String = """fun main() {
    render {
        optionsMenu<String> {
            textToggle { +"Options menu" }
            items {
                item("Option 1") { selected = true }
                item("Option 2")
                item("Option 3")
            }
        }
    }
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        optionsMenu<String> {
            disabled(true)
            textToggle { +"Options menu" }
            items {
                item("Option 1") { selected = true }
                item("Option 2")
                item("Option 3")
            }
        }
    }
}
"""

    //language=kotlin
    const val RIGHT: String = """fun main() {
    render {
        optionsMenu<String>(align = RIGHT) {
            textToggle { +"Options menu" }
            items {
                item("Right option 1") { selected = true }
                item("Right option 2")
                item("Right option 3")
            }
        }
    }
}
"""

    //language=kotlin
    const val UP: String = """fun main() {
    render {
        optionsMenu<String>(up = true) {
            textToggle { +"Options menu" }
            items {
                item("Option 1") { selected = true }
                item("Option 2")
                item("Option 3")
            }
        }
    }
}
"""

    //language=kotlin
    const val ICON: String = """fun main() {
    render {
        optionsMenu<String> {
            iconToggle { icon("sort-amount-down".fas()) }
            items {
                item("Option 1") { selected = true }
                item("Option 2")
                item("Option 3")
            }
        }
    }
}
"""

    //language=kotlin
    const val PLAIN: String = """fun main() {
    render {
        optionsMenu<String> {
            textToggle(plain = true) { +"Options menu" }
            items {
                item("Option 1") { selected = true }
                item("Option 2")
                item("Option 3")
            }
        }
    }
}
"""

    //language=kotlin
    const val MULTIPLE_OPTIONS: String = """fun main() {
    render {
        optionsMenu<String>(SINGLE, grouped = true, baseClass = "mr-md".util()) {
            textToggle { +"Single select" }
            groups {
                group {
                    item("Item 1.1")
                    item("Item 1.2")
                    item("Item 1.3")
                }
                separator()
                group("Second group") {
                    item("Item 2.1")
                    item("Item 2.2")
                }
                separator()
                group("Third group") {
                    item("Item 3")
                }
            }
        }
        optionsMenu<String>(SINGLE_PER_GROUP, grouped = true, baseClass = "mr-md".util()) {
            textToggle { +"Single per group" }
            groups {
                group {
                    item("Item 1.1")
                    item("Item 1.2")
                    item("Item 1.3")
                }
                separator()
                group("Second group") {
                    item("Item 2.1")
                    item("Item 2.2")
                }
                separator()
                group("Third group") {
                    item("Item 3")
                }
            }
        }
        optionsMenu<String>(MULTIPLE, grouped = true) {
            textToggle { +"Multi select" }
            groups {
                group {
                    item("Item 1.1")
                    item("Item 1.2")
                    item("Item 1.3")
                }
                separator()
                group("Second group") {
                    item("Item 2.1")
                    item("Item 2.2")
                }
                separator()
                group("Third group") {
                    item("Item 3")
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        lateinit var text: Input
        lateinit var enabled: Switch

        fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

        fun registerEvents(optionsMenu: OptionsMenu<String>, name: String) {
            with(optionsMenu) {
                store.selection.unwrap()
                    .drop(1)
                    .handledBy(notification {
                        severity(Severity.INFO)
                        title("${'$'}name: Selection ${'$'}it")
                    })
                expanded.collapsed handledBy notification { 
                    severity(Severity.INFO)
                    title("${'$'}name: Options menu collapsed")
                }
                expanded.expanded handledBy notification { 
                    severity(Severity.INFO)
                    title("${'$'}name: Options menu expanded")
                }
            }
        }

        fun items(store: OptionsMenuStore<String>) {
            store.updateItems { 
                    item("Option 1") { selected = true }
                    item("Option 2")
                    item("Option 3")
            }
        }

        div(baseClass = classes {
            +"flex".layout()
            +"align-items-center".modifier()
            +"mb-sm".util()
        }) {
            text = input(baseClass = classes("form-control".component(), "w-50".util())) {
                type("text")
                value("Options")
                placeholder("Options menu text")
            }
            enabled = switch("ml-md".util()) {
                label("Enabled")
                labelOff("Disabled")
                input.checked(true)
            }
        }
        optionsMenu<String>(itemSelection = SINGLE, baseClass = "mt-sm".util()) {
            disabled(enabled.input.changes.states().map { !it })
            textToggle {
                text.keyups.map { currentValue(it) }.asText()
            }
            items(store)
            registerEvents(this, "Text")
        }
        optionsMenu<String>(
            itemSelection = SINGLE_PER_GROUP,
            grouped = true,
            baseClass = "ml-sm".util()
        ) {
            disabled(enabled.input.changes.states().map { !it })
            iconToggle {
                icon("sort-amount-down".fas())
            }
            groups {
                group {
                    item("Option 1.1") { selected = true }
                    item("Option 1.2")
                }
                separator()
                group("Group 2") {
                    item("Option 2.1")
                    item("Option 2.2")
                }
                separator()
                group("Group 3") {
                    item("Option 3.1")
                    item("Option 3.2")
                }
            }
            registerEvents(this, "Icon")
        }
        optionsMenu<String>(itemSelection = MULTIPLE, baseClass = "ml-sm".util()) {
            disabled(enabled.input.changes.states().map { !it })
            textToggle(plain = true) {
                text.keyups.map { currentValue(it) }.asText()
            }
            items(store)
            registerEvents(this, "Plain")
        }

        MainScope().launch {
            delay(EVENT_DELAY)
            text.domNode.dispatchEvent(Event(Events.keyup.name))
        }
    }
}
"""
}
