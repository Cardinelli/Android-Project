package com.example.androidproject.ui.favourites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidproject.R
import com.example.androidproject.ui.data.Game

class FavouritesRecyclerViewAdapter(
    private val list: List<Game>,
    private val context: Context,
    private val removeButtonClickListener: RemoveButtonClickListener
) : RecyclerView.Adapter<FavouritesRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_favourites_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = list[position]
        holder.nameTextView.text = game.name

        Glide.with(context)
            .load(game.background_image)
            .into(holder.itemView.findViewById(R.id.favourites_game_banner))

        holder.removeButton.setOnClickListener {
            removeButtonClickListener.onRemoveButtonClickListener(game, holder.itemView)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.favourites_text_view)
        val removeButton: Button = view.findViewById(R.id.btn_remove)
    }

    interface RemoveButtonClickListener {
        fun onRemoveButtonClickListener(game: Game, view: View)
    }
}