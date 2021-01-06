@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.accordionContent
import org.patternfly.accordionDiv
import org.patternfly.accordionDl
import org.patternfly.accordionItem
import org.patternfly.accordionTitle
import org.patternfly.pageSection

object AccordionComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Accordion",
            text = "An accordion is used to deliver a lot of content in a small space, allowing the user to expand and collapse the component to show or hide information.",
            designGuidelines = "https://www.patternfly.org/v4/components/accordion/design-guidelines"
        )
        pageSection {
            snippet("Definition list", AccordionCode.DL) {
                accordionDl {
                    accordionItem {
                        accordionTitle { +"Item one" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem(expanded = true) {
                        accordionTitle { +"Item two" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item three" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item four" }
                        accordionContent {
                            p { +loremIpsum(10) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item five" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                }
            }
            snippet("Header and Div", AccordionCode.DIV) {
                accordionDiv {
                    accordionItem {
                        accordionTitle { +"Item one" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem(expanded = true) {
                        accordionTitle { +"Item two" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item three" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item four" }
                        accordionContent {
                            p { +loremIpsum(10) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item five" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                }
            }
            snippet("Multiple expand", AccordionCode.MULTI_EXPAND) {
                accordionDl(singleExpand = false) {
                    accordionItem {
                        accordionTitle { +"Item one" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem(expanded = true) {
                        accordionTitle { +"Item two" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item three" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item four" }
                        accordionContent {
                            p { +loremIpsum(10) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item five" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                }
            }
            snippet("Fixed height", AccordionCode.FIXED_HEIGHT) {
                accordionDl(fixed = true) {
                    accordionItem {
                        accordionTitle { +"Item one" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem(expanded = true) {
                        accordionTitle { +"Item two" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item three" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item four" }
                        accordionContent {
                            p { +loremIpsum(20) }
                        }
                    }
                    accordionItem {
                        accordionTitle { +"Item five" }
                        accordionContent {
                            p { +loremIpsum(2) }
                        }
                    }
                }
            }
        }
    }
}

object AccordionCode {

    //language=kotlin
    const val DL: String = """fun main() {
    render {
        accordionDl {
            accordionItem {
                accordionTitle { +"Item one" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem(expanded = true) {
                accordionTitle { +"Item two" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem {
                accordionTitle { +"Item three" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem {
                accordionTitle { +"Item four" }
                accordionContent {
                    p { +loremIpsum(10) }
                }
            }
            accordionItem {
                accordionTitle { +"Item five" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val DIV: String = """fun main() {
    render {
        accordionDiv {
            accordionItem {
                accordionTitle { +"Item one" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem(expanded = true) {
                accordionTitle { +"Item two" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem {
                accordionTitle { +"Item three" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem {
                accordionTitle { +"Item four" }
                accordionContent {
                    p { +loremIpsum(10) }
                }
            }
            accordionItem {
                accordionTitle { +"Item five" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val MULTI_EXPAND: String = """fun main() {
    render {
        accordionDl(singleExpand = false) {
            accordionItem {
                accordionTitle { +"Item one" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem(expanded = true) {
                accordionTitle { +"Item two" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem {
                accordionTitle { +"Item three" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem {
                accordionTitle { +"Item four" }
                accordionContent {
                    p { +loremIpsum(10) }
                }
            }
            accordionItem {
                accordionTitle { +"Item five" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val FIXED_HEIGHT: String = """fun main() {
    render {
        accordionDl(fixed = true) {
            accordionItem {
                accordionTitle { +"Item one" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem(expanded = true) {
                accordionTitle { +"Item two" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem {
                accordionTitle { +"Item three" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
            accordionItem {
                accordionTitle { +"Item four" }
                accordionContent {
                    p { +loremIpsum(20) }
                }
            }
            accordionItem {
                accordionTitle { +"Item five" }
                accordionContent {
                    p { +loremIpsum(2) }
                }
            }
        }
    }
}
"""
}
