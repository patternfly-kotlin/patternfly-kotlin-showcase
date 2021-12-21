@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.RootStore
import dev.fritz2.dom.html.RenderContext
import org.patternfly.ButtonVariant.primary
import org.patternfly.Severity.INFO
import org.patternfly.accordion
import org.patternfly.clickButton
import org.patternfly.notification
import org.patternfly.pageSection
import org.patternfly.util

object AccordionComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Accordion",
            text = "An accordion is used to deliver a lot of content in a small space, allowing the user to expand and collapse the component to show or hide information.",
            designGuidelines = "https://www.patternfly.org/v4/components/accordion/design-guidelines"
        )
        pageSection {
            snippet("Basic", AccordionCode.BASIC) {
                accordion {
                    item("Item one") {
                        content {
                            p { +loremIpsum(2) }
                        }
                        events {
                            clicks handledBy notification(INFO, "Clicked")
                            excos handledBy notification(INFO) { expanded ->
                                title("Expanded: $expanded")
                            }
                        }
                    }
                    item("Item two") {
                        expanded(true)
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                    item("Item three") {
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                    item("Item four") {
                        content {
                            p { +loremIpsum(10) }
                        }
                    }
                    item("Item five") {
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                }
            }
            snippet("Single expand", AccordionCode.SINGLE_EXPAND) {
                accordion {
                    singleExpand(true)
                    item("Item one") {
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                    item("Item two") {
                        expanded(true)
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                    item("Item three") {
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                    item("Item four") {
                        content {
                            p { +loremIpsum(10) }
                        }
                    }
                    item("Item five") {
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                }
            }
            snippet("Fixed height", AccordionCode.FIXED_HEIGHT) {
                accordion {
                    fixed(true)
                    item("Item one") {
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                    item("Item two") {
                        expanded(true)
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                    item("Item three") {
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                    item("Item four") {
                        content {
                            p { +loremIpsum(20) }
                        }
                    }
                    item("Item five") {
                        content {
                            p { +loremIpsum(2) }
                        }
                    }
                }
            }
            snippet("Reactive", "n/a") {
                val paragraphs = object : RootStore<List<String>>(listOf("Paragraph 1", "Paragraph 2", "Paragraph 3")) {
                    var count = 3

                    val addParagraph = handle { paragraphs ->
                        count++
                        paragraphs + "Paragraph $count"
                    }

                    val removeParagraph = handle<String> { paragraphs, paragraph ->
                        if (paragraphs.isNotEmpty()) {
                            paragraphs.minus(paragraph)
                        } else {
                            paragraphs
                        }
                    }
                }

                accordion {
                    item("First paragraph") {
                        content {
                            p { +"Static, non-removable paragraph" }
                        }
                    }
                    items(paragraphs) { paragraph ->
                        item(paragraph) {
                            title(paragraph)
                            content {
                                p { +loremIpsum(3) }
                                p {
                                    a {
                                        +"Remove paragraph"
                                        clicks.map { paragraph } handledBy paragraphs.removeParagraph
                                    }
                                }
                            }
                        }
                    }
                    item("Last paragraph") {
                        content {
                            p { +"Static, non-removable paragraph" }
                        }
                    }
                }
                clickButton(primary, baseClass = "mt-md".util()) { +"Add paragraph" } handledBy paragraphs.addParagraph
            }
        }
    }
}

object AccordionCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        accordion {
            item {
                title("Item one")
                content {
                    p { +loremIpsum(2) }
                }
            }
            item {
                expanded(true)
                title("Item two")
                content {
                    p { +loremIpsum(2) }
                }
            }
            item {
                title("Item three")
                content {
                    p { +loremIpsum(2) }
                }
            }
            item {
                title("Item four")
                content {
                    p { +loremIpsum(10) }
                }
            }
            item {
                title("Item five")
                content {
                    p { +loremIpsum(2) }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val SINGLE_EXPAND: String = """fun main() {
    render {
        accordion {
            singleExpand(true)
            item {
                title("Item one")
                content {
                    p { +loremIpsum(2) }
                }
            }
            item {
                expanded(true)
                title("Item two")
                content {
                    p { +loremIpsum(2) }
                }
            }
            item {
                title("Item three")
                content {
                    p { +loremIpsum(2) }
                }
            }
            item {
                title("Item four")
                content {
                    p { +loremIpsum(10) }
                }
            }
            item {
                title("Item five")
                content {
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
        accordion {
            fixed(true)
            item {
                title("Item one")
                content {
                    p { +loremIpsum(2) }
                }
            }
            item {
                expanded(true)
                title("Item two")
                content {
                    p { +loremIpsum(2) }
                }
            }
            item {
                title("Item three")
                content {
                    p { +loremIpsum(2) }
                }
            }
            item {
                title("Item four")
                content {
                    p { +loremIpsum(20) }
                }
            }
            item {
                title("Item five")
                content {
                    p { +loremIpsum(2) }
                }
            }
        }
    }
}
"""
}
