@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import org.patternfly.Align
import org.patternfly.fas
import org.patternfly.pfContent
import org.patternfly.pfEntries
import org.patternfly.pfGroup
import org.patternfly.pfIcon
import org.patternfly.pfItem
import org.patternfly.pfOptionsMenu
import org.patternfly.pfSection
import org.patternfly.pfSeparator
import org.patternfly.util
import org.w3c.dom.HTMLElement

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
                snippet("Single option", OptionsMenuCode.SINGLE_OPTION) {
                    pfOptionsMenu<String>(text = "Options menu") {
                        pfEntries {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                }
                snippet("Disabled", OptionsMenuCode.DISABLED) {
                    pfOptionsMenu<String>(text = "Disabled options menu") {
                        disabled = const(true)
                        pfEntries {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                }
                snippet("Multiple options", OptionsMenuCode.MULTIPLE_OPTIONS) {
                    pfOptionsMenu<String>(text = "Sort by", grouped = true) {
                        pfEntries {
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
                snippet("Plain", OptionsMenuCode.PLAIN) {
                    pfOptionsMenu<String>(icon = pfIcon("sort-amount-down".fas()), classes = "mr-sm".util()) {
                        disabled = const(true)
                        pfEntries {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                    pfOptionsMenu<String>(icon = pfIcon("sort-amount-down".fas())) {
                        pfEntries {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                }
                snippet("Align top", OptionsMenuCode.TOP) {
                    pfOptionsMenu<String>(text = "Align top", up = true) {
                        pfEntries {
                            pfItem("Option 1") { selected = true }
                            pfItem("Option 2")
                            pfItem("Option 3")
                        }
                    }
                }
                snippet("Align right", OptionsMenuCode.RIGHT) {
                    pfOptionsMenu<String>(text = "Align right", align = Align.RIGHT) {
                        pfEntries {
                            pfItem("Right option 1") { selected = true }
                            pfItem("Right option 2")
                            pfItem("Right option 3")
                        }
                    }
                }
                snippet("Grouped items with titles", OptionsMenuCode.GROUPED) {
                    pfOptionsMenu<String>(text = "Options menu", grouped = true) {
                        pfEntries {
                            pfGroup {
                                pfItem("Option 1"){ selected = true }
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
            }
        })
    }
}

internal object OptionsMenuCode {

    //language=kotlin
    const val SINGLE_OPTION: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val DISABLED: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val MULTIPLE_OPTIONS: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val PLAIN: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val TOP: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val RIGHT: String = """fun main() {
    render {
    }
}"""

    //language=kotlin
    const val GROUPED: String = """fun main() {
    render {
    }
}"""
}
