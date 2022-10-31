package com.desafios.desafioinpeace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.desafios.desafioinpeace.adapter.FriendsAdapter
import com.desafios.desafioinpeace.databinding.ActivityMainBinding
import com.desafios.desafioinpeace.model.Friend
import com.desafios.desafioinpeace.model.FriendResponse
import com.desafios.desafioinpeace.service.FriendApiService
import com.desafios.desafioinpeace.service.FriendsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale.filter


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewFriends: RecyclerView
    lateinit var adapter: FriendsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewFriends = binding.recyclerViewAmigos
        //Define a lista na vertical
        recyclerViewFriends.layoutManager = LinearLayoutManager(this)

        getFriendData { friend: List<Friend> ->
            adapter = FriendsAdapter(friend)
            recyclerViewFriends.adapter = adapter
        }
        searchFriend()
    }

    private fun getFriendData(callback: (List<Friend>) -> Unit) {
        val apiService = FriendApiService.getInstance().create(FriendsRepository::class.java)
        apiService.getFriendList().enqueue(object : Callback<FriendResponse> {
            override fun onFailure(call: Call<FriendResponse>, t: Throwable) {
                //implementar erro
            }
            override fun onResponse(
                call: Call<FriendResponse>,
                response: Response<FriendResponse>
            ) {
                return callback(response.body()!!.data)
            }

        })
    }

    private fun searchFriend() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }

        })
    }

}