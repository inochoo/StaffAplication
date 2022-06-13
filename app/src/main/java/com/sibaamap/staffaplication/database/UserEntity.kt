package com.sibaamap.staffaplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "userinfo")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "codeUser") val codeUser: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "district") val district: String,
    @ColumnInfo(name = "address") val address: String
):Serializable