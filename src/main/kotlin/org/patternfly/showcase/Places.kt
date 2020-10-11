package org.patternfly.showcase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.patternfly.Elements
import org.patternfly.elements
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
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
object Places {

    const val CONTRIBUTE = "contribute"
    const val DOCUMENTATION = "documentation"
    const val GET_IN_TOUCH = "get-in-touch"
    const val GET_STARTED = "get-started"
    const val HOME = "home"

    private val tags: Map<String, () -> Elements> = buildMap {
        put("home", ::HomePage)
        put(component("alert"), ::AlertComponent)
        put(component("alert-group"), ::AlertGroupComponent)
        put(component("badge"), ::BadgeComponent)
        put(component("brand"), ::BrandComponent)
        put(component("button"), ::ButtonComponent)
        put(component("card"), ::CardComponent)
        put(component("chip"), ::ChipComponent)
        put(component("chip-group"), ::ChipGroupComponent)
        put(component("content"), ::ContentComponent)
        put(component("drawer"), ::DrawerComponent)
        put(component("data-list"), ::DataListComponent)
        put(component("dropdown"), ::DropdownComponent)
        put(component("empty-state"), ::EmptyStateComponent)
        put(component("options-menu"), ::OptionsMenuComponent)
        put(component("pagination"), ::PaginationComponent)
        put(component("switch"), ::SwitchComponent)
        put(component("tabs"), ::TabsComponent)
        put(component("tree-view"), ::TreeViewComponent)
        put(demo("user"), ::UserDemo)
    }

    fun behaviour(name: String) = "https://www.patternfly.org/v4/design-guidelines/usage-and-behavior/$name"

    fun component(id: String): String = "$DOCUMENTATION:component=$id"

    fun demo(id: String): String = "$DOCUMENTATION:demo=$id"

    fun lookup(place: String): () -> Elements = tags.getOrElse(place) { notFound(place) }

    private fun notFound(place: String): () -> Elements = {
        object : Elements {
            override val elements = elements {
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
}
