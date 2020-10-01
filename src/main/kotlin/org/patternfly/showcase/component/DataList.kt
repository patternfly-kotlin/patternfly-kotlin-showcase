@file:Suppress("DuplicatedCode", "SpellCheckingInspection")

package org.patternfly.showcase.component

import dev.fritz2.binding.action
import dev.fritz2.binding.handledBy
import dev.fritz2.lenses.IdProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.patternfly.Align
import org.patternfly.DataListItem
import org.patternfly.Elements
import org.patternfly.Id
import org.patternfly.ItemStore
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.elements
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
import org.patternfly.pfDataListItem
import org.patternfly.pfDataListRow
import org.patternfly.pfDataListToggle
import org.patternfly.pfDropdown
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownToggleKebab
import org.patternfly.pfIcon
import org.patternfly.pfItem
import org.patternfly.pfSection
import org.patternfly.pfSeparator
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@ExperimentalTime
class DataListComponent : Elements {
    override val elements = elements {
        intro(
            title = "Data list",
            prefix = "A ",
            key = "data list",
            text = " is used to display large data sets when you need a flexible layout or need to include interactive content like charts. Related design guidelines: ",
            link = ("lists-and-tables" to "Lists and tables")
        )
        pfSection {
            pfContent {
                h2 { +"Examples" }
            }
            snippet("Basic", DataListCode.BASIC) {
                // Just a fake item w/ a display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: DataListItem<DisplayData>.() -> Unit
                )

                val data = listOf(
                    DisplayData {
                        pfDataListRow {
                            pfDataListContent {
                                pfDataListCell {
                                    span { +"Primary content" }
                                }
                                pfDataListCell { +"Secondary content" }
                            }
                        }
                    },
                    DisplayData {
                        pfDataListRow {
                            pfDataListContent {
                                pfDataListCell("no-fill".modifier()) {
                                    span { +"Secondary content (pf-m-no-fill)" }
                                }
                                pfDataListCell(classes("no-fill".modifier(), "align-right".modifier())) {
                                    +"Secondary content (pf-m-align-right pf-m-no-fill)"
                                }
                            }
                        }
                    }
                )

                val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
                val store: ItemStore<DisplayData> = ItemStore(identifier)
                pfDataList(store) {
                    display = { pfDataListItem(item = it, content = it.content) }
                }
                action(data) handledBy store.addAll
            }
            snippet("Compact", DataListCode.COMPACT) {
                // Just a fake item w/ a display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: DataListItem<DisplayData>.() -> Unit
                )

                val data = listOf(
                    DisplayData {
                        pfDataListRow {
                            pfDataListContent {
                                pfDataListCell {
                                    span { +"Primary content" }
                                }
                                pfDataListCell { +"Secondary content" }
                            }
                        }
                    },
                    DisplayData {
                        pfDataListRow {
                            pfDataListContent {
                                pfDataListCell("no-fill".modifier()) {
                                    span { +"Secondary content (pf-m-no-fill)" }
                                }
                                pfDataListCell(classes("no-fill".modifier(), "align-right".modifier())) {
                                    +"Secondary content (pf-m-align-right pf-m-no-fill)"
                                }
                            }
                        }
                    }
                )

                val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
                val store: ItemStore<DisplayData> = ItemStore(identifier)
                pfDataList(store) {
                    display = { pfDataListItem(item = it, content = it.content) }
                }
                action(data) handledBy store.addAll
            }
            snippet("Checkboxes, actions and additional cells", DataListCode.CHECKBOXES) {
                // Just a fake item w/ a display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: DataListItem<DisplayData>.() -> Unit
                )

                val data = listOf(
                    DisplayData {
                        pfDataListRow {
                            pfDataListControl {
                                pfDataListCheck()
                            }
                            pfDataListContent {
                                pfDataListCell {
                                    span { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                }
                                pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                pfDataListCell { +"Tertiary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                pfDataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                pfDataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                            }
                            pfDataListAction {
                                div(baseClass = "data-list".component("action")) {
                                    pfDropdown<String>(align = Align.RIGHT) {
                                        pfDropdownToggleKebab()
                                        pfDropdownItems {
                                            pfItem("Action")
                                            pfItem("Disabled Action") {
                                                disabled = true
                                            }
                                            pfSeparator()
                                            pfItem("Separated Action")
                                        }
                                    }
                                }
                            }
                        }
                    },
                    DisplayData {
                        pfDataListRow {
                            pfDataListControl {
                                pfDataListCheck {}
                            }
                            pfDataListContent {
                                pfDataListCell {
                                    span { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                }
                                pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                            }
                            pfDataListAction("hidden-on-lg".modifier()) {
                                div(baseClass = "data-list".component("action")) {
                                    pfDropdown<String>(align = Align.RIGHT) {
                                        pfDropdownToggleKebab()
                                        pfDropdownItems {
                                            pfItem("Action")
                                            pfItem("Disabled Action") {
                                                disabled = true
                                            }
                                            pfSeparator()
                                            pfItem("Separated Action")
                                        }
                                    }
                                }
                            }
                            pfDataListAction(classes("hidden".modifier(), "visible-on-lg".modifier())) {
                                pfButton(baseClass = "primary".modifier()) { +"Primary" }
                                pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
                            }
                        }
                    },
                    DisplayData {
                        pfDataListRow {
                            pfDataListControl {
                                pfDataListCheck {}
                            }
                            pfDataListContent {
                                pfDataListCell {
                                    span { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                                }
                                pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                            }
                            pfDataListAction("hidden-on-xl".modifier()) {
                                div(baseClass = "data-list".component("action")) {
                                    pfDropdown<String>(align = Align.RIGHT) {
                                        pfDropdownToggleKebab()
                                        pfDropdownItems {
                                            pfItem("Action")
                                            pfItem("Disabled Action") {
                                                disabled = true
                                            }
                                            pfSeparator()
                                            pfItem("Separated Action")
                                        }
                                    }
                                }
                            }
                            pfDataListAction(classes("hidden".modifier(), "visible-on-xl".modifier())) {
                                pfButton(baseClass = "primary".modifier()) { +"Primary" }
                                pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
                                pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
                                pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
                            }
                        }
                    }
                )

                val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
                val store: ItemStore<DisplayData> = ItemStore(identifier)
                pfDataList(store) {
                    display = { pfDataListItem(item = it, content = it.content) }
                }
                action(data) handledBy store.addAll
            }
            snippet("Actions: single and multiple", DataListCode.ACTIONS) {
                // Just a fake item w/ a display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: DataListItem<DisplayData>.() -> Unit
                )

                val data = listOf(
                    DisplayData {
                        pfDataListRow {
                            pfDataListContent {
                                pfDataListCell {
                                    span { +"Single actionable Primary content" }
                                }
                                pfDataListCell { +"Single actionable Secondary content" }
                            }
                            pfDataListAction {
                                pfButton(baseClass = "primary".modifier()) { +"Delete" }
                            }
                        }
                    },
                    DisplayData {
                        pfDataListRow {
                            pfDataListContent {
                                pfDataListCell {
                                    span { +"Multi actions Primary content" }
                                }
                                pfDataListCell { +"Multi actions Secondary content" }
                            }
                            pfDataListAction {
                                div(baseClass = "data-list".component("action")) {
                                    pfDropdown<String>(align = Align.RIGHT) {
                                        pfDropdownToggleKebab()
                                        pfDropdownItems {
                                            pfItem("Action")
                                            pfItem("Disabled Action") {
                                                disabled = true
                                            }
                                            pfSeparator()
                                            pfItem("Separated Action")
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
                    display = { pfDataListItem(item = it, content = it.content) }
                }
                action(data) handledBy store.addAll
            }
            snippet("Expandable", DataListCode.EXPANDABLE) {
                // Just a fake item w/ a display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: List<DataListItem<DisplayData>.() -> Unit>
                )

                val data = listOf(
                    DisplayData(content = listOf(
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
                                        div { +"Primary content" }
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
                                        pfDropdown<String>(align = Align.RIGHT) {
                                            pfDropdownToggleKebab()
                                            pfDropdownItems {
                                                pfItem("Action")
                                                pfItem("Disabled Action") {
                                                    disabled = true
                                                }
                                                pfSeparator()
                                                pfItem("Separated Action")
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        {
                            pfDataListExpandableContent {
                                pfDataListExpandableContentBody {
                                    +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                                }
                            }
                        }
                    )),
                    DisplayData(content = listOf(
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
                                        div { +"Secondary content" }
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
                                        pfDropdown<String>(align = Align.RIGHT) {
                                            pfDropdownToggleKebab()
                                            pfDropdownItems {
                                                pfItem("Action")
                                                pfItem("Disabled Action") {
                                                    disabled = true
                                                }
                                                pfSeparator()
                                                pfItem("Separated Action")
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        {
                            pfDataListExpandableContent {
                                pfDataListExpandableContentBody {
                                    +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                                }
                            }
                        }
                    )),
                    DisplayData(
                        content = listOf(
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
                                            div { +"Tertiary content" }
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
                                            pfDropdown<String>(align = Align.RIGHT) {
                                                pfDropdownToggleKebab()
                                                pfDropdownItems {
                                                    pfItem("Action")
                                                    pfItem("Disabled Action") {
                                                        disabled = true
                                                    }
                                                    pfSeparator()
                                                    pfItem("Separated Action")
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            {
                                pfDataListExpandableContent {
                                    pfDataListExpandableContentBody {
                                        +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                                    }
                                }
                            }
                        )))

                val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
                val store: ItemStore<DisplayData> = ItemStore(identifier)
                pfDataList(store) {
                    display = {
                        pfDataListItem(it) {
                            it.content.forEach { content ->
                                content(this)
                            }
                        }
                    }
                }
                action(data) handledBy store.addAll
            }
        }
    }
}

internal object DataListCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(
            val id: String = Id.unique(),
            val content: DataListItem<DisplayData>.() -> Unit
        )

        val data = listOf(
            DisplayData {
                pfDataListRow {
                    pfDataListContent {
                        pfDataListCell {
                            span { +"Primary content" }
                        }
                        pfDataListCell { +"Secondary content" }
                    }
                }
            },
            DisplayData {
                pfDataListRow {
                    pfDataListContent {
                        pfDataListCell("no-fill".modifier()) {
                            span { +"Secondary content (pf-m-no-fill)" }
                        }
                        pfDataListCell(classes("no-fill".modifier(), "align-right".modifier())) {
                            +"Secondary content (pf-m-align-right pf-m-no-fill)"
                        }
                    }
                }
            }
        )

        val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
        val store: ItemStore<DisplayData> = ItemStore(identifier)
        pfDataList(store) {
            display = { pfDataListItem(item = it, content = it.content) }
        }
        action(data) handledBy store.addAll
    }
}
"""

    //language=kotlin
    const val COMPACT: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(
            val id: String = Id.unique(),
            val content: DataListItem<DisplayData>.() -> Unit
        )

        val data = listOf(
            DisplayData {
                pfDataListRow {
                    pfDataListContent {
                        pfDataListCell {
                            span { +"Primary content" }
                        }
                        pfDataListCell { +"Secondary content" }
                    }
                }
            },
            DisplayData {
                pfDataListRow {
                    pfDataListContent {
                        pfDataListCell("no-fill".modifier()) {
                            span { +"Secondary content (pf-m-no-fill)" }
                        }
                        pfDataListCell(classes("no-fill".modifier(), "align-right".modifier())) {
                            +"Secondary content (pf-m-align-right pf-m-no-fill)"
                        }
                    }
                }
            }
        )

        val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
        val store: ItemStore<DisplayData> = ItemStore(identifier)
        pfDataList(store) {
            display = { pfDataListItem(item = it, content = it.content) }
        }
        action(data) handledBy store.addAll
    }
}
"""

    //language=kotlin
    const val CHECKBOXES: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(
            val id: String = Id.unique(),
            val content: DataListItem<DisplayData>.() -> Unit
        )

        val data = listOf(
            DisplayData {
                pfDataListRow {
                    pfDataListControl {
                        pfDataListCheck()
                    }
                    pfDataListContent {
                        pfDataListCell {
                            span { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        }
                        pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        pfDataListCell { +"Tertiary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        pfDataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        pfDataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                    }
                    pfDataListAction {
                        div(baseClass = "data-list".component("action")) {
                            pfDropdown<String>(align = Align.RIGHT) {
                                pfDropdownToggleKebab()
                                pfDropdownItems {
                                    pfItem("Action")
                                    pfItem("Disabled Action") {
                                        disabled = true
                                    }
                                    pfSeparator()
                                    pfItem("Separated Action")
                                }
                            }
                        }
                    }
                }
            },
            DisplayData {
                pfDataListRow {
                    pfDataListControl {
                        pfDataListCheck {}
                    }
                    pfDataListContent {
                        pfDataListCell {
                            span { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        }
                        pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                    }
                    pfDataListAction("hidden-on-lg".modifier()) {
                        div(baseClass = "data-list".component("action")) {
                            pfDropdown<String>(align = Align.RIGHT) {
                                pfDropdownToggleKebab()
                                pfDropdownItems {
                                    pfItem("Action")
                                    pfItem("Disabled Action") {
                                        disabled = true
                                    }
                                    pfSeparator()
                                    pfItem("Separated Action")
                                }
                            }
                        }
                    }
                    pfDataListAction(classes("hidden".modifier(), "visible-on-lg".modifier())) {
                        pfButton(baseClass = "primary".modifier()) { +"Primary" }
                        pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
                    }
                }
            },
            DisplayData {
                pfDataListRow {
                    pfDataListControl {
                        pfDataListCheck {}
                    }
                    pfDataListContent {
                        pfDataListCell {
                            span { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        }
                        pfDataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                    }
                    pfDataListAction("hidden-on-xl".modifier()) {
                        div(baseClass = "data-list".component("action")) {
                            pfDropdown<String>(align = Align.RIGHT) {
                                pfDropdownToggleKebab()
                                pfDropdownItems {
                                    pfItem("Action")
                                    pfItem("Disabled Action") {
                                        disabled = true
                                    }
                                    pfSeparator()
                                    pfItem("Separated Action")
                                }
                            }
                        }
                    }
                    pfDataListAction(classes("hidden".modifier(), "visible-on-xl".modifier())) {
                        pfButton(baseClass = "primary".modifier()) { +"Primary" }
                        pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
                        pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
                        pfButton(baseClass = "secondary".modifier()) { +"Secondary" }
                    }
                }
            }
        )

        val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
        val store: ItemStore<DisplayData> = ItemStore(identifier)
        pfDataList(store) {
            display = { pfDataListItem(item = it, content = it.content) }
        }
        action(data) handledBy store.addAll
    }
}
"""

    //language=kotlin
    const val ACTIONS: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(
            val id: String = Id.unique(),
            val content: DataListItem<DisplayData>.() -> Unit
        )

        val data = listOf(
            DisplayData {
                pfDataListRow {
                    pfDataListContent {
                        pfDataListCell {
                            span { +"Single actionable Primary content" }
                        }
                        pfDataListCell { +"Single actionable Secondary content" }
                    }
                    pfDataListAction {
                        pfButton(baseClass = "primary".modifier()) { +"Delete" }
                    }
                }
            },
            DisplayData {
                pfDataListRow {
                    pfDataListContent {
                        pfDataListCell {
                            span { +"Multi actions Primary content" }
                        }
                        pfDataListCell { +"Multi actions Secondary content" }
                    }
                    pfDataListAction {
                        div(baseClass = "data-list".component("action")) {
                            pfDropdown<String>(align = Align.RIGHT) {
                                pfDropdownToggleKebab()
                                pfDropdownItems {
                                    pfItem("Action")
                                    pfItem("Disabled Action") {
                                        disabled = true
                                    }
                                    pfSeparator()
                                    pfItem("Separated Action")
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
            display = { pfDataListItem(item = it, content = it.content) }
        }
        action(data) handledBy store.addAll
    }
}
"""

    //language=kotlin
    const val EXPANDABLE: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(
            val id: String = Id.unique(),
            val content: List<DataListItem<DisplayData>.() -> Unit>
        )

        val data = listOf(
            DisplayData(content = listOf(
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
                                div { +"Primary content" }
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
                                pfDropdown<String>(align = Align.RIGHT) {
                                    pfDropdownToggleKebab()
                                    pfDropdownItems {
                                        pfItem("Action")
                                        pfItem("Disabled Action") {
                                            disabled = true
                                        }
                                        pfSeparator()
                                        pfItem("Separated Action")
                                    }
                                }
                            }
                        }
                    }
                },
                {
                    pfDataListExpandableContent {
                        pfDataListExpandableContentBody {
                            +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        }
                    }
                }
            )),
            DisplayData(content = listOf(
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
                                div { +"Secondary content" }
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
                                pfDropdown<String>(align = Align.RIGHT) {
                                    pfDropdownToggleKebab()
                                    pfDropdownItems {
                                        pfItem("Action")
                                        pfItem("Disabled Action") {
                                            disabled = true
                                        }
                                        pfSeparator()
                                        pfItem("Separated Action")
                                    }
                                }
                            }
                        }
                    }
                },
                {
                    pfDataListExpandableContent {
                        pfDataListExpandableContentBody {
                            +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        }
                    }
                }
            )),
            DisplayData(
                content = listOf(
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
                                    div { +"Tertiary content" }
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
                                    pfDropdown<String>(align = Align.RIGHT) {
                                        pfDropdownToggleKebab()
                                        pfDropdownItems {
                                            pfItem("Action")
                                            pfItem("Disabled Action") {
                                                disabled = true
                                            }
                                            pfSeparator()
                                            pfItem("Separated Action")
                                        }
                                    }
                                }
                            }
                        }
                    },
                    {
                        pfDataListExpandableContent {
                            pfDataListExpandableContentBody {
                                +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                            }
                        }
                    }
                )))

        val identifier: IdProvider<DisplayData, String> = { Id.asId(it.id) }
        val store: ItemStore<DisplayData> = ItemStore(identifier)
        pfDataList(store) {
            display = {
                pfDataListItem(it) {
                    it.content.forEach { content ->
                        content(this)
                    }
                }
            }
        }
        action(data) handledBy store.addAll
    }
}
"""
}