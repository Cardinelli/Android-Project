package com.example.androidproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.ui.data.Game
import com.example.androidproject.ui.data.GameRepository
import com.example.androidproject.ui.game.GameRecyclerViewAdapter
import com.example.androidproject.ui.game.GameViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(),
    GameRecyclerViewAdapter.AddButtonClickListener,
    GameRecyclerViewAdapter.ViewButtonClickListener {

    private lateinit var fragmentView: View
    private lateinit var recyclerViewHome: RecyclerView

    private val gameViewModel: GameViewModel by navGraphViewModels(R.id.mobile_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerViewHome = fragmentView.findViewById(R.id.home_recycler_view)

        gameViewModel.gamesLiveData.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                emptyList<Game>()
            } else {
                recyclerViewHome.adapter = context?.let { it1 ->
                    GameRecyclerViewAdapter(
                        it as ArrayList<Game>,
                        it1,
                        false,
                        null,
                        this@HomeFragment,
                        this@HomeFragment,
                    )
                }
            }
        })
        gameViewModel.getGames()
        return fragmentView
    }

    override fun onAddButtonClickListener(game: Game, view: View) {
        context?.let {
            val gameExists = GameRepository.getGameByName(it, game.name)
            if (gameExists == null) {
                GameRepository.insertGame(
                    it, Game(
                        game.name,
                        game.released,
                        game.background_image,
                        game.rating,
                        Firebase.auth.currentUser!!.uid
                    )
                )
                Snackbar.make(view, R.string.add_game_to_favourites, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(view, R.string.game_exists, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewButtonClickListener(game: Game) {
        gameViewModel.postGame(game)
        fragmentView.findNavController().navigate(R.id.action_list_view_to_game_view)
    }
}