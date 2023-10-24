package com.example.room_db.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room_db.databinding.UserItemBinding
import com.example.room_db.fragment.list.ListFragmentDirections
import com.example.room_db.room.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.Holder>() {

    private var userList = emptyList<User>()

    class Holder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = userList[position]

        holder.binding.apply {
            username.text = currentItem.firstName + " " + currentItem.lastName
            userAge.text = currentItem.age.toString()
            itemNumber.text = currentItem.id.toString()
        }

        holder.binding.userItem.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = userList.size

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

}