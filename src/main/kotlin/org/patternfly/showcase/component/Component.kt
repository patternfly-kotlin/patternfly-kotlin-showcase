package org.patternfly.showcase.component

import dev.fritz2.mvp.PlaceRequest
import dev.fritz2.mvp.Presenter
import dev.fritz2.mvp.View
import dev.fritz2.mvp.ViewContent
import dev.fritz2.mvp.WithPresenter
import org.patternfly.pageSection
import org.patternfly.title

val components = listOf(
    Component("accordion", "Accordion", AccordionComponent.content),
    Component("alert", "Alert", AlertComponent.content),
    Component("alert-group", "Alert group", AlertGroupComponent.content),
    Component("badge", "Badge", BadgeComponent.content),
    Component("button", "Button", ButtonComponent.content),
    Component("card", "Card", CardComponent.content),
    Component("chip", "Chip", ChipComponent.content),
    Component("chip-group", "Chip group", ChipGroupComponent.content),
    Component("data-list", "Data list", DataListComponent.content),
    Component("divider", "Divider", DividerComponent.content),
    Component("drawer", "Drawer", DrawerComponent.content),
    Component("dropdown", "Dropdown", DropdownComponent.content),
    Component("empty-state", "Empty state", EmptyStateComponent.content),
    Component("navigation", "Navigation", NavigationComponent.content),
    Component("options-menu", "Options menu", OptionsMenuComponent.content),
    Component("pagination", "Pagination", PaginationComponent.content),
    Component("spinner", "Spinner", SpinnerComponent.content),
    Component("switch", "Switch", SwitchComponent.content),
    Component("tabs", "Tabs", TabsComponent.content),
    Component("text", "Text", TextContentComponent.content),
    Component("title", "Title", TitleComponent.content),
    Component("tree-view", "Tree view", TreeViewComponent.content),
)

data class Component(val id: String, val name: String, val content: ViewContent)

class ComponentPresenter : Presenter<ComponentView> {

    internal var component: String? = null
    internal var content: ViewContent? = null
    override val view: ComponentView = ComponentView(this)

    override fun prepareFromRequest(place: PlaceRequest) {
        component = place.params["id"]
        content = components.find { it.id == component }?.content
    }
}

class ComponentView(override val presenter: ComponentPresenter) : View, WithPresenter<ComponentPresenter> {
    override val content: ViewContent
        get() = presenter.content ?: {
            pageSection {
                title { +"Not Found" }
                p {
                    +"Component "
                    presenter.component?.let {
                        code { +it }
                    }
                    +" not found"
                }
            }
        }
}
