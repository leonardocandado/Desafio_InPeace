package com.desafios.desafioinpeace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafios.desafioinpeace.databinding.CardAmigosBinding
import com.desafios.desafioinpeace.model.Friend

class FriendsAdapter (private val items: List<Friend>):
    RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>(){

    class FriendsViewHolder(val binding: CardAmigosBinding):
        RecyclerView.ViewHolder(binding.root)

    //Cria os itens para a lista (recyclerView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(
            CardAmigosBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    //Exibir os itens
    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val repository = items[position]
        holder.binding.apply{
            textName.text = (repository.first_name + " " + repository.last_name)
            textEmail.text = repository.email
            Glide.with(imageFriend)
                .load(repository.avatar)
                .into(imageFriend)

        }
    }

    override fun getItemCount(): Int = items.size

}