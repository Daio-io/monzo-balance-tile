package io.daio.monzotile.api

import io.daio.monzotile.Config
import io.daio.monzotile.api.models.Balance
import io.daio.monzotile.network.HttpClient
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import java.io.IOException

class MonzoApi(private val httpClient: HttpClient) {

    private val BASE_URL = "https://kotzo.herokuapp.com/"
    private val BALANCE_QUERY = "balance?apikey=${Config.API_KEY}"

    fun getBalance(): Deferred<Balance> {
        return async(CommonPool) {
            try {
                val response = httpClient.getSync(BASE_URL + BALANCE_QUERY)
                Balance.fromJsonString(response)

            } catch (ex: IOException) {
                Balance(0, "GBP", "£0.00", "£0.00")
            }
        }
    }
}