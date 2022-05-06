@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.ListType.ICONS
import org.patternfly.ListType.LARGE_ICONS
import org.patternfly.ListType.ORDERED
import org.patternfly.ListType.PLAIN
import org.patternfly.ListType.UNORDERED
import org.patternfly.fas
import org.patternfly.list
import org.patternfly.pageSection

object ListComponent {
        val content: RenderContext.() -> Unit = {
        intro(
            title = "List",
            text = "A list component embeds a formatted list (bulleted or numbered list) into page content.",
            designGuidelines = "https://www.patternfly.org/v4/components/list/design-guidelines"
        )
        pageSection {
            snippet("Basic", ListCode.BASIC) {
                list(UNORDERED) {
                    item("First")
                    item("Second")
                    item("Third")
                }
            }
            snippet("Inline", ListCode.INLINE) {
                list(PLAIN, inline = true) {
                    item("First")
                    item("Second")
                    item("Third")
                }
            }
            snippet("Ordered", ListCode.ORDERED) {
                list(ORDERED) {
                    item("First")
                    item("Second")
                    item("Third")
                }
            }
            snippet("Plain", ListCode.PLAIN) {
                list(PLAIN) {
                    item("First")
                    item("Second")
                    item("Third")
                }
            }
            snippet("With horizontal rules", ListCode.HORIZONTAL_RULES) {
                list(PLAIN, bordered = true) {
                    item("First")
                    item("Second")
                    item("Third")
                }
            }
            snippet("With icons", ListCode.ICONS) {
                list(ICONS) {
                    item("First") { icon("book-open".fas()) }
                    item("Second") { icon("key".fas()) }
                    item("Third") { icon("desktop".fas()) }
                }
            }
            snippet("With large icons", ListCode.LARGE_ICONS) {
                list(LARGE_ICONS) {
                    item("First") { icon("book-open".fas()) }
                    item("Second") { icon("key".fas()) }
                    item("Third") { icon("desktop".fas()) }
                }
            }
        }
    }
}

object ListCode {

    //language=kotlin
    const val BASIC: String = """"""

    //language=kotlin
    const val ORDERED: String = """"""

    //language=kotlin
    const val INLINE: String = """"""

    //language=kotlin
    const val PLAIN: String = """"""

    //language=kotlin
    const val HORIZONTAL_RULES: String = """"""

    //language=kotlin
    const val ICONS: String = """"""

    //language=kotlin
    const val LARGE_ICONS: String = """"""
}
