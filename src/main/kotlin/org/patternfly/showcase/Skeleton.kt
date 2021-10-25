package org.patternfly.showcase

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.mvp.PlaceManager
import dev.fritz2.mvp.managedBy
import dev.fritz2.mvp.placeRequest
import org.patternfly.group
import org.patternfly.groups
import org.patternfly.item
import org.patternfly.page
import org.patternfly.showcase.component.components
import org.patternfly.verticalNavigation

fun RenderContext.skeleton(placeManager: PlaceManager) {
    page {
        masthead {
            brand("#home") {
                src("./header-logo.svg")
            }
//            toolbar {
//                notificationBadge()
//            }
        }
        sidebar {
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
        main {
            managedBy(placeManager)
        }
    }
}
