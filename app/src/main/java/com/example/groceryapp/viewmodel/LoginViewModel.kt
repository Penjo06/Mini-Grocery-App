package com.example.groceryapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    var phoneNumber by mutableStateOf("")
        private set
    var error by mutableStateOf("")
        private set

    var otp by mutableStateOf("")
        private set

    var isValid by mutableStateOf(false)


    fun onNumberChange(number: String){
        if (number.length <= 10){
            phoneNumber = number
            error = ""
        }
    }

    fun onOtpChange(otp: String){
        if (otp.length <= 4){
            this.otp = otp
            error = ""
        }
    }

    fun validatePhoneNumber(): Boolean {
        return when{
            phoneNumber.isEmpty() -> {
                error = "Please enter your phone number"
                false
            }
            phoneNumber.length < 10 -> {
                error = "Please enter a valid phone number"
                false
            }
            else -> {error = ""
                isValid = true
                    true
            }
        }
    }

    fun validateOtp(): Boolean{
        return when{
            otp.isEmpty() -> {
                error = "Please enter your OTP"
                false
            }
            otp != "1234" -> {
                error = "Otp entered is invalid"
                false
            }
            else -> {error = ""
                true
            }
        }

    }
}