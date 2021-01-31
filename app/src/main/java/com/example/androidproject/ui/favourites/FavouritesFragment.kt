package com.example.androidproject.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

class FavouritesFragment : Fragment(),
    GameRecyclerViewAdapter.RemoveButtonClickListener,
    GameRecyclerViewAdapter.ViewButtonClickListener {
    private lateinit var fragmentView: View
    private lateinit var recyclerViewFavourites: RecyclerView
    private lateinit var emptyGames: TextView

    private var games: List<Game> = emptyList()

    private val gameViewModel: GameViewModel by navGraphViewModels(R.id.mobile_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        fragmentView = inflater.inflate(R.layout.fragment_favourites, container, false)

        recyclerViewFavourites = fragmentView.findViewById(R.id.favourites_recycler_view)
        emptyGames = fragmentView.findViewById(R.id.empty_games_text)

        gameViewModel.gamesLiveData.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                emptyGames()
            } else {
                emptyGames(false)

                games = it

                recyclerViewFavourites.adapter = context?.let { it1 ->
                    GameRecyclerViewAdapter(
                        it,
                        it1,
                        true,
                        this@FavouritesFragment,
                        null,
                        this@FavouritesFragment
                    )
                }
            }
        })

        val uid = Firebase.auth.currentUser!!.uid
        context?.let { gameViewModel.getGamesByUser(it, uid) }

        return fragmentView
    }

    private fun emptyGames(empty: Boolean = true) {
        recyclerViewFavourites.visibility = if (empty) View.GONE else View.VISIBLE
        emptyGames.visibility = if (empty) View.VISIBLE else View.GONE
    }

    override fun onRemoveButtonClickListener(game: Game, view: View) {
        context?.let {
            GameRepository.deleteGame(it, game)
        }
        Snackbar.make(view, R.string.remove_game_from_favourites, Snackbar.LENGTH_SHORT).show()
    }

    override fun onViewButtonClickListener(game: Game) {
        gameViewModel.postGame(game)
        fragmentView.findNavController().navigate(R.id.action_list_view_to_game_view)
    }
}