package com.amttech.imageapp.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.amttech.imageapp.R
import com.amttech.imageapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlin.properties.Delegates

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    private var rememberMeChecked by Delegates.notNull<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        (activity as? AppCompatActivity)?.findViewById<View>(R.id.bottomNav)!!.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginFragment = this
        auth = FirebaseAuth.getInstance()
        sharedPreferences = requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        rememberMeChecked = sharedPreferences.getBoolean("rememberMe", false)

        if (rememberMeChecked) {
            binding.editTextEmail.setText(sharedPreferences.getString("email", ""))
            binding.editTextPassword.setText(sharedPreferences.getString("password", ""))
            binding.checkBox.isChecked = true
        }
    }

    fun login() {
        if (binding.editTextEmail.text.isNullOrEmpty() || binding.editTextPassword.text.isNullOrEmpty()) {
            Snackbar.make(requireView(), getString(R.string.blankFields), Snackbar.LENGTH_SHORT).show()
            hideKeyboard()
        } else {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    binding.animationViewCheck.visibility = View.VISIBLE

                    val sharedPreferences = requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    if (binding.checkBox.isChecked) {
                        editor.putString("email", email)
                        editor.putString("password", password)
                        editor.putBoolean("rememberMe", true)
                        editor.apply()
                    } else {
                        editor.remove("email")
                        editor.remove("password")
                        editor.remove("rememberMe")
                        editor.apply()
                    }
                    val counter = object : CountDownTimer(2000,1000){
                        override fun onTick(millisUntilFinished: Long) {}
                        override fun onFinish() {
                            val transition = LoginFragmentDirections.actionLoginToListing()
                            Navigation.findNavController(requireView()).navigate(transition)
                        }
                    }
                    counter.start()
                    Snackbar.make(requireView(), getString(R.string.loginSuccessful), Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(requireView(), getString(R.string.loginError), Snackbar.LENGTH_SHORT).show()
                    hideKeyboard()
                }
            }
        }
    }

    fun goToRegister() {
        val transition = LoginFragmentDirections.actionLoginToRegister()
        Navigation.findNavController(requireView()).navigate(transition)
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}