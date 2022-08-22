package com.ogonzalezm.testlogin.ui.login

import com.ogonzalezm.testlogin.model.User
import com.ogonzalezm.testlogin.repository.LoginRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull

class LoginViewModelTest {

    @RelaxedMockK
    private lateinit var loginRepository: LoginRepository

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun `when the login is successful`() = runBlocking {

        coEvery { loginRepository.login(any(), any()) } returns User("", "")

        val response = viewModel.testLogin("demo@demo.com", "123456")

        coVerify(exactly = 1) { loginRepository.login(any(), any()) }

        assertNotNull(response)
    }

    @Test
    fun `when the login is not successful`() = runBlocking {

        coEvery { loginRepository.login(null, null) } returns null

        val response = viewModel.testLogin(null, null)

        coVerify(exactly = 1) { loginRepository.login(any(), any()) }

        assertNull(response)
    }

}
