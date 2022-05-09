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
                    steps { it % 2 == 0 }
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

                val store2 = storeOf(25)
                h3 { +"Slider value is: "; span { store2.data.renderText() } }
                small { +"(min = -25, max = 75, step = 10, hide boundaries, hide steps, show ticks)" }
                slider(store2, -25..75 step 10) {
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
                small { +"(custom steps: A, B, C, D, E, F, show boundaries, show steps, show ticks)" }
                slider(
                    store4, listOf(
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
                    steps { it % 2 == 0 }
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
                    valueInput()
                    valueLabel { "%" }
                }
                br { }

                slider(storeOf(60), 0..100) {
                    valueInput()
                    valueLabel("%")
                }
            }
            snippet("Thumb value input", SliderCode.THUMP_VALUE_INPUT) {
                slider(storeOf(66), 0..100) {
                    valueInput(floating = true)
                    valueLabel("%")
                }
            }
            snippet("Actions", SliderCode.ACTIONS) {
                slider(storeOf(50), 0..100) {
                    headAction { decrease() }
                    tailAction { increase() }
                }
                br { }
                slider(storeOf(50), 0..100) {
                    valueInput()
                    valueLabel("%")
                    tailAction { lock() }
                }
            }
        }
    }
}

object SliderCode {

    //language=kotlin
    const val DISCRETE: String = """fun main() {
    render {
        var index = 0
        val values = arrayOf(
            storeOf(2),
            storeOf(50),
            storeOf(25),
            storeOf(50),
            storeOf(3),
        )
        
        h3 { +"Slider value is: "; span { values[index].data.renderText() } }
        small { +"(min = 0, max = 8, show boundaries, show even steps, show ticks)" }
        slider(values[index], Step(0, "0")..Step(8, "8")) {
            steps { it % 2 == 0 }
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
}"""

    //language=kotlin
    const val CONTINUOUS: String = """fun main() {
    render {
        var index = 0
        val values = arrayOf(
            storeOf(77),
            storeOf(23),
        )

        h3 { +"Slider value is: "; span { values[index].data.renderText() } }
        slider(value = values[index], steps = 0..100)
        br { }
        index++

        h3 { +"Slider value is: "; span { values[index].data.map { "${'$'}it%" }.renderText() } }
        slider(value = values[index], steps = Step(0, "0%")..Step(100, "100%"))
    }
}"""

    //language=kotlin
    const val VALUE_INPUT: String = """fun main() {
    render {
        var index = 0
        val values = arrayOf(
            storeOf(5),
            storeOf(30),
            storeOf(60),
        )

        slider(values[index], Step(0, "0")..Step(8, "8")) {
            steps { it % 2 == 0 }
            showTicks()
            valueInput()
        }
        br { }
        index++

        slider(
            values[index], listOf(
                Step(0, "0%"),
                Step(25),
                Step(50, "50%"),
                Step(75),
                Step(100, "100%"),
            )
        ) {
            valueInput()
            valueLabel { "%" }
        }
        br { }
        index++

        slider(values[index], 0..100) {
            valueInput()
            valueLabel { "%" }
        }
    }
}"""

    //language=kotlin
    const val THUMP_VALUE_INPUT: String = """fun main() {
    render {
        slider(storeOf(66), 0..100) {
            valueInput(floating = true)
            valueLabel { "%" }
        }
    }
}"""

    //language=kotlin
    const val ACTIONS: String = """"""

    //language=kotlin
    const val DISABLED: String = """"""
}
