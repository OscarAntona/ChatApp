package com.oag.chatapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.oag.chatapp.data.local.UserHelper
import com.oag.chatapp.databinding.FragmentUserSelectionBinding


class UserSelectionFragment : Fragment() {
    private var _binding: FragmentUserSelectionBinding? = null
    private val binding: FragmentUserSelectionBinding
        get() = _binding!!
    private val userListAdapter = UserListAdapter( ) {
        Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        val action  = UserSelectionFragmentDirections.actionUserSelectionFragmentToDashboardFragment(userId = it.id)
        findNavController().navigate(action)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentUserSelectionBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }
    private fun setupRecyclerView(){
        binding.userList.apply {
            adapter = userListAdapter
            layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL, false)
        }
    userListAdapter.submitList(UserHelper.usersList)
    }
}