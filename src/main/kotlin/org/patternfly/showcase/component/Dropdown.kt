@file:Suppress("DuplicatedCode", "EXPERIMENTAL_IS_NOT_ENABLED")

package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import org.patternfly.Align.RIGHT
import org.patternfly.ButtonVariant.primary
import org.patternfly.Severity.INFO
import org.patternfly.dropdown
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.notification
import org.patternfly.pageSection
import org.patternfly.util
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
object DropdownComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Dropdown",
            text = "A dropdown presents a menu of actions or links in a constrained space that will trigger a process or navigate to a new location.",
            designGuidelines = "https://www.patternfly.org/v4/components/dropdown/design-guidelines"
        )
        pageSection {
            snippet("Basic", DropdownCode.BASIC) {
                dropdown {
                    toggle { text("Dropdown") }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action") {
                        events {
                            clicks handledBy notification(INFO, "Click!")
                        }
                    }
                    events {
                        excos handledBy notification(INFO) { expanded ->
                            +if (expanded) "Expanded" else "Collapsed"
                        }
                    }
                }
            }
            snippet("Disabled", DropdownCode.DISABLED) {
                dropdown {
                    disabled(true)
                    toggle { text("Dropdown") }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action")
                }
            }
            snippet("Primary toggle", DropdownCode.PRIMARY) {
                dropdown {
                    toggle {
                        text(variant = primary) {
                            +"Dropdown"
                        }
                    }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action")
                }
            }
            snippet("Position right", DropdownCode.RIGHT) {
                dropdown(align = RIGHT) {
                    toggle { text("Dropdown") }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action")
                }
            }
            snippet("Direction up", DropdownCode.UP) {
                dropdown(up = true) {
                    toggle { text("Dropdown") }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action")
                }
            }
            snippet("With kebab", DropdownCode.KEBAB) {
                dropdown {
                    toggle { kebab() }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action")
                }
            }
            snippet("Icon only", DropdownCode.ICON) {
                dropdown {
                    toggle { icon("th".fas()) }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action")
                }
            }
            snippet("Icons and descriptions", DropdownCode.DESCRIPTION) {
                dropdown {
                    toggle { text("Dropdown") }
                    item("Action 1") {
                        icon("cog".fas())
                    }
                    item("Action 2") {
                        description("This is a description")
                    }
                    separator()
                    item("Disabled action 1") {
                        disabled(true)
                        icon("cog".fas())
                    }
                    item("Disabled action 2") {
                        disabled(true)
                        description("This is a description")
                    }
                    item("Disabled action 3") {
                        disabled(true)
                        icon("cog".fas())
                        description("This is a description")
                    }
                }
            }
            snippet("Split button", DropdownCode.CHECKBOX_TOGGLE) {
                dropdown {
                    toggle { checkbox() }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action")
                }
                dropdown(baseClass = "ml-sm".util()) {
                    toggle { checkbox("10 selected") }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action")
                }
            }
            snippet("Split button action", DropdownCode.ACTION_TOGGLE) {
                dropdown {
                    toggle {
                        action { +"Action" }
                    }
                    item("Action")
                    item("Disabled Action") {
                        disabled(true)
                    }
                    separator()
                    item("Separated Action")
                }
                dropdown(baseClass = "ml-sm".util()) {
                    toggle {
                        action { icon("cog".fas()) }
                    }
                    item("Action") {
                        icon("cog".fas())
                    }
                    item("Disabled Action") {
                        disabled(true)
                        icon("bell".fas())
                    }
                    separator()
                    item("Separated Action") {
                        icon("cubes".fas())
                    }
                }
            }
            snippet("Groups", DropdownCode.GROUPS) {
                dropdown {
                    toggle { text("Dropdown") }
                    item("Action 1")
                    item("Action 2")
                    separator()
                    group("Group 1") {
                        item("Action 1")
                        item("Action 2")
                    }
                    separator()
                    group("Group 2") {
                        item("Action 1")
                        item("Action 2")
                    }
                    separator()
                    item("Final action")
                }
            }
            snippet("Store", "n/a") {
                data class Color(val name: String, val code: String = "", val colors: List<Color> = emptyList())

                val store = storeOf(
                    listOf(
                        Color("Blue", "blue-100"),
                        Color("Green", "green-100"),
                        Color("Purple", "purple-100"),
                        Color("Gold", "gold-100"),
                        Color("Orange", "orange-100"),
                        Color("Red", "red-100"),
                        Color(
                            "Light",
                            colors = listOf(
                                Color("Light Blue", "light-blue-100"),
                                Color("Light Green", "light-green-100")
                            )
                        ),
                    )
                )
                dropdown(grouped = true) {
                    toggle { text("Colors") }
                    items(store) { color ->
                        if (color.colors.isEmpty()) {
                            item {
                                content {
                                    +color.name
                                    inlineStyle("background-color: var(--pf-global--palette--${color.code})")
                                }
                            }
                        } else {
                            group(color.name) {
                                color.colors.forEach { subcolor ->
                                    item {
                                        content {
                                            +subcolor.name
                                            inlineStyle("background-color: var(--pf-global--palette--${subcolor.code})")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
/*
            snippet("Reactive", DropdownCode.REACTIVE) {
                lateinit var text: Input
                lateinit var enabled: Switch

                fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

                fun registerEvents(dropdown: Dropdown<String>, name: String) {
                    with(dropdown) {
                        store.clicked.unwrap() handledBy notification {
                            severity(INFO)
                            title("$name: Clicked on $it")
                        }
                        expanded.expanded handledBy notification { expanded ->
                            severity(INFO)
                            title("$name: Expanded state: $expanded.")
                        }
                    }
                }

                fun items(store: DropdownStore<String>) {
                    store.updateItems {
                        item("Action")
                        item("Disabled Action") {
                            disabled = true
                        }
                        separator()
                        item("Separated Action")
                    }
                }

                div(baseClass = classes {
                    +"flex".layout()
                    +"align-items-center".modifier()
                    +"mb-sm".util()
                }) {
                    text = input(baseClass = classes("form-control".component(), "w-50".util())) {
                        type("text")
                        value("Dropdown")
                        placeholder("Dropdown text")
                    }
                    enabled = switch("ml-md".util()) {
                        label("Enabled")
                        labelOff("Disabled")
                        input.checked(true)
                    }
                }
                dropdown<String>(baseClass = "mt-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    textToggle {
                        text.keyups.map { currentValue(it) }.asText()
                    }
                    registerEvents(this, "Text toggle")
                    items(store)
                }
                dropdown<String>(baseClass = "ml-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    kebabToggle()
                    registerEvents(this, "Kebab toggle")
                    items(store)
                }
                dropdown<String>(baseClass = "ml-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    iconToggle { icon("cog".fas()) }
                    registerEvents(this, "Icon toggle")
                    items(store)
                }
                br { }
                dropdown<String>(baseClass = "mt-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    checkboxToggle {
                        text {
                            this@dropdown.store.singleSelection.unwrap().asText()
                        }
                        checkbox {
                            changes.states() handledBy notification {
                                severity(INFO)
                                title("Checkbox toggle: Checked: $it")
                            }
                            triState(this@dropdown.store.singleSelection.unwrap().map {
                                when (it) {
                                    "Select none" -> TriState.OFF
                                    "Select visible" -> TriState.INDETERMINATE
                                    "Select all" -> TriState.ON
                                    else -> TriState.OFF
                                }
                            })
                        }
                    }
                    items {
                        item("Select none")
                        item("Select visible")
                        item("Select all")
                    }
                    registerEvents(this, "Checkbox toggle")
                }
                br { }
                dropdown<String>(baseClass = "mt-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    actionToggle {
                        text.keyups.map { currentValue(it) }.asText()
                    } handledBy notification(INFO, "Action toggle (text): Clicked")
                    registerEvents(this, "Action toggle (text)")
                    items(store)
                }
                dropdown<String>(baseClass = "ml-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    actionToggle {
                        icon("cog".fas())
                    } handledBy notification(INFO, "Action toggle (icon): Clicked")
                    registerEvents(this, "Action toggle (icon)")
                    items(store)
                }

                MainScope().launch {
                    delay(EVENT_DELAY)
                    text.domNode.dispatchEvent(Event(Events.keyup.name))
                }
            }
*/
        }
    }
}

object DropdownCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        dropdown<String> {
            textToggle { +"Dropdown" }
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
    }
}
"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        dropdown<String> {
            disabled(true)
            textToggle { +"Dropdown" }
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
    }
}
"""

    //language=kotlin
    const val PRIMARY: String = """fun main() {
    render {
        dropdown<String> {
            textToggle(primary) { +"Dropdown" }
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
    }
}
"""

    //language=kotlin
    const val RIGHT: String = """fun main() {
    render {
        dropdown<String>(align = RIGHT) {
            textToggle { +"Dropdown" }
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
    }
}
"""

    //language=kotlin
    const val UP: String = """fun main() {
    render {
        dropdown<String>(up = true) {
            textToggle { +"Dropdown" }
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
    }
}
"""

    //language=kotlin
    const val KEBAB: String = """fun main() {
    render {
        dropdown<String> {
            kebabToggle()
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
    }
}
"""

    //language=kotlin
    const val ICON: String = """fun main() {
    render {
        dropdown<String> {
            iconToggle { icon("th".fas()) }
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
    }
}
"""

    //language=kotlin
    const val DESCRIPTION: String = """fun main() {
    render {
        dropdown<String> {
            textToggle { +"Dropdown" }
            items {
                item("Action 1") {
                    icon = { icon("cog".fas()) }
                }
                item("Action 2") {
                    description = "This is a description"
                }
                item("Action 3") {
                    icon = { img { src("./pf-logo-small.svg") } }
                    description = "This is a description"
                }
                separator()
                item("Disabled action 1") {
                    disabled = true
                    icon = { icon("cog".fas()) }
                }
                item("Disabled action 2") {
                    disabled = true
                    description = "This is a description"
                }
                item("Disabled action 3") {
                    disabled = true
                    icon = { icon("cog".fas()) }
                    description = "This is a description"
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val CHECKBOX_TOGGLE: String = """fun main() {
    render {
        dropdown<String> {
            checkboxToggle()
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            checkboxToggle { text { +"10 selected" } }
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
    }
}
"""

    //language=kotlin
    const val ACTION_TOGGLE: String = """fun main() {
    render {
        dropdown<String> {
            actionToggle { +"Action" }
            items {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            actionToggle { icon("cog".fas()) }
            items {
                item("Action") {
                    icon = { icon("cog".fas()) }
                }
                item("Disabled Action") {
                    disabled = true
                    icon = { icon("bell".fas()) }
                }
                separator()
                item("Separated Action") {
                    icon = { icon("cubes".fas()) }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val GROUPS: String = """fun main() {
    render {
        dropdown<String>(grouped = true) {
            textToggle { +"Dropdown" }
            groups {
                group {
                    item("Action 1")
                    item("Action 2")
                }
                separator()
                group("Group 1") {
                    item("Action 1")
                    item("Action 2")
                }
                separator()
                group("Group 2") {
                    item("Action 1")
                    item("Action 2")
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

        fun registerEvents(dropdown: Dropdown<String>, name: String) {
            with(dropdown) {
                store.clicked.unwrap() handledBy notification {
                    severity(Severity.INFO)
                    title("${'$'}name: Clicked on ${'$'}it")
                }
                expanded.expanded handledBy notification { expanded ->
                    severity(Severity.INFO)
                    title("${'$'}name: Expanded state: ${'$'}expanded.")
                }
            }
        }

        fun items(store: DropdownStore<String>) {
            store.updateItems {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }

        div(baseClass = classes {
            +"flex".layout()
            +"align-items-center".modifier()
            +"mb-sm".util()
        }) {
            text = input(baseClass = classes("form-control".component(), "w-50".util())) {
                type("text")
                value("Dropdown")
                placeholder("Dropdown text")
            }
            enabled = switch("ml-md".util()) {
                label("Enabled")
                labelOff("Disabled")
                input.checked(true)
            }
        }
        dropdown<String>(baseClass = "mt-sm".util()) {
            disabled(enabled.input.changes.states().map { !it })
            textToggle {
                text.keyups.map { currentValue(it) }.asText()
            }
            registerEvents(this, "Text toggle")
            items(store)
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            disabled(enabled.input.changes.states().map { !it })
            kebabToggle()
            registerEvents(this, "Kebab toggle")
            items(store)
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            disabled(enabled.input.changes.states().map { !it })
            iconToggle { icon("cog".fas()) }
            registerEvents(this, "Icon toggle")
            items(store)
        }
        br {}
        dropdown<String>(baseClass = "mt-sm".util()) {
            disabled(enabled.input.changes.states().map { !it })
            checkboxToggle {
                text {
                    this@dropdown.store.singleSelection.unwrap().asText()
                }
                checkbox {
                    changes.states() handledBy notification {
                        severity(Severity.INFO)
                        title("Checkbox toggle: Checked: ${'$'}it")
                    }
                    triState(this@dropdown.store.singleSelection.unwrap().map {
                        when (it) {
                            "Select none" -> TriState.OFF
                            "Select visible" -> TriState.INDETERMINATE
                            "Select all" -> TriState.ON
                            else -> TriState.OFF
                        }
                    })
                }
            }
            items {
                item("Select none")
                item("Select visible")
                item("Select all")
            }
            registerEvents(this, "Checkbox toggle")
        }
        br {}
        dropdown<String>(baseClass = "mt-sm".util()) {
            disabled(enabled.input.changes.states().map { !it })
            actionToggle {
                text.keyups.map { currentValue(it) }.asText()
            } handledBy Notification.info("Action toggle (text): Clicked")
            registerEvents(this, "Action toggle (text)")
            items(store)
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            disabled(enabled.input.changes.states().map { !it })
            actionToggle {
                icon("cog".fas())
            } handledBy Notification.info("Action toggle (icon): Clicked")
            registerEvents(this, "Action toggle (icon)")
            items(store)
        }

        MainScope().launch {
            delay(EVENT_DELAY)
            text.domNode.dispatchEvent(Event(Events.keyup.name))
        }
    }
}
"""
}
