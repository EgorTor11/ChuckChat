package com.example.chuckchat.presentation

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chuckchat.databinding.ItemBinding
import com.example.chuckchat.domaun.domain.models.User

class RVAdapter : ListAdapter<User, RVAdapter.ViewHolder>(ItemComparator()) {
    class ViewHolder(private val binding:ItemBinding): RecyclerView.ViewHolder(binding.root){
fun bind(user: User)= with(binding){
    message.text=user.message
    tvName.text=user.name
}
        companion object{
            fun create(parent:ViewGroup): ViewHolder {
                return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
            }
        }
    }
    class ItemComparator: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return  oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return  oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}