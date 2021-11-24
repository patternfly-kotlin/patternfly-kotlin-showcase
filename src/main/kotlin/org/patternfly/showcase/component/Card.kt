@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.drop
import org.patternfly.Align
import org.patternfly.Severity
import org.patternfly.card
import org.patternfly.cardAction
import org.patternfly.cardBody
import org.patternfly.cardCheckbox
import org.patternfly.cardExpandableContent
import org.patternfly.cardFooter
import org.patternfly.cardHeader
import org.patternfly.cardTitle
import org.patternfly.cardToggle
import org.patternfly.divider
import org.patternfly.dropdown
import org.patternfly.modifier
import org.patternfly.notification
import org.patternfly.pageSection

object CardComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Card",
            text = "A card is a square or rectangular container that can contain any kind of content.",
            designGuidelines = "https://www.patternfly.org/v4/components/card/design-guidelines"
        )
        pageSection {
            snippet("Basic", CardCode.BASIC) {
                card {
                    cardTitle { +"Title" }
                    cardBody { +"Body" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("With image and action", CardCode.IMAGE_ACTION) {
                card {
                    cardHeader {
                        img {
                            src("./pf-logo.svg")
                            domNode.style.width = "300px"
                        }
                        cardAction {
                            dropdown<String>(align = Align.RIGHT) {
                                toggle { kebab() }
                                item("Item 1")
                                item("Disabled Item") {
                                    disabled(true)
                                }
                                separator()
                                item("Separated Item")
                            }
                            cardCheckbox()
                        }
                    }
                    cardTitle { +"Title" }
                    cardBody { +"Body" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("Card title in card header", CardCode.TITLE_IN_HEADER) {
                card {
                    cardHeader {
                        cardAction {
                            dropdown<String>(align = Align.RIGHT) {
                                toggle { kebab() }
                                item("Item 1")
                                item("Disabled Item") {
                                    disabled(true)
                                }
                                separator()
                                item("Separated Item")
                            }
                            cardCheckbox()
                        }
                        cardTitle {
                            +"This is a really really really really really really really really really really long title"
                        }
                    }
                    cardBody { +"Body" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("Only actions in card header (no title/footer)", CardCode.ONLY_ACTIONS) {
                card {
                    cardHeader {
                        cardAction {
                            dropdown<String>(align = Align.RIGHT) {
                                toggle { kebab() }
                                item("Item 1")
                                item("Disabled Item") {
                                    disabled(true)
                                }
                                separator()
                                item("Separated Item")
                            }
                            cardCheckbox()
                        }
                    }
                    cardBody {
                        +"This is the card body, there are only actions in the card head."
                    }
                }
            }
            snippet("Only image in the card header", CardCode.ONLY_IMAGE) {
                card {
                    cardHeader {
                        img {
                            src("./pf-logo.svg")
                            domNode.style.width = "300px"
                        }
                    }
                    cardTitle { +"Title" }
                    cardBody { +"Body" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("With no footer", CardCode.NO_FOOTER) {
                card {
                    cardTitle { +"Title" }
                    cardBody { +"This card has no footer" }
                }
            }
            snippet("With no title", CardCode.NO_TITLE) {
                card {
                    cardBody { +"This card has no title" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("With only a body", CardCode.ONLY_BODY) {
                card {
                    cardBody { +"Body" }
                }
            }
            snippet("With multiple body sections", CardCode.MULTIPLE_BODY) {
                card {
                    cardTitle { +"Title" }
                    cardBody { +"Body" }
                    cardBody { +"Body" }
                    cardBody { +"Body" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("With only one body that fills", CardCode.BODY_FILLS) {
                card {
                    domNode.style.minHeight = "30em"
                    cardTitle { +"Title" }
                    cardBody(baseClass = "no-fill".modifier()) { +"Body pf-m-no-fill" }
                    cardBody(baseClass = "no-fill".modifier()) { +"Body pf-m-no-fill" }
                    cardBody { +"Body" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("Hover", CardCode.HOVER) {
                card(baseClass = "hoverable".modifier()) {
                    cardTitle { +"Title" }
                    cardBody { +"Body" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("Compact", CardCode.COMPACT) {
                card(baseClass = "compact".modifier()) {
                    cardTitle { +"Title" }
                    cardBody { +"Body" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("Selectable and selected", CardCode.SELECTABLE) {
                card(selectable = true) {
                    selected.data.drop(1) handledBy notification {
                        severity(Severity.INFO)
                        title("Card is ${if (it) "" else "not "} selected.")
                    }

                    cardHeader {
                        cardAction {
                            dropdown<String>(align = Align.RIGHT) {
                                toggle { kebab() }
                                item("Item 1")
                                item("Disabled Item") {
                                    disabled(true)
                                }
                                separator()
                                item("Separated Item")
                            }
                        }
                        cardTitle { +"First card" }
                    }
                    cardBody { +"This is a selectable card. Click me to select me. Click again to deselect me." }
                }
                br {}
                card(selectable = true) {
                    selected.data.drop(1) handledBy notification {
                        severity(Severity.INFO)
                        title("Card is ${if (it) "" else "not "} selected.")
                    }

                    cardTitle { +"Second card" }
                    cardBody { +"This is a selectable card. Click me to select me. Click again to deselect me." }
                }
                br {}
                card(selectable = true) {
                    selected.data.drop(1) handledBy notification {
                        severity(Severity.INFO)
                        title("Card is ${if (it) "" else "not "} selected.")
                    }

                    cardHeader {
                        cardAction {
                            cardCheckbox()
                        }
                        cardTitle { +"Third card" }
                    }
                    cardBody { +"This is a selectable card. Click the card or the checkbox to select me." }
                }
            }
            snippet("Flat", CardCode.FLAT) {
                card(baseClass = "flat".modifier()) {
                    cardTitle { +"Title" }
                    cardBody { +"Body" }
                    cardFooter { +"Footer" }
                }
            }
            snippet("Expandable", CardCode.EXPANDABLE) {
                card {
                    expanded.expanded handledBy notification { expanded ->
                        severity(Severity.INFO)
                        title("Expanded state of card: $expanded.")
                    }
                    cardHeader {
                        cardToggle()
                        cardAction {
                            dropdown<String>(align = Align.RIGHT) {
                                toggle { kebab() }
                                item("Item 1")
                                item("Disabled Item") {
                                    disabled(true)
                                }
                                separator()
                                item("Separated Item")
                            }
                            cardCheckbox()
                        }
                        cardTitle { +"Title" }
                    }
                    cardExpandableContent {
                        cardBody { +"Body" }
                        cardFooter { +"Footer" }
                    }
                }
            }
            snippet("Card with dividers between sections", CardCode.DIVIDERS) {
                card(baseClass = "flat".modifier()) {
                    cardTitle { +"Title" }
                    divider()
                    cardBody { +"Body" }
                    divider()
                    cardBody { +"Body" }
                    divider()
                    cardFooter { +"Footer" }
                }
            }
        }
    }
}

object CardCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        card {
            cardTitle { +"Title" }
            cardBody { +"Body" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val IMAGE_ACTION: String = """fun main() {
    render {
        card {
            cardHeader {
                img {
                    src("./pf-logo.svg")
                    domNode.style.width = "300px"
                }
                cardAction {
                    dropdown<String>(align = Align.RIGHT) {
                        kebabToggle()
                        items {
                            item("Item 1")
                            item("Disabled Item") {
                                disabled = true
                            }
                            separator()
                            item("Separated Item")
                        }
                    }
                    cardCheckbox()
                }
            }
            cardTitle { +"Title" }
            cardBody { +"Body" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val TITLE_IN_HEADER: String = """fun main() {
    render {
        card {
            cardHeader {
                cardAction {
                    dropdown<String>(align = Align.RIGHT) {
                        kebabToggle()
                        items {
                            item("Action")
                            item("Disabled Action") {
                                disabled = true
                            }
                            separator()
                            item("Separated Action")
                        }
                    }
                    cardCheckbox()
                }
                cardTitle {
                    +"This is a really really really really really really really really really really long title"
                }
            }
            cardBody { +"Body" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val ONLY_ACTIONS: String = """fun main() {
    render {
        card {
            cardHeader {
                cardAction {
                    dropdown<String>(align = Align.RIGHT) {
                        kebabToggle()
                        items {
                            item("Action")
                            item("Disabled Action") {
                                disabled = true
                            }
                            separator()
                            item("Separated Action")
                        }
                    }
                    cardCheckbox()
                }
            }
            cardBody {
                +"This is the card body, there are only actions in the card head."
            }
        }
    }
}
"""

    //language=kotlin
    const val ONLY_IMAGE: String = """fun main() {
    render {
        card {
            cardHeader {
                img {
                    src("./pf-logo.svg")
                    domNode.style.width = "300px"
                }
            }
            cardTitle { +"Title" }
            cardBody { +"Body" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val NO_FOOTER: String = """fun main() {
    render {
        card {
            cardTitle { +"Title" }
            cardBody { +"This card has no footer" }
        }
    }
}
"""

    //language=kotlin
    const val NO_TITLE: String = """fun main() {
    render {
        card {
            cardBody { +"This card has no title" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val ONLY_BODY: String = """fun main() {
    render {
        card {
            cardBody { +"Body" }
        }
    }
}
"""

    //language=kotlin
    const val MULTIPLE_BODY: String = """fun main() {
    render {
        card {
            cardTitle { +"Title" }
            cardBody { +"Body" }
            cardBody { +"Body" }
            cardBody { +"Body" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val BODY_FILLS: String = """fun main() {
    render {
        card {
            domNode.style.minHeight = "30em"
            cardTitle { +"Title" }
            cardBody(baseClass = "no-fill".modifier()) { +"Body pf-m-no-fill" }
            cardBody(baseClass = "no-fill".modifier()) { +"Body pf-m-no-fill" }
            cardBody { +"Body" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val HOVER: String = """fun main() {
    render {
        card(baseClass = "hoverable".modifier()) {
            cardTitle { +"Title" }
            cardBody { +"Body" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val COMPACT: String = """fun main() {
    render {
        card(baseClass = "compact".modifier()) {
            cardTitle { +"Title" }
            cardBody { +"Body" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val SELECTABLE: String = """fun main() {
    render {
        card(selectable = true) {
            selected.data.drop(1) handledBy notification {
                severity(Severity.INFO)
                title("Card is ${'$'}{if (it) "" else "not "} selected.")
            }

            cardHeader {
                cardAction {
                    dropdown<String>(align = Align.RIGHT) {
                        kebabToggle()
                        items {
                            item("Action")
                            item("Disabled Action") {
                                disabled = true
                            }
                            separator()
                            item("Separated Action")
                        }
                    }
                }
                cardTitle { +"First card" }
            }
            cardBody { +"This is a selectable card. Click me to select me. Click again to deselect me." }
        }
        br {}
        card(selectable = true) {
            selected.data.drop(1) handledBy notification {
                severity(Severity.INFO)
                title("Card is ${'$'}{if (it) "" else "not "} selected.")
            }

            cardTitle { +"Second card" }
            cardBody { +"This is a selectable card. Click me to select me. Click again to deselect me." }
        }
        br {}
        card(selectable = true) {
            selected.data.drop(1) handledBy notification {
                severity(Severity.INFO)
                title("Card is ${'$'}{if (it) "" else "not "} selected.")
            }

            cardHeader {
                cardAction {
                    cardCheckbox()
                }
                cardTitle { +"Third card" }
            }
            cardBody { +"This is a selectable card. Click the card or the checkbox to select me." }
        }
    }
}
"""

    //language=kotlin
    const val FLAT: String = """fun main() {
    render {
        card(baseClass = "flat".modifier()) {
            cardTitle { +"Title" }
            cardBody { +"Body" }
            cardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val EXPANDABLE: String = """fun main() {
    render {
        card {
            expanded.expanded handledBy notification { expanded ->
                severity(Severity.INFO)
                title("Expanded state of card: ${'$'}expanded.")
            }
            cardHeader {
                cardToggle()
                cardAction {
                    dropdown<String>(align = Align.RIGHT) {
                        kebabToggle()
                        items {
                            item("Action")
                            item("Disabled Action") {
                                disabled = true
                            }
                            separator()
                            item("Separated Action")
                        }
                    }
                    cardCheckbox()
                }
                cardTitle { +"Title" }
            }
            cardExpandableContent {
                cardBody { +"Body" }
                cardFooter { +"Footer" }
            }
        }
    }
}
"""

    //language=kotlin
    const val DIVIDERS: String = """fun main() {
    render {
        card(baseClass = "flat".modifier()) {
            cardTitle { +"Title" }
            divider()
            cardBody { +"Body" }
            divider()
            cardBody { +"Body" }
            divider()
            cardFooter { +"Footer" }
        }
    }
}
"""
}
