@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.states
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Align.RIGHT
import org.patternfly.ButtonVariation.primary
import org.patternfly.Dropdown
import org.patternfly.Entry
import org.patternfly.Notification
import org.patternfly.Switch
import org.patternfly.TriState
import org.patternfly.actionToggle
import org.patternfly.checkboxToggle
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.dropdown
import org.patternfly.fas
import org.patternfly.group
import org.patternfly.groups
import org.patternfly.icon
import org.patternfly.iconToggle
import org.patternfly.item
import org.patternfly.items
import org.patternfly.kebabToggle
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.separator
import org.patternfly.showcase.EVENT_DELAY
import org.patternfly.switch
import org.patternfly.textToggle
import org.patternfly.triState
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalTime::class)
object DropdownComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Dropdown",
            prefix = "Use a ",
            key = "dropdown",
            text = " when you want to present a list of actions in a limited space."
        )
        pageSection {
            h2 { +"Examples" }
            snippet("Basic", DropdownCode.BASIC) {
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
            snippet("Disabled", DropdownCode.DISABLED) {
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
            snippet("Primary toggle", DropdownCode.PRIMARY) {
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
            snippet("Position right", DropdownCode.RIGHT) {
                dropdown<String>(align = RIGHT) {
                    textToggle() { +"Dropdown" }
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
            snippet("Direction up", DropdownCode.UP) {
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
            snippet("With kebab", DropdownCode.KEBAB) {
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
            snippet("Icon only", DropdownCode.ICON) {
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
            snippet("Icons and descriptions", DropdownCode.DESCRIPTION) {
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
            snippet("Split button", DropdownCode.CHECKBOX_TOGGLE) {
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
            snippet("Split button action", DropdownCode.ACTION_TOGGLE) {
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
            snippet("Groups", DropdownCode.GROUPS) {
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
            snippet("Reactive", DropdownCode.REACTIVE) {
                lateinit var text: Input
                lateinit var enabled: Switch

                fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

                fun registerEvents(dropdown: Dropdown<String>, name: String) {
                    with(dropdown) {
                        store.select handledBy Notification.add {
                            info("$name: Clicked on $it")
                        }
                        ces.data.drop(1) handledBy Notification.add { expanded ->
                            info("$name: Expanded state: $expanded.")
                        }
                    }
                }

                fun items(): List<Entry<String>> = items {
                    item("Action")
                    item("Disabled Action") {
                        disabled = true
                    }
                    separator()
                    item("Separated Action")
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
                    store.update(items())
                }
                dropdown<String>(baseClass = "ml-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    kebabToggle()
                    registerEvents(this, "Kebab toggle")
                    store.update(items())
                }
                dropdown<String>(baseClass = "ml-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    iconToggle { icon("cog".fas()) }
                    registerEvents(this, "Icon toggle")
                    store.update(items())
                }
                br {}
                dropdown<String>(baseClass = "mt-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    checkboxToggle {
                        text {
                            this@dropdown.store.select.asText()
                        }
                        checkbox {
                            changes.states() handledBy Notification.add {
                                info("Checkbox toggle: Checked: $it")
                            }
                            triState(this@dropdown.store.select.map {
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
                    store.update(items())
                }
                dropdown<String>(baseClass = "ml-sm".util()) {
                    disabled(enabled.input.changes.states().map { !it })
                    actionToggle {
                        icon("cog".fas())
                    } handledBy Notification.info("Action toggle (icon): Clicked")
                    registerEvents(this, "Action toggle (icon)")
                    store.update(items())
                }

                MainScope().launch {
                    delay(EVENT_DELAY)
                    text.domNode.dispatchEvent(Event(Events.keyup.name))
                }
            }
        }
    }
}

object DropdownCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        dropdown<String> {
            dropdownToggle { content = { +"Dropdown" } }
            dropdownItems {
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
            dropdownToggle {
                content = { +"Dropdown" }
                disabled = const(true)
            }
            dropdownItems {
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
            dropdownToggle("primary".modifier()) { content = { +"Dropdown" } }
            dropdownItems {
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
            dropdownToggle { content = { +"Dropdown" } }
            dropdownItems {
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
            dropdownToggle { content = { +"Dropdown" } }
            dropdownItems {
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
            dropdownToggleKebab()
            dropdownItems {
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
            dropdownToggle { icon = { icon("th".fas()) } }
            dropdownItems {
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
            dropdownToggle { content = { +"Dropdown" } }
            dropdownItems {
                item("Action 1") {
                    icon = { icon("cog".fas()) }
                }
                item("Action 2") {
                    description = "This is a description"
                }
                item("Action 3") {
                    icon = { img { src = const("./pf-logo-small.svg") } }
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
            dropdownToggleCheckbox()
            dropdownItems {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            dropdownToggleCheckbox { content = { +"10 selected" } }
            dropdownItems {
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
            dropdownToggleAction { action = { +"Action" } }
            dropdownItems {
                item("Action")
                item("Disabled Action") {
                    disabled = true
                }
                separator()
                item("Separated Action")
            }
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            dropdownToggleAction { action = { icon("cog".fas()) } }
            dropdownItems {
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
        dropdown<String> {
            dropdownToggle { content = { +"Dropdown" } }
            dropdownGroups {
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
                store.clicked.unwrap()
                    .map { Notification(INFO, "${'$'}name: Clicked on ${'$'}it") }
                    .handledBy(Notification.store.add)
                ces.collapsed
                    .map { Notification(INFO, "${'$'}name: Dropdown collapsed") }
                    .handledBy(Notification.store.add)
                ces.expanded
                    .map { Notification(INFO, "${'$'}name: Dropdown expanded") }
                    .handledBy(Notification.store.add)
            }
        }

        fun items(): List<Entry<String>> = items {
            item("Action")
            item("Disabled Action") {
                disabled = true
            }
            separator()
            item("Separated Action")
        }

        div(baseClass = classes {
            +"flex".layout()
            +"align-items-center".modifier()
            +"mb-sm".util()
        }) {
            text = input(baseClass = classes("form-control".component(), "w-50".util())) {
                type = const("text")
                value = const("Dropdown")
                placeholder = const("Dropdown text")
            }
            enabled = switch("ml-md".util()) {
                label = const("Enabled")
                labelOff = const("Disabled")
                input.checked = const(true)
            }
        }
        dropdown<String>(baseClass = "mt-sm".util()) {
            dropdownToggle {
                content = { text.keyups.map { currentValue(it) }.bind() }
                disabled = enabled.input.changes.states().map { !it }
            }
            dropdownItems()
            registerEvents(this, "Text")
            action(items()) handledBy store.update
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            dropdownToggleKebab {
                disabled = enabled.input.changes.states().map { !it }
            }
            dropdownItems()
            registerEvents(this, "Kebab")
            action(items()) handledBy store.update
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            dropdownToggle {
                disabled = enabled.input.changes.states().map { !it }
                icon = { icon("cog".fas()) }
            }
            dropdownItems()
            registerEvents(this, "Icon")
            action(items()) handledBy store.update
        }
        br {}
        dropdown<String>(baseClass = "mt-sm".util()) {
            dropdownToggleCheckbox {
                content = {
                    domNode.styleHidden = true
                    merge(
                        this@dropdown.store.clicked.unwrap(),
                        this@dropdownToggleCheckbox.input.changes.states()
                    ).map {
                        val value = when (it) {
                            is String -> {
                                if (it == "Select none") {
                                    ""
                                } else it
                            }
                            is Boolean -> {
                                if (it) "Select all" else ""
                            }
                            else -> ""
                        }
                        domNode.styleHidden = value.isEmpty()
                        value
                    }.bind()
                }
                disabled = enabled.input.changes.states().map { !it }
                triState = this@dropdown.store.clicked.unwrap().map {
                    when (it) {
                        "Select none" -> TriState.OFF
                        "Select visible" -> TriState.INDETERMINATE
                        "Select all" -> TriState.ON
                        else -> TriState.OFF
                    }
                }
            }
            dropdownItems {
                item("Select none")
                item("Select visible")
                item("Select all")
            }
            registerEvents(this, "Split button")
        }
        br {}
        dropdown<String>(baseClass = "mt-sm".util()) {
            dropdownToggleAction {
                action = {
                    text.keyups.map { currentValue(it) }.bind()
                    clicks
                        .map { Notification(INFO, "Action clicked") }
                        .handledBy(Notification.store.add)
                }
                disabled = enabled.input.changes.states().map { !it }
            }
            dropdownItems()
            registerEvents(this, "Action text")
            action(items()) handledBy store.update
        }
        dropdown<String>(baseClass = "ml-sm".util()) {
            dropdownToggleAction {
                action = {
                    icon("cog".fas())
                    clicks
                        .map { Notification(INFO, "Action clicked") }
                        .handledBy(Notification.store.add)
                }
                disabled = enabled.input.changes.states().map { !it }
            }
            dropdownItems()
            registerEvents(this, "Action icon")
            action(items()) handledBy store.update
        }

        MainScope().launch {
            delay(EVENT_DELAY)
            text.domNode.dispatchEvent(Event(Events.keyup.name))
        }
    }
}
"""
}
