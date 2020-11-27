package org.patternfly.showcase.page

import dev.fritz2.dom.html.RenderContext
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.title

class HomePage  {
    val content: RenderContext.() -> Unit = {
        pageSection(baseClass = "light".modifier()) {
            title { +"PatternFly Fritz2" }
            p {
                a {
                    +"PatternFly Fritz2"
                    href("https://github.com/patternfly-kotlin/patternfly-fritz2")
                }
                +" is a "
                a {
                    +"Kotlin / JS"
                    href("https://kotl.in/js")
                }
                +" implementation of "
                a {
                    +"PatternFly"
                    href("https://www.patternfly.org//")
                }
                +" based on "
                a {
                    +"fritz2"
                    href("https://www.fritz2.dev/")
                }
                +"."
            }
        }
    }
}
