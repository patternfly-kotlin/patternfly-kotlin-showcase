package org.patternfly.showcase

import dev.fritz2.mvp.Presenter
import dev.fritz2.mvp.View
import dev.fritz2.mvp.ViewContent
import hljs.highlightBlock
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.textContent
import org.patternfly.title

class GetStartedPresenter : Presenter<GetStartedView> {
    override val view: GetStartedView = GetStartedView()
}

class GetStartedView : View {
    override val content: ViewContent = {
        pageSection(baseClass = "light".modifier()) {
            textContent {
                title { +"Get Started" }
                h2 { +"Dependencies" }
                p {
                    +"To use PatternFly Fritz2 add its dependency to your "
                    code { +"gradle.build.kts" }
                    +" file. All PatternFly components are implemented in Kotlin only. You won't need any additional external JS libraries."
                }
                pre {
                    code(baseClass = "kotlin") {
                        +"""
                            repositories {
                                maven("https://dl.bintray.com/patternfly-kotlin/patternfly-fritz2")
                            }

                            dependencies {
                                implementation("org.patternfly:patternfly-fritz2:0.2.0")
                            }
                        """.trimIndent()
                        highlightBlock(domNode)
                    }
                }
                h2 { +"PatternFly Assets" }
                p {
                    +"PatternFly Fritz2 does "
                    em { +"not" }
                    +" come with stylesheets, fonts or other static PatternFly assets. You have to include them on your own. One way is to add a "
                    code { +"npm" }
                    +" dependency to PatternFly:"
                }
                pre {
                    code(baseClass = "kotlin") {
                        +"""
                            dependencies {
                                implementation("org.jetbrains:kotlin-extensions:<version>")
                                implementation(npm("@patternfly/patternfly", "4"))
                            }
                        """.trimIndent()
                        highlightBlock(domNode)
                    }
                }
                p {
                    +"and make a call to "
                    code { +"require()" }
                    +" (provided by "
                    a {
                        +"Kotlin JS wrappers"
                        href("https://github.com/JetBrains/kotlin-wrappers")
                    }
                    +"):"
                }
                pre {
                    code(baseClass = "kotlin") {
                        +"""
                            import kotlinext.js.require
                            
                            fun main() {
                                require("@patternfly/patternfly/patternfly.css")
                                require("@patternfly/patternfly/patternfly-addons.css")
                            }                            
                        """.trimIndent()
                        highlightBlock(domNode)
                    }
                }
                p {
                    +"Another option is to download or get PatternFly using a CDN provider like "
                    a {
                        +"jsDelivr"
                        href("https://www.jsdelivr.com/package/npm/@patternfly/patternfly")
                    }
                    +" and include the stylesheets in your HTML page:"
                }
                pre {
                    code(baseClass = "html") {
                        +"""
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <title>My App</title>
                                <link rel="stylesheet" href="patternfly.css">
                                <link rel="stylesheet" href="patternfly-addons.css">
                            </head>
                            <body>
                            <script src="your-app.js"></script>
                            </body>
                            </html>
                        """.trimIndent()
                        highlightBlock(domNode)
                    }
                }
                p {
                    +"See also the "
                    a {
                        +"getting started"
                        href("https://www.patternfly.org/v4/get-started/develop#htmlcss")

                    }
                    +" section on the PatternFly website for more details."
                }
            }
        }
    }
}
