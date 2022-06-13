package com.sibaamap.staffaplication.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.sibaamap.staffaplication.R
import com.sibaamap.staffaplication.adapter.UserAdapter
import com.sibaamap.staffaplication.database.UserEntity
import com.sibaamap.staffaplication.userviewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity(),UserAdapter.RowClickListenner {

    lateinit var userAdapter: UserAdapter
    companion object{
        lateinit var viewModel: MainActivityViewModel
    }
    lateinit var mListUser: ArrayList<UserEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_search_user.clearFocus()

        img_add_user.setOnClickListener {
            val intent = Intent(this, AddUser::class.java)
            startActivity(intent)
        }

        rcv_list_user.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            userAdapter = UserAdapter(this@MainActivity)
            adapter = userAdapter
            val divider = DividerItemDecoration(applicationContext, MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            userAdapter.setListData(ArrayList(it))
            userAdapter.notifyDataSetChanged()
        })

    }
    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UserEntity) {
        val intent = Intent(this,AddUser::class.java)
        intent.putExtra("infoUser", user as Serializable)
        startActivity(intent)

    }


}