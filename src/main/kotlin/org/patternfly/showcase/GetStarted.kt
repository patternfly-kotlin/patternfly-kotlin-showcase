package org.patternfly.showcase

import dev.fritz2.mvp.Presenter
import dev.fritz2.mvp.View
import dev.fritz2.mvp.ViewContent
import hljs.highlightBlock
import org.patternfly.component
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.title

class GetStartedPresenter : Presenter<GetStartedView> {
    override val view: GetStartedView = GetStartedView()
}

class GetStartedView : View {
    override val content: ViewContent = {
        pageSection(baseClass = "light".modifier()) {
            div(baseClass = "content".component()) {
                title { +"Get Started" }
                p {
                    +"PatternFly Fritz2 is available in Bintray. To use it in your Kotlin/JS project add its dependency to your "
                    code { +"gradle.build.kts" }
                    +" file:"
                }
                p {
                    pre {
                        code("kotlin") {
                            +"""
                                repositories {
                                    maven("https://dl.bintray.com/patternfly-kotlin/patternfly-fritz2")
                                }
        
                                dependencies {
                                    implementation("org.jetbrains:kotlin-extensions:<version>")
                                    implementation("org.patternfly:patternfly-fritz2:<version>")
                                    implementation(npm("@patternfly/patternfly", "4"))
                                }
                            """.trimIndent()
                            highlightBlock(domNode)
                        }
                    }
                }
            }
        }
    }
}
