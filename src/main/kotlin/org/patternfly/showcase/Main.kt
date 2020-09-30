package org.patternfly.showcase

import dev.fritz2.routing.Router
import dev.fritz2.routing.StringRoute
import kotlinext.js.require
import kotlinx.browser.document
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.dom.clear
import org.patternfly.appendAll
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
fun main() {
    require("@patternfly/patternfly/patternfly.css")
    require("@patternfly/patternfly/patternfly-addons.css")
    require("clipboard/dist/clipboard")
    require("highlight.js/lib/core")
    require("highlight.js/lib/languages/kotlin")
    require("highlight.js/styles/github.css")

    val router = Router(StringRoute(Places.HOME))
    document.body?.appendAll(Skeleton(router))

    MainScope().launch {
        router.collect { place ->
            document.querySelector("#main")?.let { main ->
                main.clear()
                main.appendAll(Places.lookup(place).invoke())
            }
        }
    }
}
