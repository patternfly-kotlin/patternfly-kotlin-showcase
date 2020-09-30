package org.patternfly.showcase.component

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.patternfly.Elements
import org.patternfly.elements
import org.patternfly.pfBrand
import org.patternfly.pfContent
import org.patternfly.pfSection
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
class BrandComponent : Elements {
    override val elements = elements {
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
