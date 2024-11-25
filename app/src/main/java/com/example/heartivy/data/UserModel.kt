package com.example.heartify.data

data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)