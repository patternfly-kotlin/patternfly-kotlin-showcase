@file:Suppress("DuplicatedCode", "SpellCheckingInspection")

package org.patternfly.showcase.component

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.RenderContext
import kotlinx.coroutines.flow.map
import org.patternfly.ButtonVariant.link
import org.patternfly.actionList
import org.patternfly.classes
import org.patternfly.clickButton
import org.patternfly.component
import org.patternfly.dataList
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.pushButton
import org.patternfly.showcase.fixture.DropdownFixture.defaultDropdown
import org.patternfly.util

object DataListComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Data list",
            text = "A data list is used to display large data sets when you need a flexible layout or need to include interactive content like charts.",
            designGuidelines = "https://www.patternfly.org/v4/components/data-list/design-guidelines"
        )
        pageSection {
            snippet("Basic", DataListCode.BASIC) {
                dataList {
                    item {
                        cell { span(id = this@item.id) { +"Primary content" } }
                        cell { +"Secondary content" }
                    }
                    item {
                        cell(baseClass = "no-fill".modifier()) {
                            span(id = this@item.id) {
                                +"Secondary content (pf-m-no-fill)"
                            }
                        }
                        cell(baseClass = classes("no-fill".modifier(), "align-right".modifier())) {
                            +"Secondary content (pf-m-align-right pf-m-no-fill)"
                        }
                    }
                }
            }
            snippet("Compact", DataListCode.COMPACT) {
                dataList(compact = true) {
                    item {
                        cell { span(id = this@item.id) { +"Primary content" } }
                        cell { +"Secondary content" }
                    }
                    item {
                        cell(baseClass = "no-fill".modifier()) {
                            span(id = this@item.id) {
                                +"Secondary content (pf-m-no-fill)"
                            }
                        }
                        cell(baseClass = classes("no-fill".modifier(), "align-right".modifier())) {
                            +"Secondary content (pf-m-align-right pf-m-no-fill)"
                        }
                    }
                }
            }
            snippet("Checkboxes, actions and additional cells", DataListCode.CHECKBOXES) {
                val dl = dataList {
                    item {
                        check()
                        cell { span(id = this@item.id) { +loremIpsum() } }
                        cell { +loremIpsum() }
                        cell { +loremIpsum() }
                        cell { +loremIpsum() }
                        cell { +loremIpsum() }
                        actionWrapper {
                            div(baseClass = "data-list".component("action")) {
                                defaultDropdown()
                            }
                        }
                    }
                    item {
                        check()
                        cell { span(id = this@item.id) { +loremIpsum() } }
                        cell { +loremIpsum() }
                        actionWrapper(baseClass = "hidden-on-lg".modifier()) {
                            defaultDropdown()
                        }
                        action(baseClass = classes("hidden".modifier(), "visible-on-lg".modifier())) {
                            pushButton(baseClass = "primary".modifier()) { +"Primary" }
                            pushButton(baseClass = "secondary".modifier()) { +"Secondary" }
                        }
                    }
                    item {
                        check()
                        cell { span(id = this@item.id) { +loremIpsum() } }
                        cell { +loremIpsum() }
                        actionWrapper(baseClass = "hidden-on-xl".modifier()) {
                            defaultDropdown()
                        }
                        action(baseClass = classes("hidden".modifier(), "visible-on-xl".modifier())) {
                            pushButton(baseClass = "primary".modifier()) { +"Primary" }
                            pushButton(baseClass = "secondary".modifier()) { +"Secondary" }
                            pushButton(baseClass = "secondary".modifier()) { +"Secondary" }
                            pushButton(baseClass = "secondary".modifier()) { +"Secondary" }
                        }
                    }
                }
                section(baseClass = "mt-lg".util()) {
                    p {
                        +"Selected ids: "
                        dl.selectedIds.renderText()
                    }
                }
            }
            snippet("Actions: single and multiple", DataListCode.ACTIONS) {
                dataList {
                    item {
                        cell { span(id = this@item.id) { +"Single actionable - primary content" } }
                        cell { +"Single actionable - secondary content" }
                        action {
                            pushButton(baseClass = "primary".modifier()) { +"Delete" }
                        }
                    }
                    item {
                        cell { span(id = this@item.id) { +"Multi actions - primary content" } }
                        cell { +"Multi actions - secondary content" }
                        actionWrapper {
                            defaultDropdown()
                        }
                    }
                }
            }
            snippet("Expandable", DataListCode.EXPANDABLE) {
                dataList {
                    arrayOf("Primary", "Secondary", "Tertiary").forEachIndexed { index, kind ->
                        item {
                            toggle()
                            cellIcon { icon("code-branch".fas()) }
                            cell {
                                div(id = this@item.id) { +"$kind content" }
                                span { +loremIpsum() }
                            }
                            cell { span { +loremIpsum() } }
                            cell { span { +loremIpsum() } }
                            actionWrapper {
                                defaultDropdown()
                            }
                            content(baseClass = classes {
                                +("no-padding".modifier() `when` (index == 2))
                            }) {
                                +loremIpsum(3)
                            }
                        }
                    }
                }
            }
            snippet("Selectable", "n/a") {
                dataList(selectable = true) {
                    item {
                        cell { span(id = this@item.id) { +"Single actionable - primary content" } }
                        cell { +"Single actionable - secondary content" }
                        action {
                            pushButton(baseClass = "primary".modifier()) { +"Delete" }
                        }
                    }
                    item {
                        cell { span(id = this@item.id) { +"Multi actions - primary content" } }
                        cell { +"Multi actions - secondary content" }
                        actionWrapper {
                            defaultDropdown()
                        }
                    }
                }
            }
            snippet("Store (single selection)", "n/a") {
                data class Demo(val id: String, val name: String)

                val demos = listOf(
                    Demo("foo", "Foo"),
                    Demo("bar", "Bar")
                )
                val store = storeOf(demos)
                val selection = storeOf<Demo?>(null)

                dataList(selectable = true) {
                    items(store, { it.id }, selection) { demo ->
                        item {
                            cell {
                                span(id = demo.id) { +demo.name }
                            }
                            cell { +loremIpsum() }
                            actionWrapper {
                                defaultDropdown()
                            }
                        }
                    }
                }
                section(baseClass = classes("flex".layout(), "mt-lg".util())) {
                    actionList {
                        for (demo in demos) {
                            item {
                                clickButton(link) { +"Select ${demo.name}" }.map { demo } handledBy selection.update
                            }
                        }
                        item {
                            clickButton(link) { +"Select none" }.map { null } handledBy selection.update
                        }
                    }
                    div(baseClass = "align-right".modifier()) {
                        +"Selected item: "
                        selection.data.map { it?.name ?: "Nothing" }.renderText()
                    }
                }
            }
            snippet("Store (multiple selections)", "n/a") {
                data class Demo(val id: String, val name: String)

                val demos = listOf(
                    Demo("foo", "Foo"),
                    Demo("bar", "Bar")
                )
                val store = storeOf(demos)
                val selection = storeOf<List<Demo>>(emptyList())

                dataList {
                    items(store, { it.id }, selection) { demo ->
                        item {
                            check()
                            cell {
                                span(id = demo.id) { +demo.name }
                            }
                            cell { +loremIpsum() }
                            actionWrapper {
                                defaultDropdown()
                            }
                        }
                    }
                }
                section(baseClass = classes("flex".layout(), "mt-lg".util())) {
                    actionList {
                        for (demo in demos) {
                            item {
                                clickButton(link) { +"Select ${demo.name}" }.map { selection.current + demo } handledBy selection.update
                            }
                        }
                        item {
                            clickButton(link) { +"Select all" }.map { demos } handledBy selection.update
                        }
                        item {
                            clickButton(link) { +"Select none" }.map { emptyList<Demo>() } handledBy selection.update
                        }
                    }
                    div(baseClass = "align-right".modifier()) {
                        +"Selected items: "
                        selection.data.map { demos -> demos.joinToString { it.name }.ifEmpty { "None" } }.renderText()
                    }
                }
            }
        }
    }
}

object DataListCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        // Just a fake item w/ a display function
        data class DisplayData(
            val id: String = Id.unique(),
            val content: DataListItem<DisplayData>.() -> Unit
        )

        val store: ItemsStore<DisplayData> = ItemsStore { it.id }
        store.addAll(listOf(
            DisplayData {
                dataListRow {
                    dataListContent {
                        dataListCell {
                            span { +"Primary content" }
                        }
                        dataListCell { +"Secondary content" }
                    }
                }
            },
            DisplayData {
                dataListRow {
                    dataListContent {
                        dataListCell("no-fill".modifier()) {
                            span { +"Secondary content (pf-m-no-fill)" }
                        }
                        dataListCell(classes("no-fill".modifier(), "align-right".modifier())) {
                            +"Secondary content (pf-m-align-right pf-m-no-fill)"
                        }
                    }
                }
            }
        ))
        
        dataList(store) {
            display {
                dataListItem(item = it, content = it.content) 
            }
        }
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

        val store: ItemsStore<DisplayData> = ItemsStore { it.id }
        store.addAll(listOf(
            DisplayData {
                dataListRow {
                    dataListContent {
                        dataListCell {
                            span { +"Primary content" }
                        }
                        dataListCell { +"Secondary content" }
                    }
                }
            },
            DisplayData {
                dataListRow {
                    dataListContent {
                        dataListCell("no-fill".modifier()) {
                            span { +"Secondary content (pf-m-no-fill)" }
                        }
                        dataListCell(classes("no-fill".modifier(), "align-right".modifier())) {
                            +"Secondary content (pf-m-align-right pf-m-no-fill)"
                        }
                    }
                }
            }
        ))
        
        dataList(store) {
            display { dataListItem(item = it, content = it.content) }
        }
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

        val store: ItemsStore<DisplayData> = ItemsStore { it.id }
        store.addAll(listOf(
            DisplayData {
                dataListRow {
                    dataListControl {
                        dataListCheckbox()
                    }
                    dataListContent {
                        dataListCell {
                            span { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        }
                        dataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        dataListCell { +"Tertiary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        dataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        dataListCell { +"More content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                    }
                    dataListAction {
                        div(baseClass = "data-list".component("action")) {
                            dropdown<String>(align = Align.RIGHT) {
                                kebabToggle()
                                items {
                                    item("Action")
                                    item("Disabled Action") {
                                        disabled = true
                                    }
                                    separator()
                                    item("Separated Action")
                                }
                            }
                        }
                    }
                }
            },
            DisplayData {
                dataListRow {
                    dataListControl {
                        dataListCheckbox()
                    }
                    dataListContent {
                        dataListCell {
                            span { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        }
                        dataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                    }
                    dataListAction("hidden-on-lg".modifier()) {
                        div(baseClass = "data-list".component("action")) {
                            dropdown<String>(align = Align.RIGHT) {
                                kebabToggle()
                                items {
                                    item("Action")
                                    item("Disabled Action") {
                                        disabled = true
                                    }
                                    separator()
                                    item("Separated Action")
                                }
                            }
                        }
                    }
                    dataListAction(classes("hidden".modifier(), "visible-on-lg".modifier())) {
                        pushButton(baseClass = "primary".modifier()) { +"Primary" }
                        pushButton(baseClass = "secondary".modifier()) { +"Secondary" }
                    }
                }
            },
            DisplayData {
                dataListRow {
                    dataListControl {
                        dataListCheckbox()
                    }
                    dataListContent {
                        dataListCell {
                            span { +"Primary content Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                        }
                        dataListCell { +"Secondary content. Dolor sit amet, consectetur adipisicing elit, sed do eiusmod." }
                    }
                    dataListAction("hidden-on-xl".modifier()) {
                        div(baseClass = "data-list".component("action")) {
                            dropdown<String>(align = Align.RIGHT) {
                                kebabToggle()
                                items {
                                    item("Action")
                                    item("Disabled Action") {
                                        disabled = true
                                    }
                                    separator()
                                    item("Separated Action")
                                }
                            }
                        }
                    }
                    dataListAction(classes("hidden".modifier(), "visible-on-xl".modifier())) {
                        pushButton(baseClass = "primary".modifier()) { +"Primary" }
                        pushButton(baseClass = "secondary".modifier()) { +"Secondary" }
                        pushButton(baseClass = "secondary".modifier()) { +"Secondary" }
                        pushButton(baseClass = "secondary".modifier()) { +"Secondary" }
                    }
                }
            }
        ))
        
        dataList(store) {
            display { dataListItem(item = it, content = it.content) }
        }
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

        val store: ItemsStore<DisplayData> = ItemsStore { it.id }
        store.addAll(listOf(
            DisplayData {
                dataListRow {
                    dataListContent {
                        dataListCell {
                            span { +"Single actionable Primary content" }
                        }
                        dataListCell { +"Single actionable Secondary content" }
                    }
                    dataListAction {
                        pushButton(baseClass = "primary".modifier()) { +"Delete" }
                    }
                }
            },
            DisplayData {
                dataListRow {
                    dataListContent {
                        dataListCell {
                            span { +"Multi actions Primary content" }
                        }
                        dataListCell { +"Multi actions Secondary content" }
                    }
                    dataListAction {
                        div(baseClass = "data-list".component("action")) {
                            dropdown<String>(align = Align.RIGHT) {
                                kebabToggle()
                                items {
                                    item("Action")
                                    item("Disabled Action") {
                                        disabled = true
                                    }
                                    separator()
                                    item("Separated Action")
                                }
                            }
                        }
                    }
                }
            }
        ))

        dataList(store) {
            display { dataListItem(item = it, content = it.content) }
        }
    }
}
"""

    //language=kotlin
    const val EXPANDABLE: String = """fun main() {
    render {
        // Just a fake item w/ two display function
        data class DisplayData(
            val id: String = Id.unique(),
            val content: List<DataListItem<DisplayData>.() -> Unit>
        )

        val store: ItemsStore<DisplayData> = ItemsStore { it.id }
        store.addAll(listOf(
            DisplayData(content = listOf(
                {
                    dataListRow {
                        dataListControl {
                            dataListToggle()
                        }
                        dataListContent {
                            dataListCell("icon".modifier()) {
                                icon("code-branch".fas())
                            }
                            dataListCell {
                                div { +"Primary content" }
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                            dataListCell {
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                            dataListCell {
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                        }
                        dataListAction {
                            div(baseClass = "data-list".component("action")) {
                                dropdown<String>(align = Align.RIGHT) {
                                    kebabToggle()
                                    items {
                                        item("Action")
                                        item("Disabled Action") {
                                            disabled = true
                                        }
                                        separator()
                                        item("Separated Action")
                                    }
                                }
                            }
                        }
                    }
                },
                {
                    dataListExpandableContent {
                        +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    }
                }
            )),
            DisplayData(content = listOf(
                {
                    dataListRow {
                        dataListControl {
                            dataListToggle()
                        }
                        dataListContent {
                            dataListCell("icon".modifier()) {
                                icon("code-branch".fas())
                            }
                            dataListCell {
                                div { +"Secondary content" }
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                            dataListCell {
                                span { +"Lorem ipsum dolor sit amet." }
                            }
                            dataListCell {
                                span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                            }
                        }
                        dataListAction {
                            div(baseClass = "data-list".component("action")) {
                                dropdown<String>(align = Align.RIGHT) {
                                    kebabToggle()
                                    items {
                                        item("Action")
                                        item("Disabled Action") {
                                            disabled = true
                                        }
                                        separator()
                                        item("Separated Action")
                                    }
                                }
                            }
                        }
                    }
                },
                {
                    dataListExpandableContent {
                        +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    }
                }
            )),
            DisplayData(
                content = listOf(
                    {
                        dataListRow {
                            dataListControl {
                                dataListToggle()
                            }
                            dataListContent {
                                dataListCell("icon".modifier()) {
                                    icon("code-branch".fas())
                                }
                                dataListCell {
                                    div { +"Tertiary content" }
                                    span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                                }
                                dataListCell {
                                    span { +"Lorem ipsum dolor sit amet." }
                                }
                                dataListCell {
                                    span { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit." }
                                }
                            }
                            dataListAction {
                                div(baseClass = "data-list".component("action")) {
                                    dropdown<String>(align = Align.RIGHT) {
                                        kebabToggle()
                                        items {
                                            item("Action")
                                            item("Disabled Action") {
                                                disabled = true
                                            }
                                            separator()
                                            item("Separated Action")
                                        }
                                    }
                                }
                            }
                        }
                    },
                    {
                        dataListExpandableContent {
                            +"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        }
                    }
                ))))

        dataList(store) {
            display {
                dataListItem(it) {
                    it.content.forEach { content ->
                        content(this)
                    }
                }
            }
        }
    }
}
"""
}
