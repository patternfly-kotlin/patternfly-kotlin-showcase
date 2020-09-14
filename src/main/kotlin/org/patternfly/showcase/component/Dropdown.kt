@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.action
import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.render
import dev.fritz2.dom.states
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Align
import org.patternfly.Dropdown
import org.patternfly.Entry
import org.patternfly.Notification
import org.patternfly.Severity.INFO
import org.patternfly.Switch
import org.patternfly.TriState
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfContent
import org.patternfly.pfDropdown
import org.patternfly.pfDropdownGroups
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownToggleActionIcon
import org.patternfly.pfDropdownToggleActionText
import org.patternfly.pfDropdownToggleCheckbox
import org.patternfly.pfDropdownToggleIcon
import org.patternfly.pfDropdownToggleKebab
import org.patternfly.pfDropdownToggleText
import org.patternfly.pfGroup
import org.patternfly.pfIcon
import org.patternfly.pfItem
import org.patternfly.pfItems
import org.patternfly.pfSection
import org.patternfly.pfSeparator
import org.patternfly.pfSwitch
import org.patternfly.util
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

@OptIn(InternalCoroutinesApi::class)
object DropdownComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Dropdown",
                prefix = "Use a ",
                key = "dropdown",
                text = " when you want to present a list of actions in a limited space."
            )
        })
        yield(render {
            pfSection {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", DropdownCode.BASIC) {
                    pfDropdown<String> {
                        pfDropdownToggleText { +"Dropdown" }
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
                        pfDropdownToggleText {
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
                snippet("Primary toggle", DropdownCode.PRIMARY) {
                    pfDropdown<String> {
                        pfDropdownToggleText("primary".modifier()) { +"Dropdown" }
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
                    pfDropdown<String>(align = Align.RIGHT) {
                        pfDropdownToggleText { +"Dropdown" }
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
                        pfDropdownToggleText { +"Dropdown" }
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
                        pfDropdownToggleIcon { pfIcon("th".fas()) }
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
                        pfDropdownToggleText { +"Dropdown" }
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
                snippet("Split button action", DropdownCode.ACTION_TOGGLE) {
                    pfDropdown<String> {
                        pfDropdownToggleActionText { +"Action" }
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
                        pfDropdownToggleActionIcon(icon = pfIcon("cog".fas()))
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
                        pfDropdownToggleText { +"Dropdown" }
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
                            store.clicks
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

                    fun checkboxItems(): List<Entry<String>> = pfItems {
                        pfItem("Select none")
                        pfItem("Select visible")
                        pfItem("Select all")
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
                        pfDropdownToggleText {
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
                        pfDropdownToggleIcon {
                            pfIcon("cog".fas())
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
                            triState = this@pfDropdown.store.clicks.map {
                                when (it) {
                                    "Select none" -> TriState.OFF
                                    "Select visible" -> TriState.INDETERMINATE
                                    "Select all" -> TriState.ON
                                    else -> TriState.OFF
                                }
                            }
                            input.changes.states()
                                .map { Notification(INFO, "Split button ${if (it) "checked" else "unchecked"}") }
                                .handledBy(Notification.store.add)
                            this@pfDropdown.store.clicks.map { if (it == "Select none") "" else it }.bind()
                        }
                        pfDropdownItems()
                        registerEvents(this, "Split button")
                        action(checkboxItems()) handledBy store.update
                    }
                    br {}
                    pfDropdown<String>(classes = "mt-sm".util()) {
                        pfDropdownToggleActionText {
                            disabled = enabled.input.changes.states().map { !it }
                            text.keyups.map { currentValue(it) }.bind()
                            action.clicks
                                .map { Notification(INFO, "Action clicked") }
                                .handledBy(Notification.store.add)
                        }
                        pfDropdownItems()
                        registerEvents(this, "Action text")
                        action(items()) handledBy store.update
                    }
                    pfDropdown<String>(classes = "ml-sm".util()) {
                        pfDropdownToggleActionIcon(pfIcon("cog".fas())) {
                            disabled = enabled.input.changes.states().map { !it }
                            action.clicks
                                .map { Notification(INFO, "Action clicked") }
                                .handledBy(Notification.store.add)
                        }
                        pfDropdownItems()
                        registerEvents(this, "Action icon")
                        action(items()) handledBy store.update
                    }

                    MainScope().launch {
                        delay(333)
                        text.domNode.dispatchEvent(Event(Events.keyup.name))
                    }
                }
            }
        })
    }
}

internal object DropdownCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pfDropdown<String> {
            pfDropdownToggleText { +"Dropdown" }
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
            pfDropdownToggleText {
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
            pfDropdownToggleText("primary".modifier()) { +"Dropdown" }
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
            pfDropdownToggleText { +"Dropdown" }
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
            pfDropdownToggleText { +"Dropdown" }
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
    }
}"""

    //language=kotlin
    const val ICON: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val DESCRIPTION: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val CHECKBOX_TOGGLE: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val ACTION_TOGGLE: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val GROUPS: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
    }
}"""
}
