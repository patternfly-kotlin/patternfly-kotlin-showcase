@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import org.patternfly.pfBrand
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.w3c.dom.HTMLElement

object BrandComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Brand",
                key = "Brand",
                text = " is used to place a product logotype on a screen."
            )
        })
        yield(render {
            pfSection {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", BrandCode.BASIC) {
                    pfBrand("./pf-logo.svg")
                }
            }
        })
    }
}

internal object BrandCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pfBrand("./pf-logo.svg")
    }
}
"""

}
