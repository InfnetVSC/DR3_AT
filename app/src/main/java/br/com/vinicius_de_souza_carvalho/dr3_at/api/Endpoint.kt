package br.com.vinicius_de_souza_carvalho.dr3_at.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET(value = "/gh/fawazahmed0/currency-api@1/latest/currencies.json")
    //@GET(value = "/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/countries.json")
    fun getCurrencies() : Call<JsonObject>

    @GET(value = "/gh/fawazahmed0/currency-api@1/latest/currencies.json")
    //@GET(value = "/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/countries.json")
    fun getCurrencyRate(@Path(value = "from", encoded = true) from : String, @Path(value = "to", encoded = true) to : String) :Call<JsonObject>
}