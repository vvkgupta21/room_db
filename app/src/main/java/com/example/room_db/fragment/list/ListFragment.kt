package com.example.room_db.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room_db.R
import com.example.room_db.viewmodel.UserViewModel
import com.example.room_db.adapter.UserAdapter
import com.example.room_db.baseFragment.BaseFragment
import com.example.room_db.databinding.FragmentListBinding

class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        initAdapter()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        mUserViewModel.readAllData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        return binding.root
    }

    private fun initAdapter(){
        adapter = UserAdapter()
        binding.rv.adapter = adapter
    }
}