package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.Color
import org.patternfly.Color.GREY
import org.patternfly.Severity.INFO
import org.patternfly.fas
import org.patternfly.label
import org.patternfly.notification
import org.patternfly.pageSection
import org.patternfly.util

object LabelComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Label",
            text = "Use a label when you want to highlight an element on a page to draw attention to it or make it more searchable. Labels can also be used to tag items of the same category. If you want to show a count, use a badge instead.",
            designGuidelines = "https://www.patternfly.org/v4/components/label/design-guidelines"
        )
        pageSection {
            snippet("Filled", LabelCode.FILLED) {
                for (color in Color.values()) {
                    label(color, baseClass = "mr-xs".util()) { +"Grey" }
                    label(color, baseClass = "mr-xs".util()) {
                        icon("info-circle".fas())
                        +"${color.name.lowercase()} icon"
                    }
                    label(color, baseClass = "mr-xs".util()) {
                        +"${color.name.lowercase()} removable"
                        closable(true)
                        events {
                            closes handledBy notification(INFO, "Label closed")
                        }
                    }
                    label(color, baseClass = "mr-xs".util()) {
                        icon("info-circle".fas())
                        +"${color.name.lowercase()} icon removable"
                        closable(true)
                        events {
                            closes handledBy notification(INFO, "Label closed")
                        }
                    }
                    label(color, baseClass = "mr-xs".util()) {
                        +"${color.name.lowercase()} link"
                        href("https://www.patternfly.org/")
                    }
                    label(color, baseClass = "mr-xs".util()) {
                        +"${color.name.lowercase()} link removable"
                        href("https://www.patternfly.org/")
                        closable(true)
                        events {
                            closes handledBy notification(INFO, "Label closed")
                        }
                    }
                    label(color, baseClass = "mr-xs".util()) {
                        +"${color.name.lowercase()} label with icon that truncates"
                        icon("info-circle".fas())
                        closable(true)
                        events {
                            closes handledBy notification(INFO, "Label closed")
                        }
                    }
                    br {}
                    br {}
                }
            }
            snippet("Outline", LabelCode.OUTLINE) {
                for (color in Color.values()) {
                    label(color, outline = true, baseClass = "mr-xs".util()) { +"Grey" }
                    label(color, outline = true, baseClass = "mr-xs".util()) {
                        icon("info-circle".fas())
                        +"${color.name.lowercase()} icon"
                    }
                    label(color, outline = true, baseClass = "mr-xs".util()) {
                        +"${color.name.lowercase()} removable"
                        closable(true)
                        events {
                            closes handledBy notification(INFO, "Label closed")
                        }
                    }
                    label(color, outline = true, baseClass = "mr-xs".util()) {
                        icon("info-circle".fas())
                        +"${color.name.lowercase()} icon removable"
                        closable(true)
                        events {
                            closes handledBy notification(INFO, "Label closed")
                        }
                    }
                    label(color, outline = true, baseClass = "mr-xs".util()) {
                        +"${color.name.lowercase()} link"
                        href("https://www.patternfly.org/")
                    }
                    label(color, outline = true, baseClass = "mr-xs".util()) {
                        +"${color.name.lowercase()} link removable"
                        href("https://www.patternfly.org/")
                        closable(true)
                        events {
                            closes handledBy notification(INFO, "Label closed")
                        }
                    }
                    label(color, outline = true, baseClass = "mr-xs".util()) {
                        +"${color.name.lowercase()} label with icon that truncates"
                        icon("info-circle".fas())
                        closable(true)
                        events {
                            closes handledBy notification(INFO, "Label closed")
                        }
                    }
                    br {}
                    br {}
                }
            }
            snippet("Compact", LabelCode.COMPACT) {
                label(GREY, compact = true, baseClass = "mr-xs".util()) { +"Grey" }
                label(GREY, compact = true, baseClass = "mr-xs".util()) {
                    icon("info-circle".fas())
                    +"Grey icon"
                }
                label(GREY, compact = true, baseClass = "mr-xs".util()) {
                    +"Grey removable"
                    closable(true)
                    events {
                        closes handledBy notification(INFO, "Label closed")
                    }
                }
                label(GREY, compact = true, baseClass = "mr-xs".util()) {
                    icon("info-circle".fas())
                    +"Grey icon removable"
                }
                label(GREY, compact = true, baseClass = "mr-xs".util()) {
                    +"Grey link"
                    href("https://www.patternfly.org/")
                }
                label(GREY, compact = true, baseClass = "mr-xs".util()) {
                    +"Grey link removable"
                    href("https://www.patternfly.org/")
                    closable(true)
                    events {
                        closes handledBy notification(INFO, "Label closed")
                    }
                }
                label(GREY, compact = true, baseClass = "mr-xs".util()) {
                    +"Grey label with icon that truncates"
                    icon("info-circle".fas())
                    closable(true)
                    events {
                        closes handledBy notification(INFO, "Label closed")
                    }
                }
            }
        }
    }
}

object LabelCode {

    //language=kotlin
    const val FILLED: String = """"""

    //language=kotlin
    const val OUTLINE: String = """"""

    //language=kotlin
    const val COMPACT: String = """"""
}
