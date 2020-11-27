package org.patternfly.showcase.page

import dev.fritz2.dom.html.RenderContext
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.title

class GetStartedPage {
    val content: RenderContext.() -> Unit = {
        pageSection(baseClass = "light".modifier()) {
            title { +"Contribute" }
            p {
                +"Pending"
            }
        }
    }
}
