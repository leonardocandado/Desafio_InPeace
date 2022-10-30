package com.desafios.desafioinpeace.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FriendApiService {
    companion object{
        private const val BASE_URL = "https://reqres.in/"
        private var retrofit : Retrofit? = null

        fun getInstance() : Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }

}