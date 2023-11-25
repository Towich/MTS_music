package com.example.mts_music

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(var context: Context) {
    private val PREFS_NAME = "UserData"
    private val sharedPref: SharedPreferences? = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveString(KEY_NAME: String, value: String) {
        val editor: SharedPreferences.Editor? = sharedPref?.edit()

        if (editor != null) {
            editor.putString(KEY_NAME, value)
            editor.apply()
        }
    }

    fun saveInt(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor? = sharedPref?.edit()

        if (editor != null) {
            editor.putInt(KEY_NAME, value)
            editor.apply()
        }
    }

    fun getValueString(KEY_NAME: String): String? {

        return sharedPref?.getString(KEY_NAME, null)
    }

    fun getValueInt(KEY_NAME: String): Int? {

        return sharedPref?.getInt(KEY_NAME, 0)
    }

    fun clearSharedPreference() {

        val editor: SharedPreferences.Editor? = sharedPref?.edit()

        if (editor != null) {
            editor.clear()
            editor.apply()
        }
    }

}