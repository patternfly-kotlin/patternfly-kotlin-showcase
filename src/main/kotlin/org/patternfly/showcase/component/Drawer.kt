@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.DomListener
import dev.fritz2.dom.html.RenderContext
import org.patternfly.ButtonVariant.primary
import org.patternfly.DrawerVariant.bottom
import org.patternfly.DrawerVariant.inline
import org.patternfly.DrawerVariant.left
import org.patternfly.DrawerVariant.resizable
import org.patternfly.DrawerVariant.static
import org.patternfly.clickButton
import org.patternfly.drawer
import org.patternfly.pageSection
import org.patternfly.title
import org.patternfly.util
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent

object DrawerComponent {
    private const val HEIGHT: String = "height: 400px"

    val content: RenderContext.() -> Unit = {
        intro(
            title = "Drawer",
            text = "A drawer is a sliding panel that enters from the right edge of the viewport.",
            designGuidelines = "https://www.patternfly.org/v4/components/drawer/design-guidelines"
        )
        pageSection {
            snippet("Basic", DrawerCode.BASIC) {
                val toggle = toggle()
                drawer {
                    primary {
                        content { +loremIpsum(10) }
                    }
                    detail {
                        head {  +"Drawer panel" }
                        content { +loremIpsum() }
                    }
                    toggle handledBy expandedStore.toggle
                }
            }
            snippet("Panel on left", DrawerCode.LEFT) {
                val toggle = toggle()
                drawer(left) {
                    primary {
                        content { +loremIpsum(10) }
                    }
                    detail {
                        head {  +"Drawer panel" }
                        content { +loremIpsum() }
                    }
                    toggle handledBy expandedStore.toggle
                }
            }
            snippet("Panel on bottom", DrawerCode.BOTTOM) {
                val toggle = toggle()
                div {
                    inlineStyle(HEIGHT)
                    drawer(bottom) {
                        primary {
                            content { +loremIpsum(10) }
                        }
                        detail {
                            head {  +"Drawer panel" }
                            content { +loremIpsum() }
                        }
                        toggle handledBy expandedStore.toggle
                    }
                }
            }
            snippet("Inline", DrawerCode.INLINE) {
                val toggle = toggle()
                drawer(inline) {
                    primary {
                        content { +loremIpsum(10) }
                    }
                    detail {
                        head {  +"Drawer panel" }
                        content { +loremIpsum() }
                    }
                    toggle handledBy expandedStore.toggle
                }
            }
            snippet("Inline left", DrawerCode.INLINE_LEFT) {
                val toggle = toggle()
                drawer(inline, left) {
                    primary {
                        content { +loremIpsum(10) }
                    }
                    detail {
                        head {  +"Drawer panel" }
                        content { +loremIpsum() }
                    }
                    toggle handledBy expandedStore.toggle
                }
            }
            snippet("Additional section above drawer content", DrawerCode.SECTION) {
                val toggle = toggle()
                drawer {
                    primary {
                        content(fullWidth = true) {
                            title { +"Title" }
                            p {
                                +loremIpsum(2)
                            }
                        }
                        content { +loremIpsum(10) }
                    }
                    detail {
                        head {  +"Drawer panel" }
                        content { +loremIpsum() }
                    }
                    toggle handledBy expandedStore.toggle
                }
            }
            snippet("Static", DrawerCode.STATIC) {
                drawer(static) {
                    primary {
                        content { +loremIpsum(10) }
                    }
                    detail {
                        head {  +"Drawer panel" }
                        content { +loremIpsum() }
                    }
                }
            }
            snippet("Resizable", DrawerCode.RESIZABLE) {
                val toggle = toggle()
                drawer(resizable) {
                    primary {
                        content { +loremIpsum(10) }
                    }
                    detail {
                        head {  +"Drawer panel" }
                        content { +loremIpsum() }
                    }
                    toggle handledBy expandedStore.toggle
                }
            }
            snippet("Resizable on left", DrawerCode.RESIZABLE_LEFT) {
                val toggle = toggle()
                drawer(resizable, left) {
                    primary {
                        content { +loremIpsum(10) }
                    }
                    detail {
                        head {  +"Drawer panel" }
                        content { +loremIpsum() }
                    }
                    toggle handledBy expandedStore.toggle
                }
            }
            snippet("Resizable on bottom", DrawerCode.RESIZABLE_BOTTOM) {
                val toggle = toggle()
                div {
                    inlineStyle(HEIGHT)
                    drawer(resizable, bottom) {
                        primary {
                            content { +loremIpsum(10) }
                        }
                        detail {
                            head {  +"Drawer panel" }
                            content { +loremIpsum() }
                        }
                        toggle handledBy expandedStore.toggle
                    }
                }
            }
            snippet("Resizable on inline", DrawerCode.RESIZABLE_INLINE) {
                val toggle = toggle()
                drawer(resizable, inline) {
                    primary {
                        content { +loremIpsum(10) }
                    }
                    detail {
                        head {  +"Drawer panel" }
                        content { +loremIpsum() }
                    }
                    toggle handledBy expandedStore.toggle
                }
            }
        }
    }

    private fun RenderContext.toggle(): DomListener<MouseEvent, HTMLElement> =
        clickButton(primary, baseClass = "mb-md".util()) { +"Toggle" }
}

object DrawerCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        drawer {
            button.clicks handledBy expanded.toggle
            drawerContent {
                drawerBody {
                    +loremIpsum(10)
                }
            }
            drawerPanel {
                drawerBodyWithClose {
                    +"Drawer panel"
                }
                drawerBody {
                    +loremIpsum()
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val LEFT: String = """fun main() {
    render {
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        drawer(panelPosition = LEFT) {
            button.clicks handledBy expanded.toggle
            drawerContent {
                drawerBody {
                    +loremIpsum(10)
                }
            }
            drawerPanel {
                drawerBodyWithClose {
                    +"Drawer panel"
                }
                drawerBody {
                    +loremIpsum()
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val BOTTOM: String = """fun main() {
    render {
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        div {
            inlineStyle("height: 400px")
            drawer(panelPosition = BOTTOM) {
                button.clicks handledBy expanded.toggle
                drawerContent {
                    drawerBody {
                        +loremIpsum(10)
                    }
                }
                drawerPanel {
                    drawerBodyWithClose {
                        +"Drawer panel"
                    }
                    drawerBody {
                        +loremIpsum()
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
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        drawer(inline = true) {
            button.clicks handledBy expanded.toggle
            drawerContent {
                drawerBody {
                    +loremIpsum(10)
                }
            }
            drawerPanel {
                drawerBodyWithClose {
                    +"Drawer panel"
                }
                drawerBody {
                    +loremIpsum()
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val INLINE_LEFT: String = """fun main() {
    render {
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        drawer(panelPosition = LEFT, inline = true) {
            button.clicks handledBy expanded.toggle
            drawerContent {
                drawerBody {
                    +loremIpsum(10)
                }
            }
            drawerPanel {
                drawerBodyWithClose {
                    +"Drawer panel"
                }
                drawerBody {
                    +loremIpsum()
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val SECTION: String = """fun main() {
    render {
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        drawer {
            button.clicks handledBy expanded.toggle
            drawerSection {
                title { +"Title" }
                p {
                    +loremIpsum(2)
                }
            }
            drawerContent {
                drawerBody {
                    +loremIpsum(10)
                }
            }
            drawerPanel {
                drawerBodyWithClose {
                    +"Drawer panel"
                }
                drawerBody {
                    +loremIpsum()
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val STATIC: String = """fun main() {
    render {
        drawer(static = true) {
            expanded.expand(Unit)
            drawerContent {
                drawerBody {
                    +loremIpsum(10)
                }
            }
            drawerPanel {
                drawerBodyWithClose {
                    +"Drawer panel"
                }
                drawerBody {
                    +loremIpsum()
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val RESIZABLE: String = """fun main() {
    render {
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        drawer(resizable = true) {
            button.clicks handledBy expanded.toggle
            drawerContent {
                drawerBody {
                    +loremIpsum(10)
                }
            }
            drawerPanel {
                drawerBodyWithClose {
                    +"Drawer panel"
                }
                drawerBody {
                    +loremIpsum()
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val RESIZABLE_LEFT: String = """fun main() {
    render {
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        drawer(resizable = true, panelPosition = LEFT) {
            button.clicks handledBy expanded.toggle
            drawerContent {
                drawerBody {
                    +loremIpsum(10)
                }
            }
            drawerPanel {
                drawerBodyWithClose {
                    +"Drawer panel"
                }
                drawerBody {
                    +loremIpsum()
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val RESIZABLE_BOTTOM: String = """fun main() {
    render {
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        div {
            inlineStyle("height: 400px")
            drawer(resizable = true, panelPosition = BOTTOM) {
                button.clicks handledBy expanded.toggle
                drawerContent {
                    drawerBody {
                        +loremIpsum(10)
                    }
                }
                drawerPanel {
                    drawerBodyWithClose {
                        +"Drawer panel"
                    }
                    drawerBody {
                        +loremIpsum()
                    }
                }
            }
        }
    }
}
"""

    //language=kotlin
    const val RESIZABLE_INLINE: String = """fun main() {
    render {
        val button = pushButton(primary, baseClass = "mb-md".util()) { 
            +"Toggle" 
        }
        drawer(resizable = true, inline = true) {
            button.clicks handledBy expanded.toggle
            drawerContent {
                drawerBody {
                    +loremIpsum(10)
                }
            }
            drawerPanel {
                drawerBodyWithClose {
                    +"Drawer panel"
                }
                drawerBody {
                    +loremIpsum()
                }
            }
        }
    }
}
"""
}
