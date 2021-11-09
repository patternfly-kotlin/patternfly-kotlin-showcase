@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.ButtonVariation
import org.patternfly.Severity.INFO
import org.patternfly.Size
import org.patternfly.emptyState
import org.patternfly.emptyStateBody
import org.patternfly.emptyStateNoResults
import org.patternfly.emptyStatePrimary
import org.patternfly.emptyStateSecondary
import org.patternfly.emptyStateSpinner
import org.patternfly.fas
import org.patternfly.notification
import org.patternfly.pageSection
import org.patternfly.pushButton

object EmptyStateComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Empty state",
            text = "An empty state component fills a screen that is not yet populated with data or information.",
            designGuidelines = "https://www.patternfly.org/v4/components/empty-state/design-guidelines"
        )
        pageSection {
            snippet("Basic", EmptyStateCode.BASIC) {
                emptyState(iconClass = "cubes".fas(), title = "Empty State") {
                    emptyStateBody {
                        +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
                    }
                    pushButton(ButtonVariation.primary) {
                        +"Primary action"
                    }
                    emptyStateSecondary {
                        pushButton(ButtonVariation.link) { +"Multiple" }
                        pushButton(ButtonVariation.link) { +"Action buttons" }
                        pushButton(ButtonVariation.link) { +"Can" }
                        pushButton(ButtonVariation.link) { +"Go here" }
                        pushButton(ButtonVariation.link) { +"In the" }
                        pushButton(ButtonVariation.link) { +"Secondary" }
                        pushButton(ButtonVariation.link) { +"Area" }
                    }
                }
            }
            snippet("Extra small", EmptyStateCode.EXTRA_SMALL) {
                emptyState(size = Size.XS, title = "Empty State") {
                    emptyStateBody {
                        +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
                    }
                    pushButton(ButtonVariation.primary) {
                        +"Primary action"
                    }
                    emptyStateSecondary {
                        pushButton(ButtonVariation.link) { +"Multiple" }
                        pushButton(ButtonVariation.link) { +"Action buttons" }
                        pushButton(ButtonVariation.link) { +"Can" }
                        pushButton(ButtonVariation.link) { +"Go here" }
                        pushButton(ButtonVariation.link) { +"In the" }
                        pushButton(ButtonVariation.link) { +"Secondary" }
                        pushButton(ButtonVariation.link) { +"Area" }
                    }
                }
            }
            snippet("Small", EmptyStateCode.SMALL) {
                emptyState(size = Size.SM, iconClass = "cubes".fas(), title = "Empty State") {
                    emptyStateBody {
                        +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
                    }
                    pushButton(ButtonVariation.primary) {
                        +"Primary action"
                    }
                    emptyStateSecondary {
                        pushButton(ButtonVariation.link) { +"Multiple" }
                        pushButton(ButtonVariation.link) { +"Action buttons" }
                        pushButton(ButtonVariation.link) { +"Can" }
                        pushButton(ButtonVariation.link) { +"Go here" }
                        pushButton(ButtonVariation.link) { +"In the" }
                        pushButton(ButtonVariation.link) { +"Secondary" }
                        pushButton(ButtonVariation.link) { +"Area" }
                    }
                }
            }
            snippet("Large", EmptyStateCode.LARGE) {
                emptyState(size = Size.LG, iconClass = "cubes".fas(), title = "Empty State") {
                    emptyStateBody {
                        +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
                    }
                    pushButton(ButtonVariation.primary) {
                        +"Primary action"
                    }
                    emptyStateSecondary {
                        pushButton(ButtonVariation.link) { +"Multiple" }
                        pushButton(ButtonVariation.link) { +"Action buttons" }
                        pushButton(ButtonVariation.link) { +"Can" }
                        pushButton(ButtonVariation.link) { +"Go here" }
                        pushButton(ButtonVariation.link) { +"In the" }
                        pushButton(ButtonVariation.link) { +"Secondary" }
                        pushButton(ButtonVariation.link) { +"Area" }
                    }
                }
            }
            snippet("Extra large", EmptyStateCode.EXTRA_LARGE) {
                emptyState(size = Size.XL, iconClass = "cubes".fas(), title = "Empty State") {
                    emptyStateBody {
                        +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
                    }
                    pushButton(ButtonVariation.primary) {
                        +"Primary action"
                    }
                    emptyStateSecondary {
                        pushButton(ButtonVariation.link) { +"Multiple" }
                        pushButton(ButtonVariation.link) { +"Action buttons" }
                        pushButton(ButtonVariation.link) { +"Can" }
                        pushButton(ButtonVariation.link) { +"Go here" }
                        pushButton(ButtonVariation.link) { +"In the" }
                        pushButton(ButtonVariation.link) { +"Secondary" }
                        pushButton(ButtonVariation.link) { +"Area" }
                    }
                }
            }
            snippet("With primary element", EmptyStateCode.PRIMARY_ELEMENT) {
                emptyState(iconClass = "cubes".fas(), title = "Empty State") {
                    emptyStateBody {
                        +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
                    }
                    emptyStatePrimary {
                        pushButton(ButtonVariation.link) { +"Primary action" }
                    }
                }
            }
            snippet("Spinner", EmptyStateCode.SPINNER) {
                emptyStateSpinner()
            }
            snippet("No match found", EmptyStateCode.NO_RESULTS) {
                emptyStateNoResults(action = Pair("Clear filter", notification(INFO, "This should clear all filters.")))
            }
        }
    }
}

object EmptyStateCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        emptyState(iconClass = "cubes".fas(), title = "Empty State") {
            emptyStateBody {
                +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
            }
            pushButton(ButtonVariation.primary) {
                +"Primary action"
            }
            emptyStateSecondary {
                pushButton(ButtonVariation.link) { +"Multiple" }
                pushButton(ButtonVariation.link) { +"Action buttons" }
                pushButton(ButtonVariation.link) { +"Can" }
                pushButton(ButtonVariation.link) { +"Go here" }
                pushButton(ButtonVariation.link) { +"In the" }
                pushButton(ButtonVariation.link) { +"Secondary" }
                pushButton(ButtonVariation.link) { +"Area" }
            }
        }
    }
}
"""

    //language=kotlin
    const val EXTRA_SMALL: String = """fun main() {
    render {
        emptyState(size = Size.XS, title = "Empty State") {
            emptyStateBody {
                +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
            }
            pushButton(ButtonVariation.primary) {
                +"Primary action"
            }
            emptyStateSecondary {
                pushButton(ButtonVariation.link) { +"Multiple" }
                pushButton(ButtonVariation.link) { +"Action buttons" }
                pushButton(ButtonVariation.link) { +"Can" }
                pushButton(ButtonVariation.link) { +"Go here" }
                pushButton(ButtonVariation.link) { +"In the" }
                pushButton(ButtonVariation.link) { +"Secondary" }
                pushButton(ButtonVariation.link) { +"Area" }
            }
        }
    }
}
"""

    //language=kotlin
    const val SMALL: String = """fun main() {
    render {
        emptyState(size = Size.SM, iconClass = "cubes".fas(), title = "Empty State") {
            emptyStateBody {
                +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
            }
            pushButton(ButtonVariation.primary) {
                +"Primary action"
            }
            emptyStateSecondary {
                pushButton(ButtonVariation.link) { +"Multiple" }
                pushButton(ButtonVariation.link) { +"Action buttons" }
                pushButton(ButtonVariation.link) { +"Can" }
                pushButton(ButtonVariation.link) { +"Go here" }
                pushButton(ButtonVariation.link) { +"In the" }
                pushButton(ButtonVariation.link) { +"Secondary" }
                pushButton(ButtonVariation.link) { +"Area" }
            }
        }
    }
}
"""

    //language=kotlin
    const val LARGE: String = """fun main() {
    render {
        emptyState(size = Size.LG, iconClass = "cubes".fas(), title = "Empty State") {
            emptyStateBody {
                +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
            }
            pushButton(ButtonVariation.primary) {
                +"Primary action"
            }
            emptyStateSecondary {
                pushButton(ButtonVariation.link) { +"Multiple" }
                pushButton(ButtonVariation.link) { +"Action buttons" }
                pushButton(ButtonVariation.link) { +"Can" }
                pushButton(ButtonVariation.link) { +"Go here" }
                pushButton(ButtonVariation.link) { +"In the" }
                pushButton(ButtonVariation.link) { +"Secondary" }
                pushButton(ButtonVariation.link) { +"Area" }
            }
        }
    }
}
"""

    //language=kotlin
    const val EXTRA_LARGE: String = """fun main() {
    render {
        emptyState(size = Size.XL, iconClass = "cubes".fas(), title = "Empty State") {
            emptyStateBody {
                +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
            }
            pushButton(ButtonVariation.primary) {
                +"Primary action"
            }
            emptyStateSecondary {
                pushButton(ButtonVariation.link) { +"Multiple" }
                pushButton(ButtonVariation.link) { +"Action buttons" }
                pushButton(ButtonVariation.link) { +"Can" }
                pushButton(ButtonVariation.link) { +"Go here" }
                pushButton(ButtonVariation.link) { +"In the" }
                pushButton(ButtonVariation.link) { +"Secondary" }
                pushButton(ButtonVariation.link) { +"Area" }
            }
        }
    }
}
"""

    //language=kotlin
    const val PRIMARY_ELEMENT: String = """fun main() {
    render {
        emptyState(iconClass = "cubes".fas(), title = "Empty State") {
            emptyStateBody {
                +"This represents an the empty state pattern in Patternfly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs."
            }
            emptyStatePrimary {
                pushButton(ButtonVariation.link) { +"Primary action" }
            }
        }
    }
}
"""

    //language=kotlin
    const val SPINNER: String = """fun main() {
    render {
        emptyStateSpinner()
    }
}
"""

    //language=kotlin
    const val NO_RESULTS: String = """fun main() {
    render {
        emptyStateNoResults(action = Pair("Clear filter", Notification.info("This should clear all filters.")))
    }
}
"""
}
