package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.DividerVariant.DIV
import org.patternfly.DividerVariant.HR
import org.patternfly.DividerVariant.LI
import org.patternfly.classes
import org.patternfly.divider
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection

object DividerComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Divider",
            text = "A divider is a horizontal separator that creates space or groups items within a page or list.",
            designGuidelines = "https://www.patternfly.org/v4/components/divider/design-guidelines"
        )
        pageSection {
            snippet("Using hr", DividerCode.HR) {
                divider(HR)
            }
            snippet("Using li", DividerCode.LI) {
                ul {
                    li { +"First item" }
                    divider(LI)
                    li { +"Second item" }
                }
            }
            snippet("Using div", DividerCode.DIV) {
                divider(DIV)
            }
            snippet("Inset medium", DividerCode.INSET_MEDIUM) {
                divider(DIV, baseClass = "inset-md".modifier())
            }
            snippet("Inset at various breakpoints", DividerCode.INSET_BREAKPOINTS) {
                divider(DIV, baseClass = classes {
                    +"inset-md".modifier()
                    +"inset-none-on-md".modifier()
                    +"inset-3xl-on-lg".modifier()
                    +"inset-lg-on-xl".modifier()
                })
            }
            snippet("Vertical in flex layout", DividerCode.VERTICAL) {
                div(baseClass = "flex".layout()) {
                    div { +"First item" }
                    divider(vertical = true)
                    div { +"Second item" }
                }
            }
            snippet("Vertical, inset medium in flex layout", DividerCode.VERTICAL_INSET) {
                div(baseClass = "flex".layout()) {
                    div { +"First item" }
                    divider(vertical = true, baseClass = "inset-md".modifier())
                    div { +"Second item" }
                }
            }
            snippet("Vertical, inset at various breakpoints", DividerCode.VERTICAL_INSET_BREAKPOINTS) {
                div(baseClass = "flex".layout()) {
                    div { +"First item" }
                    divider(vertical = true, baseClass = classes {
                        +"inset-md".modifier()
                        +"inset-none-on-md".modifier()
                        +"inset-sm-on-lg".modifier()
                        +"inset-xs-on-xl".modifier()
                    })
                    div { +"Second item" }
                }
            }
        }
    }
}

object DividerCode {

    //language=kotlin
    const val HR: String = """fun main() {
    render {
        divider(HR)
    }
}
"""

    //language=kotlin
    const val LI: String = """fun main() {
    render {
        ul {
            li { +"First item" }
            divider(LI)
            li { +"Second item" }
        }
    }
}
"""

    //language=kotlin
    const val DIV: String = """fun main() {
    render {
        divider(DIV)
    }
}
"""

    //language=kotlin
    const val INSET_MEDIUM: String = """fun main() {
    render {
        divider(DIV, baseClass = "inset-md".modifier())
    }
}
"""

    //language=kotlin
    const val INSET_BREAKPOINTS: String = """fun main() {
    render {
        divider(DIV, baseClass = classes {
            +"inset-md".modifier()
            +"inset-none-on-md".modifier()
            +"inset-3xl-on-lg".modifier()
            +"inset-lg-on-xl".modifier()
        })
    }
}
"""

    //language=kotlin
    const val VERTICAL: String = """fun main() {
    render {
        div(baseClass = "flex".layout()) {
            div { +"First item" }
            divider(vertical = true)
            div { +"Second item" }
        }
    }
}
"""

    //language=kotlin
    const val VERTICAL_INSET: String = """fun main() {
    render {
        div(baseClass = "flex".layout()) {
            div { +"First item" }
            divider(vertical = true, baseClass = "inset-md".modifier())
            div { +"Second item" }
        }
    }
}
"""

    //language=kotlin
    const val VERTICAL_INSET_BREAKPOINTS: String = """fun main() {
    render {
        div(baseClass = "flex".layout()) {
            div { +"First item" }
            divider(vertical = true, baseClass = classes {
                +"inset-md".modifier()
                +"inset-none-on-md".modifier()
                +"inset-sm-on-lg".modifier()
                +"inset-xs-on-xl".modifier()
            })
            div { +"Second item" }
        }
    }
}
"""
}
