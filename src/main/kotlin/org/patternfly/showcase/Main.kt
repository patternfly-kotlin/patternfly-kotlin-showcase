package org.patternfly.showcase

import dev.fritz2.dom.appendToBody
import dev.fritz2.dom.html.renderElement
import dev.fritz2.routing.Router
import dev.fritz2.routing.StringRoute
import kotlinext.js.require
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.dom.clear
import org.patternfly.pageMain
import org.patternfly.pageSidebar

fun main() {
    require("@github/time-elements/dist/time-elements")
    require("@patternfly/patternfly/patternfly.css")
    require("@patternfly/patternfly/patternfly-addons.css")
    require("clipboard/dist/clipboard")
    require("highlight.js/lib/core")
    require("highlight.js/lib/languages/kotlin")
    require("highlight.js/styles/github.css")

    val router = Router(StringRoute("home"))
    appendToBody(renderElement { Skeleton(router).content(this) })
    document.pageSidebar()?.visible(router.map { it.startsWith("documentation:") })

    MainScope().launch {
        router.collect { place ->
            document.pageMain()?.let {
                it.domNode.clear()
                Place.lookup(place).invoke(it)
            }
        }
    }
}
