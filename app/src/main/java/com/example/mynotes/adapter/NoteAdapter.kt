package com.example.mynotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.databinding.SingleNoteLayoutBinding
import com.example.mynotes.fragments.HomeFragmentDirections
import com.example.mynotes.model.Note

class NoteAdapter(): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


//     General way of doing it but here we will be using View Binding
//    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//
//    }


    class NoteViewHolder(val itemBinding: SingleNoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            SingleNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc

        // When user clicks on the note, the user should be directed to edit note fragment
        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }

    // This is used to efficiently update the contents of a recycler view without refreshing the entire dataset
    private val differCallback = object : DiffUtil.ItemCallback<Note>(){

        // This function is used to check if the items of the Note class are same by comparing each of their attributes
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        // This function is used to check if the content of the oldItem is the same as the newItem
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    // This creates an AsyncListDiffer instance which helps us determine differences between two lists on a background thread
    // It helps us update the data efficiently
    val differ = AsyncListDiffer(this, differCallback)
}