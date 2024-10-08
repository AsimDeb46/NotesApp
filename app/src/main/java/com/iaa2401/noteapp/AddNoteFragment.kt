package com.iaa2401.noteapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.iaa2401.noteapp.databinding.FragmentAddNoteBinding
import java.util.Calendar


class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding

      var showTime : String ? = null
     var showDate : String? = null

    lateinit var database: NoteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNoteBinding.inflate(inflater,container,false)

        database = Room.databaseBuilder(requireContext(),NoteDatabase::class.java,"Note_DB")
            .allowMainThreadQueries().build()

        binding.pickDate.setOnClickListener {

            pickDate()
        }

        binding.pickTime.setOnClickListener {

            pickTime()
        }

        binding.SubmitBtn.setOnClickListener {

            val titileStr = binding.textTv.text.toString()

            val dateStr = showDate ?: "00/00/0000"
            val timeStr = showTime ?: "00:00"

            val note = Note(title = titileStr, time = timeStr, date = dateStr)
            database.getNote().insertData(note)

            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)

        }


        return binding.root
    }

    private fun pickTime() {

        val calendar = Calendar.getInstance()

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

      val showTimePicker = TimePickerDialog(requireContext(),TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->

            showTime = "$hour:$minute"
            binding.pickTime.text = showTime

        },hour,minute,false

        )
        showTimePicker.show()

    }

    private fun pickDate() {

        val calendar = Calendar.getInstance()

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

       val showDatePicker = DatePickerDialog(requireContext(),DatePickerDialog.OnDateSetListener { datePicker, year, month, dayofMonth ->

            showDate = "$dayofMonth/${month+1}/$year"
            binding.pickDate.text = showDate

        },year,month,day

        )

        showDatePicker.show()

    }


}