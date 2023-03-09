package com.oag.chatapp.ui.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oag.chatapp.databinding.FragmentSocialBinding


class SocialFragment : Fragment() {
    private var _binding: FragmentSocialBinding? = null
    private val binding: FragmentSocialBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSocialBinding.inflate(inflater, container, false)
        return binding.root
    }
}