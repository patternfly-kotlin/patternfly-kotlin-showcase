@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.patternfly.ButtonVariation.secondary
import org.patternfly.NotificationAlert
import org.patternfly.NotificationStore
import org.patternfly.Severity.DANGER
import org.patternfly.Severity.DEFAULT
import org.patternfly.Severity.INFO
import org.patternfly.Severity.SUCCESS
import org.patternfly.Severity.WARNING
import org.patternfly.alertGroup
import org.patternfly.clickButton
import org.patternfly.notification
import org.patternfly.pageSection
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
object AlertGroupComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Alert group",
            text = "An alert group stacks and positions alerts in a layer over the main content of a page. This component is mainly used for positioning toast alerts.",
            designGuidelines = "https://www.patternfly.org/v4/components/alert-group/design-guidelines"
        )
        pageSection(baseClass = "sc-component__buttons") {
            snippet("Static alert group", AlertGroupCode.STATIC_ALERT_GROUP) {
                alertGroup {
                    alert {
                        severity(DEFAULT)
                        title("Default alert title")
                    }
                    alert {
                        severity(INFO)
                        title("Info alert title")
                        content {
                            p {
                                +"Info alert description. "
                                a {
                                    href("#")
                                    +"This is a link."
                                }
                            }
                        }
                    }
                    alert {
                        severity(SUCCESS)
                        title("Success alert title")
                    }
                    alert {
                        severity(WARNING)
                        title("Warning alert title")
                    }
                    alert {
                        severity(DANGER)
                        title("Danger alert title")
                    }
                }
            }
            snippet("Toast alert group", AlertGroupCode.TOAST_ALERT_GROUP) {
                clickButton(secondary) {
                    +"Add toast success alert"
                } handledBy notification(SUCCESS, "Toast Success Alert")
                clickButton(secondary) {
                    +"Add toast danger alert"
                } handledBy notification(DANGER, "Toast Danger Alert")
                clickButton(secondary) {
                    +"Add toast info alert"
                } handledBy notification(INFO, "Toast Info Alert")
            }
            snippet("Reactive", AlertGroupCode.REACTIVE) {
                val tick = storeOf(false)
                clickButton(secondary) { +"Start async alerts" }.map { true } handledBy tick.update
                clickButton(secondary) { +"Stop async alerts" }.map { false } handledBy tick.update

                var counter = 0
                MainScope().launch {
                    tick.data.collect {
                        while (tick.current) {
                            NotificationStore.add(NotificationAlert(INFO, "Async notification #$counter"))
                            delay(750.milliseconds)
                            counter++
                        }
                    }
                }
            }
        }
    }
}

internal object AlertGroupCode {

    //language=kotlin
    const val STATIC_ALERT_GROUP: String = """fun main() {
    render {
        alertGroup {
            alert {
                severity(DEFAULT)
                title("Default alert title")
            }
            alert {
                severity(INFO)
                title("Info alert title")
                content {
                    p {
                        +"Info alert description. "
                        a {
                            href("#")
                            +"This is a link."
                        }
                    }
                }
            }
            alert { 
                severity(SUCCESS)
                title("Success alert title") 
            }
            alert { 
                severity(WARNING)
                title("Warning alert title") 
            }
            alert { 
                severity(DANGER)
                title("Danger alert title") 
            }
        }
    }
}
"""

    //language=kotlin
    const val TOAST_ALERT_GROUP: String = """fun main() {
    render {
        clickButton(secondary) {
            +"Add toast success alert"
        } handledBy Notification.success("Toast Success Alert")
        clickButton(secondary) {
            +"Add toast danger alert"
        } handledBy Notification.error("Toast Danger Alert")
        clickButton(secondary) {
            +"Add toast info alert"
        } handledBy Notification.info("Toast Info Alert")
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        val tick = storeOf(false)
        clickButton(secondary) { +"Start async alerts" }.map { true } handledBy tick.update
        clickButton(secondary) { +"Stop async alerts" }.map { false } handledBy tick.update

        var counter = 0
        MainScope().launch {
            tick.data.collect {
                while (tick.current) {
                    notification<Int> { counter ->
                        severity(INFO)
                        title("Async notification #${'$'}counter")
                    }
                    counter++
                    delay(750.milliseconds)
                }
            }
        }
    }
}
"""
}
