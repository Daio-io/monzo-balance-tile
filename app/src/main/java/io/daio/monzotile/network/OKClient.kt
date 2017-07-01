package io.daio.monzotile.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit

class OKClient : HttpClient {

    companion object {
        @JvmStatic val instance by lazy { OKClient() }
    }

    private val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

    override fun getSync(url: String, headers: Map<String, String>?): String? {
        val request = Request.Builder()
                .url(url)
                .get()

        headers?.forEach{ request.addHeader(it.key, it.value) }

        val response = client.newCall(request.build()).execute()

        if (response.isSuccessful) {
            return response.body()?.string()
        } else {
            throw IOException(response.body()?.string())
        }
    }
}