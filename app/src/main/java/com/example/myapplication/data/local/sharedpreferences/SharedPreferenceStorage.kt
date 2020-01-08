package com.example.myapplication.data.local.sharedpreferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceStorage
@Inject constructor(context: Context) {
    companion object {
        const val PREFS_NAME = "akatwitter"
        const val userID = "userid"
    }

    private val preferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    inner class UserSharedPreferenceStorage {
        var userId by StringPreference(preferences, userID, null)
    }

}

class StringPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.edit { putString(name, value) }
    }
}
