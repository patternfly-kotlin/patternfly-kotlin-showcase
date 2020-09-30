@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.action
import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.states
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import org.patternfly.Align.RIGHT
import org.patternfly.Dropdown
import org.patternfly.Elements
import org.patternfly.Entry
import org.patternfly.Notification
import org.patternfly.Severity.INFO
import org.patternfly.Switch
import org.patternfly.TriState
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.elements
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfContent
import org.patternfly.pfDropdown
import org.patternfly.pfDropdownGroups
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownToggle
import org.patternfly.pfDropdownToggleAction
import org.patternfly.pfDropdownToggleCheckbox
import org.patternfly.pfDropdownToggleKebab
import org.patternfly.pfGroup
import org.patternfly.pfIcon
import org.patternfly.pfItem
import org.patternfly.pfItems
import org.patternfly.pfSection
import org.patternfly.pfSeparator
import org.patternfly.pfSwitch
import org.patternfly.styleHidden
import org.patternfly.unwrap
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
class DropdownComponent : Elements {
    private lateinit var text: Input
    private lateinit var enabled: Switch
    override val elements = elements {
        intro(
            title = "Dropdown",
            prefix = "Use a ",
            key = "dropdown",
            text = " when you want to present a list of actions in a limited space."
        )
        pfSection {
            pfContent {
                h2 { +"Examples" }
            }
            snippet("Basic", DropdownCode.BASIC) {
                pfDropdown<String> {
                    pfDropdownToggle { content = { +"Dropdown" } }
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
            }
            snippet("Disabled", DropdownCode.DISABLED) {
                pfDropdown<String> {
                    pfDropdownToggle {
                        content = { +"Dropdown" }
                        disabled = const(true)
                    }
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
            }
            snippet("Primary toggle", DropdownCode.PRIMARY) {
                pfDropdown<String> {
                    pfDropdownToggle("primary".modifier()) { content = { +"Dropdown" } }
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
            }
            snippet("Position right", DropdownCode.RIGHT) {
                pfDropdown<String>(align = RIGHT) {
                    pfDropdownToggle { content = { +"Dropdown" } }
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
            }
            snippet("Direction up", DropdownCode.UP) {
                pfDropdown<String>(up = true) {
                    pfDropdownToggle { content = { +"Dropdown" } }
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
            }
            snippet("With kebab", DropdownCode.KEBAB) {
                pfDropdown<String> {
                    pfDropdownToggleKebab()
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
            }
            snippet("Icon only", DropdownCode.ICON) {
                pfDropdown<String> {
                    pfDropdownToggle { icon = { pfIcon("th".fas()) } }
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
            }
            snippet("Icons and descriptions", DropdownCode.DESCRIPTION) {
                pfDropdown<String> {
                    pfDropdownToggle { content = { +"Dropdown" } }
                    pfDropdownItems {
                        pfItem("Action 1") {
                            icon = { pfIcon("cog".fas()) }
                        }
                        pfItem("Action 2") {
                            description = "This is a description"
                        }
                        pfItem("Action 3") {
                            icon = { img { src = const("./pf-logo-small.svg") } }
                            description = "This is a description"
                        }
                        pfSeparator()
                        pfItem("Disabled action 1") {
                            disabled = true
                            icon = { pfIcon("cog".fas()) }
                        }
                        pfItem("Disabled action 2") {
                            disabled = true
                            description = "This is a description"
                        }
                        pfItem("Disabled action 3") {
                            disabled = true
                            icon = { pfIcon("cog".fas()) }
                            description = "This is a description"
                        }
                    }
                }
            }
            snippet("Split button", DropdownCode.CHECKBOX_TOGGLE) {
                pfDropdown<String> {
                    pfDropdownToggleCheckbox()
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
                pfDropdown<String>(classes = "ml-sm".util()) {
                    pfDropdownToggleCheckbox { content = { +"10 selected" } }
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
            }
            snippet("Split button action", DropdownCode.ACTION_TOGGLE) {
                pfDropdown<String> {
                    pfDropdownToggleAction { action = { +"Action" } }
                    pfDropdownItems {
                        pfItem("Action")
                        pfItem("Disabled Action") {
                            disabled = true
                        }
                        pfSeparator()
                        pfItem("Separated Action")
                    }
                }
                pfDropdown<String>(classes = "ml-sm".util()) {
                    pfDropdownToggleAction { action = { pfIcon("cog".fas()) } }
                    pfDropdownItems {
                        pfItem("Action") {
                            icon = { pfIcon("cog".fas()) }
                        }
                        pfItem("Disabled Action") {
                            disabled = true
                            icon = { pfIcon("bell".fas()) }
                        }
                        pfSeparator()
                        pfItem("Separated Action") {
                            icon = { pfIcon("cubes".fas()) }
                        }
                    }
                }
            }
            snippet("Groups", DropdownCode.GROUPS) {
                pfDropdown<String> {
                    pfDropdownToggle { content = { +"Dropdown" } }
                    pfDropdownGroups {
                        pfGroup {
                            pfItem("Action 1")
                            pfItem("Action 2")
                        }
                        pfSeparator()
                        pfGroup("Group 1") {
                            pfItem("Action 1")
                            pfItem("Action 2")
                        }
                        pfSeparator()
                        pfGroup("Group 2") {
                            pfItem("Action 1")
                            pfItem("Action 2")
                        }
                    }
                }
            }
            snippet("Reactive", DropdownCode.REACTIVE) {
                fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

                fun registerEvents(dropdown: Dropdown<String>, name: String) {
                    with(dropdown) {
                        store.clicked.unwrap()
                            .map { Notification(INFO, "$name: Clicked on $it") }
                            .handledBy(Notification.store.add)
                        ces.collapsed
                            .map { Notification(INFO, "$name: Dropdown collapsed") }
                            .handledBy(Notification.store.add)
                        ces.expanded
                            .map { Notification(INFO, "$name: Dropdown expanded") }
                            .handledBy(Notification.store.add)
                    }
                }

                fun items(): List<Entry<String>> = pfItems {
                    pfItem("Action")
                    pfItem("Disabled Action") {
                        disabled = true
                    }
                    pfSeparator()
                    pfItem("Separated Action")
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
                    enabled = pfSwitch("ml-md".util()) {
                        label = const("Enabled")
                        labelOff = const("Disabled")
                        input.checked = const(true)
                    }
                }
                pfDropdown<String>(classes = "mt-sm".util()) {
                    pfDropdownToggle {
                        content = { text.keyups.map { currentValue(it) }.bind() }
                        disabled = enabled.input.changes.states().map { !it }
                    }
                    pfDropdownItems()
                    registerEvents(this, "Text")
                    action(items()) handledBy store.update
                }
                pfDropdown<String>(classes = "ml-sm".util()) {
                    pfDropdownToggleKebab {
                        disabled = enabled.input.changes.states().map { !it }
                    }
                    pfDropdownItems()
                    registerEvents(this, "Kebab")
                    action(items()) handledBy store.update
                }
                pfDropdown<String>(classes = "ml-sm".util()) {
                    pfDropdownToggle {
                        disabled = enabled.input.changes.states().map { !it }
                        icon = { pfIcon("cog".fas()) }
                    }
                    pfDropdownItems()
                    registerEvents(this, "Icon")
                    action(items()) handledBy store.update
                }
                br {}
                pfDropdown<String>(classes = "mt-sm".util()) {
                    pfDropdownToggleCheckbox {
                        content = {
                            domNode.styleHidden = true
                            merge(
                                this@pfDropdown.store.clicked.unwrap(),
                                this@pfDropdownToggleCheckbox.input.changes.states()
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
                        triState = this@pfDropdown.store.clicked.unwrap().map {
                            when (it) {
                                "Select none" -> TriState.OFF
                                "Select visible" -> TriState.INDETERMINATE
                                "Select all" -> TriState.ON
                                else -> TriState.OFF
                            }
                        }
                    }
                    pfDropdownItems {
                        pfItem("Select none")
                        pfItem("Select visible")
                        pfItem("Select all")
                    }
                    registerEvents(this, "Split button")
                }
                br {}
                pfDropdown<String>(classes = "mt-sm".util()) {
                    pfDropdownToggleAction {
                        action = {
                            text.keyups.map { currentValue(it) }.bind()
                            clicks
                                .map { Notification(INFO, "Action clicked") }
                                .handledBy(Notification.store.add)
                        }
                        disabled = enabled.input.changes.states().map { !it }
                    }
                    pfDropdownItems()
                    registerEvents(this, "Action text")
                    action(items()) handledBy store.update
                }
                pfDropdown<String>(classes = "ml-sm".util()) {
                    pfDropdownToggleAction {
                        action = {
                            pfIcon("cog".fas())
                            clicks
                                .map { Notification(INFO, "Action clicked") }
                                .handledBy(Notification.store.add)
                        }
                        disabled = enabled.input.changes.states().map { !it }
                    }
                    pfDropdownItems()
                    registerEvents(this, "Action icon")
                    action(items()) handledBy store.update
                }
            }
        }
    }

    init {
        MainScope().launch {
            delay(EVENT_DELAY)
            text.domNode.dispatchEvent(Event(Events.keyup.name))
        }
    }
}

internal object DropdownCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggle { +"Dropdown" }
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
    }
}"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggle {
                disabled = const(true)
                +"Dropdown"
            }
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
    }
}"""

    //language=kotlin
    const val PRIMARY: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggle("primary".modifier()) { +"Dropdown" }
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
    }
}"""

    //language=kotlin
    const val RIGHT: String = """fun main() {
    render {
        pfDropdown<String>(align = Align.RIGHT) {
            pfDropdownToggle { +"Dropdown" }
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
    }
}"""

    //language=kotlin
    const val UP: String = """fun main() {
    render {
        pfDropdown<String>(up = true) {
            pfDropdownToggle { +"Dropdown" }
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
    }
}"""

    //language=kotlin
    const val KEBAB: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggleKebab()
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
    }
}"""

    //language=kotlin
    const val ICON: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggle { icon = { pfIcon("th".fas()) } }
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
    }
}"""

    //language=kotlin
    const val DESCRIPTION: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggle { +"Dropdown" }
            pfDropdownItems {
                pfItem("Action 1") {
                    icon = { { pfIcon("cog".fas()) } }
                }
                pfItem("Action 2") {
                    description = "This is a description"
                }
                pfItem("Action 3") {
                    icon = { img { src = const("./pf-logo-small.svg") } }
                    description = "This is a description"
                }
                pfSeparator()
                pfItem("Disabled action 1") {
                    disabled = true
                    icon = { pfIcon("cog".fas()) }
                }
                pfItem("Disabled action 2") {
                    disabled = true
                    description = "This is a description"
                }
                pfItem("Disabled action 3") {
                    disabled = true
                    icon = { pfIcon("cog".fas()) }
                    description = "This is a description"
                }
            }
        }
    }
}"""

    //language=kotlin
    const val CHECKBOX_TOGGLE: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggleCheckbox()
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
        pfDropdown<String>(classes = "ml-sm".util()) {
            pfDropdownToggleCheckbox { +"10 selected" }
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
    }
}"""

    //language=kotlin
    const val ACTION_TOGGLE: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggleAction { +"Action" }
            pfDropdownItems {
                pfItem("Action")
                pfItem("Disabled Action") {
                    disabled = true
                }
                pfSeparator()
                pfItem("Separated Action")
            }
        }
        pfDropdown<String>(classes = "ml-sm".util()) {
            pfDropdownToggleAction { icon = { pfIcon("cog".fas()) } }
            pfDropdownItems {
                pfItem("Action") {
                    icon = { pfIcon("cog".fas()) }
                }
                pfItem("Disabled Action") {
                    disabled = true
                    icon = { pfIcon("bell".fas()) }
                }
                pfSeparator()
                pfItem("Separated Action") {
                    icon = { pfIcon("cubes".fas()) }
                }
            }
        }
    }
}"""

    //language=kotlin
    const val GROUPS: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggle { +"Dropdown" }
            pfDropdownGroups {
                pfGroup {
                    pfItem("Action 1")
                    pfItem("Action 2")
                }
                pfSeparator()
                pfGroup("Group 1") {
                    pfItem("Action 1")
                    pfItem("Action 2")
                }
                pfSeparator()
                pfGroup("Group 2") {
                    pfItem("Action 1")
                    pfItem("Action 2")
                }
            }
        }
    }
}"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

        fun registerEvents(dropdown: Dropdown<String>, name: String) {
            with(dropdown) {
                store.clicks.unwrap()
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

        fun items(): List<Entry<String>> = pfItems {
            pfItem("Action")
            pfItem("Disabled Action") {
                disabled = true
            }
            pfSeparator()
            pfItem("Separated Action")
        }

        lateinit var text: Input
        lateinit var enabled: Switch
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
            enabled = pfSwitch("ml-md".util()) {
                label = const("Enabled")
                labelOff = const("Disabled")
                input.checked = const(true)
            }
        }
        pfDropdown<String>(classes = "mt-sm".util()) {
            pfDropdownToggle {
                disabled = enabled.input.changes.states().map { !it }
                text.keyups.map { currentValue(it) }.bind()
            }
            pfDropdownItems()
            registerEvents(this, "Text")
            action(items()) handledBy store.update
        }
        pfDropdown<String>(classes = "ml-sm".util()) {
            pfDropdownToggleKebab {
                disabled = enabled.input.changes.states().map { !it }
            }
            pfDropdownItems()
            registerEvents(this, "Kebab")
            action(items()) handledBy store.update
        }
        pfDropdown<String>(classes = "ml-sm".util()) {
            pfDropdownToggle {
                icon = { pfIcon("cog".fas()) }
                disabled = enabled.input.changes.states().map { !it }
            }
            pfDropdownItems()
            registerEvents(this, "Icon")
            action(items()) handledBy store.update
        }
        br {}
        pfDropdown<String>(classes = "mt-sm".util()) {
            pfDropdownToggleCheckbox {
                disabled = enabled.input.changes.states().map { !it }
                triState = this@pfDropdown.store.clicks.unwrap().map {
                    when (it) {
                        "Select none" -> TriState.OFF
                        "Select visible" -> TriState.INDETERMINATE
                        "Select all" -> TriState.ON
                        else -> TriState.OFF
                    }
                }
                merge(this@pfDropdown.store.clicks.unwrap(), input.changes.states())
                    .map {
                        when (it) {
                            is String -> {
                                if (it == "Select none") "" else it
                            }
                            is Boolean -> {
                                if (it) "Select all" else ""
                            }
                            else -> ""
                        }
                    }.bind()
            }
            pfDropdownItems {
                pfItem("Select none")
                pfItem("Select visible")
                pfItem("Select all")
            }
            registerEvents(this, "Split button")
        }
        br {}
        pfDropdown<String>(classes = "mt-sm".util()) {
            pfDropdownToggleAction {
                disabled = enabled.input.changes.states().map { !it }
                action.clicks
                    .map { Notification(INFO, "Action clicked") }
                    .handledBy(Notification.store.add)
                text.keyups.map { currentValue(it) }.bind()
            }
            pfDropdownItems()
            registerEvents(this, "Action text")
            action(items()) handledBy store.update
        }
        pfDropdown<String>(classes = "ml-sm".util()) {
            pfDropdownToggleAction {
                icon = { pfIcon("cog".fas()) }
                disabled = enabled.input.changes.states().map { !it }
                action.clicks
                    .map { Notification(INFO, "Action clicked") }
                    .handledBy(Notification.store.add)
            }
            pfDropdownItems()
            registerEvents(this, "Action icon")
            action(items()) handledBy store.update
        }
    }
}"""
}
