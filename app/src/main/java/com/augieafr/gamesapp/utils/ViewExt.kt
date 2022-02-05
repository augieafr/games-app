package com.augieafr.gamesapp.utils

import android.view.View
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> viewBindingWithBinder(binder: (View) -> T) =
    FragmentAutoClearedBinding(binder)
