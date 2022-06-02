package com.example.note.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note.Models.NoteModel
import com.example.note.R
import com.example.note.databinding.NoteItemBinding

class RecAdapter(
    private val noteModelList: MutableList<NoteModel>, val context: Context
) : RecyclerView.Adapter<RecAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tempNotes = noteModelList[position]
        holder.bind(tempNotes)
    }

    override fun getItemCount(): Int = noteModelList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding = NoteItemBinding.bind(itemView)

        fun bind(noteModel: NoteModel) {

        }

    }


}