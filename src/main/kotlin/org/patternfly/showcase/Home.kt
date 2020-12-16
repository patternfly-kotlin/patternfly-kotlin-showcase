package org.patternfly.showcase

import dev.fritz2.mvp.Presenter
import dev.fritz2.mvp.View
import dev.fritz2.mvp.ViewContent
import org.patternfly.component
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.title

class HomePresenter : Presenter<HomeView> {
    override val view: HomeView = HomeView()
}

class HomeView : View {
    override val content: ViewContent = {
        pageSection(baseClass = "light".modifier()) {
            div(baseClass = "content".component()) {
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
                        href("https://www.patternfly.org/")
                    }
                    +" based on "
                    a {
                        +"fritz2"
                        href("https://www.fritz2.dev/")
                    }
                    +"."
                }
                p {
                    +"The goal of this project is to provide all PatternFly components in Kotlin/JS. This is done in a way that matches the reactive nature of fritz2. In particular, the components use "
                    a {
                        +"stores"
                        href("https://api.fritz2.dev/core/core/dev.fritz2.binding/-store/index.html")
                    }
                    +", "
                    a {
                        +"handlers"
                        href("https://api.fritz2.dev/core/core/dev.fritz2.binding/-handler/index.html")
                    }
                    +" and other elements from the fritz2 API."
                }
                p {
                    +"To get a quick overview what this is all about go to the "
                    a {
                        +"documentation"
                        href("#component;id=alert")
                    }
                    +" page which demonstrates the usage of all supported components and also includes more complex demos of data driven components."
                }
                p {
                    +"To get all details about how to use PatternFly Fritz2 take a look at the "
                    a {
                        +"API documentation"
                        href("https://patternfly-kotlin.github.io/patternfly-fritz2/patternfly-fritz2/")
                    }
                    +"."
                }
            }
        }
    }
}
