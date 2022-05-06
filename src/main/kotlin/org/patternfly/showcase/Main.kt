package org.patternfly.showcase

import dev.fritz2.dom.html.render
import org.patternfly.mvp.PlaceManager
import org.patternfly.mvp.placeRequest
import org.patternfly.pageSection
import org.patternfly.title

external fun require(name: String): dynamic

fun main() {
    require("showcase.scss")
    require("@github/time-elements/dist/time-elements")
    require("@patternfly/patternfly/patternfly.scss")
    require("@patternfly/patternfly/patternfly-addons.scss")
    require("clipboard/dist/clipboard")
    require("highlight.js/lib/core")
    require("highlight.js/lib/languages/kotlin")
    require("highlight.js/styles/github.css")

    registerPresenters()
    val placeManager = PlaceManager(placeRequest(HOME)) { placeRequest ->
        pageSection {
            title { +"Not Found" }
            p {
                +"Page "
                code { +placeRequest.token }
                +" not found"
            }
        }
    }

    render {
        skeleton(placeManager)
    }
}
