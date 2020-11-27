@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.states
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Align.RIGHT
import org.patternfly.Entry
import org.patternfly.Notification
import org.patternfly.NotificationStore
import org.patternfly.OptionsMenu
import org.patternfly.Severity
import org.patternfly.Switch
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.fas
import org.patternfly.group
import org.patternfly.icon
import org.patternfly.item
import org.patternfly.items
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.optionsMenu
import org.patternfly.optionsMenuGroups
import org.patternfly.optionsMenuItems
import org.patternfly.optionsMenuToggle
import org.patternfly.optionsMenuTogglePlain
import org.patternfly.pageSection
import org.patternfly.separator
import org.patternfly.showcase.EVENT_DELAY
import org.patternfly.switch
import org.patternfly.unwrap
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class OptionsMenuComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Options menu",
            prefix = "An ",
            key = "options menu",
            text = " is similar to a dropdown, but provides a way to select among a set of optional settings rather than trigger an action."
        )
        pageSection {
            h2 { +"Examples" }
            snippet("Basic", OptionsMenuCode.BASIC) {
                optionsMenu<String> {
                    optionsMenuToggle { content = { +"Options menu" } }
                    optionsMenuItems {
                        item("Option 1") { selected = true }
                        item("Option 2")
                        item("Option 3")
                    }
                }
            }
            snippet("Disabled", OptionsMenuCode.DISABLED) {
                optionsMenu<String> {
                    optionsMenuToggle {
                        content = { +"Options menu" }
                        disabled(true)
                    }
                    optionsMenuItems {
                        item("Option 1") { selected = true }
                        item("Option 2")
                        item("Option 3")
                    }
                }
            }
            snippet("Position right", OptionsMenuCode.RIGHT) {
                optionsMenu<String>(align = RIGHT) {
                    optionsMenuToggle { content = { +"Options menu" } }
                    optionsMenuItems {
                        item("Right option 1") { selected = true }
                        item("Right option 2")
                        item("Right option 3")
                    }
                }
            }
            snippet("Direction up", OptionsMenuCode.UP) {
                optionsMenu<String>(up = true) {
                    optionsMenuToggle { content = { +"Options menu" } }
                    optionsMenuItems {
                        item("Option 1") { selected = true }
                        item("Option 2")
                        item("Option 3")
                    }
                }
            }
            snippet("Plain", OptionsMenuCode.ICON) {
                optionsMenu<String> {
                    optionsMenuToggle { icon = { icon("sort-amount-down".fas()) } }
                    optionsMenuItems {
                        item("Option 1") { selected = true }
                        item("Option 2")
                        item("Option 3")
                    }
                }
            }
            snippet("Plain with text", OptionsMenuCode.PLAIN) {
                optionsMenu<String> {
                    optionsMenuTogglePlain { content = { +"Options menu" } }
                    optionsMenuItems {
                        item("Option 1") { selected = true }
                        item("Option 2")
                        item("Option 3")
                    }
                }
            }
            snippet("Multiple options", OptionsMenuCode.MULTIPLE_OPTIONS) {
                optionsMenu<String> {
                    optionsMenuToggle { content = { +"Sort by" } }
                    optionsMenuGroups {
                        group {
                            item("Name")
                            item("Date") { selected = true }
                            item("Disabled") { disabled = true }
                            item("Size")
                        }
                        separator()
                        group {
                            item("Ascending") { selected = true }
                            item("Descending")
                        }
                    }
                }
            }
            snippet("Grouped items with titles", OptionsMenuCode.GROUPED) {
                optionsMenu<String> {
                    optionsMenuToggle { content = { +"Options" } }
                    optionsMenuGroups {
                        group {
                            item("Option 1") { selected = true }
                            item("Option 2")
                        }
                        separator()
                        group("Group 1") {
                            item("Option 1")
                            item("Option 2")
                        }
                        separator()
                        group("Group 2") {
                            item("Option 1")
                            item("Option 2")
                        }
                    }
                }
            }
            snippet("Reactive", OptionsMenuCode.REACTIVE) {
                lateinit var text: Input
                lateinit var enabled: Switch

                fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

                fun registerEvents(optionsMenu: OptionsMenu<String>, name: String) {
                    with(optionsMenu) {
                        store.selection.unwrap()
                            .drop(1)
                            .map { Notification(Severity.INFO, "$name: Selection $it") }
                            .handledBy(NotificationStore.add)
                        ces.collapsed
                            .map { Notification(Severity.INFO, "$name: Options menu collapsed") }
                            .handledBy(NotificationStore.add)
                        ces.expanded
                            .map { Notification(Severity.INFO, "$name: Options menu expanded") }
                            .handledBy(NotificationStore.add)
                    }
                }

                fun items(): List<Entry<String>> = items {
                    item("Option 1") { selected = true }
                    item("Option 2")
                    item("Option 3")
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
                optionsMenu<String>(baseClass = "mt-sm".util()) {
                    optionsMenuToggle {
                        content = { text.keyups.map { currentValue(it) }.asText() }
                        disabled(enabled.input.changes.states().map { !it })
                    }
                    optionsMenuItems()
                    store.update(items())
                    registerEvents(this, "Text")
                }
                optionsMenu<String>(baseClass = "ml-sm".util()) {
                    optionsMenuToggle {
                        disabled(enabled.input.changes.states().map { !it })
                        icon = { icon("sort-amount-down".fas()) }
                    }
                    optionsMenuGroups {
                        group {
                            item("Option 1") { selected = true }
                            item("Option 2")
                        }
                        separator()
                        group("Group 1") {
                            item("Option 1")
                            item("Option 2")
                        }
                        separator()
                        group("Group 2") {
                            item("Option 1")
                            item("Option 2")
                        }
                    }
                    registerEvents(this, "Plain")
                }
                optionsMenu<String>(baseClass = "ml-sm".util()) {
                    optionsMenuTogglePlain {
                        content = { text.keyups.map { currentValue(it) }.asText() }
                        disabled(enabled.input.changes.states().map { !it })
                    }
                    optionsMenuItems()
                    store.update(items())
                    registerEvents(this, "Plain with text")
                }

                MainScope().launch {
                    delay(EVENT_DELAY)
                    text.domNode.dispatchEvent(Event(Events.keyup.name))
                }
            }
        }
    }
}

internal object OptionsMenuCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        optionsMenu<String> {
            optionsMenuToggle { content = { +"Options menu" } }
            optionsMenuItems {
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
            optionsMenuToggle {
                content = { +"Options menu" }
                disabled = const(true)
            }
            optionsMenuItems {
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
            optionsMenuToggle { content = { +"Options menu" } }
            optionsMenuItems {
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
            optionsMenuToggle { content = { +"Options menu" } }
            optionsMenuItems {
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
            optionsMenuToggle { icon = { icon("sort-amount-down".fas()) } }
            optionsMenuItems {
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
            optionsMenuTogglePlain { content = { +"Options menu" } }
            optionsMenuItems {
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
        optionsMenu<String> {
            optionsMenuToggle { content = { +"Sort by" } }
            optionsMenuGroups {
                group {
                    item("Name")
                    item("Date") { selected = true }
                    item("Disabled") { disabled = true }
                    item("Size")
                }
                separator()
                group {
                    item("Ascending") { selected = true }
                    item("Descending")
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val GROUPED: String = """fun main() {
    render {
        optionsMenu<String> {
            optionsMenuToggle { content = { +"Options" } }
            optionsMenuGroups {
                group {
                    item("Option 1") { selected = true }
                    item("Option 2")
                }
                separator()
                group("Group 1") {
                    item("Option 1")
                    item("Option 2")
                }
                separator()
                group("Group 2") {
                    item("Option 1")
                    item("Option 2")
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
                    .map { Notification(Severity.INFO, "${'$'}name: Selection ${'$'}it") }
                    .handledBy(Notification.store.add)
                ces.collapsed
                    .map { Notification(Severity.INFO, "${'$'}name: Options menu collapsed") }
                    .handledBy(Notification.store.add)
                ces.expanded
                    .map { Notification(Severity.INFO, "${'$'}name: Options menu expanded") }
                    .handledBy(Notification.store.add)
            }
        }

        fun items(): List<Entry<String>> = items {
            item("Option 1") { selected = true }
            item("Option 2")
            item("Option 3")
        }

        div(baseClass = classes {
            +"flex".layout()
            +"align-items-center".modifier()
            +"mb-sm".util()
        }) {
            text = input(baseClass = classes("form-control".component(), "w-50".util())) {
                type = const("text")
                value = const("Options")
                placeholder = const("Options menu text")
            }
            enabled = switch("ml-md".util()) {
                label = const("Enabled")
                labelOff = const("Disabled")
                input.checked = const(true)
            }
        }
        optionsMenu<String>(baseClass = "mt-sm".util()) {
            optionsMenuToggle {
                content = { text.keyups.map { currentValue(it) }.bind() }
                disabled = enabled.input.changes.states().map { !it }
            }
            optionsMenuItems()
            action(items()) handledBy store.update
            registerEvents(this, "Text")
        }
        optionsMenu<String>(baseClass = "ml-sm".util()) {
            optionsMenuToggle {
                disabled = enabled.input.changes.states().map { !it }
                icon = { icon("sort-amount-down".fas()) }
            }
            optionsMenuGroups {
                group {
                    item("Option 1") { selected = true }
                    item("Option 2")
                }
                separator()
                group("Group 1") {
                    item("Option 1")
                    item("Option 2")
                }
                separator()
                group("Group 2") {
                    item("Option 1")
                    item("Option 2")
                }
            }
            registerEvents(this, "Plain")
        }
        optionsMenu<String>(baseClass = "ml-sm".util()) {
            optionsMenuTogglePlain {
                content = { text.keyups.map { currentValue(it) }.bind() }
                disabled = enabled.input.changes.states().map { !it }
            }
            optionsMenuItems()
            action(items()) handledBy store.update
            registerEvents(this, "Plain with text")
        }

        MainScope().launch {
            delay(EVENT_DELAY)
            text.domNode.dispatchEvent(Event(Events.keyup.name))
        }
}
"""
}
