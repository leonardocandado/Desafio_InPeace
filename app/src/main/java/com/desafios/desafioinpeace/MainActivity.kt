package com.desafios.desafioinpeace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.desafios.desafioinpeace.adapter.FriendsAdapter
import com.desafios.desafioinpeace.databinding.ActivityMainBinding
import com.desafios.desafioinpeace.model.Friend
import com.desafios.desafioinpeace.service.RetrofitService
import com.desafios.desafioinpeace.viewModel.MainViewModel
import com.desafios.desafioinpeace.viewModel.MainViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewFriends: RecyclerView
    lateinit var adapter: FriendsAdapter
    lateinit var viewModel: MainViewModel
    lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory()).get(
            MainViewModel::class.java
        )
        recyclerViewFriends = binding.recyclerViewAmigos
        recyclerViewFriends.layoutManager = LinearLayoutManager(this)

        getFriendData()
        searchFriend()
        refresh()

    }

    private fun getFriendData() {
        viewModel.getFriendData{ friend: List<Friend> ->
            adapter = FriendsAdapter(friend)
            recyclerViewFriends.adapter = adapter
        }

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

    private fun refresh(){
        swipeRefreshLayout = binding.swipeRefresh
        swipeRefreshLayout.setOnRefreshListener{
            getFriendData()
            swipeRefreshLayout.isRefreshing = false
        }

    }
}
