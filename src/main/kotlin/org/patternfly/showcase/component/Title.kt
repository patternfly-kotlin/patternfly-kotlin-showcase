package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.Level
import org.patternfly.Level.H1
import org.patternfly.Level.H2
import org.patternfly.Level.H3
import org.patternfly.Level.H4
import org.patternfly.Level.H5
import org.patternfly.Level.H6
import org.patternfly.Size
import org.patternfly.Size.LG
import org.patternfly.Size.MD
import org.patternfly.Size.XL
import org.patternfly.Size.XL_2
import org.patternfly.Size.XL_3
import org.patternfly.Size.XL_4
import org.patternfly.pageSection
import org.patternfly.textContent
import org.patternfly.title

object TitleComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Title",
            text = "A title component applies top and bottom margins, font-weight, font-size, and line-height to titles.",
            designGuidelines = "https://www.patternfly.org/v4/components/title/design-guidelines"
        )
        pageSection {
            snippet("Sizes", TitleCode.SIZES) {
                title(H1, XL_4) { +"4xl Title" }
                title(H2, XL_3) { +"3xl Title" }
                title(H3, XL_2) { +"2xl Title" }
                title(H4, XL) { +"xl Title" }
                title(H5, LG) { +"lg Title" }
                title(H6, MD) { +"md Title" }
            }
            snippet("Default size mappings", TitleCode.DEFAULT_SIZES) {
                title(H1) { +"h1 default to 2xl" }
                title(H2) { +"h2 defaults to xl" }
                title(H3) { +"h3 defaults to lg" }
                title(H4) { +"h4 defaults to md" }
                title(H5) { +"h5 defaults to md" }
                title(H6) { +"h6 defaults to md" }
            }
        }
    }
}

object TitleCode {

    //language=kotlin
    const val SIZES: String = """fun main() {
    render {
        title(H1, XL_4) { +"4xl Title" }
        title(H2, XL_3) { +"3xl Title" }
        title(H3, XL_2) { +"2xl Title" }
        title(H4, XL) { +"xl Title" }
        title(H5, LG) { +"lg Title" }
        title(H6, MD) { +"md Title" }
    }
}
"""

    //language=kotlin
    const val DEFAULT_SIZES: String = """fun main() {
    render {
        title(H1) { +"h1 default to 2xl" }
        title(H2) { +"h2 defaults to xl" }
        title(H3) { +"h3 defaults to lg" }
        title(H4) { +"h4 defaults to md" }
        title(H5) { +"h5 defaults to md" }
        title(H6) { +"h6 defaults to md" }
    }
}
"""
}
