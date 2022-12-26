package br.com.vinicius_de_souza_carvalho.dr3_at

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.vinicius_de_souza_carvalho.dr3_at.api.Endpoint
import br.com.vinicius_de_souza_carvalho.dr3_at.util.NetworkUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var moeda : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moeda = findViewById(R.id.moeda)

        getCurrencies()

    }

    fun getCurrencies(){
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://cdn.jsdelivr.net/")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getCurrencies().enqueue(object : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                var data = mutableListOf<String>()

                response.body()?.keySet()?.iterator()?.forEach {
                    data.add(it)
                }
                    val adapter = ArrayAdapter(baseContext, android.R.layout.simple_spinner_dropdown_item,data)
                    moeda.adapter = adapter
            //println(data.count())
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("Não foi")

            }

        })
    }
}