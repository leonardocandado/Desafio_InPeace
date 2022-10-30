package com.desafios.desafioinpeace.service

import com.desafios.desafioinpeace.model.FriendResponse
import retrofit2.Call
import retrofit2.http.GET

interface FriendsRepository {

    @GET("api/users?page=2")
    fun getFriendList(): Call<FriendResponse>

}