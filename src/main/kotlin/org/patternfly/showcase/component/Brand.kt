package org.patternfly.showcase.component

import dev.fritz2.elemento.elements
import org.patternfly.pfBrand
import org.patternfly.pfContent
import org.patternfly.pfSection

class BrandComponent {
    val elements = elements {
        intro(
            title = "Brand",
            key = "Brand",
            text = " is used to place a product logotype on a screen."
        )
        pfSection {
            pfContent {
                h2 { +"Examples" }
            }
            snippet("Basic", BrandCode.BASIC) {
                pfBrand("./pf-logo.svg")
            }
        }
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
