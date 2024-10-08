package com.iaa2401.noteapp

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.iaa2401.noteapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater,container,false)

        binding.loginBtn.setOnClickListener {

            val email = binding.emailTv.text.toString().trim()
            val password = binding.passwordTv.text.toString().trim()

            if (isEmailValid(email) && isPasswordValid(password)){

                loginUser(email,password)

            }else{

                Toast.makeText(requireContext(),"Invalid email & password",Toast.LENGTH_LONG).show()
            }

        }

        binding.createTv.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_signInFragment)
        }


        return binding.root
    }

    private fun loginUser(email: String, password: String) {

        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->

            if (task.isSuccessful){

                val user = auth.currentUser
                Toast.makeText(requireContext(),"Successfully Login ${user?.email}",Toast.LENGTH_LONG).show()

                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

            }else{
                Toast.makeText(requireContext(),"${task.exception?.message}",Toast.LENGTH_LONG).show()

            }

        }

    }

    fun isEmailValid(email:String) : Boolean{

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }

    fun isPasswordValid(password:String) : Boolean{

        return password.length >= 6

    }


}