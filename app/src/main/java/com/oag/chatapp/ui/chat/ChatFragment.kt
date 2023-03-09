package com.oag.chatapp.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.firebase.Timestamp
import com.oag.chatapp.data.local.UserHelper
import com.oag.chatapp.databinding.FragmentChatBinding
import com.oag.chatapp.domain.ext.dpToPx
import com.oag.chatapp.domain.ext.hideKeyboard
import com.oag.chatapp.domain.model.Message
import com.oag.chatapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding: FragmentChatBinding
        get() = _binding!!

    private val args: ChatFragmentArgs by navArgs()
    private val viewModel: ChatViewModel by viewModels()

    private val messagesListAdapter by lazy {
        MessageListAdapter(args.userId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDetach() {
        super.onDetach()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        setupRecyclerView()
        subscribeViewModel()
        setupClickListeners()
        setupUserInformation()
        setupMessageListener()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.messageList.apply {
            adapter = messagesListAdapter
            addItemDecoration(VerticalMarginItemDecoration(requireContext().dpToPx(8)))
        }
    }

    private fun setupClickListeners() {
        binding.backButton.setOnClickListener { activity?.onBackPressed() }
        binding.bSendMessage.setOnClickListener { handleSendMessage() }
    }

    private fun subscribeViewModel() {
        viewModel.messagesListState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> {
                    handleMessages(messages = state.data)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    handleLoading(isLoading = true)
                }
                else -> Unit
            }
        }

        viewModel.sendMessageState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> clearMessage()
                is Resource.Error -> clearMessage()
                else -> Unit
            }
        }
    }

    private fun setupUserInformation() {

        val userToText = UserHelper.usersList.find {
            it.id == args.userToTextId
        }?: throw Exception("Invalid chat id")

        with(binding) {
            chatImage.setImageResource(userToText.image)
            chatName.text = userToText.name
        }
    }

    private fun setupMessageListener() {
        with(binding) {
            etMessage.addTextChangedListener {
                llSendMessage.isVisible = it.toString().isNotEmpty()
            }

            etMessage.setOnEditorActionListener { textView, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    handleSendMessage()
                }
                true
            }
        }
    }

    private fun handleSendMessage() {
        hideKeyboard()
        viewModel.sendMessage(
            Message(
                chatId = args.chatId,
                message = binding.etMessage.text.toString(),
                senderId = args.userId,
                timestamp = Timestamp.now()
            )
        )
    }

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            pbMessages.isVisible = isLoading
        }
    }

    private fun handleMessages(messages: List<Message>) {
        if (messages.isEmpty()) {
            showEmptyScreen()
        } else {
            messagesListAdapter.submitList(messages)
            showMessages()
        }
    }

    private fun showEmptyScreen() {
        handleLoading(isLoading = false)
        with(binding) {
            messageList.isVisible = false
            tvEmptyMessages.isVisible = true
        }
    }

    private fun showMessages() {
        handleLoading(isLoading = false)
        with(binding) {
            tvEmptyMessages.isVisible = false
            messageList.isVisible = true
        }
    }

    private fun clearMessage() {
        binding.etMessage.text.clear()
        binding.messageList.smoothScrollToPosition(0)
    }

}