@file:Suppress("DuplicatedCode", "SpellCheckingInspection")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.Align
import org.patternfly.DataListItem
import org.patternfly.ItemStore
import org.patternfly.classes
import org.patternfly.component
import org.patternfly.dataList
import org.patternfly.dataListAction
import org.patternfly.dataListCell
import org.patternfly.dataListCheck
import org.patternfly.dataListContent
import org.patternfly.dataListControl
import org.patternfly.dataListExpandableContent
import org.patternfly.dataListItem
import org.patternfly.dataListRow
import org.patternfly.dataListToggle
import org.patternfly.dom.Id
import org.patternfly.dropdown
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.item
import org.patternfly.items
import org.patternfly.kebabToggle
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.pushButton
import org.patternfly.separator

object DataListComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Data list",
            prefix = "A ",
            key = "data list",
            text = " is used to display large data sets when you need a flexible layout or need to include interactive content like charts."
        )
        pageSection {
            h2 { +"Examples" }
            snippet("Basic", DataListCode.BASIC) {
                // Just a fake item w/ a display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: DataListItem<DisplayData>.() -> Unit
                )

                val store: ItemStore<DisplayData> = ItemStore { it.id }
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
            snippet("Compact", DataListCode.COMPACT) {
                // Just a fake item w/ a display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: DataListItem<DisplayData>.() -> Unit
                )

                val store: ItemStore<DisplayData> = ItemStore { it.id }
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
            snippet("Checkboxes, actions and additional cells", DataListCode.CHECKBOXES) {
                // Just a fake item w/ a display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: DataListItem<DisplayData>.() -> Unit
                )

                val store: ItemStore<DisplayData> = ItemStore { it.id }
                store.addAll(listOf(
                    DisplayData {
                        dataListRow {
                            dataListControl {
                                dataListCheck()
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
                                dataListCheck {}
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
                                dataListCheck {}
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
            snippet("Actions: single and multiple", DataListCode.ACTIONS) {
                // Just a fake item w/ a display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: DataListItem<DisplayData>.() -> Unit
                )

                val store: ItemStore<DisplayData> = ItemStore { it.id }
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
            snippet("Expandable", DataListCode.EXPANDABLE) {
                // Just a fake item w/ two display function
                data class DisplayData(
                    val id: String = Id.unique(),
                    val content: List<DataListItem<DisplayData>.() -> Unit>
                )

                val store: ItemStore<DisplayData> = ItemStore { it.id }
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

        val store: ItemStore<DisplayData> = ItemStore { it.id }
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

        val store: ItemStore<DisplayData> = ItemStore { it.id }
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

        val store: ItemStore<DisplayData> = ItemStore { it.id }
        store.addAll(listOf(
            DisplayData {
                dataListRow {
                    dataListControl {
                        dataListCheck()
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
                        dataListCheck {}
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
                        dataListCheck {}
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

        val store: ItemStore<DisplayData> = ItemStore { it.id }
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

        val store: ItemStore<DisplayData> = ItemStore { it.id }
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
