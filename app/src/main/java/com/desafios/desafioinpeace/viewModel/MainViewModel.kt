package com.desafios.desafioinpeace.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.desafios.desafioinpeace.model.Friend
import com.desafios.desafioinpeace.model.FriendResponse
import com.desafios.desafioinpeace.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    fun getFriendData(callback: (List<Friend>) -> Unit) {
        val apiService = RetrofitService.getInstance()
        apiService.getAllFriends().enqueue(object : Callback<FriendResponse> {
            override fun onFailure(call: Call<FriendResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
            override fun onResponse(
                call: Call<FriendResponse>,
                response: Response<FriendResponse>
            ) {
                return callback(response.body()!!.data)
            }

        })
    }



}