package org.patternfly.showcase

import dev.fritz2.dom.html.RenderContext
import dev.fritz2.routing.Router
import org.patternfly.Page
import org.patternfly.brand
import org.patternfly.expandableGroup
import org.patternfly.headerTools
import org.patternfly.horizontalNavigation
import org.patternfly.installToastAlertGroup
import org.patternfly.layout
import org.patternfly.navigationItem
import org.patternfly.navigationItems
import org.patternfly.notificationBadge
import org.patternfly.page
import org.patternfly.pageHeader
import org.patternfly.pageMain
import org.patternfly.pageSidebar
import org.patternfly.verticalNavigation

class Skeleton(private val router: Router<String>) {

    val content: RenderContext.() -> Page = {
        page {
            pageHeader {
                brand {
                    home("#home")
                    img(src = "./header-logo.svg")
                }
                horizontalNavigation(router) {
                    navigationItems {
                        navigationItem("get-started", "Get Started")
                        navigationItem(item = "documentation:component=alert", text = "Documentation") {
                            it.startsWith("documentation:")
                        }
                        navigationItem("contribute", "Contribute")
                        navigationItem("get-in-touch", "Get in Touch")
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
                verticalNavigation(router) {
                    navigationItems {
                        expandableGroup("Components") {
                            components.map { navigationItem(it.place, it.name) }
                        }
                        expandableGroup("Demos") {
                            demos.map { navigationItem(it.place, it.name) }
                        }
                    }
                }
            }
            pageMain(id = "main")
        }
    }

    init {
        installToastAlertGroup()
    }
}
