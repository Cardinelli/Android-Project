package com.example.androidproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.androidproject.R

class GameInfoFragment : Fragment() {
    private lateinit var gameViewName: TextView
    private lateinit var gameReleaseDate: TextView
    private lateinit var gameRating: TextView
    private lateinit var gameBanner: ImageView

    private var game: GameInfo? = null
    private val homeViewModel: HomeViewModel by navGraphViewModels(R.id.mobile_navigation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_view, container, false)

        gameViewName = view.findViewById(R.id.game_name)
        gameRating = view.findViewById(R.id.rating)
        gameReleaseDate = view.findViewById(R.id.release_date)
        gameBanner = view.findViewById(R.id.game_info_banner)

        homeViewModel.gameLiveData.observe(viewLifecycleOwner, {
            with(it) {
                game = this
                gameViewName.text = name
                gameRating.text = rating.toString()
                gameReleaseDate.text = released

                context?.let { it1 ->
                    Glide.with(it1)
                        .load(background_image)
                        .into(gameBanner)
                }
            }
        })
        return view
    }
}