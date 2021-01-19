package com.example.androidproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R

class HomeFragment : Fragment() {
    private lateinit var fragmentView: View
    private lateinit var recyclerViewHome: RecyclerView

    private val homeViewModel: HomeViewModel by navGraphViewModels(R.id.mobile_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerViewHome = fragmentView.findViewById(R.id.home_recycler_view)


        homeViewModel.gamesLiveData.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                emptyList<GameInfo>()
            } else {
                recyclerViewHome.layoutManager = LinearLayoutManager(context)
                recyclerViewHome.adapter = context?.let { it1 ->
                    HomeRecyclerViewAdapter(
                        it as ArrayList<GameInfo>,
                        it1
                    )
                }
            }
        })
        homeViewModel.getGames()
        return fragmentView
    }
}