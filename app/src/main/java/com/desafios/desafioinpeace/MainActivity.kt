package com.desafios.desafioinpeace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafios.desafioinpeace.adapter.FriendsAdapter
import com.desafios.desafioinpeace.databinding.ActivityMainBinding
import com.desafios.desafioinpeace.databinding.ToolbarBinding
import com.desafios.desafioinpeace.model.Friend
import com.desafios.desafioinpeace.model.FriendResponse
import com.desafios.desafioinpeace.service.FriendApiService
import com.desafios.desafioinpeace.service.FriendsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarBinding: ToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerViewFriends = binding.recyclerViewAmigos
        //Define a lista na vertical
        recyclerViewFriends.layoutManager = LinearLayoutManager(this)
        getFriendData { friend: List<Friend> ->
            recyclerViewFriends.adapter = FriendsAdapter(friend)
        }


    }

    private fun getFriendData(callback: (List<Friend>) -> Unit) {
        val apiService = FriendApiService.getInstance().create(FriendsRepository::class.java)
        apiService.getFriendList().enqueue(object : Callback<FriendResponse> {
            override fun onFailure(call: Call<FriendResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<FriendResponse>, response: Response<FriendResponse>) {
                return callback(response.body()!!.data)
            }

        })
    }

    private fun searchFriend (){
        var search = toolbarBinding.textSearch.toString()



    }



}