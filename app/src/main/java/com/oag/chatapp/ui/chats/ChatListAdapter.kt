package com.oag.chatapp.ui.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.oag.chatapp.databinding.ItemChatBinding
import com.oag.chatapp.domain.model.Chat
import com.oag.chatapp.ui.common.BaseListViewHolder

class ChatListAdapter(
    private val onChatClick: (Chat) -> Unit,
): ListAdapter<Chat, BaseListViewHolder<*>>(DiffUtilCallback)  {

    private object DiffUtilCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolder -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolder(private val binding: ItemChatBinding) : BaseListViewHolder<Chat>(binding.root) {

        override fun bind(item: Chat, position: Int) = with(binding) {

            chatName.text = item.userTwo
            chatImage.setImageResource(item.userImage)

            chatParent.setOnClickListener { onChatClick.invoke(item) }
        }
    }
}