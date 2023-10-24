package com.example.room_db.baseFragment

import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs

open class BaseFragment : Fragment() {

    open fun showDeleteDialog(){
        val dialog = MaterialAlertDialogBuilder(requireContext())

    }

}