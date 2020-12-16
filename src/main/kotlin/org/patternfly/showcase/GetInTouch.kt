package org.patternfly.showcase

import dev.fritz2.mvp.Presenter
import dev.fritz2.mvp.View
import dev.fritz2.mvp.ViewContent
import org.patternfly.component
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.title

class GetInTouchPresenter : Presenter<GetInTouchView> {
    override val view: GetInTouchView = GetInTouchView()
}

class GetInTouchView : View {
    override val content: ViewContent = {
        pageSection(baseClass = "light".modifier()) {
            div(baseClass = "content".component()) {
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
                    +" to file bugs or open feature requests."
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
