@file:Suppress("DuplicatedCode", "SpellCheckingInspection")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.ButtonVariation.primary
import org.patternfly.classes
import org.patternfly.divider
import org.patternfly.drawer
import org.patternfly.drawerAction
import org.patternfly.drawerBody
import org.patternfly.drawerClose
import org.patternfly.drawerContent
import org.patternfly.drawerHead
import org.patternfly.drawerPanel
import org.patternfly.drawerSection
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.pushButton
import org.patternfly.title
import org.patternfly.util

const val LOREM_IPSUM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus pretium est a porttitor vehicula. Quisque vel commodo urna. Morbi mattis rutrum ante, id vehicula ex accumsan ut. Morbi viverra, eros vel porttitor facilisis, eros purus aliquet erat,nec lobortis felis elit pulvinar sem. Vivamus vulputate, risus eget commodo eleifend, eros nibh porta quam, vitae lacinia leo libero at magna. Maecenas aliquam sagittis orci, et posuere nisi ultrices sit amet. Aliquam ex odio, malesuada sed posuere quis, pellentesque at mauris. Phasellus venenatis massa ex, eget pulvinar libero auctor pretium. Aliquam erat volutpat. Duis euismod justo in quam ullamcorper, in commodo massa vulputate."

object DrawerComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Drawer",
            text = "A drawer is a sliding panel that enters from the right edge of the viewport.",
            designGuidelines = "https://www.patternfly.org/v4/components/drawer/design-guidelines"
        )
        pageSection {
            snippet("Basic", DrawerCode.BASIC) {
                val button = pushButton(primary) { +"Toggle" }
                drawer(baseClass = "mt-md".util()) {
                    button.clicks handledBy ces.toggle
                    drawerContent {
                        drawerBody { +LOREM_IPSUM }
                    }
                    drawerPanel {
                        drawerBody {
                            drawerHead {
                                span {
                                    domNode.tabIndex = 0
                                    +"Drawer panel"
                                }
                                drawerAction {
                                    drawerClose()
                                }
                            }
                        }
                    }
                }
            }
            snippet("Left", DrawerCode.LEFT) {
                val button = pushButton(primary) { +"Toggle" }
                drawer(baseClass = classes("panel-left".modifier(), "mt-md".util())) {
                    button.clicks handledBy ces.toggle
                    drawerContent {
                        drawerBody { +LOREM_IPSUM }
                    }
                    drawerPanel {
                        drawerBody {
                            drawerHead {
                                span {
                                    domNode.tabIndex = 0
                                    +"Drawer panel"
                                }
                                drawerAction {
                                    drawerClose()
                                }
                            }
                        }
                    }
                }
            }
            snippet("Inline", DrawerCode.INLINE) {
                val button = pushButton(primary) { +"Toggle" }
                drawer(baseClass = classes("inline".modifier(), "mt-md".util())) {
                    button.clicks handledBy ces.toggle
                    drawerContent {
                        drawerBody { +LOREM_IPSUM }
                    }
                    drawerPanel {
                        drawerBody {
                            drawerHead {
                                span {
                                    domNode.tabIndex = 0
                                    +"Drawer panel"
                                }
                                drawerAction {
                                    drawerClose()
                                }
                            }
                        }
                    }
                }
            }
            snippet("Inline left", DrawerCode.INLINE_LEFT) {
                val button = pushButton(primary) { +"Toggle" }
                drawer(classes("inline".modifier(), "panel-left".modifier(), "mt-md".util())) {
                    button.clicks handledBy ces.toggle
                    drawerContent {
                        drawerBody { +LOREM_IPSUM }
                    }
                    drawerPanel {
                        drawerBody {
                            drawerHead {
                                span {
                                    domNode.tabIndex = 0
                                    +"Drawer panel"
                                }
                                drawerAction {
                                    drawerClose()
                                }
                            }
                        }
                    }
                }
            }
            snippet("Additional section above drawer content", DrawerCode.SECTION) {
                val button = pushButton(primary) { +"Toggle" }
                drawer(baseClass = "mt-md".util()) {
                    button.clicks handledBy ces.toggle
                    drawerSection {
                        title { +"Title" }
                        p { +"${LOREM_IPSUM.take(15)}..." }
                        divider(baseClass = "mb-md".util())
                    }
                    drawerContent {
                        drawerBody { +LOREM_IPSUM }
                    }
                    drawerPanel {
                        drawerBody {
                            drawerHead {
                                span {
                                    domNode.tabIndex = 0
                                    +"Drawer panel"
                                }
                                drawerAction {
                                    drawerClose()
                                }
                            }
                        }
                    }
                }
            }
            snippet("Static", DrawerCode.STATIC) {
                drawer("static".modifier()) {
                    ces.expand(Unit)
                    drawerContent {
                        drawerBody { +LOREM_IPSUM }
                    }
                    drawerPanel {
                        drawerBody {
                            drawerHead {
                                span { +"Drawer panel" }
                                drawerAction {
                                    drawerClose()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

object DrawerCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        val button = pushButton(primary) { +"Toggle" }
        drawer(baseClass = "mt-md".util()) {
            button.clicks handledBy ces.toggle
            drawerContent {
                drawerBody { +LOREM_IPSUM }
            }
            drawerPanel {
                drawerBody {
                    drawerHead {
                        span {
                            domNode.tabIndex = 0
                            +"Drawer panel"
                        }
                        drawerAction {
                            drawerClose()
                        }
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val LEFT: String = """fun main() {
    render {
        val button = pushButton(primary) { +"Toggle" }
        drawer(baseClass = classes("panel-left".modifier(), "mt-md".util())) {
            button.clicks handledBy ces.toggle
            drawerContent {
                drawerBody { +LOREM_IPSUM }
            }
            drawerPanel {
                drawerBody {
                    drawerHead {
                        span {
                            domNode.tabIndex = 0
                            +"Drawer panel"
                        }
                        drawerAction {
                            drawerClose()
                        }
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val INLINE: String = """fun main() {
    render {
        val button = pushButton(primary) { +"Toggle" }
        drawer(baseClass = classes("inline".modifier(), "mt-md".util())) {
            button.clicks handledBy ces.toggle
            drawerContent {
                drawerBody { +LOREM_IPSUM }
            }
            drawerPanel {
                drawerBody {
                    drawerHead {
                        span {
                            domNode.tabIndex = 0
                            +"Drawer panel"
                        }
                        drawerAction {
                            drawerClose()
                        }
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val INLINE_LEFT: String = """fun main() {
    render {
        val button = pushButton(primary) { +"Toggle" }
        drawer(classes("inline".modifier(), "panel-left".modifier(), "mt-md".util())) {
            button.clicks handledBy ces.toggle
            drawerContent {
                drawerBody { +LOREM_IPSUM }
            }
            drawerPanel {
                drawerBody {
                    drawerHead {
                        span {
                            domNode.tabIndex = 0
                            +"Drawer panel"
                        }
                        drawerAction {
                            drawerClose()
                        }
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val SECTION: String = """fun main() {
    render {
        val button = pushButton(primary) { +"Toggle" }
        drawer(baseClass = "mt-md".util()) {
            button.clicks handledBy ces.toggle
            drawerSection {
                title { +"Title" }
                p { +"${'$'}{LOREM_IPSUM.take(15)}..." }
                divider(baseClass = "mb-md".util())
            }
            drawerContent {
                drawerBody { +LOREM_IPSUM }
            }
            drawerPanel {
                drawerBody {
                    drawerHead {
                        span {
                            domNode.tabIndex = 0
                            +"Drawer panel"
                        }
                        drawerAction {
                            drawerClose()
                        }
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val STATIC: String = """fun main() {
    render {
        drawer("static".modifier()) {
            drawerContent {
                drawerBody { +LOREM_IPSUM }
            }
            drawerPanel {
                drawerBody {
                    drawerHead {
                        span { +"Drawer panel" }
                        drawerAction {
                            drawerClose()
                        }
                    }
                }
            }
        }
    }
}
"""
}
