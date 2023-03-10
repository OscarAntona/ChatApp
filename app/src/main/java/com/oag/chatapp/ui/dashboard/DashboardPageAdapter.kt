package com.oag.chatapp.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oag.chatapp.ui.calls.CallsFragment
import com.oag.chatapp.ui.chats.ChatsFragment
import com.oag.chatapp.ui.social.SocialFragment

class DashboardPagerAdapter(fragment: Fragment, private val userId: String): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                val chatFragment = ChatsFragment()
                val args = Bundle()
                args.putString("userId", userId)
                chatFragment.arguments = args
                chatFragment
            }
            1 -> { SocialFragment() }
            2 -> { CallsFragment() }
            else -> throw IllegalArgumentException("Invalid fragment position, max value 2")
        }
    }

}