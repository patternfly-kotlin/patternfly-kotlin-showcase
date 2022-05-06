@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.RootStore
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.patternfly.ButtonVariant.inline
import org.patternfly.ButtonVariant.link
import org.patternfly.CardVariant.compact
import org.patternfly.CardVariant.expandable
import org.patternfly.CardVariant.flat
import org.patternfly.CardVariant.hoverable
import org.patternfly.CardVariant.selectable
import org.patternfly.Color.BLUE
import org.patternfly.Color.GREEN
import org.patternfly.Color.ORANGE
import org.patternfly.Color.PURPLE
import org.patternfly.card
import org.patternfly.checkbox
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.dom.Id
import org.patternfly.dom.hideIf
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.label
import org.patternfly.labelGroup
import org.patternfly.layout
import org.patternfly.linkButton
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.showcase.fixture.DropdownFixture.defaultDropdown
import org.patternfly.showcase.require

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
                        main {
                            img {
                                src(require("pf-logo.svg") as String)
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
                        main {
                            img {
                                src(require("pf-logo.svg") as String)
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
            snippet("Horizontal card grid", "n/a") {
                card(expandable) {
                    header {
                        actions {
                            defaultDropdown()
                        }
                        content {
                            div(baseClass = classes("level".layout(), "gutter".modifier())) {
                                expandedStore.data.render(into = this) { expanded ->
                                    div(baseClass = "card".component("title")) { +"Getting started" }
                                    labelGroup(compact = true) {
                                        element { hideIf(expanded) }
                                        label(BLUE, "Setup your cluster") { icon("info-circle".fas()) }
                                        label(PURPLE, "Guided tours") { icon("info-circle".fas()) }
                                        label(GREEN, "Quick starts") { icon("info-circle".fas()) }
                                        label(ORANGE, "Learning resources") { icon("info-circle".fas()) }
                                    }
                                }
                            }
                        }
                    }
                    body {
                        div(
                            baseClass = classes(
                                "grid".layout(),
                                "gutter".modifier(),
                                "all-6-col-on-md".modifier(),
                                "all-3-col-on-lg".modifier()
                            )
                        ) {
                            div(
                                baseClass = classes(
                                    "flex".layout(),
                                    "column".modifier(),
                                    "space-items-lg".modifier(),
                                    "align-items-flex-start".modifier()
                                )
                            ) {
                                div(
                                    baseClass = classes(
                                        "flex".layout(),
                                        "column".modifier(),
                                        "grow".modifier(),
                                        "align-items-flex-start".modifier(),
                                        "space-items-sm".modifier()
                                    )
                                ) {
                                    label(BLUE, "Setup your cluster")
                                    p { +"Continue setting up your cluster to access all you cain in the Console" }
                                    // TODO use list component
                                    ul(baseClass = classes("list".component(), "plain".modifier())) {
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"Add identity provider" }
                                        }
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"Configure alert receivers" }
                                        }
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"Configure default ingress certificate" }
                                        }
                                    }
                                }
                                linkButton(inline, link) {
                                    +"View all set up cluster steps"
                                    icon("arrow-right".fas())
                                }
                            }
                            div(
                                baseClass = classes(
                                    "flex".layout(),
                                    "column".modifier(),
                                    "space-items-lg".modifier(),
                                    "align-items-flex-start".modifier()
                                )
                            ) {
                                div(
                                    baseClass = classes(
                                        "flex".layout(),
                                        "column".modifier(),
                                        "grow".modifier(),
                                        "align-items-flex-start".modifier(),
                                        "space-items-sm".modifier()
                                    )
                                ) {
                                    label(PURPLE, "Guided tours")
                                    p { +"Tour some of the key features around the console" }
                                    // TODO use list component
                                    ul(baseClass = classes("list".component(), "plain".modifier())) {
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"Tour the console" }
                                        }
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"Explore the Developer perspective" }
                                        }
                                    }
                                }
                                linkButton(inline, link) {
                                    +"View all guided tours"
                                    icon("arrow-right".fas())
                                }
                            }
                            div(
                                baseClass = classes(
                                    "flex".layout(),
                                    "column".modifier(),
                                    "space-items-lg".modifier(),
                                    "align-items-flex-start".modifier()
                                )
                            ) {
                                div(
                                    baseClass = classes(
                                        "flex".layout(),
                                        "column".modifier(),
                                        "grow".modifier(),
                                        "align-items-flex-start".modifier(),
                                        "space-items-sm".modifier()
                                    )
                                ) {
                                    label(GREEN, "Quick starts")
                                    p { +"Get started with features using our step-by-step documentation" }
                                    // TODO use list component
                                    ul(baseClass = classes("list".component(), "plain".modifier())) {
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"Getting started with Serverless" }
                                        }
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"Explore virtualization" }
                                        }
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"Build pipelines" }
                                        }
                                    }
                                }
                                linkButton(inline, link) {
                                    +"View all quickstarts"
                                    icon("arrow-right".fas())
                                }
                            }
                            div(
                                baseClass = classes(
                                    "flex".layout(),
                                    "column".modifier(),
                                    "space-items-lg".modifier(),
                                    "align-items-flex-start".modifier()
                                )
                            ) {
                                div(
                                    baseClass = classes(
                                        "flex".layout(),
                                        "column".modifier(),
                                        "grow".modifier(),
                                        "align-items-flex-start".modifier(),
                                        "space-items-sm".modifier()
                                    )
                                ) {
                                    label(ORANGE, "Learning resources")
                                    p { +"Learn about new features within the Console and get started with demo apps" }
                                    // TODO use list component
                                    ul(baseClass = classes("list".component(), "plain".modifier())) {
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"See what's possible with the Explore page" }
                                        }
                                        li(baseClass = "list".component("item")) {
                                            a {
                                                href("#")
                                                +"OpenShift 4.5: Top Tasks "
                                                icon("external-link-alt".fas())
                                            }
                                        }
                                        li(baseClass = "list".component("item")) {
                                            a { href("#"); +"Try a demo app" }
                                        }
                                    }
                                }
                                linkButton(inline, link) {
                                    +"View all learning resources"
                                    icon("arrow-right".fas())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

object CardCode {

    //language=kotlin
    const val BASIC: String = """
fun main() {
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
    const val IMAGE_ACTION: String = """
fun main() {
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
