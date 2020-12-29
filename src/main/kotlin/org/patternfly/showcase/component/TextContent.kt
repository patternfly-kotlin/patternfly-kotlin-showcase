@file:Suppress("SpellCheckingInspection")

package org.patternfly.showcase.component

import dev.fritz2.dom.html.RenderContext
import org.patternfly.pageSection
import org.patternfly.textContent

object TextContentComponent {
    val content: RenderContext.() -> Unit = {
        intro(
            title = "Text",
            text = "A text component can wrap any static HTML content you want to place on your page to provide correct formatting when using standard HTML tags.",
            designGuidelines = "https://www.patternfly.org/v4/components/text/design-guidelines"
        )
        pageSection {
            snippet("Basic", TextContentCode.BASIC) {
                textContent {
                    h1 { +"Hello world" }
                    p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla accumsan, metus ultrices eleifend gravida, nulla nunc varius lectus, nec rutrum justo nibh eu lectus. Ut vulputate semper dui. Fusce erat odio, sollicitudin vel erat vel, interdum mattis neque. Sub works as well!" }
                    h2 { +"Second level" }
                    p {
                        +"Curabitur accumsan turpis pharetra "
                        strong { +"augue tincidunt" }
                        +" blandit. Quisque condimentum maximus mi, sit amet commodo arcu rutrum id. Proin pretium urna vel cursus venenatis. Suspendisse potenti. Etiam mattis sem rhoncus lacus dapibus facilisis. Donec at dignissim dui. Ut et neque nisl."
                    }
                    ul {
                        li { +"In fermentum leo eu lectus mollis, quis dictum mi aliquet." }
                        li { +"Morbi eu nulla lobortis, lobortis est in, fringilla felis." }
                        li { +"Morbi eu nulla lobortis, lobortis est in, fringilla felis." }
                        li {
                            +"Aliquam nec felis in sapien venenatis viverra fermentum nec lectus."
                            ul {
                                li { +"In fermentum leo eu lectus mollis, quis dictum mi aliquet." }
                                li { +"Morbi eu nulla lobortis, lobortis est in, fringilla felis." }
                                li {
                                    +"Ut venenatis, nisl scelerisque."
                                    ol {
                                        li { +"Donec blandit a lorem id convallis." }
                                        li { +"Cras gravida arcu at diam gravida gravida." }
                                        li { +"Integer in volutpat libero." }
                                    }
                                }
                            }
                        }
                        li { +"Integer in volutpat libero." }
                    }
                    h3 { +"Third level" }
                    p {
                        +"Quisque ante lacus, malesuada ac auctor vitae, congue "
                        a {
                            +"non ante"
                            href("#")
                        }
                        +". Phasellus lacus ex, semper ac tortor nec, fringilla condimentum orci. Fusce eu rutrum tellus."
                    }
                    ol {
                        li { +"Donec blandit a lorem id convallis." }
                        li { +"Cras gravida arcu at diam gravida gravida." }
                        li { +"Integer in volutpat libero." }
                        li { +"Donec a diam tellus." }
                        li {
                            +"Etiam auctor nisl et."
                            ul {
                                li { +"Donec blandit a lorem id convallis." }
                                li { +"Cras gravida arcu at diam gravida gravida." }
                                li {
                                    +"Integer in volutpat libero."
                                    ol {
                                        li { +"Donec blandit a lorem id convallis." }
                                        li { +"Cras gravida arcu at diam gravida gravida." }
                                    }
                                }
                            }
                        }
                        li { +"Aenean nec tortor orci." }
                        li { +"Quisque aliquam cursus urna, non bibendum massa viverra eget." }
                        li { +"Vivamus maximus ultricies pulvinar." }
                    }
                    blockquote { +"Ut venenatis, nisl scelerisque sollicitudin fermentum, quam libero hendrerit ipsum, ut blandit est tellus sit amet turpis." }
                    p {
                        +"Quisque at semper enim, eu hendrerit odio. Etiam auctor nisl et "
                        em { +"justo sodales" }
                        +" elementum. Maecenas ultrices lacus quis neque consectetur, et lobortis nisi molestie."
                    }
                    hr {}
                    p { +"Sed sagittis enim ac tortor maximus rutrum. Nulla facilisi. Donec mattis vulputate risus in luctus. Maecenas vestibulum interdum commodo." }
                    dl {
                        dt { +"Web" }
                        dd { +"The part of the internet that contains websites and web pages" }
                        dt { +"HTML" }
                        dd { +"A markup language for creating web pages" }
                        dt { +"CSS" }
                        dd { +"A technology to make HTML look better" }
                    }
                    p {
                        +"Suspendisse egestas sapien non felis placerat elementum. Morbi tortor nisl, suscipit sed mi sit amet, mollis malesuada nulla. Nulla facilisi. Nullam ac erat ante."
                    }
                    h4 { +"Fourth level" }
                    p { +"Nulla efficitur eleifend nisi, sit amet bibendum sapien fringilla ac. Mauris euismod metus a tellus laoreet, at elementum ex efficitur." }
                    p { +"Maecenas eleifend sollicitudin dui, faucibus sollicitudin augue cursus non. Ut finibus eleifend arcu ut vehicula. Mauris eu est maximus est porta condimentum in eu justo. Nulla id iaculis sapien." }
                    small { +"Sometimes you need small text to display things like date created" }
                    p { +"Phasellus porttitor enim id metus volutpat ultricies. Ut nisi nunc, blandit sed dapibus at, vestibulum in felis. Etiam iaculis lorem ac nibh bibendum rhoncus. Nam interdum efficitur ligula sit amet ullamcorper. Etiam tristique, leo vitae porta faucibus, mi lacus laoreet metus, at cursus leo est vel tellus. Sed ac posuere est. Nunc ultricies nunc neque, vitae ultricies ex sodales quis. Aliquam eu nibh in libero accumsan pulvinar. Nullam nec nisl placerat, pretium metus vel, euismod ipsum. Proin tempor cursus nisl vel condimentum. Nam pharetra varius metus non pellentesque." }
                    h5 { +"Fifth level" }
                    p { +"Aliquam sagittis rhoncus vulputate. Cras non luctus sem, sed tincidunt ligula. Vestibulum at nunc elit. Praesent aliquet ligula mi, in luctus elit volutpat porta. Phasellus molestie diam vel nisi sodales, a eleifend augue laoreet. Sed nec eleifend justo. Nam et sollicitudin odio." }
                    h6 { +"Sixth level" }
                    p { +"Cras in nibh lacinia, venenatis nisi et, auctor urna. Donec pulvinar lacus sed diam dignissim, ut eleifend eros accumsan. Phasellus non tortor eros. Ut sed rutrum lacus. Etiam purus nunc, scelerisque quis enim vitae, malesuada ultrices turpis. Nunc vitae maximus purus, nec consectetur dui. Suspendisse euismod, elit vel rutrum commodo, ipsum tortor maximus dui, sed varius sapien odio vitae est. Etiam at cursus metus." }
                }
            }
        }
    }
}

object TextContentCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        textContent {
            h1 { +"Hello world" }
            p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla accumsan, metus ultrices eleifend gravida, nulla nunc varius lectus, nec rutrum justo nibh eu lectus. Ut vulputate semper dui. Fusce erat odio, sollicitudin vel erat vel, interdum mattis neque. Sub works as well!" }
            h2 { +"Second level" }
            p {
                +"Curabitur accumsan turpis pharetra "
                strong { +"augue tincidunt" }
                +" blandit. Quisque condimentum maximus mi, sit amet commodo arcu rutrum id. Proin pretium urna vel cursus venenatis. Suspendisse potenti. Etiam mattis sem rhoncus lacus dapibus facilisis. Donec at dignissim dui. Ut et neque nisl."
            }
            ul {
                li { +"In fermentum leo eu lectus mollis, quis dictum mi aliquet." }
                li { +"Morbi eu nulla lobortis, lobortis est in, fringilla felis." }
                li { +"Morbi eu nulla lobortis, lobortis est in, fringilla felis." }
                li {
                    +"Aliquam nec felis in sapien venenatis viverra fermentum nec lectus."
                    ul {
                        li { +"In fermentum leo eu lectus mollis, quis dictum mi aliquet." }
                        li { +"Morbi eu nulla lobortis, lobortis est in, fringilla felis." }
                        li {
                            +"Ut venenatis, nisl scelerisque."
                            ol {
                                li { +"Donec blandit a lorem id convallis." }
                                li { +"Cras gravida arcu at diam gravida gravida." }
                                li { +"Integer in volutpat libero." }
                            }
                        }
                    }
                }
                li { +"Integer in volutpat libero." }
            }
            h3 { +"Third level" }
            p {
                +"Quisque ante lacus, malesuada ac auctor vitae, congue "
                a {
                    +"non ante"
                    href("#")
                }
                +". Phasellus lacus ex, semper ac tortor nec, fringilla condimentum orci. Fusce eu rutrum tellus."
            }
            ol {
                li { +"Donec blandit a lorem id convallis." }
                li { +"Cras gravida arcu at diam gravida gravida." }
                li { +"Integer in volutpat libero." }
                li { +"Donec a diam tellus." }
                li {
                    +"Etiam auctor nisl et."
                    ul {
                        li { +"Donec blandit a lorem id convallis." }
                        li { +"Cras gravida arcu at diam gravida gravida." }
                        li {
                            +"Integer in volutpat libero."
                            ol {
                                li { +"Donec blandit a lorem id convallis." }
                                li { +"Cras gravida arcu at diam gravida gravida." }
                            }
                        }
                    }
                }
                li { +"Aenean nec tortor orci." }
                li { +"Quisque aliquam cursus urna, non bibendum massa viverra eget." }
                li { +"Vivamus maximus ultricies pulvinar." }
            }
            blockquote { +"Ut venenatis, nisl scelerisque sollicitudin fermentum, quam libero hendrerit ipsum, ut blandit est tellus sit amet turpis." }
            p {
                +"Quisque at semper enim, eu hendrerit odio. Etiam auctor nisl et "
                em { +"justo sodales" }
                +" elementum. Maecenas ultrices lacus quis neque consectetur, et lobortis nisi molestie."
            }
            hr {}
            p { +"Sed sagittis enim ac tortor maximus rutrum. Nulla facilisi. Donec mattis vulputate risus in luctus. Maecenas vestibulum interdum commodo." }
            dl {
                dt { +"Web" }
                dd { +"The part of the internet that contains websites and web pages" }
                dt { +"HTML" }
                dd { +"A markup language for creating web pages" }
                dt { +"CSS" }
                dd { +"A technology to make HTML look better" }
            }
            p {
                +"Suspendisse egestas sapien non felis placerat elementum. Morbi tortor nisl, suscipit sed mi sit amet, mollis malesuada nulla. Nulla facilisi. Nullam ac erat ante."
            }
            h4 { +"Fourth level" }
            p { +"Nulla efficitur eleifend nisi, sit amet bibendum sapien fringilla ac. Mauris euismod metus a tellus laoreet, at elementum ex efficitur." }
            p { +"Maecenas eleifend sollicitudin dui, faucibus sollicitudin augue cursus non. Ut finibus eleifend arcu ut vehicula. Mauris eu est maximus est porta condimentum in eu justo. Nulla id iaculis sapien." }
            small { +"Sometimes you need small text to display things like date created" }
            p { +"Phasellus porttitor enim id metus volutpat ultricies. Ut nisi nunc, blandit sed dapibus at, vestibulum in felis. Etiam iaculis lorem ac nibh bibendum rhoncus. Nam interdum efficitur ligula sit amet ullamcorper. Etiam tristique, leo vitae porta faucibus, mi lacus laoreet metus, at cursus leo est vel tellus. Sed ac posuere est. Nunc ultricies nunc neque, vitae ultricies ex sodales quis. Aliquam eu nibh in libero accumsan pulvinar. Nullam nec nisl placerat, pretium metus vel, euismod ipsum. Proin tempor cursus nisl vel condimentum. Nam pharetra varius metus non pellentesque." }
            h5 { +"Fifth level" }
            p { +"Aliquam sagittis rhoncus vulputate. Cras non luctus sem, sed tincidunt ligula. Vestibulum at nunc elit. Praesent aliquet ligula mi, in luctus elit volutpat porta. Phasellus molestie diam vel nisi sodales, a eleifend augue laoreet. Sed nec eleifend justo. Nam et sollicitudin odio." }
            h6 { +"Sixth level" }
            p { +"Cras in nibh lacinia, venenatis nisi et, auctor urna. Donec pulvinar lacus sed diam dignissim, ut eleifend eros accumsan. Phasellus non tortor eros. Ut sed rutrum lacus. Etiam purus nunc, scelerisque quis enim vitae, malesuada ultrices turpis. Nunc vitae maximus purus, nec consectetur dui. Suspendisse euismod, elit vel rutrum commodo, ipsum tortor maximus dui, sed varius sapien odio vitae est. Etiam at cursus metus." }
        }
    }
}
"""
}
