@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import org.patternfly.Modifier.primary
import org.patternfly.classes
import org.patternfly.modifier
import org.patternfly.pfButton
import org.patternfly.pfContent
import org.patternfly.pfDivider
import org.patternfly.pfDrawer
import org.patternfly.pfDrawerActions
import org.patternfly.pfDrawerBody
import org.patternfly.pfDrawerClose
import org.patternfly.pfDrawerContent
import org.patternfly.pfDrawerHead
import org.patternfly.pfDrawerMain
import org.patternfly.pfDrawerPanel
import org.patternfly.pfDrawerSection
import org.patternfly.pfSection
import org.patternfly.pfTitle
import org.patternfly.util
import org.w3c.dom.HTMLElement

object DrawerComponent : Iterable<Tag<HTMLElement>> {
    const val LOREM_IPSUM =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus pretium est a porttitor vehicula. Quisque vel commodo urna. Morbi mattis rutrum ante, id vehicula ex accumsan ut. Morbi viverra, eros vel porttitor facilisis, eros purus aliquet erat,nec lobortis felis elit pulvinar sem. Vivamus vulputate, risus eget commodo eleifend, eros nibh porta quam, vitae lacinia leo libero at magna. Maecenas aliquam sagittis orci, et posuere nisi ultrices sit amet. Aliquam ex odio, malesuada sed posuere quis, pellentesque at mauris. Phasellus venenatis massa ex, eget pulvinar libero auctor pretium. Aliquam erat volutpat. Duis euismod justo in quam ullamcorper, in commodo massa vulputate."

    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Drawer",
                prefix = "The ",
                key = "drawer",
                text = " The Drawer is a sliding panel that enters from the right edge of the viewport. It can be configured to either overlay content on a page or create a sidebar by pushing that content to the left."
            )
        })
        yield(render {
            pfSection {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", DrawerCode.BASIC) {
                    val button = pfButton(classes { +primary; +"mb-md".util() }) {
                        +"Toggle drawer"
                    }
                    br {}
                    pfDrawer {
                        button.clicks handledBy expanded.toggle
                        pfDrawerMain {
                            pfDrawerContent {
                                pfDrawerBody { +LOREM_IPSUM }
                            }
                            pfDrawerPanel {
                                pfDrawerBody {
                                    pfDrawerHead {
                                        span {
                                            domNode.tabIndex = 0
                                            +"Drawer panel"
                                        }
                                        pfDrawerActions {
                                            pfDrawerClose()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                snippet("Left", DrawerCode.LEFT) {
                    val button = pfButton(classes { +primary; +"mb-md".util() }) {
                        +"Toggle drawer"
                    }
                    br {}
                    pfDrawer("panel-left".modifier()) {
                        button.clicks handledBy expanded.toggle
                        pfDrawerMain {
                            pfDrawerContent {
                                pfDrawerBody { +LOREM_IPSUM }
                            }
                            pfDrawerPanel {
                                pfDrawerBody {
                                    pfDrawerHead {
                                        span {
                                            domNode.tabIndex = 0
                                            +"Drawer panel"
                                        }
                                        pfDrawerActions {
                                            pfDrawerClose()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                snippet("Inline", DrawerCode.INLINE) {
                    val button = pfButton(classes { +primary; +"mb-md".util() }) {
                        +"Toggle drawer"
                    }
                    br {}
                    pfDrawer("inline".modifier()) {
                        button.clicks handledBy expanded.toggle
                        pfDrawerMain {
                            pfDrawerContent {
                                pfDrawerBody { +LOREM_IPSUM }
                            }
                            pfDrawerPanel {
                                pfDrawerBody {
                                    pfDrawerHead {
                                        span {
                                            domNode.tabIndex = 0
                                            +"Drawer panel"
                                        }
                                        pfDrawerActions {
                                            pfDrawerClose()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                snippet("Inline left", DrawerCode.INLINE_LEFT) {
                    val button = pfButton(classes { +primary; +"mb-md".util() }) {
                        +"Toggle drawer"
                    }
                    br {}
                    pfDrawer(classes("inline".modifier(), "panel-left".modifier())) {
                        button.clicks handledBy expanded.toggle
                        pfDrawerMain {
                            pfDrawerContent {
                                pfDrawerBody { +LOREM_IPSUM }
                            }
                            pfDrawerPanel {
                                pfDrawerBody {
                                    pfDrawerHead {
                                        span {
                                            domNode.tabIndex = 0
                                            +"Drawer panel"
                                        }
                                        pfDrawerActions {
                                            pfDrawerClose()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                snippet("Additional section above drawer content", DrawerCode.SECTION) {
                    val button = pfButton(classes { +primary; +"mb-md".util() }) {
                        +"Toggle drawer"
                    }
                    br {}
                    pfDrawer {
                        button.clicks handledBy expanded.toggle
                        pfDrawerSection {
                            pfContent {
                                pfTitle { +"Title" }
                                p { +"${LOREM_IPSUM.take(15)}..." }
                                pfDivider(classes = "mb-md".util())
                            }
                        }
                        pfDrawerMain {
                            pfDrawerContent {
                                pfDrawerBody { +LOREM_IPSUM }
                            }
                            pfDrawerPanel {
                                pfDrawerBody {
                                    pfDrawerHead {
                                        span {
                                            domNode.tabIndex = 0
                                            +"Drawer panel"
                                        }
                                        pfDrawerActions {
                                            pfDrawerClose()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                snippet("Static", DrawerCode.STATIC) {
                    pfDrawer("static".modifier()) {
                        pfDrawerMain {
                            pfDrawerContent {
                                pfDrawerBody { +LOREM_IPSUM }
                            }
                            pfDrawerPanel {
                                pfDrawerBody {
                                    pfDrawerHead {
                                        span { +"Drawer panel" }
                                        pfDrawerActions {
                                            pfDrawerClose()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })
    }
}

internal object DrawerCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        val button = pfButton(classes { +primary; +"mb-md".util() }) {
            +"Toggle drawer"
        }
        br{}
        pfDrawer {
            button.clicks handledBy expanded.toggle
            pfDrawerMain {
                pfDrawerContent {
                    pfDrawerBody { +LOREM_IPSUM }
                }
                pfDrawerPanel {
                    pfDrawerBody {
                        pfDrawerHead {
                            span {
                                domNode.tabIndex = 0
                                +"Drawer panel"
                            }
                            pfDrawerActions {
                                pfDrawerClose()
                            }
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
        val button = pfButton(classes { +primary; +"mb-md".util() }) {
            +"Toggle drawer"
        }
        br{}
        pfDrawer("panel-left".modifier()) {
            button.clicks handledBy expanded.toggle
            pfDrawerMain {
                pfDrawerContent {
                    pfDrawerBody { +LOREM_IPSUM }
                }
                pfDrawerPanel {
                    pfDrawerBody {
                        pfDrawerHead {
                            span {
                                domNode.tabIndex = 0
                                +"Drawer panel"
                            }
                            pfDrawerActions {
                                pfDrawerClose()
                            }
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
        val button = pfButton(classes { +primary; +"mb-md".util() }) {
            +"Toggle drawer"
        }
        br{}
        pfDrawer("inline".modifier()) {
            button.clicks handledBy expanded.toggle
            pfDrawerMain {
                pfDrawerContent {
                    pfDrawerBody { +LOREM_IPSUM }
                }
                pfDrawerPanel {
                    pfDrawerBody {
                        pfDrawerHead {
                            span {
                                domNode.tabIndex = 0
                                +"Drawer panel"
                            }
                            pfDrawerActions {
                                pfDrawerClose()
                            }
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
        val button = pfButton(classes { +primary; +"mb-md".util() }) {
            +"Toggle drawer"
        }
        br{}
        pfDrawer(classes("inline".modifier(), "panel-left".modifier())) {
            button.clicks handledBy expanded.toggle
            pfDrawerMain {
                pfDrawerContent {
                    pfDrawerBody { +LOREM_IPSUM }
                }
                pfDrawerPanel {
                    pfDrawerBody {
                        pfDrawerHead {
                            span {
                                domNode.tabIndex = 0
                                +"Drawer panel"
                            }
                            pfDrawerActions {
                                pfDrawerClose()
                            }
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
        val button = pfButton(classes { +primary; +"mb-md".util() }) {
            +"Toggle drawer"
        }
        br {}
        pfDrawer {
            button.clicks handledBy expanded.toggle
            pfDrawerSection {
                pfContent {
                    pfTitle { +"Title" }
                    p { +"${'$'}{LOREM_IPSUM.take(15)}..." }
                    pfDivider(classes = "mb-md".util())
                }
            }
            pfDrawerMain {
                pfDrawerContent {
                    pfDrawerBody { +LOREM_IPSUM }
                }
                pfDrawerPanel {
                    pfDrawerBody {
                        pfDrawerHead {
                            span {
                                domNode.tabIndex = 0
                                +"Drawer panel"
                            }
                            pfDrawerActions {
                                pfDrawerClose()
                            }
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
        pfDrawer("static".modifier()) {
            pfDrawerMain {
                pfDrawerContent {
                    pfDrawerBody { +LOREM_IPSUM }
                }
                pfDrawerPanel {
                    pfDrawerBody {
                        pfDrawerHead {
                            span { +"Drawer panel" }
                            pfDrawerActions {
                                pfDrawerClose()
                            }
                        }
                    }
                }
            }
        }
    }
}
"""
}
