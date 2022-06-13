package com.sibaamap.staffaplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sibaamap.staffaplication.R
import com.sibaamap.staffaplication.adapter.UserAdapter
import com.sibaamap.staffaplication.view.MainActivity.Companion.viewModel
import com.sibaamap.staffaplication.database.UserEntity
import com.sibaamap.staffaplication.userviewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.activity_add_user.edt_user_name
import kotlinx.android.synthetic.main.activity_add_user.view.*
import java.io.Serializable

class AddUser : AppCompatActivity() {

    //lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        val user = intent.getSerializableExtra("infoUser") as? UserEntity

            if (user != null) {
                edt_user_name.setText(user.name)
                edt_code_user.setText(user.codeUser)
                edt_email.setText(user.email)
                edt_password.setText(user.password)
                edt_city.setText(user.city)
                edt_district.setText(user.district)
                edt_address.setText(user.address)
                edt_user_name.setTag(edt_user_name.id,user.id)
                btn_save_user.text="Update User"
            }
        btn_save_user.setOnClickListener {
            editAddUser()
        }
    }
    private fun editAddUser(){
        val name = edt_user_name.text.toString()
        val code = edt_code_user.text.toString()
        val email = edt_email.text.toString()
        val password = edt_password.text.toString()
        val city = edt_city.text.toString()
        val district = edt_district.text.toString()
        val address = edt_address.text.toString()
        if(name.isNullOrBlank() || code.isNullOrBlank() || email.isNullOrBlank() || password.isNullOrBlank()
            || city.isNullOrBlank() || district.isNullOrBlank() || address.isNullOrBlank()){
            Toast.makeText(this, "Vui lòng điền vào các ô bắt buộc", Toast.LENGTH_SHORT).show()
        }else if(password.length<6 && !password.matches(".*[A-Z].*".toRegex()) &&
                    !password.matches(".*[a-z].*".toRegex()) &&
                    !password.matches(".*[!@#$%^&*()_+].*".toRegex())){

            Toast.makeText(this, "password phải có ít nhất 6 kí tự, phải bao gồm cả viết hoa, số và 1 kí tự đặng biệt", Toast.LENGTH_SHORT).show()
        }
        else{
            if(btn_save_user.text.equals("LƯU DỮ LIỆU")){
                val user = UserEntity(0, name, code,email,password,city,district,address)
                viewModel.insertUserInfo(user)
            }else{
                val user = UserEntity(edt_user_name.getTag(edt_user_name.id).toString().toInt(), name,code, email,password,city,district,address)
                viewModel.updateUserInfo(user)
                btn_save_user.text = "LƯU DỮ LIỆU"
            }
            edt_user_name.setText("")
            edt_code_user.setText("")
            edt_email.setText("")
            edt_password.setText("")
            edt_city.setText("")
            edt_district.setText("")
            edt_address.setText("")
            onBackPressed()
        }
    }
}