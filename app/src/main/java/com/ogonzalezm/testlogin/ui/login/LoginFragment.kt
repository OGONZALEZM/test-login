package com.ogonzalezm.testlogin.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ogonzalezm.testlogin.R
import com.ogonzalezm.testlogin.databinding.FragmentLoginBinding
import com.ogonzalezm.testlogin.util.Resource
import com.ogonzalezm.testlogin.util.isValidEmail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)


        binding.login.setOnClickListener {
            login()
        }

        return binding.root
    }

    private fun login() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        var hasError = false

        if (email.isEmpty() || !email.isValidEmail()) {
            binding.email.error = resources.getString(R.string.error_email_invalid)
            hasError = true
        }

        if (password.isEmpty()) {
            binding.password.error = resources.getString(R.string.error_password_invalid)
            hasError = true
        }

        if(hasError) return

        viewModel.login(email, password).observe(viewLifecycleOwner) {
            when(it.status) {
                Resource.Status.LOADING -> {
                    showError(false)
                    binding.login.text = resources.getString(R.string.loading)
                    enableForm(false)
                }
                Resource.Status.SUCCESS -> {
                    showError(false)
                    loginComplete()
                }
                Resource.Status.ERROR -> {
                    showError(true, it.message)
                    binding.login.text = resources.getString(R.string.login)
                    enableForm()
                }
            }
        }
    }

    private fun loginComplete() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun showError(show: Boolean, message: String? = "") {
        binding.message.visibility = if (show) View.VISIBLE else View.GONE
        binding.message.text = message ?: resources.getString(R.string.error_login)
    }

    private fun enableForm(enable: Boolean = true) {
        binding.email.isEnabled = enable
        binding.password.isEnabled = enable
        binding.login.isEnabled = enable
    }

}