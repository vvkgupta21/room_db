package com.example.room_db.fragment.update

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
import androidx.navigation.fragment.navArgs
import com.example.room_db.R
import com.example.room_db.databinding.FragmentUpdateBinding
import com.example.room_db.room.User
import com.example.room_db.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var mUserViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        with(binding){
            editTextText.setText(args.currentUser.firstName)
            editTextText2.setText(args.currentUser.lastName)
            editTextText3.setText(args.currentUser.age.toString())
        }

        binding.button.setOnClickListener {
            updateDataToDatabase()
        }
        return binding.root
    }

    private fun updateDataToDatabase() {
        with(binding) {
            val firstName = editTextText.text.toString()
            val lastName = editTextText2.text.toString()
            val age = editTextText3.text

            if (inputCheck(firstName, lastName, age)){
                val user = User(firstName, lastName, Integer.parseInt(age.toString()), args.currentUser.id)
                mUserViewModel.updateUser(user)
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }else{
                Toast.makeText(context, "incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputCheck(firstname: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}