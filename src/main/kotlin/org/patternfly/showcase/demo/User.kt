package org.patternfly.showcase.demo

import dev.fritz2.binding.action
import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.HtmlElements
import dev.fritz2.dom.html.Img
import dev.fritz2.dom.html.TextElement
import dev.fritz2.dom.html.Ul
import dev.fritz2.dom.values
import dev.fritz2.elemento.Id
import dev.fritz2.elemento.aria
import dev.fritz2.elemento.elements
import dev.fritz2.elemento.hide
import dev.fritz2.elemento.plusAssign
import dev.fritz2.elemento.show
import dev.fritz2.remote.getBody
import dev.fritz2.remote.remote
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.patternfly.Align
import org.patternfly.ComponentType
import org.patternfly.ItemStore
import org.patternfly.SortInfo
import org.patternfly.classes
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfBulkSelect
import org.patternfly.pfButton
import org.patternfly.pfCard
import org.patternfly.pfCardActions
import org.patternfly.pfCardBody
import org.patternfly.pfCardCheckbox
import org.patternfly.pfCardFooter
import org.patternfly.pfCardHeader
import org.patternfly.pfCardView
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
import org.patternfly.pfDataTable
import org.patternfly.pfDataTableActionColumn
import org.patternfly.pfDataTableColumn
import org.patternfly.pfDataTableColumns
import org.patternfly.pfDataTableSelectColumn
import org.patternfly.pfDataTableSimpleColumn
import org.patternfly.pfDataTableToggleColumn
import org.patternfly.pfDropdown
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownToggleKebab
import org.patternfly.pfIcon
import org.patternfly.pfInputFormControl
import org.patternfly.pfInputGroup
import org.patternfly.pfItem
import org.patternfly.pfPagination
import org.patternfly.pfSection
import org.patternfly.pfSortOptions
import org.patternfly.pfTitle
import org.patternfly.pfToolbar
import org.patternfly.pfToolbarContent
import org.patternfly.pfToolbarContentSection
import org.patternfly.pfToolbarGroup
import org.patternfly.pfToolbarItem
import org.patternfly.util
import kotlin.js.Date

// ------------------------------------------------------ data

@Serializable
data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: DateOfBirth,
    val registered: DateOfBirth,
    val phone: String,
    val cell: String,
    val picture: Picture,
    val nat: String
) {
    fun match(query: String): Boolean = if (query.isEmpty()) true else {
        name.first.toLowerCase().contains(query.toLowerCase()) ||
                name.last.toLowerCase().contains(query.toLowerCase()) ||
                email.toLowerCase().contains(query.toLowerCase()) ||
                login.username.toLowerCase().contains(query.toLowerCase())
    }
}

@Serializable
data class Name(val title: String, val first: String, val last: String) {
    override fun toString(): String = "$first $last"
}

@Serializable
data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    var coordinates: Coordinates,
    val timezone: Timezone
)

@Serializable
data class Street(val name: String, val number: Int)

@Serializable
data class Coordinates(val latitude: String, val longitude: String)

@Serializable
data class Timezone(val offset: String, val description: String)

@Serializable
data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

@Serializable
data class DateOfBirth(val date: String, val age: Int) {
    fun asDate() = Date(Date.parse(date))
}

@Serializable
data class Picture(val large: String, val medium: String, val thumbnail: String)

@Serializable
internal data class RandomUsers(val results: List<User>, val info: Info)

@Serializable
internal data class Info(val seed: String, val results: Int, val page: Int, val version: String)

suspend fun randomUsers(size: Int = 123): List<User> {
    val payload = remote("https://randomuser.me/api/?exc=id&results=$size")
        .acceptJson()
        .get()
        .getBody()
    val json = Json { isLenient = true }
    val randomUsers = json.decodeFromString<RandomUsers>(payload)
    return randomUsers.results
}

// ------------------------------------------------------ components

fun HtmlElements.address(user: User, content: TextElement.() -> Unit = {}): TextElement = address {
    content.invoke(this)
    +"${user.location.street.name} ${user.location.street.number}"
    br { }
    +"${user.location.postcode} ${user.location.city}"
    br { }
    +"${user.location.state} ${user.nat}"
    a(baseClass = "ml-sm".util()) {
        href = const(googleMaps(user.location))
        target = const("map")
        pfIcon("map-marked-alt".fas())
    }
}

fun HtmlElements.contact(user: User): Ul = ul {
    li {
        a {
            href = const("mailto:${user.email}")
            pfIcon("envelope".fas(), baseClass = "mr-sm".util())
            +user.email
        }
    }
    li {
        a {
            href = const("tel:${user.phone}")
            pfIcon("phone".fas(), baseClass = "mr-sm".util())
            +user.phone
        }
    }
    li {
        a {
            href = const("tel:${user.cell}")
            pfIcon("mobile-alt".fas(), baseClass = "mr-sm".util())
            +user.cell
        }
    }
}

fun HtmlElements.nat(user: User): Img = img(baseClass = "sc-user-nat") {
    src = const("https://www.countryflags.io/${user.nat}/flat/32.png")
    with(domNode) {
        title = user.nat
    }
}

fun HtmlElements.photo(user: User): Div = div(baseClass = "sc-user-photo-75") {
    img {
        src = const(user.picture.medium)
    }
}

private fun googleMaps(location: Location): String =
    "https://www.google.com/maps/search/?api=1&query=${location.coordinates.latitude},${location.coordinates.longitude}"

// ------------------------------------------------------ UI

class UserDemo {
    private val cardViewId = Id.unique(ComponentType.CardView.id)
    private val dataListId = Id.unique(ComponentType.DataList.id)
    private val dataTableId = Id.unique(ComponentType.DataTable.id)
    private val dataViewIds = setOf(cardViewId, dataListId, dataTableId)
    private val userStore = ItemStore<User> { it.login.uuid }
    private val sortInfos = linkedMapOf<String, SortInfo<User>>(
        "last-name" to SortInfo("last-name", "Last name", compareBy { it.name.last }),
        "first-name" to SortInfo("first-name", "First name", compareBy { it.name.first }),
        "user-name" to SortInfo("user-name", "User name", compareBy { it.login.username }),
        "age" to SortInfo("age", "Age", compareBy { it.dob.age }),
        "nat" to SortInfo("nat", "Nationality", compareBy { it.nat }),
    )

    val elements = elements {
        pfSection {
            pfTitle { +"User Demo" }
            p {
                +"A list of 73 random users provided by "
                a {
                    +"https://randomuser.me"
                    href = const("https://randomuser.me")
                    target = const("ext")
                }
                +". Flags generated with "
                a {
                    +"https://www.countryflags.io"
                    href = const("https://www.countryflags.io")
                    target = const("ext")
                }
                +". See "
                a {
                    +"User.kt"
                    href =
                        const("https://github.com/patternfly-kotlin/patternfly-fritz2-showcase/blob/master/src/main/kotlin/org/patternfly/showcase/demo/User.kt")
                    target = const("ext")
                }
                +" for the code."
            }
        }
        pfSection(baseClass = "light".modifier()) {
            pfToolbar {
                pfToolbarContent {
                    pfToolbarContentSection {
                        pfToolbarItem {
                            pfBulkSelect(userStore)
                        }
                        pfToolbarItem {
                            pfInputGroup {
                                pfInputFormControl(id = Id.unique("usr", "flt")) {
                                    type = const("search")
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
                                pfButton("control".modifier()) {
                                    pfIcon("search".fas())
                                }
                            }
                        }
                        pfToolbarItem {
                            pfSortOptions(userStore, sortInfos.values.toList())
                        }
                        pfToolbarGroup("icon-button-group".modifier()) {
                            pfToolbarItem {
                                pfButton("plain".modifier()) {
                                    pfIcon("address-card".fas())
                                    domNode.onclick = { show(cardViewId) }
                                }
                            }
                            pfToolbarItem {
                                pfButton("plain".modifier()) {
                                    pfIcon("list".fas())
                                    domNode.onclick = { show(dataListId) }
                                }
                            }
                            pfToolbarItem {
                                pfButton("plain".modifier()) {
                                    pfIcon("table".fas())
                                    domNode.onclick = { show(dataTableId) }
                                }
                            }
                        }
                        pfToolbarItem {
                            pfPagination(itemStore = userStore, compact = true)
                        }
                    }
                }
            }
            pfCardView(userStore, id = cardViewId) {
                display = { user ->
                    pfCard(user, baseClass = classes {
                        +"hoverable".modifier()
                        +"compact".modifier()
                        +"flat".modifier()
                        +"sc-user-card"
                    }) {
                        pfCardHeader {
                            nat(user)
                            span(baseClass = classes("ml-sm".util(), "sc-user-card__title")) {
                                +user.name.toString()
                            }
                            pfCardActions {
                                pfDropdown<String>(align = Align.RIGHT) {
                                    pfDropdownToggleKebab()
                                    pfDropdownItems {
                                        pfItem("Edit")
                                        pfItem("Remove")
                                    }
                                }
                                pfCardCheckbox()
                            }
                        }
                        pfCardBody(baseClass = classes {
                            +"flex".layout()
                            +"inline-flex".modifier()
                            +"align-items-center".modifier()
                        }) {
                            photo(user)
                            address(user) {
                                domNode.classList += "sc-user-card__address"
                            }
                        }
                        pfCardFooter {
                            pfIcon(iconClass = "user-alt".fas(), baseClass = "mr-sm".util())
                            +user.login.username
                        }
                    }
                }
            }
            pfDataList(userStore, id = dataListId) {
                display = { user ->
                    pfDataListItem(user) {
                        pfDataListRow {
                            pfDataListControl {
                                pfDataListToggle()
                                pfDataListCheck()
                            }
                            pfDataListContent {
                                pfDataListCell(baseClass = "icon".modifier()) {
                                    nat(user)
                                }
                                pfDataListCell {
                                    p(id = userStore.identifier(user)) { +user.name.toString() }
                                }
                                pfDataListCell("flex-4".modifier()) {
                                    p {
                                        pfIcon("user-alt".fas(), baseClass = "mr-sm".util())
                                        +user.login.username
                                    }
                                    p { small { +"MD5: "; code { +user.login.md5 } } }
                                    p { small { +"SHA-1: "; code { +user.login.sha1 } } }
                                }
                            }
                            pfDataListAction {
                                pfButton(baseClass = "secondary".modifier()) { +"Edit" }
                                pfButton(baseClass = "secondary".modifier()) { +"Remove" }
                            }
                        }
                        pfDataListExpandableContent {
                            pfDataListExpandableContentBody {
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
            pfDataTable(userStore, id = dataTableId) {
                pfDataTableColumns {
                    pfDataTableToggleColumn(baseClass = classes {
                        +"flex".layout()
                        +"align-items-center".modifier()
                        +"space-items-2xl".modifier()
                    }) { user ->
                        {
                            photo(user); address(user); contact(user)
                        }
                    }
                    pfDataTableSelectColumn()
                    pfDataTableColumn("First name") {
                        hasId = true
                        sortInfo = sortInfos["first-name"]
                        headerBaseClass = "with-25".modifier()
                        cellDisplay = { user ->
                            { +user.name.first }
                        }
                    }
                    pfDataTableColumn("Last name") {
                        sortInfo = sortInfos["last-name"]
                        headerBaseClass = "with-25".modifier()
                        cellDisplay = { user ->
                            { +user.name.last }
                        }
                    }
                    pfDataTableColumn("Birthday") {
                        sortInfo = SortInfo("birthday", "Birthday", compareBy { it.dob.date })
                        cellDisplay = { user ->
                            { +user.dob.asDate().toLocaleDateString("en") }
                        }
                    }
                    pfDataTableSimpleColumn("Registered") { user ->
                        {
                            custom("relative-time") {
                                attr("datetime", user.registered.date)
                            }
                        }
                    }
                    pfDataTableActionColumn {
                        {
                            pfDropdown<String>(align = Align.RIGHT) {
                                pfDropdownToggleKebab()
                                pfDropdownItems {
                                    pfItem("Edit")
                                    pfItem("Remove")
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
            show(cardViewId)
            action(randomUsers(73)) handledBy userStore.addAll
        }
    }

    private fun show(id: String) {
        dataViewIds
            .filterNot { it == id }
            .forEach { document.getElementById(it)?.hide() }
        document.getElementById(id)?.show()
    }
}
