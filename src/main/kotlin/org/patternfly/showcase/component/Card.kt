@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import org.patternfly.Align
import org.patternfly.DropdownStore
import org.patternfly.Modifier.compact
import org.patternfly.Modifier.flat
import org.patternfly.Modifier.hoverable
import org.patternfly.Modifier.noFill
import org.patternfly.pfBrand
import org.patternfly.pfCard
import org.patternfly.pfCardActions
import org.patternfly.pfCardBody
import org.patternfly.pfCardCheckbox
import org.patternfly.pfCardFooter
import org.patternfly.pfCardHeader
import org.patternfly.pfCardHeaderMain
import org.patternfly.pfCardTitle
import org.patternfly.pfContent
import org.patternfly.pfDropdown
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownKebab
import org.patternfly.pfItem
import org.patternfly.pfSection
import org.patternfly.pfSeparator
import org.w3c.dom.HTMLElement

object CardComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Card",
                prefix = "A ",
                key = "card",
                text = " is a flexible element for containing any kind of content. Cards are used on dashboards, in data displays (e.g. Card View), or for positioning content on a page."
            )
        })
        yield(render {
            pfSection {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", CardCode.BASIC) {
                    pfCard {
                        pfCardTitle { +"Title" }
                        pfCardBody { +"Body" }
                        pfCardFooter { +"Footer" }
                    }
                }
                snippet("With image and action", CardCode.IMAGE_ACTION) {
                    pfCard {
                        pfCardHeader {
                            pfCardHeaderMain {
                                pfBrand("./pf_logo.svg") {
                                    domNode.style.width = "300px"
                                }
                            }
                            pfCardActions {
                                pfDropdown(DropdownStore<String>(), align = Align.RIGHT) {
                                    pfDropdownKebab()
                                    pfDropdownItems {
                                        pfItem("Item 1")
                                        pfItem("Disabled Item") {
                                            disabled = true
                                        }
                                        pfSeparator()
                                        pfItem("Separated Item")
                                    }
                                }
                                pfCardCheckbox()
                            }
                        }
                        pfCardTitle { +"Title" }
                        pfCardBody { +"Body" }
                        pfCardFooter { +"Footer" }
                    }
                }
                snippet("Card title in card header", CardCode.TITLE_IN_HEADER) {
                    pfCard {
                        pfCardHeader {
                            pfCardActions {
                                pfDropdown(DropdownStore<String>(), align = Align.RIGHT) {
                                    pfDropdownKebab()
                                    pfDropdownItems {
                                        pfItem("Action 1")
                                        pfItem("Disabled Action") {
                                            disabled = true
                                        }
                                        pfSeparator()
                                        pfItem("Separated Action")
                                    }
                                }
                                pfCardCheckbox()
                            }
                            pfCardTitle {
                                +"This is a really really really really really really really really really really long title"
                            }
                        }
                        pfCardBody { +"Body" }
                        pfCardFooter { +"Footer" }
                    }
                }
                snippet("Only actions in card header (no title/footer)", CardCode.ONLY_ACTIONS) {
                    pfCard {
                        pfCardHeader {
                            pfCardActions {
                                pfDropdown(DropdownStore<String>(), align = Align.RIGHT) {
                                    pfDropdownKebab()
                                    pfDropdownItems {
                                        pfItem("Action 1")
                                        pfItem("Disabled Action") {
                                            disabled = true
                                        }
                                        pfSeparator()
                                        pfItem("Separated Action")
                                    }
                                }
                                pfCardCheckbox()
                            }
                        }
                        pfCardBody {
                            +"This is the card body, there are only actions in the card head."
                        }
                    }
                }
                snippet("Only image in the card header", CardCode.ONLY_IMAGE) {
                    pfCard {
                        pfCardHeader {
                            pfCardHeaderMain {
                                pfBrand("./pf_logo.svg") {
                                    domNode.style.width = "300px"
                                }
                            }
                        }
                        pfCardTitle { +"Title" }
                        pfCardBody { +"Body" }
                        pfCardFooter { +"Footer" }
                    }
                }
                snippet("With no footer", CardCode.NO_FOOTER) {
                    pfCard {
                        pfCardTitle { +"Title" }
                        pfCardBody { +"This card has no footer" }
                    }
                }
                snippet("With no title", CardCode.NO_TITLE) {
                    pfCard {
                        pfCardBody { +"This card has no title" }
                        pfCardFooter { +"Footer" }
                    }
                }
                snippet("With only a body", CardCode.ONLY_BODY) {
                    pfCard {
                        pfCardBody { +"Body" }
                    }
                }
                snippet("With multiple body sections", CardCode.MULTIPLE_BODY) {
                    pfCard {
                        pfCardTitle { +"Title" }
                        pfCardBody { +"Body" }
                        pfCardBody { +"Body" }
                        pfCardBody { +"Body" }
                        pfCardFooter { +"Footer" }
                    }
                }
                snippet("With only one body that fills", CardCode.BODY_FILLS) {
                    pfCard {
                        domNode.style.minHeight = "30em"
                        pfCardTitle { +"Title" }
                        pfCardBody(noFill) { +"Body pf-m-no-fill" }
                        pfCardBody(noFill) { +"Body pf-m-no-fill" }
                        pfCardBody { +"Body" }
                        pfCardFooter { +"Footer" }
                    }
                }
                snippet("Hover", CardCode.HOVER) {
                    pfCard(modifier = hoverable) {
                        pfCardTitle { +"Title" }
                        pfCardBody { +"Body" }
                        pfCardFooter { +"Footer" }
                    }
                }
                snippet("Compact", CardCode.COMPACT) {
                    pfCard(modifier = compact) {
                        pfCardTitle { +"Title" }
                        pfCardBody { +"Body" }
                        pfCardFooter { +"Footer" }
                    }
                }
                snippet("Selectable and selected", CardCode.SELECTABLE) {
                    pfCard(selectable = true) {
                        pfCardHeader {
                            pfCardActions {
                                pfDropdown(DropdownStore<String>(), align = Align.RIGHT) {
                                    pfDropdownKebab()
                                    pfDropdownItems {
                                        pfItem("Action 1")
                                        pfItem("Disabled Action") {
                                            disabled = true
                                        }
                                        pfSeparator()
                                        pfItem("Separated Action")
                                    }
                                }
                            }
                        }
                        pfCardTitle { +"First card" }
                        pfCardBody { +"This is a selectable card. Click me to select me. Click again to deselect me." }
                    }
                    br {}
                    pfCard(selectable = true) {
                        pfCardTitle { +"Second card" }
                        pfCardBody { +"This is a selectable card. Click me to select me. Click again to deselect me." }
                    }
                    br {}
                    pfCard(selectable = true) {
                        pfCardHeader {
                            pfCardActions {
                                pfCardCheckbox()
                            }
                        }
                        pfCardTitle { +"Third card" }
                        pfCardBody { +"This is a selectable card. Click the card or the checkbox to select me." }
                    }
                }
                snippet("Flat", CardCode.FLAT) {
                    pfCard(modifier = flat) {
                        pfCardTitle { +"Title" }
                        pfCardBody { +"Body" }
                        pfCardFooter { +"Footer" }
                    }
                }
            }
        })
    }
}

internal object CardCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pfCard {
            pfCardTitle { +"Title" }
            pfCardBody { +"Body" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val IMAGE_ACTION: String = """fun main() {
    render {
        pfCard {
            pfCardHeader {
                pfCardHeaderMain {
                    pfBrand("./pf_logo.svg") {
                        domNode.style.width = "300px"
                    }
                }
                pfCardActions {
                    pfDropdown(DropdownStore<String>(), align = Align.RIGHT) {
                        pfDropdownKebab()
                        pfDropdownItems {
                            pfItem("Action 1")
                            pfItem("Disabled Action") {
                                disabled = true
                            }
                            pfSeparator()
                            pfItem("Separated Action")
                        }
                    }
                    pfCardCheckbox()
                }
            }
            pfCardTitle { +"Title" }
            pfCardBody { +"Body" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val TITLE_IN_HEADER: String = """fun main() {
    render {
        pfCard {
            pfCardHeader {
                pfCardActions {
                    pfDropdown(DropdownStore<String>(), align = Align.RIGHT) {
                        pfDropdownKebab()
                        pfDropdownItems {
                            pfItem("Action 1")
                            pfItem("Disabled Action") {
                                disabled = true
                            }
                            pfSeparator()
                            pfItem("Separated Action")
                        }
                    }
                    pfCardCheckbox()
                }
                pfCardTitle {
                    +"This is a really really really really really really really really really really long title"
                }
            }
            pfCardBody { +"Body" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val ONLY_ACTIONS: String = """fun main() {
    render {
        pfCard {
            pfCardHeader {
                pfCardActions {
                    pfDropdown(DropdownStore<String>(), align = Align.RIGHT) {
                        pfDropdownKebab()
                        pfDropdownItems {
                            pfItem("Action 1")
                            pfItem("Disabled Action") {
                                disabled = true
                            }
                            pfSeparator()
                            pfItem("Separated Action")
                        }
                    }
                    pfCardCheckbox()
                }
            }
            pfCardBody {
                +"This is the card body, there are only actions in the card head."
            }
        }
    }
}
"""

    //language=kotlin
    const val ONLY_IMAGE: String = """fun main() {
    render {
        pfCard {
            pfCardHeader {
                pfCardHeaderMain {
                    pfBrand("./pf_logo.svg") {
                        domNode.style.width = "300px"
                    }
                }
            }
            pfCardTitle { +"Title" }
            pfCardBody { +"Body" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val NO_FOOTER: String = """fun main() {
    render {
        pfCard {
            pfCardTitle { +"Title" }
            pfCardBody { +"This card has no footer" }
        }
    }
}
"""

    //language=kotlin
    const val NO_TITLE: String = """fun main() {
    render {
        pfCard {
            pfCardBody { +"This card has no title" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val ONLY_BODY: String = """fun main() {
    render {
        pfCard {
            pfCardBody { +"Body" }
        }
    }
}
"""

    //language=kotlin
    const val MULTIPLE_BODY: String = """fun main() {
    render {
        pfCard {
            pfCardTitle { +"Title" }
            pfCardBody { +"Body" }
            pfCardBody { +"Body" }
            pfCardBody { +"Body" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val BODY_FILLS: String = """fun main() {
    render {
        pfCard {
            domNode.style.minHeight = "30em"
            pfCardTitle { +"Title" }
            pfCardBody(noFill) { +"Body pf-m-no-fill" }
            pfCardBody(noFill) { +"Body pf-m-no-fill" }
            pfCardBody { +"Body" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val HOVER: String = """fun main() {
    render {
        pfCard(modifier = hoverable) {
            pfCardTitle { +"Title" }
            pfCardBody { +"Body" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val COMPACT: String = """fun main() {
    render {
        pfCard(modifier = compact) {
            pfCardTitle { +"Title" }
            pfCardBody { +"Body" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""

    //language=kotlin
    const val SELECTABLE: String = """fun main() {
    render {
        pfCard(selectable = true) {
            pfCardHeader {
                pfCardActions {
                    pfDropdown(DropdownStore<String>(), align = Align.RIGHT) {
                        pfDropdownKebab()
                        pfDropdownItems {
                            pfItem("Action 1")
                            pfItem("Disabled Action") {
                                disabled = true
                            }
                            pfSeparator()
                            pfItem("Separated Action")
                        }
                    }
                }
            }
            pfCardTitle { +"First card" }
            pfCardBody { +"This is a selectable card. Click me to select me. Click again to deselect me." }
        }
        pfCard(selectable = true) {
            pfCardTitle { +"Second card" }
            pfCardBody { +"This is a selectable card. Click me to select me. Click again to deselect me." }
        }
        pfCard(selectable = true) {
            pfCardHeader {
                pfCardActions {
                    pfCardCheckbox()
                }
            }
            pfCardTitle { +"Third card" }
            pfCardBody { +"This is a selectable card. Click the card or the checkbox to select me." }
        }
    }
}
"""

    //language=kotlin
    const val FLAT: String = """fun main() {
    render {
        pfCard(modifier = flat) {
            pfCardTitle { +"Title" }
            pfCardBody { +"Body" }
            pfCardFooter { +"Footer" }
        }
    }
}
"""
}
