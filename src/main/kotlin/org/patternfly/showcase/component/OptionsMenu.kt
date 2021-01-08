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
import org.patternfly.ItemSelection.MULTIPLE
import org.patternfly.ItemSelection.SINGLE
import org.patternfly.ItemSelection.SINGLE_PER_GROUP
import org.patternfly.Notification
import org.patternfly.NotificationStore
import org.patternfly.OptionsMenu
import org.patternfly.OptionsMenuStore
import org.patternfly.Severity
import org.patternfly.Switch
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.fas
import org.patternfly.group
import org.patternfly.groups
import org.patternfly.icon
import org.patternfly.iconToggle
import org.patternfly.item
import org.patternfly.items
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.optionsMenu
import org.patternfly.pageSection
import org.patternfly.separator
import org.patternfly.showcase.EVENT_DELAY
import org.patternfly.switch
import org.patternfly.textToggle
import org.patternfly.unwrap
import org.patternfly.updateItems
import org.patternfly.util
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

object OptionsMenuComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Options menu",
            text = "An options menu is similar to a dropdown, but provides a way to select among a set of optional settings rather than trigger an action.",
            designGuidelines = "https://www.patternfly.org/v4/components/options-menu/design-guidelines"
        )
        pageSection {
            snippet("Basic", OptionsMenuCode.BASIC) {
                optionsMenu<String> {
                    textToggle { +"Options menu" }
                    items {
                        item("Option 1") { selected = true }
                        item("Option 2")
                        item("Option 3")
                    }
                }
            }
            snippet("Disabled", OptionsMenuCode.DISABLED) {
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
            snippet("Position right", OptionsMenuCode.RIGHT) {
                optionsMenu<String>(align = RIGHT) {
                    textToggle { +"Options menu" }
                    items {
                        item("Right option 1") { selected = true }
                        item("Right option 2")
                        item("Right option 3")
                    }
                }
            }
            snippet("Direction up", OptionsMenuCode.UP) {
                optionsMenu<String>(up = true) {
                    textToggle { +"Options menu" }
                    items {
                        item("Option 1") { selected = true }
                        item("Option 2")
                        item("Option 3")
                    }
                }
            }
            snippet("Plain", OptionsMenuCode.ICON) {
                optionsMenu<String> {
                    iconToggle { icon("sort-amount-down".fas()) }
                    items {
                        item("Option 1") { selected = true }
                        item("Option 2")
                        item("Option 3")
                    }
                }
            }
            snippet("Plain with text", OptionsMenuCode.PLAIN) {
                optionsMenu<String> {
                    textToggle(plain = true) { +"Options menu" }
                    items {
                        item("Option 1") { selected = true }
                        item("Option 2")
                        item("Option 3")
                    }
                }
            }
            snippet("Grouped items with titles", OptionsMenuCode.GROUPED) {
                optionsMenu<String>(grouped = true) {
                    textToggle { +"Sort by" }
                    groups {
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
            snippet("Multiselect", OptionsMenuCode.MULTIPLE_OPTIONS) {
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
                        expanded.collapsed
                            .map { Notification(Severity.INFO, "$name: Options menu collapsed") }
                            .handledBy(NotificationStore.add)
                        expanded.expanded
                            .map { Notification(Severity.INFO, "$name: Options menu expanded") }
                            .handledBy(NotificationStore.add)
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
    }
}

object OptionsMenuCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
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
    const val GROUPED: String = """fun main() {
    render {
        optionsMenu<String>(grouped = true) {
            textToggle { +"Sort by" }
            groups {
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
                    .map { Notification(Severity.INFO, "${'$'}name: Selection ${'$'}it") }
                    .handledBy(NotificationStore.add)
                expanded.collapsed
                    .map { Notification(Severity.INFO, "${'$'}name: Options menu collapsed") }
                    .handledBy(NotificationStore.add)
                expanded.expanded
                    .map { Notification(Severity.INFO, "${'$'}name: Options menu expanded") }
                    .handledBy(NotificationStore.add)
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
