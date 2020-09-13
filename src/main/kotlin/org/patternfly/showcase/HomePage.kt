package org.patternfly.showcase

import dev.fritz2.binding.const
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import org.patternfly.modifier
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.patternfly.pfTitle
import org.w3c.dom.HTMLElement

object HomePage : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            pfSection("light".modifier()) {
                pfContent {
                    pfTitle { +"PatternFly Fritz2" }
                    p {
                        a {
                            +"PatternFly Fritz2"
                            href = const("https://github.com/patternfly-kotlin/patternfly-fritz2")
                        }
                        +" is a "
                        a {
                            +"Kotlin / JS"
                            href = const("https://kotl.in/js")
                        }
                        +" implementation of "
                        a {
                            +"PatternFly"
                            href = const("https://www.patternfly.org//")
                        }
                        +" based on "
                        a {
                            +"fritz2"
                            href = const("https://www.fritz2.dev/")
                        }
                        +"."
                    }
                }
            }
        })
    }
}
