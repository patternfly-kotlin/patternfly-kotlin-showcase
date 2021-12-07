package org.patternfly.showcase

import org.patternfly.mvp.Presenter
import org.patternfly.mvp.View
import org.patternfly.mvp.ViewContent
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.textContent
import org.patternfly.title

class HomePresenter : Presenter<HomeView> {
    override val view: HomeView = HomeView()
}

class HomeView : View {
    override val content: ViewContent = {
        pageSection(baseClass = "light".modifier()) {
            textContent {
                title { +"PatternFly Fritz2" }
                p {
                    a {
                        +"PatternFly Fritz2"
                        href("https://github.com/patternfly-kotlin/patternfly-fritz2")
                    }
                    +" is is a 💯 Kotlin implementation of "
                    a {
                        +"PatternFly"
                        href("https://www.patternfly.org/")
                    }
                    +" based on "
                    a {
                        +"fritz2"
                        href("https://www.fritz2.dev/")
                    }
                    +" targeting "
                    a {
                        +"Kotlin/JS"
                        href("https://kotl.in/js")
                    }
                    +"."
                }
                p {
                    +"The goal of this project is to provide all PatternFly components in Kotlin. This is done in a way that matches the reactive nature of fritz2. In particular, the components use "
                    a {
                        +"stores"
                        href("https://api.fritz2.dev/core/core/dev.fritz2.binding/-store/index.html")
                    }
                    +", "
                    a {
                        +"handlers"
                        href("https://api.fritz2.dev/core/core/dev.fritz2.binding/-handler/index.html")
                    }
                    +" and other elements from the "
                    a {
                        +"fritz2 API"
                        href("https://api.fritz2.dev/core/core/index.html")
                    }
                    +"."
                }
                p {
                    +"To get a quick overview what this is all about, take a look at the "
                    a {
                        +"components"
                        href("#component;id=accordion")
                    }
                    +" and "
                    a {
                        +"demos"
                        href("#user-demo")
                    }
                    +"."
                }
                p {
                    +"To get all details about how to use PatternFly Fritz2 read the "
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

