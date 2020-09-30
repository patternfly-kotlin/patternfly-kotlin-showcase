package org.patternfly.showcase.component

import ClipboardJS
import dev.fritz2.binding.RootStore
import dev.fritz2.binding.const
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.HtmlElements
import hljs.highlightBlock
import kotlinx.browser.window
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import org.patternfly.By
import org.patternfly.COMPONENT_TYPE
import org.patternfly.ComponentType.Content
import org.patternfly.Section
import org.patternfly.Size
import org.patternfly.fas
import org.patternfly.minusAssign
import org.patternfly.modifier
import org.patternfly.pfButton
import org.patternfly.pfContent
import org.patternfly.pfIcon
import org.patternfly.pfSection
import org.patternfly.pfTitle
import org.patternfly.plusAssign
import org.patternfly.querySelector
import org.patternfly.showcase.Places
import org.patternfly.util
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
fun HtmlElements.intro(
    title: String,
    prefix: String? = null,
    key: String,
    text: String,
    link: Pair<String, String>? = null
): Section = pfSection("pb-0".util()) {
    pfTitle(size = Size.XL_3, baseClass = "mb-md".util()) { +title }
    p {
        prefix?.let { +it }
        strong { +key }
        +text
        link?.let { (url, text) ->
            a {
                href = const(Places.behaviour(url))
                target = const("pf4")
                +text
            }
        }
    }
}

fun HtmlElements.snippet(header: String, code: String, content: HtmlElements.() -> Unit): Snippet =
    register(Snippet(header, code, content), {})

internal class CodeStore : RootStore<Boolean>(true) {
    val toggle = handle { hidden -> !hidden }
}

class Snippet(header: String, code: String, val content: HtmlElements.() -> Unit) :
    Tag<HTMLElement>("section", baseClass = "sc-snippet") {
    private var timeoutHandle = -1
    private val codeStore = CodeStore()

    init {
        pfTitle(level = 3, size = Size.LG, baseClass = "sc-snippet__title") { +header }
        div("sc-snippet__content") {
            this@Snippet.content(this)
        }
        div("sc-snippet__toolbar") {
            pfButton(baseClass = "plain".modifier()) {
                pfIcon("code".fas())
                clicks handledBy this@Snippet.codeStore.toggle
            }
            pfButton(baseClass = "plain".modifier()) {
                pfIcon("copy".fas())
                val clipboard = ClipboardJS(domNode, object : ClipboardJS.Options {
                    override val text: ((Element) -> String)? = { code }
                })
                clipboard.on("success") {
                    this@Snippet.showCopied()
                }
            }
            pfContent(baseClass = "display-none".util()) {
                span("sc-snippet__copied") { +"Copied to clipboard" }
            }
        }
        div("sc-snippet__code") {
            classMap = this@Snippet.codeStore.data.map { hidden ->
                mapOf("display-none".util() to hidden)
            }
            pre {
                code("kotlin") {
                    +code
                    highlightBlock(domNode)
                }
            }
        }
    }

    private fun showCopied() {
        window.clearTimeout(timeoutHandle)
        domNode.querySelector(By.data(COMPONENT_TYPE, Content.id))?.let {
            it.classList -= "display-none".util()
            timeoutHandle = window.setTimeout({
                it.classList += "display-none".util()
            }, 2000)
        }
    }
}
