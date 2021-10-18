@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.Notification
import org.patternfly.Severity.DANGER
import org.patternfly.Severity.DEFAULT
import org.patternfly.Severity.INFO
import org.patternfly.Severity.SUCCESS
import org.patternfly.Severity.WARNING
import org.patternfly.alert
import org.patternfly.pageSection

internal object AlertComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Alert",
            text = "An alert is used to notify the user about a change in status or other event without blocking other actions in an interface.",
            designGuidelines = "https://www.patternfly.org/v4/components/alert/design-guidelines"
        )
        pageSection {
            snippet("Types", AlertCode.TYPES) {
                alert {
                    severity(DEFAULT)
                    title("Default alert title")
                }
                br {}
                alert {
                    severity(INFO)
                    title("Info alert title")
                }
                br {}
                alert {
                    severity(SUCCESS)
                    title("Success alert title")
                }
                br {}
                alert {
                    severity(WARNING)
                    title("Warning alert title")
                }
                br {}
                alert {
                    severity(DANGER)
                    title("Danger alert title")
                }
            }
            snippet("Variations", AlertCode.VARIATIONS) {
                alert {
                    severity(SUCCESS)
                    title("Success alert title")
                    content {
                        p { +"Success alert description. This should tell the user more information about the alert." }
                    }
                    closable(true)
                    action("View details")
                    action("Ignore")
                }
                br {}
                alert {
                    severity(SUCCESS)
                    title("Success alert title")
                    content {
                        p {
                            +"Success alert description. This should tell the user more information about the alert. "
                            a {
                                href("#")
                                +"This is a link."
                            }
                        }
                    }
                    closable(true)
                }
                br {}
                alert {
                    severity(SUCCESS)
                    title("Success alert title")
                    closable(true)
                    action("View details")
                    action("Ignore")
                }
                br {}
                alert {
                    severity(SUCCESS)
                    title("Success alert title")
                    closable(true)
                }
                br {}
                alert {
                    severity(SUCCESS)
                    title("Success alert title")
                }
            }
            snippet("Inline types", AlertCode.INLINE_TYPES) {
                alert {
                    severity(DEFAULT)
                    inline(true)
                    title("Default inline alert title")
                }
                alert {
                    severity(INFO)
                    inline(true)
                    title("Info inline alert title")
                }
                alert {
                    severity(SUCCESS)
                    inline(true)
                    title("Success inline alert title")
                }
                alert {
                    severity(WARNING)
                    inline(true)
                    title("Warning inline alert title")
                }
                alert {
                    severity(DANGER)
                    inline(true)
                    title("Danger inline alert title")
                }
            }
            snippet("Inline Variations", AlertCode.INLINE_VARIATIONS) {
                alert {
                    severity(SUCCESS)
                    inline(true)
                    title("Success alert title")
                    content {
                        p { +"Success alert description. This should tell the user more information about the alert." }
                    }
                    closable(true)
                    action("View details")
                    action("Ignore")
                }
                br {}
                alert {
                    severity(SUCCESS)
                    inline(true)
                    title("Success alert title")
                    content {
                        p {
                            +"Success alert description. This should tell the user more information about the alert. "
                            a {
                                href("#")
                                +"This is a link."
                            }
                        }
                    }
                    closable(true)
                }
                br {}
                alert {
                    severity(SUCCESS)
                    inline(true)
                    title("Success alert title")
                    closable(true)
                    action("View details")
                    action("Ignore")
                }
                br {}
                alert {
                    severity(SUCCESS)
                    inline(true)
                    title("Success alert title")
                    closable(true)
                }
                br {}
                alert {
                    severity(SUCCESS)
                    inline(true)
                    title("Success alert title")
                }
            }
            snippet("Reactive", AlertCode.REACTIVE) {
                alert {
                    severity(DEFAULT)
                    title("Close me")
                    content { +loremIpsum(3) }
                    closable(true) {
                        clicks handledBy Notification.default("Notification closed")
                    }
                    action("Click me") {
                        clicks handledBy Notification.default("You did it!")
                    }
                }
            }
        }
    }
}

internal object AlertCode {

    //language=kotlin
    const val TYPES: String = """fun main() {
    render {
        alert {
            severity(DEFAULT)
            title("Default alert title")
        }
        br {}
        alert {
            severity(INFO)
            title("Info alert title")
        }
        br {}
        alert {
            severity(SUCCESS)
            title("Success alert title")
        }
        br {}
        alert {
            severity(WARNING)
            title("Warning alert title")
        }
        br {}
        alert {
            severity(DANGER)
            title("Danger alert title")
        }
    }
}
"""

    //language=kotlin
    const val VARIATIONS: String = """fun main() {
    render {
        alert { 
            severity(SUCCESS)
            title("Success alert title")
            content {
                p { +"Success alert description. This should tell the user more information about the alert." }
            }
            closable(true)
            action("View details")
            action("Ignore")
        }
        br {}
        alert { 
            severity(SUCCESS)
            title("Success alert title")
            content {
                p {
                    +"Success alert description. This should tell the user more information about the alert. "
                    a {
                        href("#")
                        +"This is a link."
                    }
                }
            }
            closable(true)
        }
        br {}
        alert { 
            severity(SUCCESS)
            title("Success alert title")
            closable(true)
            action("View details")
            action("Ignore")
        }
        br {}
        alert { 
            severity(SUCCESS)
            title("Success alert title")
            closable(true)
        }
        br {}
        alert {
            severity(SUCCESS)
            title("Success alert title")
        }
    }    
}
"""

    //language=kotlin
    const val INLINE_TYPES: String = """fun main() {
    render {
        alert { 
            severity(DEFAULT)
            inline(true)
            title("Default inline alert title")
        }
        alert { 
            severity(INFO)
            inline(true)
            title("Info inline alert title")
        }
        alert { 
            severity(SUCCESS)
            inline(true)
            title("Success inline alert title")
        }
        alert { 
            severity(WARNING)
            inline(true)
            title("Warning inline alert title")
        }
        alert { 
            severity(DANGER)
            inline(true)
            title("Danger inline alert title")
        }
    }
}
"""

    //language=kotlin
    const val INLINE_VARIATIONS: String = """fun main() {
    render {
        alert {
            severity(SUCCESS)
            inline(true)
            title("Success alert title")
            content {
                p { +"Success alert description. This should tell the user more information about the alert." }
            }
            closable(true)
            action("View details")
            action("Ignore")
        }
        br {}
        alert {
            severity(SUCCESS)
            inline(true)
            title("Success alert title")
            content {
                p {
                    +"Success alert description. This should tell the user more information about the alert. "
                    a {
                        href("#")
                        +"This is a link."
                    }
                }
            }
            closable(true)
        }
        br {}
        alert {
            severity(SUCCESS)
            inline(true)
            title("Success alert title")
            closable(true)
            action("View details")
            action("Ignore")
        }
        br {}
        alert {
            severity(SUCCESS)
            inline(true)
            title("Success alert title")
            closable(true)
        }
        br {}
        alert {
            severity(SUCCESS)
            inline(true)
            title("Success alert title")
        }
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        alert {
            severity(DEFAULT)
            title("Close me")
            content { +loremIpsum(3) }
            closable(true) {
                clicks handledBy Notification.default("Notification closed")
            }
            action("Click me") {
                clicks handledBy Notification.default("You did it!")
            }
        }
    }
}
"""
}
