package com.example.heartify.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserHomeModel (
    var name: String? = null,
    var email: String? = null,
    var age: Int = 0,
    var jk: Boolean = false,
    var desk: String? = null,
    var phoneNumber: Int = 0,
    var collestrol: Int = 0,
    var isLove: Boolean = false
) : Parcelable