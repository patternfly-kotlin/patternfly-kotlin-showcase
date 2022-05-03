@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.RootStore
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.patternfly.Align
import org.patternfly.CardVariant.compact
import org.patternfly.CardVariant.expandable
import org.patternfly.CardVariant.flat
import org.patternfly.CardVariant.hoverable
import org.patternfly.CardVariant.selectable
import org.patternfly.card
import org.patternfly.checkbox
import org.patternfly.dom.Id
import org.patternfly.dropdown
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.showcase.fixture.DropdownFixture.defaultDropdown

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
                    title { +"Title" }
                    body { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("With image and action", CardCode.IMAGE_ACTION) {
                card {
                    header {
                        content {
                            img {
                                src("./pf-logo.svg")
                                inlineStyle("width: 300px")
                            }
                        }
                        actions {
                            defaultDropdown()
                            checkbox(Id.unique("card-check"), standalone = true)
                        }
                    }
                    title { +"Title" }
                    body { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("Card title in card header", CardCode.TITLE_IN_HEADER) {
                card {
                    header {
                        actions {
                            defaultDropdown()
                            checkbox(Id.unique("card-check"), standalone = true)
                        }
                        title {
                            +"This is a really really really really really really really really really really long title"
                        }
                    }
                    body { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("Only actions in card header (no title/footer)", CardCode.ONLY_ACTIONS) {
                card {
                    header {
                        actions {
                            defaultDropdown()
                            checkbox(Id.unique("card-check"), standalone = true)
                        }
                    }
                    body {
                        +"This is the card body, there are only actions in the card head."
                    }
                }
            }
            snippet("Only image in the card header", CardCode.ONLY_IMAGE) {
                card {
                    header {
                        content {
                            img {
                                src("./pf-logo.svg")
                                inlineStyle("width: 300px")
                            }
                        }
                    }
                    title { +"Title" }
                    body { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("With no footer", CardCode.NO_FOOTER) {
                card {
                    title { +"Title" }
                    body { +"This card has no footer" }
                }
            }
            snippet("With no title", CardCode.NO_TITLE) {
                card {
                    body { +"This card has no title" }
                    footer { +"Footer" }
                }
            }
            snippet("With only a body", CardCode.ONLY_BODY) {
                card {
                    body { +"Body" }
                }
            }
            snippet("With multiple body sections", CardCode.MULTIPLE_BODY) {
                card {
                    title { +"Title" }
                    body { +"Body" }
                    body { +"Body" }
                    body { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("With only one body that fills", CardCode.BODY_FILLS) {
                card {
                    element {
                        inlineStyle("min-height: 30em")
                    }
                    title { +"Title" }
                    body { +"Body pf-m-no-fill" }
                    body { +"Body pf-m-no-fill" }
                    body(fill = true) { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("Hover", CardCode.HOVER) {
                card(hoverable) {
                    title { +"Title" }
                    body { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("Compact", CardCode.COMPACT) {
                card(compact) {
                    title { +"Title" }
                    body { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("Selectable and selected", CardCode.SELECTABLE) {
                class OnOff : RootStore<Boolean>(false) {
                    val toggle = handle { !it }
                }

                card(selectable) {
                    val onOff = OnOff()
                    selected(onOff.data)
                    events {
                        clicks handledBy onOff.toggle
                    }
                    header {
                        actions {
                            defaultDropdown()
                        }
                    }
                    title { +"First card" }
                    body { +"This is a selectable card. Click me to select me. Click again to deselect me." }
                }
                br {}
                card(selectable) {
                    val onOff = OnOff()
                    selected(onOff.data)
                    events {
                        clicks handledBy onOff.toggle
                    }
                    title { +"Second card" }
                    body { +"This is a selectable card. Click me to select me. Click again to deselect me." }
                }
                br {}
                card(selectable) {
                    val onOff = OnOff()
                    selected(onOff.data)
                    events {
                        clicks handledBy onOff.toggle
                    }
                    header {
                        actions {
                            checkbox("card-check", standalone = true) {
                                checked(onOff.data)
                                events {
                                    changes handledBy onOff.toggle
                                }
                            }
                        }
                        title { +"Third card" }
                    }
                    body { +"This is a selectable card. Click the card or the checkbox to select me." }
                }
            }
            snippet("Flat", CardCode.FLAT) {
                card(flat) {
                    title { +"Title" }
                    body { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("Expandable", CardCode.EXPANDABLE) {
                card(expandable) {
                    header {
                        actions {
                            defaultDropdown()
                            checkbox(Id.unique("card-check"), standalone = true)
                        }
                        title {
                            expandedStore.data.map { "Expanded state: $it" }.renderText(into = this)
                        }
                    }
                    body { +"Body" }
                    footer { +"Footer" }
                }
            }
            snippet("Card with dividers between sections", CardCode.DIVIDERS) {
                card(baseClass = "flat".modifier()) {
                    title { +"Title" }
                    divider()
                    body { +"Body" }
                    divider()
                    body { +"Body" }
                    divider()
                    footer { +"Footer" }
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
            title { +"Title" }
            body { +"Body" }
            footer { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val IMAGE_ACTION: String = """fun main() {
    render {
        card {
            header {
                img {
                    src("./pf-logo.svg")
                    domNode.style.width = "300px"
                }
                actions {
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
            title { +"Title" }
            body { +"Body" }
            footer { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val TITLE_IN_HEADER: String = """fun main() {
    render {
        card {
            header {
                actions {
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
                title {
                    +"This is a really really really really really really really really really really long title"
                }
            }
            body { +"Body" }
            footer { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val ONLY_ACTIONS: String = """fun main() {
    render {
        card {
            header {
                actions {
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
            body {
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
            header {
                img {
                    src("./pf-logo.svg")
                    domNode.style.width = "300px"
                }
            }
            title { +"Title" }
            body { +"Body" }
            footer { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val NO_FOOTER: String = """fun main() {
    render {
        card {
            title { +"Title" }
            body { +"This card has no footer" }
        }
    }
}
"""

    //language=kotlin
    const val NO_TITLE: String = """fun main() {
    render {
        card {
            body { +"This card has no title" }
            footer { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val ONLY_BODY: String = """fun main() {
    render {
        card {
            body { +"Body" }
        }
    }
}
"""

    //language=kotlin
    const val MULTIPLE_BODY: String = """fun main() {
    render {
        card {
            title { +"Title" }
            body { +"Body" }
            body { +"Body" }
            body { +"Body" }
            footer { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val BODY_FILLS: String = """fun main() {
    render {
        card {
            domNode.style.minHeight = "30em"
            title { +"Title" }
            body(baseClass = "no-fill".modifier()) { +"Body pf-m-no-fill" }
            body(baseClass = "no-fill".modifier()) { +"Body pf-m-no-fill" }
            body { +"Body" }
            footer { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val HOVER: String = """fun main() {
    render {
        card(baseClass = "hoverable".modifier()) {
            title { +"Title" }
            body { +"Body" }
            footer { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val COMPACT: String = """fun main() {
    render {
        card(baseClass = "compact".modifier()) {
            title { +"Title" }
            body { +"Body" }
            footer { +"Footer" }
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

            header {
                actions {
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
                title { +"First card" }
            }
            body { +"This is a selectable card. Click me to select me. Click again to deselect me." }
        }
        br {}
        card(selectable = true) {
            selected.data.drop(1) handledBy notification {
                severity(Severity.INFO)
                title("Card is ${'$'}{if (it) "" else "not "} selected.")
            }

            title { +"Second card" }
            body { +"This is a selectable card. Click me to select me. Click again to deselect me." }
        }
        br {}
        card(selectable = true) {
            selected.data.drop(1) handledBy notification {
                severity(Severity.INFO)
                title("Card is ${'$'}{if (it) "" else "not "} selected.")
            }

            header {
                actions {
                    cardCheckbox()
                }
                title { +"Third card" }
            }
            body { +"This is a selectable card. Click the card or the checkbox to select me." }
        }
    }
}
"""

    //language=kotlin
    const val FLAT: String = """fun main() {
    render {
        card(baseClass = "flat".modifier()) {
            title { +"Title" }
            body { +"Body" }
            footer { +"Footer" }
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
            header {
                cardToggle()
                actions {
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
                title { +"Title" }
            }
            cardExpandableContent {
                body { +"Body" }
                footer { +"Footer" }
            }
        }
    }
}
"""

    //language=kotlin
    const val DIVIDERS: String = """fun main() {
    render {
        card(baseClass = "flat".modifier()) {
            title { +"Title" }
            divider()
            body { +"Body" }
            divider()
            body { +"Body" }
            divider()
            footer { +"Footer" }
        }
    }
}
"""
}
