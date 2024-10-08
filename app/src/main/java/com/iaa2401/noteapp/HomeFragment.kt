package com.iaa2401.noteapp

import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.iaa2401.noteapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var database: NoteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)

        database = Room.databaseBuilder(requireContext(),NoteDatabase::class.java,"Note_DB")
            .allowMainThreadQueries().build()

        val notes : List<Note> = database.getNote().getAllData()

        val ada = NoteAdapter()
        ada.submitList(notes)

        binding.recyclerView.adapter = ada



        binding.AddBtn.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)

        }

        return binding.root
    }




}