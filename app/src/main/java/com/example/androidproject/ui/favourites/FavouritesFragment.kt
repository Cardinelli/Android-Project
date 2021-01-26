package com.example.androidproject.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.ui.data.Game
import com.example.androidproject.ui.home.HomeRecyclerViewAdapter

class FavouritesFragment : Fragment() {
    private lateinit var fragmentView: View
    private lateinit var recyclerViewFavourites: RecyclerView

    private var games: List<Game> = emptyList()

    private val favouritesViewModel: FavouritesViewModel by navGraphViewModels(R.id.mobile_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        fragmentView = inflater.inflate(R.layout.fragment_favourites, container, false)

        recyclerViewFavourites = fragmentView.findViewById(R.id.favourites_recycler_view)

        favouritesViewModel.gamesLiveData.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                emptyGames()
            } else {
                emptyGames(false)

                games = it

                recyclerViewFavourites.adapter = context?.let { it1 ->
                    FavouritesRecyclerViewAdapter(
                        it,
                        it1
                    )
                }
            }
        })

        context?.let { favouritesViewModel.getGames(it) }

        return fragmentView
    }

    private fun emptyGames(empty: Boolean = true) {
        recyclerViewFavourites.visibility = if (empty) View.GONE else View.VISIBLE
    }
}