package com.ritika.taskapi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    object retrofit {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Utility.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    val api : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    }
