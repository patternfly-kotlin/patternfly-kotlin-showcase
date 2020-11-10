package org.patternfly.showcase

import dev.fritz2.elemento.elements
import org.patternfly.modifier
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.patternfly.pfTitle
import org.patternfly.showcase.component.AlertComponent
import org.patternfly.showcase.component.AlertGroupComponent
import org.patternfly.showcase.component.BadgeComponent
import org.patternfly.showcase.component.BrandComponent
import org.patternfly.showcase.component.ButtonComponent
import org.patternfly.showcase.component.CardComponent
import org.patternfly.showcase.component.ChipComponent
import org.patternfly.showcase.component.ChipGroupComponent
import org.patternfly.showcase.component.ContentComponent
import org.patternfly.showcase.component.DataListComponent
import org.patternfly.showcase.component.DrawerComponent
import org.patternfly.showcase.component.DropdownComponent
import org.patternfly.showcase.component.EmptyStateComponent
import org.patternfly.showcase.component.OptionsMenuComponent
import org.patternfly.showcase.component.PaginationComponent
import org.patternfly.showcase.component.SwitchComponent
import org.patternfly.showcase.component.TabsComponent
import org.patternfly.showcase.component.TreeViewComponent
import org.patternfly.showcase.demo.UserDemo
import org.w3c.dom.Element

@OptIn(ExperimentalStdlibApi::class)
object Places {

    const val CONTRIBUTE = "contribute"
    const val DOCUMENTATION = "documentation"
    const val GET_IN_TOUCH = "get-in-touch"
    const val GET_STARTED = "get-started"
    const val HOME = "home"

    private val tags: Map<String, () -> List<Element>> = buildMap {
        put("home") { HomePage().elements }
        put(component("alert")) { AlertComponent().elements }
        put(component("alert-group")) { AlertGroupComponent().elements }
        put(component("badge")) { BadgeComponent().elements }
        put(component("brand")) { BrandComponent().elements }
        put(component("button")) { ButtonComponent().elements }
        put(component("card")) { CardComponent().elements }
        put(component("chip")) { ChipComponent().elements }
        put(component("chip-group")) { ChipGroupComponent().elements }
        put(component("content")) { ContentComponent().elements }
        put(component("drawer")) { DrawerComponent().elements }
        put(component("data-list")) { DataListComponent().elements }
        put(component("dropdown")) { DropdownComponent().elements }
        put(component("empty-state")) { EmptyStateComponent().elements }
        put(component("options-menu")) { OptionsMenuComponent().elements }
        put(component("pagination")) { PaginationComponent().elements }
        put(component("switch")) { SwitchComponent().elements }
        put(component("tabs")) { TabsComponent().elements }
        put(component("tree-view")) { TreeViewComponent().elements }
        put(demo("user")) { UserDemo().elements }
    }

    fun behaviour(name: String) = "https://www.patternfly.org/v4/design-guidelines/usage-and-behavior/$name"

    fun component(id: String): String = "$DOCUMENTATION:component=$id"

    fun demo(id: String): String = "$DOCUMENTATION:demo=$id"

    fun lookup(place: String): () -> List<Element> = tags.getOrElse(place) { notFound(place) }

    private fun notFound(place: String): () -> List<Element> = {
        elements {
            pfSection("light".modifier()) {
                pfContent {
                    pfTitle { +"Not Found" }
                    p {
                        +"Page "
                        code { +place }
                        +" not found"
                    }
                }
            }
        }
    }
}
