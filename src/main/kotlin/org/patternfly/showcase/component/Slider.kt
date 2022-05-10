package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.patternfly.Step
import org.patternfly.pageSection
import org.patternfly.slider

object SliderComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Slider",
            text = "A slider provides a quick and effective way for users to set and adjust a numeric value from a defined range of values.",
            designGuidelines = "https://www.patternfly.org/v4/components/slider/design-guidelines"
        )
        pageSection {
            snippet("Sizes", SliderCode.DISCRETE) {
                val store0 = storeOf(2)
                h3 { +"Slider value is: "; span { store0.data.renderText() } }
                small { +"(min = 0, max = 8, show boundaries, show even steps, show ticks)" }
                slider(store0, Step(0, "0")..Step(8, "8")) {
                    showSteps { it % 2 == 0 }
                    showTicks()
                }
                br { }

                val store1 = storeOf(50)
                h3 { +"Slider value is: "; span { store1.data.renderText() } }
                small { +"(min = 0, max = 200, step = 50, show boundaries, hide steps, show ticks)" }
                slider(store1, 0..200 step 50) {
                    showTicks()
                }
                br { }

                val store2 = storeOf(35)
                h3 { +"Slider value is: "; span { store2.data.renderText() } }
                small { +"(min = 25, max = 75, step = 10, hide boundaries, hide steps, show ticks)" }
                slider(store2, 25..75 step 10) {
                    hideBoundaries()
                    showTicks()
                }
                br { }

                val store3 = storeOf(50)
                h3 { +"Slider value is: "; span { store3.data.renderText() } }
                small { +"(min = -25, max = 75, step = 10, show boundaries, hide steps, hide ticks)" }
                slider(store3, -25..75 step 10)
                br { }

                val store4 = storeOf(3)
                h3 { +"Slider value is: "; span { store4.data.renderText() } }
                small { +"(custom steps: A(1), B(2), C(3), D(4), F(5), show boundaries, show steps, show ticks)" }
                slider(
                    store4, listOf(
                        Step(1, "A"),
                        Step(2, "B"),
                        Step(3, "C"),
                        Step(4, "D"),
                        Step(5, "F"),
                    )
                )
            }
            snippet("Continuous", SliderCode.CONTINUOUS) {
                val store0 = storeOf(77)
                h3 { +"Slider value is: "; span { store0.data.renderText() } }
                slider(store0, 0..100)
                br { }

                val store1 = storeOf(23)
                h3 { +"Slider value is: "; span { store1.data.map { "$it%" }.renderText() } }
                slider(store1, Step(0, "0%")..Step(100, "100%"))
            }
            snippet("Value input", SliderCode.VALUE_INPUT) {
                slider(storeOf(5), Step(0, "0")..Step(8, "8")) {
                    showSteps { it % 2 == 0 }
                    showTicks()
                    valueInput()
                }
                br { }

                slider(
                    storeOf(30), listOf(
                        Step(0, "0%"),
                        Step(25),
                        Step(50, "50%"),
                        Step(75),
                        Step(100, "100%"),
                    )
                ) {
                    valueInput(label = "%")
                }
                br { }

                slider(storeOf(60), 0..100) {
                    valueInput(label = "%")
                }
            }
            snippet("Thumb value input", SliderCode.THUMP_VALUE_INPUT) {
                slider(storeOf(66), 0..100) {
                    valueInput(floating = true, label = "%")
                }
            }
            snippet("Actions", SliderCode.ACTIONS) {
                slider(storeOf(50), 0..100) {
                    leftActions { decrease() }
                    rightActions { increase() }
                }
                br { }
                val disabled = storeOf(false)
                slider(storeOf(50), 0..100) {
                    disabled(disabled.data)
                    valueInput(label = "%")
                    rightActions { lock(disabled) }
                }
            }
        }
    }
}

object SliderCode {

    //language=kotlin
    const val DISCRETE: String = """fun main() {
    render {
        val store0 = storeOf(2)
        h3 { +"Slider value is: "; span { store0.data.renderText() } }
        small { +"(min = 0, max = 8, show boundaries, show even steps, show ticks)" }
        slider(store0, Step(0, "0")..Step(8, "8")) {
            showSteps { it % 2 == 0 }
            showTicks()
        }
        br { }

        val store1 = storeOf(50)
        h3 { +"Slider value is: "; span { store1.data.renderText() } }
        small { +"(min = 0, max = 200, step = 50, show boundaries, hide steps, show ticks)" }
        slider(store1, 0..200 step 50) {
            showTicks()
        }
        br { }

        val store2 = storeOf(35)
        h3 { +"Slider value is: "; span { store2.data.renderText() } }
        small { +"(min = 25, max = 75, step = 10, hide boundaries, hide steps, show ticks)" }
        slider(store2, 25..75 step 10) {
            hideBoundaries()
            showTicks()
        }
        br { }

        val store3 = storeOf(50)
        h3 { +"Slider value is: "; span { store3.data.renderText() } }
        small { +"(min = -25, max = 75, step = 10, show boundaries, hide steps, hide ticks)" }
        slider(store3, -25..75 step 10)
        br { }

        val store4 = storeOf(3)
        h3 { +"Slider value is: "; span { store4.data.renderText() } }
        small { +"(custom steps: A(1), B(2), C(3), D(4), F(5), show boundaries, show steps, show ticks)" }
        slider(
            store4, listOf(
                Step(1, "A"),
                Step(2, "B"),
                Step(3, "C"),
                Step(4, "D"),
                Step(5, "F"),
            )
        )
    }
}"""

    //language=kotlin
    const val CONTINUOUS: String = """fun main() {
    render {
        val store0 = storeOf(77)
        h3 { +"Slider value is: "; span { store0.data.renderText() } }
        slider(store0, 0..100)
        br { }

        val store1 = storeOf(23)
        h3 { +"Slider value is: "; span { store1.data.map { "${'$'}it%" }.renderText() } }
        slider(store1, Step(0, "0%")..Step(100, "100%"))
    }
}"""

    //language=kotlin
    const val VALUE_INPUT: String = """fun main() {
    render {
        slider(storeOf(5), Step(0, "0")..Step(8, "8")) {
            showSteps { it % 2 == 0 }
            showTicks()
            valueInput()
        }
        br { }

        slider(
            storeOf(30), listOf(
                Step(0, "0%"),
                Step(25),
                Step(50, "50%"),
                Step(75),
                Step(100, "100%"),
            )
        ) {
            valueInput(label = "%")
        }
        br { }

        slider(storeOf(60), 0..100) {
            valueInput(label = "%")
        }
    }
}"""

    //language=kotlin
    const val THUMP_VALUE_INPUT: String = """fun main() {
    render {
        slider(storeOf(66), 0..100) {
            valueInput(floating = true, label = "%")
        }
    }
}"""

    //language=kotlin
    const val ACTIONS: String = """fun main() {
    render {
        slider(storeOf(50), 0..100) {
            leftActions { decrease() }
            rightActions { increase() }
        }
        br { }
        val disabled = storeOf(false)
        slider(storeOf(50), 0..100) {
            disabled(disabled.data)
            valueInput(label = "%")
            rightActions { lock(disabled) }
        }
    }   
}"""
}
