package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
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
                    storeOf(50),
                    storeOf(3),
                )

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small {
                    +"(steps: "; b { +"0" }; +", 1, "; b { +"2" }; +", 3, ";b { +"4" };+", 5, ";b { +"6" };+", 7, ";b { +"8" };+")"
                }
                slider(
                    value = values[index],
                    steps = listOf(
                        Step(0, "0"),
                        Step(1),
                        Step(2, "2"),
                        Step(3),
                        Step(4, "4"),
                        Step(5),
                        Step(6, "6"),
                        Step(7),
                        Step(8, "8"),
                    )
                )
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(min = 0, max = 200, step = 50)" }
                slider(value = values[index], progression = 0..200 step 50, hideSteps = true)
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(min = -25, max = 75, step = 10, boundaries not shown)" }
                slider(value = values[index], progression = -25..75 step 10, hideBoundaries = true, hideSteps = true)
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(min = -25, max = 75, step = 10, boundaries shown)" }
                slider(value = values[index], progression = -25..75 step 10, hideSteps = true)
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(min = -25, max = 75, step = 10, boundaries shown, ticks not shown)" }
                slider(progression = -25..75 step 10, hideSteps = true, hideTicks = true) {
                    value(values[index])
                }
                br { }
                index++

                h3 { +"Slider value is: "; span { values[index].data.renderText() } }
                small { +"(steps: A, B, C, D, E, F)" }
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
