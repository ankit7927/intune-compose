package com.lmptech.intune.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmptech.intune.data.local.database.repository.UserRepository
import com.lmptech.intune.data.model.UserModel
import com.lmptech.intune.data.remote.repository.RemoteUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileData(
    val loading: Boolean = true,
    val error:String = "",
    val edited:Boolean = false,
    var userModel: UserModel? = null
)

class ProfileViewModel(
    private val userRepository: UserRepository,
    private  val remoteUserRepository: RemoteUserRepository
) : ViewModel() {
    private val profileViewState: MutableStateFlow<ProfileData> = MutableStateFlow(ProfileData())

    val profile: StateFlow<ProfileData>
        get() = profileViewState

    init {
        viewModelScope.launch (Dispatchers.IO) {
            val userData = userRepository.getUserData()
            profileViewState.emit(profile.value.copy(loading = false, userModel = userData))
        }
    }

    fun onEdit(){
        viewModelScope.launch {
            profileViewState.emit(profile.value.copy(edited = true))
        }
    }

    fun onSave() {
        viewModelScope.launch (Dispatchers.IO) {
            profileViewState.emit(profile.value.copy(loading = true))
            val updateUser = remoteUserRepository.updateUser(user = profile.value.userModel!!, section = "profile")
            if (updateUser.isSuccessful) {
                profileViewState.emit(profile.value.copy(loading = false))
                userRepository.updateUser(profile.value.userModel!!)
            } else {
                profileViewState.emit(profile.value.copy(loading = false, error = updateUser.message()))
            }
        }
    }

    fun onNameChange(it: String) {
        viewModelScope.launch {
            profileViewState.emit(profile.value.copy(userModel = profile.value.userModel?.copy(name = it)))
        }
    }

    fun onUsernameChange(it: String) {
        viewModelScope.launch {
            profileViewState.emit(profile.value.copy(userModel = profile.value.userModel?.copy(username = it)))
        }
    }

    fun onBioChange(it: String) {
    }
}