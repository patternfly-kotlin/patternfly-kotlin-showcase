package org.patternfly.showcase

import org.patternfly.mvp.Presenter
import org.patternfly.showcase.component.ComponentPresenter
import org.patternfly.showcase.demo.UserDemoPresenter

const val COMPONENT = "component"
const val GET_IN_TOUCH = "get-in-touch"
const val GET_STARTED = "get-started"
const val HOME = "home"
const val USER_DEMO = "user-demo"

fun registerPresenters() {
    Presenter.register(COMPONENT, ::ComponentPresenter)
    Presenter.register(GET_IN_TOUCH, ::GetInTouchPresenter)
    Presenter.register(GET_STARTED, ::GetStartedPresenter)
    Presenter.register(HOME, ::HomePresenter)
    Presenter.register(USER_DEMO, ::UserDemoPresenter)
}
