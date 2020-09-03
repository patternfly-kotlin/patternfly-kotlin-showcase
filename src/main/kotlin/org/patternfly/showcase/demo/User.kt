package org.patternfly.showcase.demo

import dev.fritz2.binding.const
import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.HtmlElements
import dev.fritz2.dom.html.Img
import dev.fritz2.dom.html.TextElement
import dev.fritz2.dom.html.Ul
import dev.fritz2.remote.getBody
import dev.fritz2.remote.remote
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.patternfly.fas
import org.patternfly.pfIcon
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
    fun match(query: String): Boolean {
        return name.first.toLowerCase().contains(query.toLowerCase()) ||
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
    val json = remote("https://randomuser.me/api/?results=$size")
        .acceptJson()
        .get()
        .getBody()
    val randomUsers = Json.decodeFromString(RandomUsers.serializer(), json)
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
        a {
            href = const("tel:${user.phone}")
            pfIcon("phone".fas(), "mr-sm".util())
            +user.phone
        }
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
