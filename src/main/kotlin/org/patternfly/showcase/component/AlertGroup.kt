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
import org.patternfly.Severity.DANGER
import org.patternfly.Severity.INFO
import org.patternfly.Severity.SUCCESS
import org.patternfly.alert
import org.patternfly.alertDescription
import org.patternfly.alertGroup
import org.patternfly.clickButton
import org.patternfly.pageSection
import org.patternfly.showcase.TICKER_DELAY
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class AlertGroupComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Alert group",
            prefix = "An ",
            key = "alert group",
            text = " is used to stack and position alerts in a layer over the main content of a page. This component is mainly used for positioning toast alerts. Related design guidelines: ",
            link = ("alerts-and-notifications" to "Alerts and notifications")
        )
        pageSection(baseClass = "sc-component__buttons") {
            h2 { +"Examples" }
            snippet("Static alert group", AlertGroupCode.STATIC_ALERT_GROUP) {
                alertGroup {
                    alert(SUCCESS, "Success alert title", inline = true)
                    alert(DANGER, "Danger alert title", inline = true)
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

                MainScope().launch {
                    tick.data.collect {
                        var counter = 0
                        while (it) {
                            NotificationStore.add(Notification(INFO, "Async notification $counter"))
                            counter++
                            delay(TICKER_DELAY)
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
            alert(SUCCESS, "Success alert title", inline = true)
            alert(DANGER, "Danger alert title", inline = true)
            alert(INFO, "Info alert title", inline = true) {
                alertDescription {
                    p {
                        +"Info alert description. "
                        a {
                            href = const("#")
                            +"This is a link."
                        }
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val TOAST_ALERT_GROUP: String = """fun main() {
    render {
        pushButton(baseClass = "secondary".modifier()) {
            +"Add toast success alert"
            clicks
                .map { Notification(SUCCESS, "Toast Success Alert") }
                .handledBy(Notification.store.add)
        }
        pushButton(baseClass = "secondary".modifier()) {
            +"Add toast danger alert"
            clicks
                .map { Notification(DANGER, "Toast Danger Alert") }
                .handledBy(Notification.store.add)
        }
        pushButton(baseClass = "secondary".modifier()) {
            +"Add toast info alert"
            clicks
                .map { Notification(INFO, "Toast Info Alert") }
                .handledBy(Notification.store.add)
        }
    }
}
"""

    //language=kotlin
    const val REACTIVE: String = """fun main() {
    render {
        var job: Job? = null
        val counter = object : RootStore<Int>(0, dropInitialData = true) {
            val inc = handle { it + 1 }
        }
        counter.data
            .map { Notification(INFO, "Async notification ${'$'}it") }
            .handledBy(Notification.store.add)

        fun startSending() {
            job = MainScope().launch {
                while (true) {
                    action() handledBy counter.inc
                    delay(TICKER_DELAY)
                }
            }
        }

        fun stopSending() {
            job?.cancel()
        }

        pushButton(baseClass = "secondary".modifier()) {
            +"Start async alerts"
            MainScope().launch {
                clicks.events.collect { startSending() }
            }
        }
        pushButton(baseClass = "secondary".modifier()) {
            +"Stop async alerts"
            MainScope().launch {
                clicks.events.collect { stopSending() }
            }
        }
    }
}
"""
}
