package com.iaa2401.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.iaa2401.noteapp.databinding.ItemDesignBinding

class NoteAdapter : ListAdapter<Note,ItemViewHolder>(comparator){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

      return ItemViewHolder(ItemDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        getItem(position).let {

            holder.binding.apply {

                noteTitle.text = it.title
                noteDate.text = it.date
                noteTime.text = it.time

            }

        }

    }

    companion object{

        val comparator = object : DiffUtil.ItemCallback<Note>(){

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }


            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }


        }

    }


}

class ItemViewHolder(var binding: ItemDesignBinding) : RecyclerView.ViewHolder(binding.root){


}