package com.ogonzalezm.testlogin.repository

import com.ogonzalezm.testlogin.model.User
import com.ogonzalezm.testlogin.network.APIService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val service: APIService
) {

    suspend fun login(email: String?, password: String?) : User? = service.login(email, password)

}