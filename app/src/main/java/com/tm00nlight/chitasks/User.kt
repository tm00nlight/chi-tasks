package com.tm00nlight.chitasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey var name: String,
    var age: Int,
    var isStudent: Boolean = false
)