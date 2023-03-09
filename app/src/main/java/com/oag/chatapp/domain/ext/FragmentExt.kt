package com.oag.chatapp.domain.ext

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}