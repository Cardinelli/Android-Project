package com.example.androidproject.ui.auth

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.androidproject.R
import java.util.*

class AuthTabsPageAdapter(
    fragmentManager: FragmentManager,
    val context: Context
) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> RegisterFragment()
        else -> LoginFragment()
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> context.resources.getString(R.string.tab_register).toUpperCase(Locale.ROOT)
        else -> context.resources.getString(R.string.tab_login).toUpperCase(Locale.ROOT)
    }
}