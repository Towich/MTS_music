package com.example.mts_music.ui.auth

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private var phoneNumber = ""

    fun getPhoneNumber(): String {
        return phoneNumber
    }

    fun setPhoneNumber(newPhoneNumber: String) {
        phoneNumber = newPhoneNumber
    }

    fun isPhoneCorrect(phone: String): Boolean {
        if (phone.isEmpty()) return false

        val phoneRegex = Regex("^\\+7[0-9]{10}$")
        return phoneRegex.matches(phone)
    }

    fun isDigits(string: String): Boolean {
        val charRegex = Regex("\\d+")
        return charRegex.matches(string)
    }
}
