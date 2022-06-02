package com.example.note.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseDatabase = FirebaseFirestore.getInstance()

        binding.signup.setOnClickListener {
            if (binding.FirstName.text.toString().isEmpty()) {
                binding.FirstName.error = "First Name is not correct!"
            } else if (binding.LastName.text.toString().isEmpty()) {
                binding.LastName.error = "Last Name is not correct!"
            } else if (validateEmail(binding.email.text.toString())) {
                binding.email.error = "Email is not correct!"
            } else if (binding.RetypePasswordId.text.toString() != binding.passw.text.toString()) {
                binding.RetypePasswordId.error = "Is not correct!"
            } else {
                if (validatePassword(binding.passw)) {
                    auth = FirebaseAuth.getInstance()
                    auth.createUserWithEmailAndPassword(
                        binding.email.text.toString(),
                        binding.passw.text.toString()
                    )
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "it's okay", Toast.LENGTH_SHORT).show()
                                val firbaseUser = FirebaseAuth.getInstance().currentUser
                                val hashMap = hashMapOf<String, Any>(
                                    "First Name" to binding.FirstName.text.toString(),
                                    "Last Name" to binding.LastName.text.toString(),
                                    "Email" to binding.email.text.toString(),
                                    "Repeat Password" to binding.RetypePasswordId.text.toString()
                                )
                                firebaseDatabase.collection("users").document(firbaseUser!!.uid)
                                    .set(hashMap)
                                    .addOnSuccessListener {
                                    }
                                findNavController().navigate(
                                    RegisterFragmentDirections.actionRegisterFragmentToSignUpFragment()
                                )
                            } else {
                            }
                        }
                }
            }
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_signInFragment)
        }

    }


    private fun validateEmail(email: String): Boolean {
        val emailRegex = Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        )
        return !emailRegex.matcher(email).matches()
    }

    private fun validatePassword(pass: EditText): Boolean {
        val errorText = when {
            !pass.text.contains(Regex("[A-Z]")) -> {
                pass.error = "Password must contain one capital letter"
                false
            }
            !pass.text.contains(Regex("[0-9]")) -> {
                pass.error = "Password must contain one digit"
                false
            }
            !pass.text.contains(Regex("[^a-zA-Z0-9 ]")) -> {
                pass.error = "Password must contain one special character"
                false
            }
            else -> true
        }
        return errorText
    }
}