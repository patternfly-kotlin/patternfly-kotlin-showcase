package org.patternfly.showcase

import dev.fritz2.dom.html.RenderContext
import org.patternfly.mvp.PlaceManager
import org.patternfly.mvp.managedBy
import org.patternfly.mvp.placeRequest
import org.patternfly.navigation
import org.patternfly.page
import org.patternfly.showcase.component.components

fun RenderContext.skeleton(placeManager: PlaceManager) {
    page {
        masthead {
            brand("#home") {
                src(require("header-logo.svg") as String)
            }
//            toolbar {
//                notificationBadge()
//            }
        }
        sidebar {
            navigation(placeManager, expandable = true) {
                item(placeRequest(GET_STARTED), "Get Started")
                item(placeRequest(GET_IN_TOUCH), "Get in Touch")
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
        main {
            managedBy(placeManager)
        }
    }
}
