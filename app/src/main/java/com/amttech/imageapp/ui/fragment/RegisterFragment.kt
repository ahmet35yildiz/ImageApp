package com.amttech.imageapp.ui.fragment

import android.content.Context
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
import com.amttech.imageapp.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container,false)
        (activity as? AppCompatActivity)?.findViewById<View>(R.id.bottomNav)!!.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerFragment = this
        auth = FirebaseAuth.getInstance()
    }

    fun register() {
        if (binding.editTextEmail.text.isNullOrEmpty() || binding.editTextPassword.text.isNullOrEmpty()) {
            Snackbar.make(requireView(), getString(R.string.blankFields), Snackbar.LENGTH_SHORT).show()
            hideKeyboard()
        } else {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.animationViewCheck.visibility = View.VISIBLE

                    val counter = object : CountDownTimer(2000,1000){
                        override fun onTick(millisUntilFinished: Long) {}
                        override fun onFinish() {
                            val transition = RegisterFragmentDirections.actionRegisterToListing()
                            Navigation.findNavController(requireView()).navigate(transition)
                        }
                    }
                    counter.start()
                    Snackbar.make(requireView(), getString(R.string.registerSuccessful), Snackbar.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                Snackbar.make(requireView(), getString(R.string.registerError), Snackbar.LENGTH_SHORT).show()
                hideKeyboard()
            }
        }
    }

    fun goToLogin() {
        val transition = RegisterFragmentDirections.actionRegisterToLogin()
        Navigation.findNavController(requireView()).navigate(transition)
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}