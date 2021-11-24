@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.RootStore
import dev.fritz2.dom.html.RenderContext
import org.patternfly.ButtonVariation.primary
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
                    item {
                        title("Item one")
                        content {
                            p { +loremIpsum(2) }
                        }
                        events {
                            clicks handledBy notification(INFO, "Clicked")
                            expos handledBy notification(INFO) { expanded ->
                                title("Expanded: $expanded")
                            }
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
            snippet("Single expand", AccordionCode.SINGLE_EXPAND) {
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
            snippet("Fixed height", AccordionCode.FIXED_HEIGHT) {
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
            snippet("Reactive", "n/a") {
                val items = object : RootStore<List<String>>(listOf("Item 1", "Item 2", "Item 3")) {
                    var count = 3

                    val addItem = handle { items ->
                        count++
                        items + "Item $count"
                    }

                    val deleteItem = handle<String> { items, item ->
                        if (items.size > 1) {
                            items.minus(item)
                        } else {
                            items
                        }
                    }
                }

                accordion(items) {
                    display { item ->
                        item(item) {
                            content {
                                p { +loremIpsum(3) }
                                p {
                                    a {
                                        +"Remove item"
                                        clicks.map { item } handledBy items.deleteItem
                                    }
                                }
                            }
                        }
                    }
                }
                clickButton(primary, baseClass = "mt-md".util()) { +"Add item" } handledBy items.addItem
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
