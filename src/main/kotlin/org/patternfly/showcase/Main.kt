package org.patternfly.showcase

import dev.fritz2.dom.html.render
import dev.fritz2.mvp.PlaceManager
import dev.fritz2.mvp.placeRequest
import org.patternfly.AlertGroup
import org.patternfly.pageSection
import org.patternfly.title

external fun require(name: String): dynamic

fun main() {
    require("@github/time-elements/dist/time-elements")
    require("@patternfly/patternfly/patternfly.css")
    require("@patternfly/patternfly/patternfly-addons.css")
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
    AlertGroup.addToastAlertGroup()
}

