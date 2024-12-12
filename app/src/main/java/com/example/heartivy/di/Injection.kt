package com.example.heartify.di

import android.content.Context
import com.example.heartify.data.UserPreference
import com.example.heartify.data.UserRepository
import com.example.heartify.data.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}
