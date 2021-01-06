@file:Suppress("SpellCheckingInspection")

package org.patternfly.showcase.component

import kotlin.math.max
import kotlin.math.min

private val LOREM_IPSUM: Array<String> = arrayOf(
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    "Phasellus pretium est a porttitor vehicula.",
    "Quisque vel commodo urna.",
    "Morbi mattis rutrum ante, id vehicula ex accumsan ut.",
    "Morbi viverra, eros vel porttitor facilisis, eros purus aliquet erat,nec lobortis felis elit pulvinar sem.",
    "Vivamus vulputate, risus eget commodo eleifend, eros nibh porta quam, vitae lacinia leo libero at magna.",
    "Maecenas aliquam sagittis orci, et posuere nisi ultrices sit amet.",
    "Aliquam ex odio, malesuada sed posuere quis, pellentesque at mauris.",
    "Phasellus venenatis massa ex, eget pulvinar libero auctor pretium.",
    "Aliquam erat volutpat.",
    "Duis euismod justo in quam ullamcorper, in commodo massa vulputate."
)

private const val MAX_SENTENCES = 42

fun loremIpsum(sentences: Int = 1): String {
    val safeSentences = min(max(1, sentences), MAX_SENTENCES)
    return (1..safeSentences).joinToString(" ") { LOREM_IPSUM.random() }
}