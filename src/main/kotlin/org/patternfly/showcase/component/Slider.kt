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
                var index = 0
                val values = arrayOf(
                    storeOf(2),
                    storeOf(50),
                    storeOf(25),
                    storeOf(50),
                    storeOf(3),
                )

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(min = 0, max = 8, show boundaries, show event steps, show ticks)" }
                slider(values[index], Step(0, "0")..Step(8, "8")) {
                    steps { step, _, _, _ -> step % 2 == 0 }
                    showTicks()
                }
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(min = 0, max = 200, step = 50, show boundaries, hide steps, show ticks)" }
                slider(value = values[index], steps = 0..200 step 50) {
                    showTicks()
                }
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(min = -25, max = 75, step = 10, hide boundaries, hide steps, show ticks)" }
                slider(value = values[index], steps = -25..75 step 10) {
                    hideBoundaries()
                    showTicks()
                }
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(min = -25, max = 75, step = 10, show boundaries, hide steps, hide ticks)" }
                slider(value = values[index], steps = -25..75 step 10)
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(custom steps: A, B, C, D, E, F, show boundaries, show steps, show ticks)" }
                slider(
                    value = values[index],
                    steps = listOf(
                        Step(0, "A"),
                        Step(1, "B"),
                        Step(2, "C"),
                        Step(3, "D"),
                        Step(4, "E"),
                        Step(5, "F"),
                    )
                )
            }
            snippet("Continuous", SliderCode.CONTINUOUS) {
                var index = 0
                val values = arrayOf(
                    storeOf(77),
                    storeOf(23),
                )

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                slider(value = values[index], steps = 0..100)
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.map { "$it%" }.renderText() } }
                slider(value = values[index], steps = Step(0, "0%")..Step(100, "100%"))
            }
        }
    }
}

object SliderCode {

    //language=kotlin
    const val DISCRETE: String = """"""

    //language=kotlin
    const val CONTINUOUS: String = """"""

    //language=kotlin
    const val VALUE_INPUT: String = """"""

    //language=kotlin
    const val THUMP_VALUE_INPUT: String = """"""

    //language=kotlin
    const val ACTIONS: String = """"""

    //language=kotlin
    const val DISABLED: String = """"""
}
