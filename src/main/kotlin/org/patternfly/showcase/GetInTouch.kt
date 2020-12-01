package org.patternfly.showcase

import dev.fritz2.mvp.Presenter
import dev.fritz2.mvp.View
import dev.fritz2.mvp.ViewContent
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.title

class GetInTouchPresenter : Presenter<GetInTouchView> {
    override val view: GetInTouchView = GetInTouchView()
}

class GetInTouchView : View {
    override val content: ViewContent = {
        pageSection(baseClass = "light".modifier()) {
            title { +"Get in Touch" }
            p {
                +"Pending"
            }
        }
    }
}