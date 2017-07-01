package io.daio.monzotile.api.models

import org.json.JSONException
import org.json.JSONObject
import java.text.NumberFormat
import java.util.*

data class Balance(val amount: Int,
                   val currency: String,
                   val amountString: String) {

    companion object {
        fun fromJsonString(json: String?): Balance = try {
            val jsonObj = JSONObject(json)
            val amount = jsonObj.optInt("balance", 0)
            val currency = jsonObj.getString("currency")
            val formatter = NumberFormat.getCurrencyInstance(Locale.UK)

            val formattedAmount = amount / 100.0

            Balance(amount, currency, formatter.format(formattedAmount))

        } catch (ex: JSONException) {
            Balance(0, "", "£0.00")
        }
    }
}
