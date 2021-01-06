package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.DividerVariant.DIV
import org.patternfly.DividerVariant.HR
import org.patternfly.DividerVariant.LI
import org.patternfly.Size
import org.patternfly.classes
import org.patternfly.divider
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.spinner
import org.patternfly.util

object SpinnerComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Spinner",
            text = "A spinner is used to indicate to users that an action is in progress.",
            designGuidelines = "https://www.patternfly.org/v4/components/spinner/design-guidelines"
        )
        pageSection {
            snippet("Basic", SpinnerCode.BASIC) {
                spinner()
            }
            snippet("Size variations", SpinnerCode.SIZE_VARIATIONS) {
                spinner(Size.SM, baseClass = "mr-md".util())
                spinner(Size.MD, baseClass = "mr-md".util())
                spinner(Size.LG, baseClass = "mr-md".util())
                spinner(Size.XL)
            }
        }
    }
}

object SpinnerCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        spinner()
    }
}
"""

    //language=kotlin
    const val SIZE_VARIATIONS: String = """fun main() {
    render {
        spinner(Size.SM, baseClass = "mr-md".util())
        spinner(Size.MD, baseClass = "mr-md".util())
        spinner(Size.LG, baseClass = "mr-md".util())
        spinner(Size.XL)
    }
}
"""
}
