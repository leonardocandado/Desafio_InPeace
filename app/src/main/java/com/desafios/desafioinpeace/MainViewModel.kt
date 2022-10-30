package com.desafios.desafioinpeace

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.desafios.desafioinpeace.model.Friend
import com.desafios.desafioinpeace.service.FriendsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: FriendsRepository) : ViewModel() {


    private val _repositoryResponse: MutableLiveData<List<Friend>> = MutableLiveData()
    val repositoryResponse: LiveData<List<Friend>>
        get() = _repositoryResponse

//    fun getAllFriends() {
//        val request = this.repository.
//        request.enqueue(object : Callback<List<Friend>> {
//            override fun onResponse(
//                call: Call<List<Friend>>,
//                response: Response<List<Friend>>
//            ) {
//                if (response.code() == 200) {
//                    friendsList.postValue(response.body())
//                } else {
//                    //projetar erro
//                }
//            }
//
//            override fun onFailure(call: Call<List<Friend>>, t: Throwable) {
//                //projetar msg erro
//            }
//
//        })
//    }
}