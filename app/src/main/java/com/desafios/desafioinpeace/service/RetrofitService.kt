package com.desafios.desafioinpeace.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Fonte de dados
interface RetrofitService {

    companion object{
        private val retrofitService : RetrofitService by lazy{
            val retrofitService = Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofitService.create(RetrofitService::class.java)
        }

        fun getInstance(): RetrofitService{
            return retrofitService
        }

    }
}