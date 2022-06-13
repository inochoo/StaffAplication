package com.sibaamap.staffaplication.userviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sibaamap.staffaplication.database.RoomAppDb
import com.sibaamap.staffaplication.database.UserEntity

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    lateinit var allUser : MutableLiveData<List<UserEntity>>

    init {
        allUser = MutableLiveData()
        getAllUsers()
    }



    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allUser
    }

    fun getAllUsers(){
        val userDao = RoomAppDb.getAppDatabase((getApplication())).userDao()
        val list = userDao?.getAllUserInfo()

        allUser.postValue(list!!)

    }
    fun insertUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication()).userDao()
        userDao?.insertUser(entity)
        getAllUsers()
    }

    fun updateUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication()).userDao()
        userDao?.updateUser(entity)
        getAllUsers()
    }
    fun deleteUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication()).userDao()
        userDao?.deleteUser(entity)
        getAllUsers()
    }
//    fun searchUserInfo(name: String){
//        val userDao = RoomAppDb.getAppDatabase(getApplication()).userDao()
//        userDao?.searchUser(name)
//        getAllUsers()
//    }


}
