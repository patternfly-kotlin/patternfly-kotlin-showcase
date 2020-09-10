@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.Events
import dev.fritz2.dom.html.Input
import dev.fritz2.dom.html.render
import dev.fritz2.dom.states
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Align
import org.patternfly.Modifier.primary
import org.patternfly.Notification
import org.patternfly.Severity.INFO
import org.patternfly.Switch
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfContent
import org.patternfly.pfDropdown
import org.patternfly.pfDropdownGroups
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownToggle
import org.patternfly.pfGroup
import org.patternfly.pfIcon
import org.patternfly.pfItem
import org.patternfly.pfSection
import org.patternfly.pfSeparator
import org.patternfly.pfSwitch
import org.patternfly.util
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

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
                snippet("Disabled", DropdownCode.DISABLED) {
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
                snippet("Primary toggle", DropdownCode.PRIMARY) {
                    pfDropdown<String> {
                        pfDropdownToggle(primary) { +"Dropdown" }
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
                snippet("Direction up", DropdownCode.UP) {
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
                snippet("With kebab", DropdownCode.KEBAB) {
                    pfDropdown<String> {
                        pfDropdownToggle {
                            disabled = const(false)
                            pfIcon("ellipsis-v".fas())
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
                    pfDropdown<String> {
                        pfDropdownToggle { pfIcon("ellipsis-v".fas()) }
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
                        pfDropdownToggle {
                            disabled = const(false)
                            pfIcon("th".fas())
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
                    pfDropdown<String> {
                        pfDropdownToggle { pfIcon("th".fas()) }
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
                snippet("Groups", DropdownCode.GROUPS) {
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
                snippet("Reactive", DropdownCode.REACTIVE) {
                    fun currentValue(event: Event) = event.target.unsafeCast<HTMLInputElement>().value

                    lateinit var text: Input
                    lateinit var enabled: Switch
                    div(baseClass = classes {
                        +"flex".layout()
                        +"align-items-center".modifier()
                        +"mb-md".util()
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
                    br {}
                    val dropdown = pfDropdown<String> {
                        pfDropdownToggle {
                            disabled = enabled.input.changes.states().map { !it }
                            text.keyups.map { currentValue(it) }.bind()
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
                    dropdown.store.clicks
                        .map { Notification(INFO, "Clicked on $it") } handledBy Notification.store.add
                    dropdown.ces.collapsed
                        .map { Notification(INFO, "Dropdown collapsed") } handledBy Notification.store.add
                    dropdown.ces.expanded
                        .map { Notification(INFO, "Dropdown expanded") } handledBy Notification.store.add

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
    }
}"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val PRIMARY: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val RIGHT: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val UP: String = """fun main() {
    render {
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
