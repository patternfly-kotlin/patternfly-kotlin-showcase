package org.patternfly.showcase.component

import dev.fritz2.elemento.elements
import org.patternfly.pfContent
import org.patternfly.pfSection

class EmptyStateComponent {
    val elements = elements {
        intro(
            title = "Empty State",
            key = "Empty State",
            text = " is not yet implemented"
        )
        pfSection {
            pfContent {
                h2 { +"Examples" }
            }
            snippet("Basic", EmptyStateCode.BASIC) {
            }
        }
    }
}

internal object EmptyStateCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
    }
}
"""
}
