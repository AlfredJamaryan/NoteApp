package com.example.note.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.databinding.FragmentSignInBinding


class SignInFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.SingIn.setOnClickListener(this)
        binding.SingUp.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.SingIn ->{
                findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
            }
            R.id.SingUp ->{
                findNavController().navigate(R.id.action_signInFragment_to_registerFragment)
            }
        }
    }


}
