package com.example.retrofitexample.network

import android.telecom.Call
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response // Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    // the base character url that we are gonna use it.
    private val BASE_URL= "https://rickandmortyapi.com/api/"
    // we have to create a variable for moshi builder
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    //here we create the moshi
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder() // build sth with retrofit
            .baseUrl(BASE_URL) // use the base url
            .addConverterFactory(MoshiConverterFactory // add converter factory and using our convertor moshi factory
            .create(moshi)).build() //which create using our moshi and then build it!
        //and basically it create us the url to which will get the data from api
    }
    // instance of api service inside our object client.
    val apiService:ApiService by lazy { // api service is going to load lazily
        retrofit.create(ApiService::class.java) // we use the above retrofit in order  to create us an Api service. it allow us to use the api service to get our characters.
    }

}
interface ApiService {
    // we creat the get which give us the character
    @GET("character")
    // we have a function that fetch the query and we quering the page. and we call get the charchter response.
    suspend fun fetchCharacters(@Query("page") page: String): Response<CharacterResponse>
}