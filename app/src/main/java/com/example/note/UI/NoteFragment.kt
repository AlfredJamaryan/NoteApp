package com.example.note.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.Adapter.RecAdapter
import com.example.note.R
import com.example.note.databinding.FragmentNoteBinding
import com.google.firebase.auth.FirebaseAuth


class NoteFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentNoteBinding
   // lateinit var adapter: RecAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener(this)
        binding.logOut.setOnClickListener(this)

        //adapter = context?.let { RecAdapter(videoList, it) }!!
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
  //      binding.recyclerView.adapter = adapter
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.log_out -> {
                FirebaseAuth.getInstance().signOut()
                findNavController().navigate(R.id.action_noteFragment_to_signInFragment)
            }
            R.id.addButton -> {
                binding.createItem.visibility = View.VISIBLE
            }
        }

    }
}