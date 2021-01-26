package com.example.androidproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.ui.data.Game
import com.example.androidproject.ui.data.GameRepository
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(),
    HomeRecyclerViewAdapter.AddButtonClickListener {

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
                recyclerViewHome.adapter = context?.let { it1 ->
                    HomeRecyclerViewAdapter(
                        it as ArrayList<GameInfo>,
                        it1,
                        this@HomeFragment
                    )
                }
            }
        })

        homeViewModel.getGames()
        return fragmentView
    }

    override fun onAddButtonClickListener(game: GameInfo, view: View) {
        context?.let {
            val gameExists = GameRepository.getGameByName(it, game.name)
            if (gameExists == null) {
                GameRepository.insertGame(
                    it, Game(
                        game.name,
                        game.released,
                        game.background_image,
                        game.rating,
                    )
                )
                Snackbar.make(view, R.string.add_game_to_favourites, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(view, R.string.game_exists, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}