package io.daio.monzotile.api

import io.daio.monzotile.Config
import io.daio.monzotile.api.models.Balance
import io.daio.monzotile.network.HttpClient
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

class MonzoApi(private val httpClient: HttpClient) {

    private val BASE_URL = "https://api.monzo.com/"
    private val BALANCE_QUERY = "/balance?account_id=" + Config.ACCOUNT_ID
    private val ACCESS_TOKEN = "Bearer " + Config.ACCESS_TOKEN
    private val headers = mapOf(Pair("Authorization", ACCESS_TOKEN))

    fun getBalance(): Deferred<Balance> {
        return async(CommonPool) {
            val response = httpClient.getSync(BASE_URL + BALANCE_QUERY, headers)
            Balance.fromJsonString(response)
        }
    }
}