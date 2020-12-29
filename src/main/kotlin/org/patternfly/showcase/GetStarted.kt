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
                    +"To use PatternFly Fritz2 add its dependency to your "
                    code { +"gradle.build.kts" }
                    +" file. All PatternFly components are implemented in Kotlin only. You won't need any additional external JS libraries."
                }
                p {
                    pre {
                        code("kotlin") {
                            +"""
                                repositories {
                                    maven("https://dl.bintray.com/patternfly-kotlin/patternfly-fritz2")
                                }
        
                                dependencies {
                                    implementation("org.patternfly:patternfly-fritz2:<version>")
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
