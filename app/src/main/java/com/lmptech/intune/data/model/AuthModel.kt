package com.lmptech.intune.data.model

data class LoginRequestModel(val username:String, val password:String)

data class LoginResponseModel(val accessToken: String, val refreshToken: String)

data class RegisterRequestModel(val name:String, val username:String, val email:String, val password:String)