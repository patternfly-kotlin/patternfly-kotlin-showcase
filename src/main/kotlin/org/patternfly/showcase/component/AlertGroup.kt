package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.patternfly.ButtonVariation.secondary
import org.patternfly.Notification
import org.patternfly.NotificationStore
import org.patternfly.Severity
import org.patternfly.Severity.DANGER
import org.patternfly.Severity.DEFAULT
import org.patternfly.Severity.INFO
import org.patternfly.Severity.SUCCESS
import org.patternfly.Severity.WARNING
import org.patternfly.alert
import org.patternfly.alertDescription
import org.patternfly.alertGroup
import org.patternfly.clickButton
import org.patternfly.pageSection
import kotlin.time.milliseconds

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
                    alert(DEFAULT, "Default alert title", inline = true)
                    alert(INFO, "Info alert title", inline = true) {
                        alertDescription {
                            p {
                                +"Info alert description. "
                                a {
                                    href("#")
                                    +"This is a link."
                                }
                            }
                        }
                    }
                    alert(SUCCESS, "Success alert title", inline = true)
                    alert(WARNING, "Warning alert title", inline = true)
                    alert(DANGER, "Danger alert title", inline = true)
                }
            }
            snippet("Toast alert group", AlertGroupCode.TOAST_ALERT_GROUP) {
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
            snippet("Reactive", AlertGroupCode.REACTIVE) {
                val tick = storeOf(false)
                clickButton(secondary) { +"Start async alerts" }.map { true } handledBy tick.update
                clickButton(secondary) { +"Stop async alerts" }.map { false } handledBy tick.update

                var counter = 0
                MainScope().launch {
                    tick.data.collect {
                        while (tick.current) {
                            NotificationStore.add(Notification(INFO, "Async notification #$counter"))
                            counter++
                            delay(750.milliseconds)
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
            alert(DEFAULT, "Default alert title", inline = true)
            alert(INFO, "Info alert title", inline = true) {
                alertDescription {
                    p {
                        +"Info alert description. "
                        a {
                            href("#")
                            +"This is a link."
                        }
                    }
                }
            }
            alert(SUCCESS, "Success alert title", inline = true)
            alert(WARNING, "Warning alert title", inline = true)
            alert(DANGER, "Danger alert title", inline = true)
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
                    NotificationStore.add(Notification(INFO, "Async notification #${'$'}counter"))
                    counter++
                    delay(750.milliseconds)
                }
            }
        }
    }
}
"""
}
