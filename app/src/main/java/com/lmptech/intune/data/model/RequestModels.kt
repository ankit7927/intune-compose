package com.lmptech.intune.data.model

data class LoginRequest(val username:String, val password:String)

data class RegisterRequest(val name:String, val username:String, val email:String, val password:String)