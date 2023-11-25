package com.example.mts_music.ui.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mts_music.Constants.PHONENUMBER
import com.example.mts_music.Constants.USERNAME
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

    suspend fun mobileLogin(mobilePhone: String): String {
        val response = repository.mobileLogin(mobilePhone)
        return response
    }

    fun sendSms() {
        repository.sendSms()
//        context
//        Toast.makeText(contex, "СМС отправлено!", Toast.LENGTH_SHORT).show()
    }

    suspend fun smsLogin(code: String) {
        val response = repository.smsLogin(code)
        if(response == "success") {
            sharedPreference.saveString(PHONENUMBER, getPhoneNumber())
        }
    }

    fun checkAuthorization():Boolean {
        if(sharedPreference.getValueString(USERNAME)!= null &&
            sharedPreference.getValueString(PHONENUMBER)!= null) {
            return true
        }
        return false
    }

    class AuthViewModelFactory(private val context: Context, private val repository: AuthRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = AuthViewModel(context, repository) as T
    }
}
