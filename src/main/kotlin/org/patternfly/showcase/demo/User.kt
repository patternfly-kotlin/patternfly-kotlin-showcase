package org.patternfly.showcase.demo

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.Img
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.html.TextElement
import dev.fritz2.dom.html.Ul
import dev.fritz2.dom.values
import dev.fritz2.elemento.Id
import dev.fritz2.elemento.aria
import dev.fritz2.elemento.plusAssign
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.patternfly.Align
import org.patternfly.ButtonVariation.control
import org.patternfly.ButtonVariation.plain
import org.patternfly.ButtonVariation.secondary
import org.patternfly.ItemStore
import org.patternfly.SortInfo
import org.patternfly.bulkSelect
import org.patternfly.card
import org.patternfly.cardActions
import org.patternfly.cardBody
import org.patternfly.cardCheckbox
import org.patternfly.cardFooter
import org.patternfly.cardHeader
import org.patternfly.cardView
import org.patternfly.classes
import org.patternfly.clickButton
import org.patternfly.dataList
import org.patternfly.dataListAction
import org.patternfly.dataListCell
import org.patternfly.dataListCheck
import org.patternfly.dataListContent
import org.patternfly.dataListControl
import org.patternfly.dataListExpandableContent
import org.patternfly.dataListExpandableContentBody
import org.patternfly.dataListItem
import org.patternfly.dataListRow
import org.patternfly.dataListToggle
import org.patternfly.dataTable
import org.patternfly.dataTableActionColumn
import org.patternfly.dataTableColumn
import org.patternfly.dataTableColumns
import org.patternfly.dataTableSelectColumn
import org.patternfly.dataTableSimpleColumn
import org.patternfly.dataTableToggleColumn
import org.patternfly.dropdown
import org.patternfly.dropdownItems
import org.patternfly.dropdownKebabToggle
import org.patternfly.fas
import org.patternfly.icon
import org.patternfly.inputFormControl
import org.patternfly.inputGroup
import org.patternfly.item
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pageSection
import org.patternfly.pagination
import org.patternfly.pushButton
import org.patternfly.showcase.data.Location
import org.patternfly.showcase.data.User
import org.patternfly.showcase.data.randomUsers
import org.patternfly.showcase.demo.UserDemo.DataComponent.CARD
import org.patternfly.showcase.demo.UserDemo.DataComponent.LIST
import org.patternfly.showcase.demo.UserDemo.DataComponent.TABLE
import org.patternfly.sortOptions
import org.patternfly.title
import org.patternfly.toolbar
import org.patternfly.toolbarContent
import org.patternfly.toolbarContentSection
import org.patternfly.toolbarGroup
import org.patternfly.toolbarItem
import org.patternfly.util

fun RenderContext.address(user: User, content: TextElement.() -> Unit = {}): TextElement = address {
    content(this)
    +"${user.location.street.name} ${user.location.street.number}"
    br { }
    +"${user.location.postcode} ${user.location.city}"
    br { }
    +"${user.location.state} ${user.nat}"
    a(baseClass = "ml-sm".util()) {
        href(googleMaps(user.location))
        target("map")
        icon("map-marked-alt".fas())
    }
}

fun RenderContext.contact(user: User): Ul = ul {
    li {
        a {
            href("mailto:${user.email}")
            icon("envelope".fas(), baseClass = "mr-sm".util())
            +user.email
        }
    }
    li {
        a {
            href("tel:${user.phone}")
            icon("phone".fas(), baseClass = "mr-sm".util())
            +user.phone
        }
    }
    li {
        a {
            href("tel:${user.cell}")
            icon("mobile-alt".fas(), baseClass = "mr-sm".util())
            +user.cell
        }
    }
}

fun RenderContext.nat(user: User): Img = img(baseClass = "sc-user-nat") {
    src("https://www.countryflags.io/${user.nat}/flat/32.png")
    with(domNode) {
        title = user.nat
    }
}

fun RenderContext.photo(user: User): Div = div(baseClass = "sc-user-photo-75") {
    img {
        src(user.picture.medium)
    }
}

fun googleMaps(location: Location): String =
    "https://www.google.com/maps/search/?api=1&query=${location.coordinates.latitude},${location.coordinates.longitude}"

class UserDemo {
    internal enum class DataComponent { CARD, LIST, TABLE }

    private val activeComponent = storeOf(CARD)
    private val userStore = ItemStore<User> { it.login.uuid }
    private val sortInfos = linkedMapOf<String, SortInfo<User>>(
        "last-name" to SortInfo("last-name", "Last name", compareBy { it.name.last }),
        "first-name" to SortInfo("first-name", "First name", compareBy { it.name.first }),
        "user-name" to SortInfo("user-name", "User name", compareBy { it.login.username }),
        "age" to SortInfo("age", "Age", compareBy { it.dob.age }),
        "nat" to SortInfo("nat", "Nationality", compareBy { it.nat }),
    )

    val content: RenderContext.() -> Unit = {
        pageSection {
            title { +"User Demo" }
            p {
                +"A list of 73 random users provided by "
                a {
                    +"https://randomuser.me"
                    href("https://randomuser.me")
                    target("ext")
                }
                +". Flags generated with "
                a {
                    +"https://www.countryflags.io"
                    href("https://www.countryflags.io")
                    target("ext")
                }
                +". See "
                a {
                    +"User.kt"
                    href("https://github.com/patternfly-kotlin/patternfly-fritz2-showcase/blob/master/src/main/kotlin/org/patternfly/showcase/demo/User.kt")
                    target("ext")
                }
                +" for the code."
            }
        }
        pageSection(baseClass = "light".modifier()) {
            toolbar {
                toolbarContent {
                    toolbarContentSection {
                        toolbarItem {
                            bulkSelect(userStore)
                        }
                        toolbarItem {
                            inputGroup {
                                inputFormControl(id = Id.unique("usr", "flt")) {
                                    type("search")
                                    aria["invalid"] = false
                                    changes.values()
                                        .filter { it.isEmpty() }
                                        .map { domNode.id }
                                        .handledBy(userStore.removeFilter)
                                    changes.values()
                                        .filter { it.isNotEmpty() }
                                        .map { domNode.id to { user: User -> user.match(it) } }
                                        .handledBy(userStore.addFilter)
                                }
                                pushButton(control) {
                                    icon("search".fas())
                                }
                            }
                        }
                        toolbarItem {
                            sortOptions(userStore, sortInfos.values.toList())
                        }
                        toolbarGroup("icon-button-group".modifier()) {
                            toolbarItem {
                                clickButton(plain) {
                                    icon("address-card".fas())
                                }.map { CARD } handledBy activeComponent.update
                            }
                            toolbarItem {
                                clickButton(plain) {
                                    icon("list".fas())
                                }.map { LIST } handledBy activeComponent.update
                            }
                            toolbarItem {
                                clickButton(plain) {
                                    icon("table".fas())
                                }.map { TABLE } handledBy activeComponent.update
                            }
                        }
                        toolbarItem {
                            pagination(itemStore = userStore, compact = true)
                        }
                    }
                }
            }
            cardView(userStore) {
                classMap(activeComponent.data.map { mapOf("display-none".util() to (it == CARD)) })
                display { user ->
                    card(user, baseClass = classes {
                        +"hoverable".modifier()
                        +"compact".modifier()
                        +"flat".modifier()
                        +"sc-user-card"
                    }) {
                        cardHeader {
                            nat(user)
                            span(baseClass = classes("ml-sm".util(), "sc-user-card__title")) {
                                +user.name.toString()
                            }
                            cardActions {
                                dropdown<String>(align = Align.RIGHT) {
                                    dropdownKebabToggle()
                                    dropdownItems {
                                        item("Edit")
                                        item("Remove")
                                    }
                                }
                                cardCheckbox()
                            }
                        }
                        cardBody(baseClass = classes {
                            +"flex".layout()
                            +"inline-flex".modifier()
                            +"align-items-center".modifier()
                        }) {
                            photo(user)
                            address(user) {
                                domNode.classList += "sc-user-card__address"
                            }
                        }
                        cardFooter {
                            icon(iconClass = "user-alt".fas(), baseClass = "mr-sm".util())
                            +user.login.username
                        }
                    }
                }
            }
            dataList(userStore) {
                classMap(activeComponent.data.map { mapOf("display-none".util() to (it == LIST)) })
                display { user ->
                    dataListItem(user) {
                        dataListRow {
                            dataListControl {
                                dataListToggle()
                                dataListCheck()
                            }
                            dataListContent {
                                dataListCell(baseClass = "icon".modifier()) {
                                    nat(user)
                                }
                                dataListCell {
                                    p(id = userStore.identifier(user)) { +user.name.toString() }
                                }
                                dataListCell("flex-4".modifier()) {
                                    p {
                                        icon("user-alt".fas(), baseClass = "mr-sm".util())
                                        +user.login.username
                                    }
                                    p { small { +"MD5: "; code { +user.login.md5 } } }
                                    p { small { +"SHA-1: "; code { +user.login.sha1 } } }
                                }
                            }
                            dataListAction {
                                pushButton(secondary) { +"Edit" }
                                pushButton(secondary) { +"Remove" }
                            }
                        }
                        dataListExpandableContent {
                            dataListExpandableContentBody {
                                div(baseClass = classes {
                                    +"flex".layout()
                                    +"align-items-center".modifier()
                                    +"space-items-2xl".modifier()
                                }) {
                                    photo(user); address(user); contact(user)
                                }
                            }
                        }
                    }
                }
            }
            dataTable(userStore) {
                classMap(activeComponent.data.map { mapOf("display-none".util() to (it == TABLE)) })
                dataTableColumns {
                    dataTableToggleColumn(baseClass = classes {
                        +"flex".layout()
                        +"align-items-center".modifier()
                        +"space-items-2xl".modifier()
                    }) { user ->
                        {
                            photo(user); address(user); contact(user)
                        }
                    }
                    dataTableSelectColumn()
                    dataTableColumn("First name") {
                        hasId = true
                        sortInfo = sortInfos["first-name"]
                        headerBaseClass = "with-25".modifier()
                        cellDisplay = { user ->
                            { +user.name.first }
                        }
                    }
                    dataTableColumn("Last name") {
                        sortInfo = sortInfos["last-name"]
                        headerBaseClass = "with-25".modifier()
                        cellDisplay = { user ->
                            { +user.name.last }
                        }
                    }
                    dataTableColumn("Birthday") {
                        sortInfo = SortInfo("birthday", "Birthday", compareBy { it.dob.date })
                        cellDisplay = { user ->
                            { +user.dob.asDate().toLocaleDateString("en") }
                        }
                    }
                    dataTableSimpleColumn("Registered") { user ->
                        {
                            custom("relative-time") {
                                attr("datetime", user.registered.date)
                            }
                        }
                    }
                    dataTableActionColumn {
                        {
                            dropdown<String>(align = Align.RIGHT) {
                                dropdownKebabToggle()
                                dropdownItems {
                                    item("Edit")
                                    item("Remove")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    init {
        MainScope().launch {
            userStore.addAll(randomUsers(73))
        }
    }
}
