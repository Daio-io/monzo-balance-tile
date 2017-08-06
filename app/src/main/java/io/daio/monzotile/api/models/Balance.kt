package io.daio.monzotile.api.models

import org.json.JSONException
import org.json.JSONObject

data class Balance(val amount: Int,
                   val currency: String,
                   val amountString: String,
                   val spendString: String) {

    companion object {
        fun fromJsonString(json: String?): Balance = try {
            val jsonObj = JSONObject(json)

            Balance(0, "GBP", jsonObj.getString("balance"), jsonObj.getString("spend"))

        } catch (ex: JSONException) {
            Balance(0, "", "£0.00", "£0.00")
        }
    }
}
