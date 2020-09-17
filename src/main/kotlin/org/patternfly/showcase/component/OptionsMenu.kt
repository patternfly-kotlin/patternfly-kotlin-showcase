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
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Align.RIGHT
import org.patternfly.Entry
import org.patternfly.Notification
import org.patternfly.OptionsMenu
import org.patternfly.Severity
import org.patternfly.Switch
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfContent
import org.patternfly.pfGroup
import org.patternfly.pfIcon
import org.patternfly.pfItem
import org.patternfly.pfItems
import org.patternfly.pfOptionsMenu
import org.patternfly.pfOptionsMenuGroups
import org.patternfly.pfOptionsMenuItems
import org.patternfly.pfOptionsMenuToggle
import org.patternfly.pfOptionsMenuTogglePlain
import org.patternfly.pfSection
import org.patternfly.pfSeparator
import org.patternfly.pfSwitch
import org.patternfly.unwrap
import org.patternfly.util
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

object OptionsMenuComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Options menu",
                prefix = "An ",
                key = "options menu",
                text = " is similar to a dropdown, but provides a way to select among a set of optional settings rather than trigger an action."
            )
        })
        yield(render {
            pfSection {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", OptionsMenuCode.BASIC) {
                    pfOptionsMenu<String> {
                        pfOptionsMenuToggle { content = { +"Options menu" } }
                        pfOptionsMenuItems {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                }
                snippet("Disabled", OptionsMenuCode.DISABLED) {
                    pfOptionsMenu<String> {
                        pfOptionsMenuToggle {
                            content = { +"Options menu" }
                            disabled = const(true)
                        }
                        pfOptionsMenuItems {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                }
                snippet("Position right", OptionsMenuCode.RIGHT) {
                    pfOptionsMenu<String>(align = RIGHT) {
                        pfOptionsMenuToggle { content = { +"Options menu" } }
                        pfOptionsMenuItems {
                            pfItem("Right option 1") { selected = true }
                            pfItem("Right option 2")
                            pfItem("Right option 3")
                        }
                    }
                }
                snippet("Direction up", OptionsMenuCode.UP) {
                    pfOptionsMenu<String>(up = true) {
                        pfOptionsMenuToggle { content = { +"Options menu" } }
                        pfOptionsMenuItems {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                }
                snippet("Plain", OptionsMenuCode.ICON) {
                    pfOptionsMenu<String> {
                        pfOptionsMenuToggle { icon = { pfIcon("sort-amount-down".fas()) } }
                        pfOptionsMenuItems {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                }
                snippet("Plain with text", OptionsMenuCode.PLAIN) {
                    pfOptionsMenu<String> {
                        pfOptionsMenuTogglePlain { content = { +"Options menu" } }
                        pfOptionsMenuItems {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                }
                snippet("Multiple options", OptionsMenuCode.MULTIPLE_OPTIONS) {
                    pfOptionsMenu<String> {
                        pfOptionsMenuToggle { content = { +"Sort by" } }
                        pfOptionsMenuGroups {
                            pfGroup {
                                pfItem("Name")
                                pfItem("Date") { selected = true }
                                pfItem("Disabled") { disabled = true }
                                pfItem("Size")
                            }
                            pfSeparator()
                            pfGroup {
                                pfItem("Ascending") { selected = true }
                                pfItem("Descending")
                            }
                        }
                    }
                }
                snippet("Grouped items with titles", OptionsMenuCode.GROUPED) {
                    pfOptionsMenu<String> {
                        pfOptionsMenuToggle { content = { +"Options" } }
                        pfOptionsMenuGroups {
                            pfGroup {
                                pfItem("Option 1") { selected = true }
                                pfItem("Option 2")
                            }
                            pfSeparator()
                            pfGroup("Group 1") {
                                pfItem("Option 1")
                                pfItem("Option 2")
                            }
                            pfSeparator()
                            pfGroup("Group 2") {
                                pfItem("Option 1")
                                pfItem("Option 2")
                            }
                        }
                    }
                }
                snippet("Reactive", OptionsMenuCode.REACTIVE) {
                    fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

                    fun registerEvents(optionsMenu: OptionsMenu<String>, name: String) {
                        with(optionsMenu) {
                            store.selection.unwrap()
                                .drop(1)
                                .map { Notification(Severity.INFO, "$name: Selection $it") }
                                .handledBy(Notification.store.add)
                            ces.collapsed
                                .map { Notification(Severity.INFO, "$name: Options menu collapsed") }
                                .handledBy(Notification.store.add)
                            ces.expanded
                                .map { Notification(Severity.INFO, "$name: Options menu expanded") }
                                .handledBy(Notification.store.add)
                        }
                    }

                    fun items(): List<Entry<String>> = pfItems {
                        pfItem("Option 1") { selected = true }
                        pfItem("Option 2")
                        pfItem("Option 3")
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
                            value = const("Options")
                            placeholder = const("Options menu text")
                        }
                        enabled = pfSwitch("ml-md".util()) {
                            label = const("Enabled")
                            labelOff = const("Disabled")
                            input.checked = const(true)
                        }
                    }
                    pfOptionsMenu<String>(classes = "mt-sm".util()) {
                        pfOptionsMenuToggle {
                            content = { text.keyups.map { currentValue(it) }.bind() }
                            disabled = enabled.input.changes.states().map { !it }
                        }
                        pfOptionsMenuItems()
                        action(items()) handledBy store.update
                        registerEvents(this, "Text")
                    }
                    pfOptionsMenu<String>(classes = "ml-sm".util()) {
                        pfOptionsMenuToggle {
                            disabled = enabled.input.changes.states().map { !it }
                            icon = { pfIcon("sort-amount-down".fas()) }
                        }
                        pfOptionsMenuGroups {
                            pfGroup {
                                pfItem("Option 1") { selected = true }
                                pfItem("Option 2")
                            }
                            pfSeparator()
                            pfGroup("Group 1") {
                                pfItem("Option 1")
                                pfItem("Option 2")
                            }
                            pfSeparator()
                            pfGroup("Group 2") {
                                pfItem("Option 1")
                                pfItem("Option 2")
                            }
                        }
                        registerEvents(this, "Plain")
                    }
                    pfOptionsMenu<String>(classes = "ml-sm".util()) {
                        pfOptionsMenuTogglePlain {
                            content = { text.keyups.map { currentValue(it) }.bind() }
                            disabled = enabled.input.changes.states().map { !it }
                        }
                        pfOptionsMenuItems()
                        action(items()) handledBy store.update
                        registerEvents(this, "Plain with text")
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

internal object OptionsMenuCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pfOptionsMenu<String> {
            pfOptionsMenuToggle { +"Options menu" }
            pfOptionsMenuItems {
                pfItem("Option 1") { selected = true }
                pfItem("Option 2")
                pfItem("Option 3")
            }
        }
    }
}"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
        pfOptionsMenu<String> {
            pfOptionsMenuToggle {
                disabled = const(true)
                +"Options menu"
            }
            pfOptionsMenuItems {
                pfItem("Option 1") { selected = true }
                pfItem("Option 2")
                pfItem("Option 3")
            }
        }
    }
}"""

    //language=kotlin
    const val RIGHT: String = """fun main() {
    render {
        pfOptionsMenu<String>(align = RIGHT) {
            pfOptionsMenuToggle { +"Options menu" }
            pfOptionsMenuItems {
                pfItem("Right option 1") { selected = true }
                pfItem("Right option 2")
                pfItem("Right option 3")
            }
        }
    }
}"""

    //language=kotlin
    const val UP: String = """fun main() {
    render {
        pfOptionsMenu<String>(up = true) {
            pfOptionsMenuToggle { +"Options menu" }
            pfOptionsMenuItems {
                pfItem("Option 1") { selected = true }
                pfItem("Option 2")
                pfItem("Option 3")
            }
        }
    }
}"""

    //language=kotlin
    const val ICON: String = """fun main() {
    render {
        pfOptionsMenu<String> {
            pfOptionsMenuToggle { icon = { pfIcon("sort-amount-down".fas()) } }
            pfOptionsMenuItems {
                pfItem("Option 1") { selected = true }
                pfItem("Option 2")
                pfItem("Option 3")
            }
        }
    }
}"""

    //language=kotlin
    const val PLAIN: String = """fun main() {
    render {
        pfOptionsMenu<String> {
            pfOptionsMenuTogglePlain { +"Options menu" }
            pfOptionsMenuItems {
                pfItem("Option 1") { selected = true }
                pfItem("Option 2")
                pfItem("Option 3")
            }
        }
    }
}"""

    //language=kotlin
    const val MULTIPLE_OPTIONS: String = """fun main() {
    render {
        pfOptionsMenu<String> {
            pfOptionsMenuToggle { +"Sort by" }
            pfOptionsMenuGroups {
                pfGroup {
                    pfItem("Name")
                    pfItem("Date") { selected = true }
                    pfItem("Disabled") { disabled = true }
                    pfItem("Size")
                }
                pfSeparator()
                pfGroup {
                    pfItem("Ascending") { selected = true }
                    pfItem("Descending")
                }
            }
        }
    }
}"""

    //language=kotlin
    const val GROUPED: String = """fun main() {
    render {
        pfOptionsMenu<String> {
            pfOptionsMenuToggle { +"Options" }
            pfOptionsMenuGroups {
                pfGroup {
                    pfItem("Option 1") { selected = true }
                    pfItem("Option 2")
                }
                pfSeparator()
                pfGroup("Group 1") {
                    pfItem("Option 1")
                    pfItem("Option 2")
                }
                pfSeparator()
                pfGroup("Group 2") {
                    pfItem("Option 1")
                    pfItem("Option 2")
                }
            }
        }
    }
}"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
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

        fun items(): List<Entry<String>> = pfItems {
            pfItem("Option 1") { selected = true }
            pfItem("Option 2")
            pfItem("Option 3")
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
                value = const("Options")
                placeholder = const("Options menu text")
            }
            enabled = pfSwitch("ml-md".util()) {
                label = const("Enabled")
                labelOff = const("Disabled")
                input.checked = const(true)
            }
        }
        pfOptionsMenu<String>(classes = "mt-sm".util()) {
            pfOptionsMenuToggle {
                disabled = enabled.input.changes.states().map { !it }
                text.keyups.map { currentValue(it) }.bind()
            }
            pfOptionsMenuItems()
            action(items()) handledBy store.update
            registerEvents(this, "Text")
        }
        pfOptionsMenu<String>(classes = "ml-sm".util()) {
            pfOptionsMenuToggle {
                disabled = enabled.input.changes.states().map { !it }
                icon = { pfIcon("sort-amount-down".fas()) }
            }
            pfOptionsMenuGroups {
                pfGroup {
                    pfItem("Option 1") { selected = true }
                    pfItem("Option 2")
                }
                pfSeparator()
                pfGroup("Group 1") {
                    pfItem("Option 1")
                    pfItem("Option 2")
                }
                pfSeparator()
                pfGroup("Group 2") {
                    pfItem("Option 1")
                    pfItem("Option 2")
                }
            }
            registerEvents(this, "Plain")
        }
        pfOptionsMenu<String>(classes = "ml-sm".util()) {
            pfOptionsMenuTogglePlain {
                disabled = enabled.input.changes.states().map { !it }
                text.keyups.map { currentValue(it) }.bind()
            }
            pfOptionsMenuItems()
            action(items()) handledBy store.update
            registerEvents(this, "Plain with text")
        }
    }
}"""
}
