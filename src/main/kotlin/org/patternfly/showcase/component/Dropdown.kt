@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import kotlinx.coroutines.flow.map
import org.patternfly.Notification
import org.patternfly.Severity.INFO
import org.patternfly.pfContent
import org.patternfly.pfDropdown
import org.patternfly.pfDropdownGroups
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownToggle
import org.patternfly.pfGroup
import org.patternfly.pfItem
import org.patternfly.pfSection
import org.patternfly.pfSeparator
import org.w3c.dom.HTMLElement

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
                            pfItem("Item 1")
                            pfItem("Disabled Item") {
                                disabled = true
                            }
                            pfSeparator()
                            pfItem("Separated Item")
                        }
                    }
                }
                snippet("Disabled", DropdownCode.DISABLED) {
                    pfDropdown<String> {
                        pfDropdownToggle {
                            +"Dropdown"
                        }
                        pfDropdownItems {
                            pfItem("Item 1")
                            pfItem("Item 2")
                            pfItem("Item 3")
                        }
                    }
                }
                snippet("Dropdown", DropdownCode.PRIMARY) {
                    pfDropdown<String> {
                        pfDropdownToggle {
                            +"Dropdown"
                        }
                        pfDropdownItems {
                            pfItem("Item 1")
                            pfItem("Item 2")
                            pfItem("Item 3")
                        }
                    }
                }
                snippet("Basic", DropdownCode.GROUPS) {
                    pfDropdown<String> {
                        pfDropdownToggle { +"Dropdown" }
                        pfDropdownGroups {
                            pfGroup {
                                pfItem("Item 1")
                                pfItem("Item 2")
                            }
                            pfSeparator()
                            pfGroup("Group 1") {
                                pfItem("Item 1")
                                pfItem("Item 2")
                            }
                            pfSeparator()
                            pfGroup("Group 2") {
                                pfItem("Item 1")
                                pfItem("Item 2")
                            }
                        }
                    }
                }
                snippet("Dropdown events", DropdownCode.EVENTS) {
                    val dropdown = pfDropdown<String> {
                        pfDropdownToggle { +"Dropdown" }
                        pfDropdownItems {
                            pfItem("Item 1")
                            pfItem("Disabled Item") {
                                disabled = true
                            }
                            pfSeparator()
                            pfItem("Separated Item")
                        }
                    }
                    dropdown.store.clicks
                        .map { Notification(INFO, "Clicked on $it") }
                        .handledBy(Notification.store.add)
                    dropdown.ces.collapsed
                        .map { Notification(INFO, "Dropdown collapsed") }
                        .handledBy(Notification.store.add)
                    dropdown.ces.expanded
                        .map { Notification(INFO, "Dropdown expanded") }
                        .handledBy(Notification.store.add)
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
    const val GROUPS: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val EVENTS: String = """fun main() {
    render {
    }
}"""
}
