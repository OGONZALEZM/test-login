package com.ogonzalezm.testlogin.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ogonzalezm.testlogin.model.User
import com.ogonzalezm.testlogin.repository.LoginRepository
import com.ogonzalezm.testlogin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    fun login(email: String, password: String) = liveData {
        emit(Resource.loading(null))
        try {
            val user = loginRepository.login(email, password)
            emit(Resource.success(user))
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "error", null))
        }
    }

    suspend fun testLogin(email: String?, password: String?): User? {
        return loginRepository.login(email, password)
    }
}