package org.patternfly.showcase

import dev.fritz2.dom.html.RenderContext
import org.patternfly.pageSection
import org.patternfly.showcase.component.AlertComponent
import org.patternfly.showcase.component.AlertGroupComponent
import org.patternfly.showcase.component.BadgeComponent
import org.patternfly.showcase.component.ButtonComponent
import org.patternfly.showcase.component.CardComponent
import org.patternfly.showcase.component.ChipComponent
import org.patternfly.showcase.component.ChipGroupComponent
import org.patternfly.showcase.component.DataListComponent
import org.patternfly.showcase.component.DrawerComponent
import org.patternfly.showcase.component.DropdownComponent
import org.patternfly.showcase.component.OptionsMenuComponent
import org.patternfly.showcase.component.PaginationComponent
import org.patternfly.showcase.component.SwitchComponent
import org.patternfly.showcase.component.TabsComponent
import org.patternfly.showcase.component.TreeViewComponent
import org.patternfly.showcase.demo.UserDemo
import org.patternfly.showcase.page.HomePage
import org.patternfly.title

sealed class Place {
    abstract val place: String
    abstract val content: RenderContext.() -> Unit

    companion object {
        fun lookup(place: String): RenderContext.() -> Unit =
            places.find { place == it.place }?.content ?: notFound(place)

        private fun notFound(place: String): RenderContext.() -> Unit = {
            pageSection {
                title { +"Not Found" }
                p {
                    +"Page "
                    code { +place }
                    +" not found"
                }
            }
        }
    }
}

data class Page(override val place: String, override val content: RenderContext.() -> Unit) : Place()

data class Component(val id: String, val name: String, override val content: RenderContext.() -> Unit) : Place() {
    override val place: String = "documentation:component=$id"
}

data class Demo(val id: String, val name: String, override val content: RenderContext.() -> Unit) : Place() {
    override val place: String = "documentation:demo=$id"
}

val pages = listOf(
    Page("home") { HomePage().content },
    Page("contribute") { HomePage().content },
    Page("get-in-touch") { HomePage().content },
    Page("get-started") { HomePage().content }
)

val components = listOf(
    Component("alert", "Alert") { AlertComponent().content },
    Component("alert-group", "Alert group") { AlertGroupComponent().content },
    Component("badge", "Badge") { BadgeComponent().content },
    Component("button", "Button") { ButtonComponent().content },
    Component("card", "Card") { CardComponent().content },
    Component("chip", "Chip") { ChipComponent().content },
    Component("chip-group", "Chip group") { ChipGroupComponent().content },
    Component("data-list", "Data list") { DataListComponent().content },
    Component("drawer", "Drawer") { DrawerComponent().content },
    Component("dropdown", "Dropdown") { DropdownComponent().content },
    Component("options-menu", "Options menu") { OptionsMenuComponent().content },
    Component("pagination", "Pagination") { PaginationComponent().content },
    Component("switch", "Switch") { SwitchComponent().content },
    Component("tabs", "Tabs") { TabsComponent().content },
    Component("tree-view", "Tree view") { TreeViewComponent().content },
)

val demos = listOf(
    Demo("user", "User") { UserDemo().content }
)

val places = pages + components + demos
