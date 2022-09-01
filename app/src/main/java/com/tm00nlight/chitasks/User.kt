package com.tm00nlight.chitasks

import androidx.room.Entity

@Entity
data class User (
    var name: String,
    var age: Int,
    var isStudent: Boolean = false
)