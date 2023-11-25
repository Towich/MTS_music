package com.example.mts_music.ui.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.MainActivity
import com.example.mts_music.SharedPreferences

class AuthViewModel(context: Context, private val repository: AuthRepository) : ViewModel() {

    private var phoneNumber = ""
    val sharedPreference: SharedPreferences =SharedPreferences(context = context)

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

    fun mobileLogin(mobilePhone: String) {
        repository.mobileLogin(mobilePhone)
    }

    fun sendSms() {
//        context
//        Toast.makeText(contex, "СМС отправлено!", Toast.LENGTH_SHORT).show()
    }

    class AuthViewModelFactory(private val context: Context, private val repository: AuthRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = AuthViewModel(context, repository) as T
    }
}
