package org.patternfly.showcase

import org.patternfly.modifier
import org.patternfly.mvp.Presenter
import org.patternfly.mvp.View
import org.patternfly.mvp.ViewContent
import org.patternfly.pageSection
import org.patternfly.textContent
import org.patternfly.title

class GetInTouchPresenter : Presenter<GetInTouchView> {
    override val view: GetInTouchView = GetInTouchView()
}

class GetInTouchView : View {
    override val content: ViewContent = {
        pageSection(baseClass = "light".modifier()) {
            textContent {
                title { +"Get in Touch" }
                p {
                    +"PatternFly Fritz2 is still under development. The API might change and things might not work as expected. Please give it a try and share your feedback. Join the chat at "
                    a {
                        +"Gitter"
                        href("https://gitter.im/patternfly-kotlin/patternfly-fritz2")
                    }
                    +" or use the GitHub "
                    a {
                        +"issues"
                        href("https://github.com/patternfly-kotlin/patternfly-fritz2/issues")
                    }
                    +" to report bugs or request new features."
                }
                p {
                    +"Of course, you're very welcome to "
                    a {
                        +"contribute"
                        href("https://github.com/patternfly-kotlin/patternfly-fritz2/blob/master/CONTRIBUTING.md")
                    }
                    +" to PatternFly Fritz2."
                }
            }
        }
    }
}
