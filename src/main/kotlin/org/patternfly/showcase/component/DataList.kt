@file:Suppress("DuplicatedCode", "SpellCheckingInspection")

package org.patternfly.showcase.component

import dev.fritz2.binding.action
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import dev.fritz2.lenses.IdProvider
import org.patternfly.Align
import org.patternfly.DataListDisplay
import org.patternfly.Id
import org.patternfly.ItemStore
import org.patternfly.Modifier.alignRight
import org.patternfly.Modifier.noFill
import org.patternfly.Modifier.primary
import org.patternfly.Modifier.secondary
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.fas
import org.patternfly.modifier
import org.patternfly.pfButton
import org.patternfly.pfContent
import org.patternfly.pfDataList
import org.patternfly.pfDataListAction
import org.patternfly.pfDataListCell
import org.patternfly.pfDataListCheck
import org.patternfly.pfDataListContent
import org.patternfly.pfDataListControl
import org.patternfly.pfDataListExpandableContent
import org.patternfly.pfDataListExpandableContentBody
import org.patternfly.pfDataListRow
import org.patternfly.pfDataListToggle
import org.patternfly.pfDropdownItem
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownKebab
import org.patternfly.pfIcon
import org.patternfly.pfSection
import org.patternfly.plusAssign
import org.w3c.dom.HTMLElement

object DataListComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Data list",
                prefix = "A ",
                key = "data list",
                text = " is used to display large data sets when you need a flexible layout or need to include interactive content like charts. Related design guidelines: ",
                link = ("lists-and-tables" to "Lists and tables")
            )
        })
        yield(render {
            pfSection {
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", DataListCode.BASIC) {
                    // Just a fake item w/ a display function
                    data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

                    val data = listOf(
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListContent {
                                        pfDataListCell {
                                            span(id = it.id) { +"Primary content" }
                                        }
                                        pfDataListCell { +"Secondary content" }
                                    }
                                }
                            }
                        },
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListContent {
                                        pfDataListCell(noFill) {
                                            span(id = it.id) { +"Secondary content (pf-m-no-fill)" }
                                        }
                                        pfDataListCell(classes(noFill, alignRight)) {
                                            +"Secondary content (pf-m-align-right pf-m-no-fill)"
                                        }
                                    }
                                }
                            }
                        }
                    )

                    val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
                    val store: ItemStore<DisplayData> = ItemStore(identifier)
                    pfDataList(store) {
                        display = {
                            it.display(it)
                        }
                    }
                    action(data) handledBy store.addAll
                }
                snippet("Compact", DataListCode.COMPACT) {
                    // Just a fake item w/ a display function
                    data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

                    val data = listOf(
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListContent {
                                        pfDataListCell {
                                            span(id = it.id) { +"Primary content" }
                                        }
                                        pfDataListCell { +"Secondary content" }
                                    }
                                }
                            }
                        },
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListContent {
                                        pfDataListCell(noFill) {
                                            span(id = it.id) { +"Secondary content (pf-m-no-fill)" }
                                        }
                                        pfDataListCell(classes(noFill, alignRight)) {
                                            +"Secondary content (pf-m-align-right pf-m-no-fill)"
                                        }
                                    }
                                }
                            }
                        }
                    )

                    val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
                    val store: ItemStore<DisplayData> = ItemStore(identifier)
                    pfDataList(store) {
                        domNode.classList += "compact".modifier()
                        display = {
                            it.display(it)
                        }
                    }
                    action(data) handledBy store.addAll
                }
                snippet("Checkboxes, actions and additional cells", DataListCode.CHECKBOXES) {
                    // Just a fake item w/ a display function
                    data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

                    val data = listOf(
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListControl {
                                        pfDataListCheck {}
                                    }
                                    pfDataListContent {
                                        pfDataListCell {
                                            span(id = it.id) { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                        }
                                        pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                        pfDataListCell { +"Tertiary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                        pfDataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                        pfDataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                    }
                                    pfDataListAction {
                                        div(baseClass = "data-list".component("action")) {
                                            pfDropdownKebab<String>(align = Align.RIGHT) {
                                                pfDropdownItems {
                                                    pfDropdownItem("Link")
                                                    pfDropdownItem("Action")
                                                    pfDropdownItem("Disabled Link") {
                                                        disabled = true
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListControl {
                                        pfDataListCheck {}
                                    }
                                    pfDataListContent {
                                        pfDataListCell {
                                            span(id = it.id) { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                        }
                                        pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                    }
                                    pfDataListAction("hidden-on-lg".modifier()) {
                                        div(baseClass = "data-list".component("action")) {
                                            pfDropdownKebab<String>(align = Align.RIGHT) {
                                                pfDropdownItems {
                                                    pfDropdownItem("Link")
                                                    pfDropdownItem("Action")
                                                    pfDropdownItem("Disabled Link") {
                                                        disabled = true
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    pfDataListAction(classes("hidden".modifier(), "visible-on-lg".modifier())) {
                                        pfButton(primary) { +"Primary" }
                                        pfButton(secondary) { +"Secondary" }
                                    }
                                }
                            }
                        },
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListControl {
                                        pfDataListCheck {}
                                    }
                                    pfDataListContent {
                                        pfDataListCell {
                                            span(id = it.id) { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                        }
                                        pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                    }
                                    pfDataListAction("hidden-on-xl".modifier()) {
                                        div(baseClass = "data-list".component("action")) {
                                            pfDropdownKebab<String>(align = Align.RIGHT) {
                                                pfDropdownItems {
                                                    pfDropdownItem("Link")
                                                    pfDropdownItem("Action")
                                                    pfDropdownItem("Disabled Link") {
                                                        disabled = true
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    pfDataListAction(classes("hidden".modifier(), "visible-on-xl".modifier())) {
                                        pfButton(primary) { +"Primary" }
                                        pfButton(secondary) { +"Secondary" }
                                        pfButton(secondary) { +"Secondary" }
                                        pfButton(secondary) { +"Secondary" }
                                    }
                                }
                            }
                        }
                    )

                    val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
                    val store: ItemStore<DisplayData> = ItemStore(identifier)
                    pfDataList(store) {
                        display = {
                            it.display(it)
                        }
                    }
                    action(data) handledBy store.addAll
                }
                snippet("Actions: single and multiple", DataListCode.ACTIONS) {
                    // Just a fake item w/ a display function
                    data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

                    val data = listOf(
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListContent {
                                        pfDataListCell {
                                            span(id = it.id) { +"Single actionable Primary content" }
                                        }
                                        pfDataListCell { +"Single actionable Secondary content" }
                                    }
                                    pfDataListAction {
                                        pfButton(primary) { +"Delete" }
                                    }
                                }
                            }
                        },
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListContent {
                                        pfDataListCell {
                                            span(id = it.id) { +"Multi actions Primary content" }
                                        }
                                        pfDataListCell { +"Multi actions Secondary content" }
                                    }
                                    pfDataListAction {
                                        div(baseClass = "data-list".component("action")) {
                                            pfDropdownKebab<String>(align = Align.RIGHT) {
                                                pfDropdownItems {
                                                    pfDropdownItem("Link")
                                                    pfDropdownItem("Action")
                                                    pfDropdownItem("Disabled Link") {
                                                        disabled = true
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    )

                    val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
                    val store: ItemStore<DisplayData> = ItemStore(identifier)
                    pfDataList(store) {
                        display = {
                            it.display(it)
                        }
                    }
                    action(data) handledBy store.addAll
                }
                snippet("Expandable", DataListCode.EXPANDABLE) {
                    // Just a fake item w/ a display function
                    data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

                    val data = listOf(
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListControl {
                                        pfDataListToggle()
                                    }
                                    pfDataListContent {
                                        pfDataListCell("icon".modifier()) {
                                            pfIcon("code-branch".fas())
                                        }
                                        pfDataListCell {
                                            div(id = it.id) { +"Primary content" }
                                            span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                                        }
                                        pfDataListCell {
                                            span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                                        }
                                        pfDataListCell {
                                            span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                                        }
                                    }
                                    pfDataListAction {
                                        div(baseClass = "data-list".component("action")) {
                                            pfDropdownKebab<String>(align = Align.RIGHT) {
                                                pfDropdownItems {
                                                    pfDropdownItem("Link")
                                                    pfDropdownItem("Action")
                                                    pfDropdownItem("Disabled Link") {
                                                        disabled = true
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                pfDataListExpandableContent {
                                    pfDataListExpandableContentBody {
                                        +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                                    }
                                }
                            }
                        },
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListControl {
                                        pfDataListToggle()
                                    }
                                    pfDataListContent {
                                        pfDataListCell("icon".modifier()) {
                                            pfIcon("code-branch".fas())
                                        }
                                        pfDataListCell {
                                            div(id = it.id) { +"Secondary content" }
                                            span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                                        }
                                        pfDataListCell {
                                            span { +"Lorem ipsum dolor sit amet." }
                                        }
                                        pfDataListCell {
                                            span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                                        }
                                    }
                                    pfDataListAction {
                                        div(baseClass = "data-list".component("action")) {
                                            pfDropdownKebab<String>(align = Align.RIGHT) {
                                                pfDropdownItems {
                                                    pfDropdownItem("Link")
                                                    pfDropdownItem("Action")
                                                    pfDropdownItem("Disabled Link") {
                                                        disabled = true
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                pfDataListExpandableContent {
                                    pfDataListExpandableContentBody {
                                        +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                                    }
                                }
                            }
                        },
                        DisplayData {
                            {
                                pfDataListRow {
                                    pfDataListControl {
                                        pfDataListToggle()
                                    }
                                    pfDataListContent {
                                        pfDataListCell("icon".modifier()) {
                                            pfIcon("code-branch".fas())
                                        }
                                        pfDataListCell {
                                            div(id = it.id) { +"Tertiary content" }
                                            span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                                        }
                                        pfDataListCell {
                                            span { +"Lorem ipsum dolor sit amet." }
                                        }
                                        pfDataListCell {
                                            span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                                        }
                                    }
                                    pfDataListAction {
                                        div(baseClass = "data-list".component("action")) {
                                            pfDropdownKebab<String>(align = Align.RIGHT) {
                                                pfDropdownItems {
                                                    pfDropdownItem("Link")
                                                    pfDropdownItem("Action")
                                                    pfDropdownItem("Disabled Link") {
                                                        disabled = true
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                pfDataListExpandableContent {
                                    pfDataListExpandableContentBody {
                                        +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                                    }
                                }
                            }
                        }
                    )

                    val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
                    val store: ItemStore<DisplayData> = ItemStore(identifier)
                    pfDataList(store) {
                        display = {
                            it.display(it)
                        }
                    }
                    action(data) handledBy store.addAll
                }
            }
        })
    }
}

internal object DataListCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

        val data = listOf(
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListContent {
                            pfDataListCell {
                                span(id = it.id) { +"Primary content" }
                            }
                            pfDataListCell { +"Secondary content" }
                        }
                    }
                }
            },
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListContent {
                            pfDataListCell("no-fill".modifier()) {
                                span(id = it.id) { +"Secondary content (pf-m-no-fill)" }
                            }
                            pfDataListCell("no-fill".modifier(), "align-right".modifier()) {
                                +"Secondary content (pf-m-align-right pf-m-no-fill)"
                            }
                        }
                    }
                }
            }
        )
        
        val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
        val store: ItemStore<DisplayData> = ItemStore(identifier)
        pfDataList(store) {
            display = {
                it.display(it)
            }
        }
        action(data) handledBy store.addAll
    }
}
"""

    //language=kotlin
    const val COMPACT: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

        val data = listOf(
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListContent {
                            pfDataListCell {
                                span(id = it.id) { +"Primary content" }
                            }
                            pfDataListCell { +"Secondary content" }
                        }
                    }
                }
            },
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListContent {
                            pfDataListCell("no-fill".modifier()) {
                                span(id = it.id) { +"Secondary content (pf-m-no-fill)" }
                            }
                            pfDataListCell("no-fill".modifier(), "align-right".modifier()) {
                                +"Secondary content (pf-m-align-right pf-m-no-fill)"
                            }
                        }
                    }
                }
            }
        )
        
        val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
        val store: ItemStore<DisplayData> = ItemStore(identifier)
        pfDataList(store) {
            domNode.classList += "compact".modifier()
            display = {
                it.display(it)
            }
        }
        action(data) handledBy store.addAll
    }
}
"""

    //language=kotlin
    const val CHECKBOXES: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

        val data = listOf(
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListControl {
                            pfDataListCheck {}
                        }
                        pfDataListContent {
                            pfDataListCell {
                                span(id = it.id) { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                            }
                            pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                            pfDataListCell { +"Tertiary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                            pfDataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                            pfDataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        }
                        pfDataListAction {
                            div(baseClass = "data-list".component("action")) {
                                pfDropdownKebab<String>(align = Align.RIGHT) {
                                    pfDropdownItems {
                                        pfDropdownItem("Link")
                                        pfDropdownItem("Action")
                                        pfDropdownItem("Disabled Link") {
                                            disabled = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListControl {
                            pfDataListCheck {}
                        }
                        pfDataListContent {
                            pfDataListCell {
                                span(id = it.id) { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                            }
                            pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        }
                        pfDataListAction("hidden-on-lg".modifier()) {
                            div(baseClass = "data-list".component("action")) {
                                pfDropdownKebab<String>(align = Align.RIGHT) {
                                    pfDropdownItems {
                                        pfDropdownItem("Link")
                                        pfDropdownItem("Action")
                                        pfDropdownItem("Disabled Link") {
                                            disabled = true
                                        }
                                    }
                                }
                            }
                        }
                        pfDataListAction("hidden".modifier(), "visible-on-lg".modifier()) {
                            pfButton(primary) { +"Primary" }
                            pfButton(secondary) { +"Secondary" }
                        }
                    }
                }
            },
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListControl {
                            pfDataListCheck {}
                        }
                        pfDataListContent {
                            pfDataListCell {
                                span(id = it.id) { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                            }
                            pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        }
                        pfDataListAction("hidden-on-xl".modifier()) {
                            div(baseClass = "data-list".component("action")) {
                                pfDropdownKebab<String>(align = Align.RIGHT) {
                                    pfDropdownItems {
                                        pfDropdownItem("Link")
                                        pfDropdownItem("Action")
                                        pfDropdownItem("Disabled Link") {
                                            disabled = true
                                        }
                                    }
                                }
                            }
                        }
                        pfDataListAction("hidden".modifier(), "visible-on-xl".modifier()) {
                            pfButton(primary) { +"Primary" }
                            pfButton(secondary) { +"Secondary" }
                            pfButton(secondary) { +"Secondary" }
                            pfButton(secondary) { +"Secondary" }
                        }
                    }
                }
            }
        )
        
        val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
        val store: ItemStore<DisplayData> = ItemStore(identifier)
        pfDataList(store) {
            display = {
                it.display(it)
            }
        }
        action(data) handledBy store.addAll
    }
}
"""

    //language=kotlin
    const val ACTIONS: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

        val data = listOf(
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListContent {
                            pfDataListCell {
                                span(id = it.id) { +"Single actionable Primary content" }
                            }
                            pfDataListCell { +"Single actionable Secondary content" }
                        }
                        pfDataListAction {
                            pfButton(primary) { +"Delete" }
                        }
                    }
                }
            },
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListContent {
                            pfDataListCell {
                                span(id = it.id) { +"Multi actions Primary content" }
                            }
                            pfDataListCell { +"Multi actions Secondary content" }
                        }
                        pfDataListAction {
                            div(baseClass = "data-list".component("action")) {
                                pfDropdownKebab<String>(align = Align.RIGHT) {
                                    pfDropdownItems {
                                        pfDropdownItem("Link")
                                        pfDropdownItem("Action")
                                        pfDropdownItem("Disabled Link") {
                                            disabled = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )

        val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
        val store: ItemStore<DisplayData> = ItemStore(identifier)
        pfDataList(store) {
            display = {
                it.display(it)
            }
        }
        action(data) handledBy store.addAll
    }
}
"""

    //language=kotlin
    const val EXPANDABLE: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(val id: String = Id.unique(), val display: DataListDisplay<DisplayData>)

        val data = listOf(
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListControl {
                            pfDataListToggle()
                        }
                        pfDataListContent {
                            pfDataListCell("icon".modifier()) {
                                pfIcon("code-branch".fas())
                            }
                            pfDataListCell {
                                div(id = it.id) { +"Primary content" }
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                            pfDataListCell {
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                            pfDataListCell {
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                        }
                        pfDataListAction {
                            div(baseClass = "data-list".component("action")) {
                                pfDropdownKebab<String>(align = Align.RIGHT) {
                                    pfDropdownItems {
                                        pfDropdownItem("Link")
                                        pfDropdownItem("Action")
                                        pfDropdownItem("Disabled Link") {
                                            disabled = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                    pfDataListExpandableContent {
                        pfDataListExpandableContentBody {
                            +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        }
                    }
                }
            },
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListControl {
                            pfDataListToggle()
                        }
                        pfDataListContent {
                            pfDataListCell("icon".modifier()) {
                                pfIcon("code-branch".fas())
                            }
                            pfDataListCell {
                                div(id = it.id) { +"Secondary content" }
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                            pfDataListCell {
                                span { +"Lorem ipsum dolor sit amet." }
                            }
                            pfDataListCell {
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                        }
                        pfDataListAction {
                            div(baseClass = "data-list".component("action")) {
                                pfDropdownKebab<String>(align = Align.RIGHT) {
                                    pfDropdownItems {
                                        pfDropdownItem("Link")
                                        pfDropdownItem("Action")
                                        pfDropdownItem("Disabled Link") {
                                            disabled = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                    pfDataListExpandableContent {
                        pfDataListExpandableContentBody {
                            +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        }
                    }
                }
            },
            DisplayData {
                {
                    pfDataListRow {
                        pfDataListControl {
                            pfDataListToggle()
                        }
                        pfDataListContent {
                            pfDataListCell("icon".modifier()) {
                                pfIcon("code-branch".fas())
                            }
                            pfDataListCell {
                                div(id = it.id) { +"Tertiary content" }
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                            pfDataListCell {
                                span { +"Lorem ipsum dolor sit amet." }
                            }
                            pfDataListCell {
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                        }
                        pfDataListAction {
                            div(baseClass = "data-list".component("action")) {
                                pfDropdownKebab<String>(align = Align.RIGHT) {
                                    pfDropdownItems {
                                        pfDropdownItem("Link")
                                        pfDropdownItem("Action")
                                        pfDropdownItem("Disabled Link") {
                                            disabled = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                    pfDataListExpandableContent {
                        pfDataListExpandableContentBody {
                            +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        }
                    }
                }
            }
        )

        val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
        val store: ItemStore<DisplayData> = ItemStore(identifier)
        pfDataList(store) {
            display = {
                it.display(it)
            }
        }
        action(data) handledBy store.addAll
    }
}
"""
}
