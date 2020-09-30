package org.patternfly.showcase

import dev.fritz2.binding.const
import org.patternfly.Elements
import org.patternfly.elements
import org.patternfly.modifier
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.patternfly.pfTitle

class HomePage : Elements {
    override val elements = elements {
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
    }
}
