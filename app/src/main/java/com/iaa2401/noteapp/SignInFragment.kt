package com.iaa2401.noteapp

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.iaa2401.noteapp.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignInBinding.inflate(inflater,container,false)

        binding.signInBtn.setOnClickListener {

            val email = binding.emailTv.text.toString().trim()
            val password = binding.passwordTv.text.toString().trim()
            val user = binding.UserTv.text.toString().trim()

            if (isEmailValid(email) && isPasswordValid(password)){

                signInUser(user,email,password)

            }else{

                Toast.makeText(requireContext(),"Invalid email & password",Toast.LENGTH_LONG).show()
            }

        }


        return binding.root
    }

    private fun signInUser(user: String, email: String, password: String) {

        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->

            if(task.isSuccessful){

                Toast.makeText(requireContext(),"Create Account Successfully",Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_signInFragment_to_loginFragment)

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