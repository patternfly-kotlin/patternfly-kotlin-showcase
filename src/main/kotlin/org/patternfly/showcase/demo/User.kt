package org.patternfly.showcase.demo

import dev.fritz2.binding.action
import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.HtmlElements
import dev.fritz2.dom.html.Img
import dev.fritz2.dom.html.TextElement
import dev.fritz2.dom.html.Ul
import dev.fritz2.dom.html.render
import dev.fritz2.dom.states
import dev.fritz2.dom.values
import dev.fritz2.remote.getBody
import dev.fritz2.remote.remote
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.patternfly.Align
import org.patternfly.Id
import org.patternfly.ItemStore
import org.patternfly.aria
import org.patternfly.bind
import org.patternfly.classes
import org.patternfly.fas
import org.patternfly.layout
import org.patternfly.modifier
import org.patternfly.pfBulkSelect
import org.patternfly.pfButton
import org.patternfly.pfCardActions
import org.patternfly.pfCardBody
import org.patternfly.pfCardCheckbox
import org.patternfly.pfCardFooter
import org.patternfly.pfCardHeader
import org.patternfly.pfCardHeaderMain
import org.patternfly.pfCardTitle
import org.patternfly.pfCardView
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
import org.patternfly.pfDropdown
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownToggleKebab
import org.patternfly.pfIcon
import org.patternfly.pfInputFormControl
import org.patternfly.pfInputGroup
import org.patternfly.pfItem
import org.patternfly.pfPagination
import org.patternfly.pfSection
import org.patternfly.pfSeparator
import org.patternfly.pfSortOptions
import org.patternfly.pfTitle
import org.patternfly.pfToolbar
import org.patternfly.pfToolbarContent
import org.patternfly.pfToolbarContentSection
import org.patternfly.pfToolbarItem
import org.patternfly.plusAssign
import org.patternfly.util
import org.w3c.dom.HTMLElement
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
    val json = remote("https://randomuser.me/api/?exc=id&results=$size")
        .acceptJson()
        .get()
        .getBody()
    val format = Json { isLenient = true }
    val randomUsers = format.decodeFromString(RandomUsers.serializer(), json)
    return randomUsers.results
}

// ------------------------------------------------------ components

fun HtmlElements.address(user: User): TextElement = address {
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
            pfIcon("envelope".fas(), "mr-sm".util())
            +user.email
        }
    }
    li {
        a {
            href = const("tel:${user.phone}")
            pfIcon("phone".fas(), "mr-sm".util())
            +user.phone
        }
    }
    li {
        a {
            href = const("tel:${user.cell}")
            pfIcon("mobile-alt".fas(), "mr-sm".util())
            +user.cell
        }
    }
}

fun HtmlElements.nat(user: User): Img = img {
    src = const("https://www.countryflags.io/${user.nat}/flat/32.png")
    with(domNode) {
        title = user.nat
        style.minWidth = "32px"
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

object UserDemo : Iterable<Tag<HTMLElement>> {
    private val userStore = ItemStore<User> { it.login.uuid }

    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
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
                    +". See User.kt for the code."
                }
            }
        })
        yield(render {
            pfSection {
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
                                            .map { Pair(domNode.id, { user: User -> user.match(it) }) }
                                            .handledBy(userStore.addFilter)
                                    }
                                    pfButton("control".modifier()) {
                                        pfIcon("search".fas())
                                    }
                                }
                            }
                            pfToolbarItem {
                                pfSortOptions(
                                    userStore, mapOf(
                                        "Last name" to compareBy { it.name.last },
                                        "First name" to compareBy { it.name.first },
                                        "User name" to compareBy { it.login.username },
                                        "Age" to compareBy { it.dob.age },
                                        "Nationality" to compareBy { it.nat },
                                    )
                                )
                            }
                            pfToolbarItem {
                                pfPagination(userStore)
                            }
                        }
                    }
                }
/*
                pfDataList(userStore) {
                    display = { user ->
                        {
                            pfDataListRow {
                                pfDataListControl {
                                    pfDataListToggle()
                                    pfDataListCheck()
                                }
                                pfDataListContent {
                                    pfDataListCell("icon".modifier()) {
                                        nat(user)
                                    }
                                    pfDataListCell {
                                        p(id = this@pfDataList.store.identifier(user)) { +user.name.toString() }
                                    }
                                    pfDataListCell("flex-4".modifier()) {
                                        p {
                                            pfIcon("user-alt".fas(), "mr-sm".util())
                                            +user.login.username
                                        }
                                        p { small { +"MD5: "; code { +user.login.md5 } } }
                                        p { small { +"SHA-1: "; code { +user.login.sha1 } } }
                                    }
                                }
                                pfDataListAction {
                                    pfButton("secondary".modifier()) { +"Edit" }
                                    pfButton("secondary".modifier()) { +"Remove" }
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
*/
                pfCardView(userStore, "mt-md".util()) {
                    display = { user ->
                        {
                            domNode.classList += "hoverable".modifier()
                            domNode.classList += "compact".modifier()
                            pfCardHeader {
                                nat(user)
                                span(baseClass = "ml-sm".util()) { +user.name.toString() }
                                pfCardActions {
                                    pfDropdown<String>(align = Align.RIGHT) {
                                        pfDropdownToggleKebab()
                                        pfDropdownItems {
                                            pfItem("Edit")
                                            pfItem("Remove")
                                        }
                                    }
                                    pfCardCheckbox {
                                        bind(userStore, user)
                                    }
                                }
                            }
                            pfCardBody(classes {
                                +"flex".layout()
                                +"inline-flex".modifier()
                                +"align-items-center".modifier()
                            }) {
                                photo(user); address(user);
                            }
                            pfCardFooter {
                                pfIcon("user-alt".fas(), "mr-sm".util())
                                +user.login.username
                            }
                        }
                    }
                }
            }
        })
        MainScope().launch {
            action(randomUsers(73)) handledBy userStore.addAll
        }

    }
}