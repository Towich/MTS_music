package com.example.mts_music.ui.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mts_music.Constants.ID
import com.example.mts_music.Constants.PHONENUMBER
import com.example.mts_music.Constants.USERNAME
import com.example.mts_music.SharedPreferences
import com.example.mts_music.data.Converter
import kotlinx.coroutines.launch

class AuthViewModel(private val context: Context, private val repository: AuthRepository) : ViewModel() {

    private var phoneNumber = ""
    val sharedPreference: SharedPreferences =SharedPreferences(context = context)
    val converter = Converter()

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
        viewModelScope.launch {
            repository.sendSms(sharedPreference.getValueInt(ID).toString())
        }
        Toast.makeText(context, "СМС отправлено!", Toast.LENGTH_SHORT).show()
    }

    suspend fun smsLogin(code: String): Boolean {
        val responseUser = repository.smsLogin(code)
        if(responseUser != null) {
            sharedPreference.saveString(PHONENUMBER, getPhoneNumber())
            sharedPreference.saveInt(ID, responseUser.id)
            sharedPreference.saveString(USERNAME, responseUser.user_name)
            return true
        }
        return false
    }

    fun checkAuthorization():Boolean {
        if(sharedPreference.getValueString(USERNAME)!= null &&
            sharedPreference.getValueString(PHONENUMBER)!= null &&
            sharedPreference.getValueInt(ID)!= null) {
            return true
        }
        return false
    }

    class AuthViewModelFactory(private val context: Context, private val repository: AuthRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = AuthViewModel(context, repository) as T
    }
}
