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
import org.patternfly.alertActions
import org.patternfly.alertDescription
import org.patternfly.classes
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.pushButton

class AlertComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Alert",
            key = "Alerts",
            text = " are used to notify the user about a change in status or other event. Related design guidelines: ",
            link = ("alerts-and-notifications" to "Alerts and notifications")
        )
        pageSection {
            h2 { +"Examples" }
            snippet("Types", AlertCode.TYPES) {
                alert(DEFAULT, "Default alert title")
                br {}
                alert(INFO, "Info alert title")
                br {}
                alert(SUCCESS, "Success alert title")
                br {}
                alert(WARNING, "Warning alert title")
                br {}
                alert(DANGER, "Danger alert title")
            }
            snippet("Variations", AlertCode.VARIATIONS) {
                alert(SUCCESS, "Success alert title", closable = true) {
                    alertDescription {
                        p { +"Success alert description. This should tell the user more information about the alert." }
                    }
                    alertActions {
                        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"View details" }
                        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Ignore" }
                    }
                }
                br {}
                alert(SUCCESS, "Success alert title", closable = true) {
                    alertDescription {
                        p {
                            +"Success alert description. This should tell the user more information about the alert. "
                            a {
                                href("#")
                                +"This is a link."
                            }
                        }
                    }
                }
                br {}
                alert(SUCCESS, "Success alert title", closable = true) {
                    alertActions {
                        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"View details" }
                        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Ignore" }
                    }
                }
                br {}
                alert(SUCCESS, "Success alert title", closable = true)
                br {}
                alert(SUCCESS, "Success alert title")
            }
            snippet("Inline types", AlertCode.INLINE_TYPES) {
                alert(DEFAULT, "Default inline alert title", inline = true)
                br {}
                alert(INFO, "Info inline alert title", inline = true)
                br {}
                alert(SUCCESS, "Success inline alert title", inline = true)
                br {}
                alert(WARNING, "Warning inline alert title", inline = true)
                br {}
                alert(DANGER, "Danger inline alert title", inline = true)
            }
            snippet("Inline Variations", AlertCode.INLINE_VARIATIONS) {
                alert(SUCCESS, "Success alert title", closable = true, inline = true) {
                    alertDescription {
                        p { +"Success alert description. This should tell the user more information about the alert." }
                    }
                    alertActions {
                        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"View details" }
                        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Ignore" }
                    }
                }
                br {}
                alert(SUCCESS, "Success alert title", closable = true, inline = true) {
                    alertDescription {
                        p {
                            +"Success alert description. This should tell the user more information about the alert. "
                            a {
                                href("#")
                                +"This is a link."
                            }
                        }
                    }
                }
                br {}
                alert(SUCCESS, "Success alert title", closable = true, inline = true) {
                    alertActions {
                        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"View details" }
                        pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Ignore" }
                    }
                }
                br {}
                alert(SUCCESS, "Success alert title", closable = true, inline = true)
                br {}
                alert(SUCCESS, "Success alert title", inline = true)
            }
            snippet("Reactive", AlertCode.REACTIVE) {
                alert(DEFAULT, "Close me", closable = true) {
                    closes handledBy Notification.default("Notification closed")
                }
            }
        }
    }
}

internal object AlertCode {

    //language=kotlin
    const val TYPES: String = """fun main() {
    render {
        alert(DEFAULT, "Default alert title")
        br {}
        alert(INFO, "Info alert title")
        br {}
        alert(SUCCESS, "Success alert title")
        br {}
        alert(WARNING, "Warning alert title")
        br {}
        alert(DANGER, "Danger alert title")
    }
}
"""

    //language=kotlin
    const val VARIATIONS: String = """fun main() {
    render {
        alert(SUCCESS, "Success alert title", closable = true) {
            alertDescription {
                p { +"Success alert description. This should tell the user more information about the alert." }
            }
            alertActionGroup {
                pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"View details" }
                pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Ignore" }
            }
        }
        br {}
        alert(SUCCESS, "Success alert title", closable = true) {
            alertDescription {
                p {
                    +"Success alert description. This should tell the user more information about the alert. "
                    a {
                        href = const("#")
                        +"This is a link."
                    }
                }
            }
        }
        br {}
        alert(SUCCESS, "Success alert title", closable = true) {
            alertActionGroup {
                pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"View details" }
                pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Ignore" }
            }
        }
        br {}
        alert(SUCCESS, "Success alert title", closable = true)
        br {}
        alert(SUCCESS, "Success alert title")
    }
}
"""

    //language=kotlin
    const val INLINE_TYPES: String = """fun main() {
    render {
        alert(DEFAULT, "Default inline alert title", inline = true)
        br {}
        alert(INFO, "Info inline alert title", inline = true)
        br {}
        alert(SUCCESS, "Success inline alert title", inline = true)
        br {}
        alert(WARNING, "Warning inline alert title", inline = true)
        br {}
        alert(DANGER, "Danger inline alert title", inline = true)
    }
}
"""

    //language=kotlin
    const val INLINE_VARIATIONS: String = """fun main() {
    render {
        alert(SUCCESS, "Success alert title", closable = true, inline = true) {
            alertDescription {
                p { +"Success alert description. This should tell the user more information about the alert." }
            }
            alertActionGroup {
                pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"View details" }
                pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Ignore" }
            }
        }
        br {}
        alert(SUCCESS, "Success alert title", closable = true, inline = true) {
            alertDescription {
                p {
                    +"Success alert description. This should tell the user more information about the alert. "
                    a {
                        href = const("#")
                        +"This is a link."
                    }
                }
            }
        }
        br {}
        alert(SUCCESS, "Success alert title", closable = true, inline = true) {
            alertActionGroup {
                pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"View details" }
                pushButton(baseClass = classes("link".modifier(), "inline".modifier())) { +"Ignore" }
            }
        }
        br {}
        alert(SUCCESS, "Success alert title", closable = true, inline = true)
        br {}
        alert(SUCCESS, "Success alert title", inline = true)
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        alert(DEFAULT, "Close me", closable = true) {
            closes.map {
                Notification(DEFAULT, "Notification closed")
            } handledBy Notification.store.add
        }
    }
}
"""
}
