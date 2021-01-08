package org.patternfly.showcase

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.mvp.PlaceManager
import dev.fritz2.mvp.managedBy
import dev.fritz2.mvp.placeRequest
import org.patternfly.AlertGroup
import org.patternfly.Page
import org.patternfly.brand
import org.patternfly.group
import org.patternfly.groups
import org.patternfly.headerTools
import org.patternfly.item
import org.patternfly.layout
import org.patternfly.notificationBadge
import org.patternfly.page
import org.patternfly.pageHeader
import org.patternfly.pageMain
import org.patternfly.pageSidebar
import org.patternfly.showcase.component.components
import org.patternfly.sidebarBody
import org.patternfly.verticalNavigation

class Skeleton(private val placeManager: PlaceManager) {

    val content: RenderContext.() -> Page = {
        page {
            pageHeader {
                brand {
                    home("#home")
                    img(src = "./header-logo.svg")
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
                sidebarBody {
                    verticalNavigation(placeManager.router, expandable = true) {
                        groups {
                            group {
                                item(placeRequest(GET_STARTED), "Get Started")
                                item(placeRequest(GET_IN_TOUCH), "Get in Touch")
                            }
                            group("Components") {
                                components.map {
                                    item(placeRequest(COMPONENT, "id" to it.id), it.name)
                                }
                            }
                            group("Demos") {
                                item(placeRequest(USER_DEMO), "User")
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
