package com.olajide.capricon.login.presentation

import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.olajide.capricon.NetworkResult
import com.olajide.capricon.R
import com.olajide.capricon.collectLatestLifecycleFlow
import com.olajide.capricon.databinding.FragmentLoginBinding
import com.olajide.capricon.login.data.LoginObject
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel  by activityViewModels()
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {
            login()
        }
        handleLoginResponse()
    }

    /**Validates user's input and log's user in*/
    private fun login() {
        when {
            TextUtils.isEmpty(binding.username.text.toString()) -> {
                binding.username.error ="Please enter your valid username"
            }
            TextUtils.isEmpty(binding.password.text.toString()) -> {
                binding.username.error = "Please enter your valid password"
            }
            else -> {

                Log.d("LoginObject", "BtnPressed")
                val obj = LoginObject(binding.password.text.toString(),
                    binding.username.text.toString(), "android")
                viewModel.login(obj)
            }
        }
    }

    private fun handleLoginResponse() {
        collectLatestLifecycleFlow(viewModel.state) { loginResponseUiState ->
            when (loginResponseUiState) {
                is NetworkResult.Loading -> {
                    Log.d("LoginResponse", "loading on getting access token")

                    binding.loading.visibility = View.VISIBLE
                }
                is NetworkResult.Failure -> {
                    Log.d("LoginResponse", "failure on getting access token")
                    binding.loading.visibility = View.GONE

                }
                is NetworkResult.Success -> {
                    binding.loading.visibility = View.GONE
                    Log.d("LoginResponse", "Success on getting access token")
                    findNavController().navigate(R.id.action_LoginFragment_to_ListFragment)
                }

                is NetworkResult.Empty -> {
                    binding.loading.visibility = View.GONE

                }
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}