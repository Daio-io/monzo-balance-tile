package io.daio.monzotile.network

interface HttpClient {
    fun getSync(url: String, headers: Map<String, String>? = null): String?
}