package com.example.room_db.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("user_table")
data class User(
    val firstName: String,
    val lastName: String,
    val age: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
): Parcelable