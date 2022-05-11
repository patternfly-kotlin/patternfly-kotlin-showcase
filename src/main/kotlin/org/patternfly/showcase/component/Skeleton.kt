package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.Shape.CIRCLE
import org.patternfly.Shape.SQUARE
import org.patternfly.TextSize.LG
import org.patternfly.TextSize.MD
import org.patternfly.TextSize.SM
import org.patternfly.TextSize.XL
import org.patternfly.TextSize.XL_2
import org.patternfly.TextSize.XL_3
import org.patternfly.TextSize.XL_4
import org.patternfly.pageSection
import org.patternfly.skeleton

object SkeletonComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Skeleton",
            text = "A skeleton is a type of loading state that allows you to expose content incrementally.",
            designGuidelines = "https://www.patternfly.org/v4/components/skeleton/design-guidelines"
        )
        pageSection {
            snippet("Default", SkeletonCode.DEFAULT) {
                skeleton { +"Loading contents" }
            }
            snippet("Widths", SkeletonCode.WIDTH) {
                skeleton(width = "25%") { +"Loading contents" }
                br { }
                skeleton(width = "33%")
                br { }
                skeleton(width = "50%")
                br { }
                skeleton(width = "66%")
                br { }
                skeleton(width = "75%")
                br { }
                skeleton(width = "100%")
            }
            snippet("Heights", SkeletonCode.HEIGHT) {
                div {
                    inlineStyle("height: 400px; display: flex; align-items: flex-end; justify-content: space-between;")
                    skeleton(width = "15%", height = "25%") { +"Loading contents" }
                    skeleton(width = "15%", height = "33%")
                    skeleton(width = "15%", height = "50%")
                    skeleton(width = "15%", height = "66%")
                    skeleton(width = "15%", height = "75%")
                    skeleton(width = "15%", height = "100%")
                }
            }
            snippet("Text modifiers", SkeletonCode.TEXT) {
                div {
                    +"--pf-global--FontSize--4xl"
                    skeleton(textSize = XL_4) { +"Loading font size 4xl" }
                    br { }
                    +"--pf-global--FontSize--3xl"
                    skeleton(textSize = XL_3) { +"Loading font size 3xl" }
                    br { }
                    +"--pf-global--FontSize--2xl"
                    skeleton(textSize = XL_2) { +"Loading font size 2xl" }
                    br { }
                    +"--pf-global--FontSize--xl"
                    skeleton(textSize = XL) { +"Loading font size xl" }
                    br { }
                    +"--pf-global--FontSize--lg"
                    skeleton(textSize = LG) { +"Loading font size lg" }
                    br { }
                    +"--pf-global--FontSize--md"
                    skeleton(textSize = MD) { +"Loading font size md" }
                    br { }
                    +"--pf-global--FontSize--sm"
                    skeleton(textSize = SM) { +"Loading font size sm" }
                }
            }
            snippet("Shapes", SkeletonCode.SHAPE) {
                div {
                    +"Small circle"
                    skeleton(shape = CIRCLE, width = "15%") { +"Loading small circle contents" }
                    br { }
                    +"Medium circle"
                    skeleton(shape = CIRCLE, width = "30%") { +"Loading medium circle contents" }
                    br { }
                    +"Large circle"
                    skeleton(shape = CIRCLE, width = "50%") { +"Loading large circle contents" }
                    br { }
                    +"Small square"
                    skeleton(shape = SQUARE, width = "15%") { +"Loading small square contents" }
                    br { }
                    +"Medium square"
                    skeleton(shape = SQUARE, width = "30%") { +"Loading medium square contents" }
                    br { }
                    +"Large square"
                    skeleton(shape = SQUARE, width = "50%") { +"Loading large square contents" }
                    br { }
                    +"Small rectangle"
                    div {
                        inlineStyle("height: 200px")
                        skeleton(width = "50%", height = "50%") { +"Loading small rectangle contents" }
                    }
                    br { }
                    +"Medium rectangle"
                    div {
                        inlineStyle("height: 200px")
                        skeleton(width = "75%", height = "75%") { +"Loading medium rectangle contents" }
                    }
                    br { }
                    +"Large rectangle"
                    div {
                        inlineStyle("height: 200px")
                        skeleton(height = "100%") { +"Loading large rectangle contents" }
                    }
                }
            }
        }
    }
}

object SkeletonCode {

    //language=kotlin
    const val DEFAULT: String = """fun main() {
    render {
        skeleton { +"Loading contents" }
    }
}
"""

    //language=kotlin
    const val WIDTH: String = """fun main() {
    render {
        skeleton(width = "25%") { +"Loading contents" }
        br { }
        skeleton(width = "33%")
        br { }
        skeleton(width = "50%")
        br { }
        skeleton(width = "66%")
        br { }
        skeleton(width = "75%")
        br { }
        skeleton(width = "100%")
    }
}
"""

    //language=kotlin
    const val HEIGHT: String = """fun main() {
    render {
        div {
            inlineStyle("height: 400px; display: flex; align-items: flex-end; justify-content: space-between;")
            skeleton(width = "15%", height = "25%") { +"Loading contents" }
            skeleton(width = "15%", height = "33%")
            skeleton(width = "15%", height = "50%")
            skeleton(width = "15%", height = "66%")
            skeleton(width = "15%", height = "75%")
            skeleton(width = "15%", height = "100%")
        }
    }
}
"""

    //language=kotlin
    const val TEXT: String = """fun main() {
    render {
        div {
            +"--pf-global--FontSize--4xl"
            skeleton(textSize = XL_4) { +"Loading font size 4xl" }
            br { }
            +"--pf-global--FontSize--3xl"
            skeleton(textSize = XL_3) { +"Loading font size 3xl" }
            br { }
            +"--pf-global--FontSize--2xl"
            skeleton(textSize = XL_2) { +"Loading font size 2xl" }
            br { }
            +"--pf-global--FontSize--xl"
            skeleton(textSize = XL) { +"Loading font size xl" }
            br { }
            +"--pf-global--FontSize--lg"
            skeleton(textSize = LG) { +"Loading font size lg" }
            br { }
            +"--pf-global--FontSize--md"
            skeleton(textSize = MD) { +"Loading font size md" }
            br { }
            +"--pf-global--FontSize--sm"
            skeleton(textSize = SM) { +"Loading font size sm" }
        }
    }
}
"""

    //language=kotlin
    const val SHAPE: String = """fun main() {
    render {
        div {
            +"Small circle"
            skeleton(shape = CIRCLE, width = "15%") { +"Loading small circle contents" }
            br { }
            +"Medium circle"
            skeleton(shape = CIRCLE, width = "30%") { +"Loading medium circle contents" }
            br { }
            +"Large circle"
            skeleton(shape = CIRCLE, width = "50%") { +"Loading large circle contents" }
            br { }
            +"Small square"
            skeleton(shape = SQUARE, width = "15%") { +"Loading small square contents" }
            br { }
            +"Medium square"
            skeleton(shape = SQUARE, width = "30%") { +"Loading medium square contents" }
            br { }
            +"Large square"
            skeleton(shape = SQUARE, width = "50%") { +"Loading large square contents" }
            br { }
            +"Small rectangle"
            div {
                inlineStyle("height: 200px")
                skeleton(width = "50%", height = "50%") { +"Loading small rectangle contents" }
            }
            br { }
            +"Medium rectangle"
            div {
                inlineStyle("height: 200px")
                skeleton(width = "75%", height = "75%") { +"Loading medium rectangle contents" }
            }
            br { }
            +"Large rectangle"
            div {
                inlineStyle("height: 200px")
                skeleton(height = "100%") { +"Loading large rectangle contents" }
            }
        }
    }
}
"""
}
