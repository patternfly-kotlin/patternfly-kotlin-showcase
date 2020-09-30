package org.patternfly.showcase.component

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.patternfly.Elements
import org.patternfly.elements
import org.patternfly.pfContent
import org.patternfly.pfSection
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
class EmptyStateComponent : Elements {
    override val elements = elements {
        intro(
            title = "Empty State",
            key = "Empty State",
            text = "Pending"
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
