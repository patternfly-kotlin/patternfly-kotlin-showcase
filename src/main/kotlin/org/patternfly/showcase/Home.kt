package org.patternfly.showcase

import dev.fritz2.mvp.Presenter
import dev.fritz2.mvp.View
import dev.fritz2.mvp.ViewContent
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.title

class HomePresenter : Presenter<HomeView> {
    override val view: HomeView = HomeView()
}

class HomeView : View {
    override val content: ViewContent = {
        pageSection(baseClass = "light".modifier()) {
            title { +"PatternFly Fritz2" }
            p {
                a {
                    +"PatternFly Fritz2"
                    href("https://github.com/patternfly-kotlin/patternfly-fritz2")
                }
                +" is a "
                a {
                    +"Kotlin / JS"
                    href("https://kotl.in/js")
                }
                +" implementation of "
                a {
                    +"PatternFly"
                    href("https://www.patternfly.org//")
                }
                +" based on "
                a {
                    +"fritz2"
                    href("https://www.fritz2.dev/")
                }
                +"."
            }
        }
    }
}