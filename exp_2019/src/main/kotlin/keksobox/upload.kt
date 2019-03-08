package keksobox

import com.github.kittinunf.fuel.core.DataPart
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.httpUpload
import java.io.File

fun main(args: Array<String>) {
    val (req , resp, result) = "$host/api/image".httpUpload(Method.POST)
        .auth()
        .dataParts {req, url -> listOf(DataPart(
            File("horizon.jpg"), "imageFile", "")
        )}
        .responseString()

    println(result)
}