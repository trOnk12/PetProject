package com.example.myapplication.data.local.sharedpreferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceStorage
@Inject constructor
    (context: Context) {

    private val preferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    private var favouriteCommentsPrefs by StringSetPreference(preferences, FAVOURITE_COMMENTS)

    fun addCommentToFavourite(id: String): FavouriteStatus {
        return if (isCommentFavourite(id)) { favouriteCommentsPrefs.add(id); FavouriteStatus.IS_ADDED }
         else { favouriteCommentsPrefs.remove(id); FavouriteStatus.IS_REMOVED }
    }

    private fun isCommentFavourite(id: String): Boolean {
        return favouriteCommentsPrefs.contains(id)
    }

    companion object {
        const val PREFS_NAME = "akatwitter"
        const val FAVOURITE_COMMENTS = "favourite_comments"
    }
}

class StringSetPreference(
    private val preferences: SharedPreferences,
    private val name: String
) : ReadWriteProperty<Any, MutableSet<String>> {

    override fun getValue(thisRef: Any, property: KProperty<*>): MutableSet<String> {
        return preferences.getStringSet(name, emptySet())!!
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: MutableSet<String>) {
        preferences.edit().putStringSet(SharedPreferenceStorage.FAVOURITE_COMMENTS, value).apply()
    }
}

enum class FavouriteStatus {
    IS_ADDED, IS_REMOVED
}




