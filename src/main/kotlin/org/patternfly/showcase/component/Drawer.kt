@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.ButtonVariation.primary
import org.patternfly.DrawerPanelPosition.BOTTOM
import org.patternfly.DrawerPanelPosition.LEFT
import org.patternfly.drawer
import org.patternfly.drawerBody
import org.patternfly.drawerBodyWithClose
import org.patternfly.drawerContent
import org.patternfly.drawerPanel
import org.patternfly.drawerSection
import org.patternfly.pageSection
import org.patternfly.pushButton
import org.patternfly.title
import org.patternfly.util

object DrawerComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Drawer",
            text = "A drawer is a sliding panel that enters from the right edge of the viewport.",
            designGuidelines = "https://www.patternfly.org/v4/components/drawer/design-guidelines"
        )
        pageSection {
            snippet("Basic", DrawerCode.BASIC) {
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
            snippet("Panel on left", DrawerCode.LEFT) {
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
            snippet("Panel on bottom", DrawerCode.BOTTOM) {
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
            snippet("Inline", DrawerCode.INLINE) {
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
            snippet("Inline left", DrawerCode.INLINE_LEFT) {
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
            snippet("Additional section above drawer content", DrawerCode.SECTION) {
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
            snippet("Static", DrawerCode.STATIC) {
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
            snippet("Resizable", DrawerCode.RESIZABLE) {
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
            snippet("Resizable on left", DrawerCode.RESIZABLE_LEFT) {
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
            snippet("Resizable on bottom", DrawerCode.RESIZABLE_BOTTOM) {
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
            snippet("Resizable on inline", DrawerCode.RESIZABLE_INLINE) {
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
    }
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
