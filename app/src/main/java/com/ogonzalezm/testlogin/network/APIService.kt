package com.ogonzalezm.testlogin.network

import com.ogonzalezm.testlogin.model.User
import kotlinx.coroutines.delay
import java.lang.Exception

interface APIService {

    suspend fun login(email: String?, password: String?): User? {
        delay(2000L)
        if (email == "demo@demo.com" && password == "123456")
            return User("Oscar", "Gonzalez")
        throw Exception("Los datos del usuario son incorrectos.")
    }

}