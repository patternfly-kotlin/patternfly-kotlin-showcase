package org.patternfly.showcase

import dev.fritz2.dom.appendToBody
import dev.fritz2.dom.html.renderElement
import dev.fritz2.mvp.PlaceManager
import dev.fritz2.mvp.placeRequest
import kotlinext.js.require
import org.patternfly.pageSection
import org.patternfly.title

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

    appendToBody(renderElement {
        Skeleton(placeManager).content(this)
    })
}
