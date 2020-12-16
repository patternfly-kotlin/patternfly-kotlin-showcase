package org.patternfly.showcase

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.mvp.PlaceManager
import dev.fritz2.mvp.managedBy
import dev.fritz2.mvp.placeRequest
import kotlinx.coroutines.flow.map
import org.patternfly.AlertGroup
import org.patternfly.Page
import org.patternfly.brand
import org.patternfly.expandableGroup
import org.patternfly.headerTools
import org.patternfly.horizontalNavigation
import org.patternfly.layout
import org.patternfly.navigationItem
import org.patternfly.navigationItems
import org.patternfly.notificationBadge
import org.patternfly.page
import org.patternfly.pageHeader
import org.patternfly.pageMain
import org.patternfly.pageSidebar
import org.patternfly.showcase.component.components
import org.patternfly.sidebarBody
import org.patternfly.verticalNavigation

val CODE_STYLE = """
    padding: 16px;
    overflow: auto;
    line-height: 1.45;
    background-color: #f3f3f3;
    border-radius: 6px;
""".trimMargin()

class Skeleton(private val placeManager: PlaceManager) {

    val content: RenderContext.() -> Page = {
        page {
            pageHeader {
                brand {
                    home("#home")
                    img(src = "./header-logo.svg")
                }
                horizontalNavigation(placeManager.router) {
                    navigationItems {
                        navigationItem(placeRequest(GET_STARTED), "Get Started")
                        navigationItem(
                            item = placeRequest(COMPONENT, "id" to "alert"),
                            text = "Documentation",
                            selected = sidebarToken
                        )
                        navigationItem(placeRequest(GET_IN_TOUCH), "Get in Touch")
                    }
                }
                headerTools {
                    div("toolbar".layout()) {
                        div("toolbar".layout("group")) {
                            div("toolbar".layout("item")) {
                                notificationBadge()
                            }
                        }
                    }
                }
            }
            pageSidebar {
                visible(placeManager.placeRequests.map { sidebarToken(it) })
                sidebarBody {
                    verticalNavigation(placeManager.router) {
                        navigationItems {
                            expandableGroup("Components") {
                                components.map {
                                    navigationItem(placeRequest(COMPONENT, "id" to it.id), it.name)
                                }
                            }
                            expandableGroup("Demos") {
                                navigationItem(placeRequest(USER_DEMO), "User")
                            }
                        }
                    }
                }
            }
            pageMain(id = "main") {
                managedBy(placeManager)
            }
        }
    }

    init {
        AlertGroup.addToastAlertGroup()
    }
}
