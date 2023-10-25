package com.example.room_db.fragment.update

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room_db.R
import com.example.room_db.databinding.FragmentUpdateBinding
import com.example.room_db.room.User
import com.example.room_db.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
        setHasOptionsMenu(true)

        with(binding) {
            editTextText.setText(args.currentUser.firstName)
            editTextText2.setText(args.currentUser.lastName)
            editTextText3.setText(args.currentUser.age.toString())
        }

        binding.button.setOnClickListener {
            updateDataToDatabase()
        }
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete User")
        builder.setMessage(args.currentUser.firstName +  " " + args.currentUser.lastName)
        builder.setPositiveButton("OK") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(context, "Deleted user ${args.currentUser.firstName + " " +args.currentUser.lastName}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Cancel") { _, _ -> }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun updateDataToDatabase() {
        with(binding) {
            val firstName = editTextText.text.toString()
            val lastName = editTextText2.text.toString()
            val age = editTextText3.text

            if (inputCheck(firstName, lastName, age)) {
                val user =
                    User(firstName, lastName, Integer.parseInt(age.toString()), args.currentUser.id)
                mUserViewModel.updateUser(user)
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            } else {
                Toast.makeText(context, "incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputCheck(firstname: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}