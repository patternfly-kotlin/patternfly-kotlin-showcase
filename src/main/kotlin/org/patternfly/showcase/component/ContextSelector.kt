package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.contextSelector
import org.patternfly.item
import org.patternfly.items
import org.patternfly.pageSection

object ContextSelectorComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Context selector",
            text = "A context selector can be used in addition to global navigation when the data or resources you show in the interface need to change depending on the user’s context.",
            designGuidelines = "https://www.patternfly.org/v4/components/context-selector/design-guidelines/"
        )
        pageSection {
            snippet("Basic", ContextSelectorCode.BASIC) {
                contextSelector<String> {
                    items {
                        item("My Project") { selected = true }
                        item("OpenShift Cluster")
                        item("Production Ansible")
                        item("AWS")
                        item("Azure")
                        item("My Project 2")
                        item("Production Ansible 2 ")
                        item("AWS 2")
                    }
                }
            }
        }
    }
}

object ContextSelectorCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render { 
        contextSelector<String> {
            items {
                item("My Project") { selected = true }
                item("OpenShift Cluster")
                item("Production Ansible")
                item("AWS")
                item("Azure")
                item("My Project 2")
                item("Production Ansible 2 ")
                item("AWS 2")
            }
        }
    }
}
"""
}
