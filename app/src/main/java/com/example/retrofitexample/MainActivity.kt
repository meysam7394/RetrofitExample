package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.retrofitexample.network.ApiClient
import com.example.retrofitexample.network.CharacterResponse
import com.example.retrofitexample.network.MainAdapter
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Use coroutine to make the network call,It allows you to perform network operations asynchronously without blocking the main thread, improving the responsiveness of your Android application.
        // we can also use Retrofit's enqueue): instead of Coroutine but Coroutine is better way and easy to write and read.
        lifecycleScope.launch {
            try {
                // Make the API call using suspend function
                val response = ApiClient.apiService.fetchCharacters("1")

                // Check if the response is successful
                if (response.isSuccessful) {
                    Log.d("character", "" + response.body())
                    val result= response.body()?.result
                    result?.let {
                        val adapter=MainAdapter(result)
                        val recyclerView=findViewById<RecyclerView>(R.id.charactersRv)
                        recyclerView?.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter=adapter
                    }
                } else {
                    // Log the error if the response is not successful
                    Log.e("character", "Error: ${response.code()}")
                }
            } catch (t: Throwable) {
                // Log the exception if an error occurs
                Log.e("character", "Error: ${t.message}", t)
            }
        }
    }
}


