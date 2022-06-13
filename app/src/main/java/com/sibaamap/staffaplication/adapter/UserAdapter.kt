package com.sibaamap.staffaplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sibaamap.staffaplication.view.AddUser
import com.sibaamap.staffaplication.R
import com.sibaamap.staffaplication.database.UserEntity
import kotlinx.android.synthetic.main.user_items.view.*

class UserAdapter(val listener: RowClickListenner): RecyclerView.Adapter<UserAdapter.MyViewHolder>(){

    var items = ArrayList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.user_items,parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.editUser.setOnClickListener {
            listener.onItemClickListener(items[position])
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View, val listener: RowClickListenner): RecyclerView.ViewHolder(view) {
        val tvName = view.tv_nameUsers
        val tvCodeUser = view.tv_codeUsers
        val deleteUserID = view.deleteUserID
        val editUser = view.editUser
        val locationUser = view.locationUser

        fun bind(data: UserEntity){
            tvName.text = data.name
            tvCodeUser.text = "MSNV: "+data.codeUser
            deleteUserID.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }

        }
    }
    interface RowClickListenner{
        fun onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
    }
}