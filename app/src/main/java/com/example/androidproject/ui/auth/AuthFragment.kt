package com.example.androidproject.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.androidproject.R
import com.google.android.material.tabs.TabLayout

class AuthFragment : Fragment() {
    private lateinit var pageAdapter: AuthTabsPageAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let {
            pageAdapter = AuthTabsPageAdapter(childFragmentManager, it)
            tabLayout = view.findViewById(R.id.tab_layout)
            viewPager = view.findViewById(R.id.view_pager)

            viewPager.adapter = pageAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }
}