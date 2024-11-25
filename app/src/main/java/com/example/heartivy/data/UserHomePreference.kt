package com.example.heartify.data

import android.content.Context

internal class UserHomePreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val AGE = "age"
        private const val JENIS_KELAMIN = "jk"
        private const val DESCRIPTION = "desk"
        private const val PHONE_NUMBER = "phone"
        private const val COLLESTROL = "collestrol"
        private const val LOVE_MU = "islove"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(value: UserHomeModel) {
        val editor = preferences.edit()
        editor.putString(NAME, value.name)
        editor.putString(EMAIL, value.email)
        editor.putInt(AGE, value.age)
        editor.putBoolean(JENIS_KELAMIN, value.jk)
        editor.putString(DESCRIPTION, value.desk)
        editor.putInt(PHONE_NUMBER, value.phoneNumber)
        editor.putInt(COLLESTROL, value.collestrol)
        editor.putBoolean(LOVE_MU, value.isLove)
        editor.apply()
    }

    fun getUser(): UserHomeModel {
        val model = UserHomeModel()
        model.name = preferences.getString(NAME, "")
        model.email = preferences.getString(EMAIL, "")
        model.age = preferences.getInt(AGE, 0)
        model.jk = preferences.getBoolean(JENIS_KELAMIN, false)
        model.desk = preferences.getString(DESCRIPTION, "")
        model.phoneNumber = preferences.getInt(PHONE_NUMBER, 0)
        model.collestrol = preferences.getInt(COLLESTROL, 0)
        model.isLove = preferences.getBoolean(LOVE_MU, false)

        return model
    }
}