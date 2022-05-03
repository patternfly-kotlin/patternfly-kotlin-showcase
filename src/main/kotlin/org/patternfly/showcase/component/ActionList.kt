package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.ButtonVariant.link
import org.patternfly.ButtonVariant.plain
import org.patternfly.ButtonVariant.primary
import org.patternfly.ButtonVariant.secondary
import org.patternfly.actionList
import org.patternfly.fas
import org.patternfly.pageSection
import org.patternfly.pushButton
import org.patternfly.showcase.fixture.DropdownFixture.defaultDropdown

object ActionListComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Action list",
            text = "An action list is a group of actions with set spacing.",
            designGuidelines = "https://www.patternfly.org/v4/components/action-list/design-guidelines"
        )
        pageSection {
            snippet("Single group", ActionListCode.SINGLE_GROUP) {
                actionList {
                    item {
                        pushButton(primary) { +"Next" }
                    }
                    item {
                        pushButton(secondary) { +"Back" }
                    }
                    item {
                        defaultDropdown()
                    }
                }
            }
            snippet("Icons", ActionListCode.ICONS) {
                actionList(icons = true) {
                    item {
                        pushButton(plain) { icon("times".fas()) }
                    }
                    item {
                        pushButton(plain) { icon("check".fas()) }
                    }
                }
            }
            snippet("Mixed", ActionListCode.MIXED) {
                actionList {
                    group {
                        item {
                            pushButton(primary) { +"Next" }
                        }
                        item {
                            pushButton(secondary) { +"Back" }
                        }
                    }
                    item {
                        pushButton(link) { +"Cancel" }
                    }
                }
            }
        }
    }
}

object ActionListCode {

    //language=kotlin
    const val SINGLE_GROUP: String = """"""

    //language=kotlin
    const val ICONS: String = """"""

    //language=kotlin
    const val MIXED: String = """"""
}
