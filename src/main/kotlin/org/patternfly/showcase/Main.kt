package org.patternfly.showcase

import dev.fritz2.elemento.appendAll
import dev.fritz2.routing.Router
import dev.fritz2.routing.StringRoute
import kotlinext.js.require
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.dom.clear

fun main() {
    require("@github/time-elements/dist/time-elements")
    require("@patternfly/patternfly/patternfly.css")
    require("@patternfly/patternfly/patternfly-addons.css")
    require("clipboard/dist/clipboard")
    require("highlight.js/lib/core")
    require("highlight.js/lib/languages/kotlin")
    require("highlight.js/styles/github.css")

    val router = Router(StringRoute(Places.HOME))
    document.body?.appendAll(Skeleton(router).elements)

    MainScope().launch {
        router.collect { place ->
            document.querySelector("#main")?.let { main ->
                main.clear()
                main.appendAll(Places.lookup(place).invoke())
            }
        }
    }
}
