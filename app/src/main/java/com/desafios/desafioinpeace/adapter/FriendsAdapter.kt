package com.desafios.desafioinpeace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafios.desafioinpeace.databinding.CardAmigosBinding
import com.desafios.desafioinpeace.model.Friend

class FriendsAdapter(private var items: List<Friend>) :
    RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>(), Filterable {

    private var friendList = items

    class FriendsViewHolder(val binding: CardAmigosBinding) : RecyclerView.ViewHolder(binding.root)

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
        holder.binding.apply {
            textName.text = (repository.first_name + " " + repository.last_name)
            textEmail.text = repository.email
            Glide.with(imageFriend).load(repository.avatar).into(imageFriend)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getFilter(): Filter {
        val filter = object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val filterResult = FilterResults()
                if (p0 == null || p0.isEmpty()) {
                    filterResult.values = friendList
                    filterResult.count = friendList.size
                } else {
                    val searchChar = p0.toString()
                    val filteredResults = ArrayList<Friend>()

                    for (friendModel in items) {
                        if (friendModel.first_name?.lowercase()?.contains(searchChar) == true ||
                            friendModel.last_name?.lowercase()?.contains(searchChar) == true ||
                            friendModel.first_name?.contains(searchChar) == true ||
                            friendModel.last_name?.contains(searchChar) == true
                        ) {
                            filteredResults.add(friendModel)
                        }
                    }
                    filterResult.values = filteredResults
                    filterResult.count = filteredResults.size
                }

                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                items = p1!!.values as List<Friend>
                notifyDataSetChanged()

            }

        }
        return filter
    }


}