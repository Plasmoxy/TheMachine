package keksobox

import com.github.kittinunf.fuel.core.Request

const val host = "http://localhost"

fun Request.auth() = authenticate(
    "sebo",
""
)