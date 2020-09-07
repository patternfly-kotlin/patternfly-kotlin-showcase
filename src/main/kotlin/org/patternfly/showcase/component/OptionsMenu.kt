@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import org.patternfly.pfContent
import org.patternfly.pfEntries
import org.patternfly.pfItem
import org.patternfly.pfOptionsMenu
import org.patternfly.pfSection
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
}
