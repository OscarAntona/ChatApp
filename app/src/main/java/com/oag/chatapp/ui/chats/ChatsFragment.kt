package com.oag.chatapp.ui.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oag.chatapp.data.local.UserHelper
import com.oag.chatapp.databinding.FragmentChatsBinding
import com.oag.chatapp.domain.ext.gone
import com.oag.chatapp.domain.ext.show
import com.oag.chatapp.ui.dashboard.DashboardFragmentDirections


class ChatsFragment() : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val binding: FragmentChatsBinding
        get() = _binding!!

    private val chatListAdapter by lazy {
        ChatListAdapter {
            val action = DashboardFragmentDirections.actionDashboardFragmentToChatFragment(
                chatId = it.id,
                userId = arguments?.getString("userId") ?: throw Exception("Invalid user id"),
                userToTextId = it.userTwoId
            )
            findNavController().navigate(action)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChatsBinding.inflate(inflater, container, false)

        setupRecyclerView()
        handleChatList()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.chatList.apply {
            adapter = chatListAdapter
        }
    }

    private fun handleChatList() {

        val chatList = when(arguments?.getString("userId")?: throw Exception("Invalid user id")) {
            "1" -> UserHelper.antmanChatList
            "2" -> UserHelper.hulkChatList
            "3" -> UserHelper.thorChatList
            "4" -> UserHelper.wandaChatList
            else -> throw Exception("Invalid user id")
        }

        if (chatList.isEmpty()) {
            binding.chatList.gone()
            binding.emptyMessage.show()
        } else {
            binding.emptyMessage.gone()
            binding.chatList.show()

            chatListAdapter.submitList(chatList)
        }
    }

}