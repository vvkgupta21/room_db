package com.example.room_db.fragment.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room_db.R
import com.example.room_db.viewmodel.UserViewModel
import com.example.room_db.databinding.FragmentAddBinding
import com.example.room_db.room.User

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.button.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        with(binding) {
            val firstName = editTextText.text.toString()
            val lastName = editTextText2.text.toString()
            val age = editTextText3.text

            if (inputCheck(firstName, lastName, age)){
                val user = User(firstName, lastName, Integer.parseInt(age.toString()), 0)
                mUserViewModel.adduser(user)
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }else{
                Toast.makeText(context, "incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputCheck(firstname: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}