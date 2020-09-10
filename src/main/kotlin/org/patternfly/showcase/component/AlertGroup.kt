@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.RootStore
import dev.fritz2.binding.action
import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Modifier.secondary
import org.patternfly.Notification
import org.patternfly.Severity.DANGER
import org.patternfly.Severity.INFO
import org.patternfly.Severity.SUCCESS
import org.patternfly.pfAlert
import org.patternfly.pfAlertDescription
import org.patternfly.pfAlertGroup
import org.patternfly.pfButton
import org.patternfly.pfContent
import org.patternfly.pfSection
import org.w3c.dom.HTMLElement

object AlertGroupComponent : Iterable<Tag<HTMLElement>> {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Alert group",
                prefix = "An ",
                key = "alert group",
                text = " is used to stack and position alerts in a layer over the main content of a page. This component is mainly used for positioning toast alerts. Related design guidelines: ",
                link = ("alerts-and-notifications" to "Alerts and notifications")
            )
        })
        yield(render {
            pfSection {
                className = const("sc-component__buttons")
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Static alert group", AlertGroupCode.STATIC_ALERT_GROUP) {
                    pfAlertGroup {
                        pfAlert(SUCCESS, "Success alert title", inline = true)
                        pfAlert(DANGER, "Danger alert title", inline = true)
                        pfAlert(INFO, "Info alert title", inline = true) {
                            pfAlertDescription {
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
                snippet("Toast alert group", AlertGroupCode.TOAST_ALERT_GROUP) {
                    pfButton(secondary) {
                        +"Add toast success alert"
                        clicks
                            .map { Notification(SUCCESS, "Toast Success Alert") }
                            .handledBy(Notification.store.add)
                    }
                    pfButton(secondary) {
                        +"Add toast danger alert"
                        clicks
                            .map { Notification(DANGER, "Toast Danger Alert") }
                            .handledBy(Notification.store.add)
                    }
                    pfButton(secondary) {
                        +"Add toast info alert"
                        clicks
                            .map { Notification(INFO, "Toast Info Alert") }
                            .handledBy(Notification.store.add)
                    }
                }
                snippet("Reactive", AlertGroupCode.REACTIVE) {
                    var job: Job? = null
                    val counter = object : RootStore<Int>(0, dropInitialData = true) {
                        val inc = handle { it + 1 }
                    }
                    counter.data
                        .map { Notification(INFO, "Async notification $it") }
                        .handledBy(Notification.store.add)

                    fun startSending() {
                        job = MainScope().launch {
                            while (true) {
                                action() handledBy counter.inc
                                delay(1000)
                            }
                        }
                    }

                    fun stopSending() {
                        job?.cancel()
                    }

                    pfButton(secondary) {
                        +"Start async alerts"
                        MainScope().launch {
                            clicks.events.collect { startSending() }
                        }
                    }
                    pfButton(secondary) {
                        +"Stop async alerts"
                        MainScope().launch {
                            clicks.events.collect { stopSending() }
                        }
                    }
                }
            }
        })
    }
}

internal object AlertGroupCode {

    //language=kotlin
    const val STATIC_ALERT_GROUP: String = """fun main() {
    render {
        pfAlertGroup {
            pfAlert(SUCCESS, "Success alert title", inline = true)
            pfAlert(DANGER, "Danger alert title", inline = true)
            pfAlert(INFO, "Info alert title", inline = true) {
                pfAlertDescription {
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
        pfButton(secondary) {
            +"Add toast success alert"
            clicks
                .map { Notification(SUCCESS, "Toast Success Alert") }
                .handledBy(Notification.store.add)
        }
        pfButton(secondary) {
            +"Add toast danger alert"
            clicks
                .map { Notification(DANGER, "Toast Danger Alert") }
                .handledBy(Notification.store.add)
        }
        pfButton(secondary) {
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
        var counter = 1
        var job: Job? = null

        fun startSending() {
            job = MainScope().launch {
                while (true) {
                    Notification.info("Async notification ${'$'}counter was added to the queue.")
                    counter++
                    delay(750)
                }
            }
        }

        fun stopSending() {
            job?.cancel()
        }

        pfButton(secondary) {
            +"Start async alerts"
            domNode.onclick = { startSending() }
        }
        pfButton(secondary) {
            +"Stop async alerts"
            domNode.onclick = { stopSending() }
        }
    }
}
"""
}
