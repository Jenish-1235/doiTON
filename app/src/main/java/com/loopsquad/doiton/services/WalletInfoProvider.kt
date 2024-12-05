package com.loopsquad.doiton.services

import android.util.Log
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class WalletInfoProvider {

    fun getAccountBalance(address:String){
        Log.d("Lavda", address)
        val client = OkHttpClient()
        val mediaType = MediaType.parse("application/json")
        val body = RequestBody.create(
            mediaType,
            "{\"id\":1,\"jsonrpc\":\"2.0\",\"method\":\"getAccountBalance\",\"params\":{\"address\":\"${address}\"}}"
        )
        val request = Request.Builder()
            .url("https://mainnet-rpc.tonxapi.com/v2/json-rpc/00b30977-4888-4744-9ad5-9d6b4fc6ee59")
            .post(body)
            .addHeader("accept", "application/json")
            .addHeader("content-type", "application/json")
            .build()

        val response = client.newCall(request).execute()


        Log.d("Bubbu", "getAccountBalance: ${response.body()?.string()}")

    }
}