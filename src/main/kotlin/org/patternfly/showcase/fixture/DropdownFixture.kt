package org.patternfly.showcase.fixture

import dev.fritz2.dom.html.RenderContext
import org.patternfly.Align
import org.patternfly.dropdown

object DropdownFixture {

    fun RenderContext.defaultDropdown() {
        dropdown(align = Align.RIGHT) {
            toggle { kebab() }
            item("Item 1")
            item("Disabled Item") {
                disabled(true)
            }
            separator()
            item("Separated Item")
        }
    }
}