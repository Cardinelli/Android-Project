package com.example.androidproject.ui.game

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidproject.R
import com.example.androidproject.ui.data.Game

class GameRecyclerViewAdapter(
    private val list: List<Game>,
    private val context: Context,
    private val isFavourites: Boolean,
    private val removeButtonClickListener: RemoveButtonClickListener?,
    private val addButtonClickListener: AddButtonClickListener?,
    private val viewButtonClickListener: ViewButtonClickListener,
) : RecyclerView.Adapter<GameRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_game_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = list[position]


        Glide.with(context)
            .load(game.background_image)
            .into(holder.gameBanner)

        holder.nameTextView.text = game.name

        if (isFavourites) {
            holder.addButton.visibility = View.GONE
            holder.removeButton.visibility = View.VISIBLE
            holder.removeButton.setOnClickListener {
                removeButtonClickListener?.onRemoveButtonClickListener(game, holder.itemView)
            }
        } else {
            holder.removeButton.visibility = View.GONE
            holder.addButton.visibility = View.VISIBLE
            holder.addButton.setOnClickListener {
                addButtonClickListener?.onAddButtonClickListener(game, holder.itemView)
            }
        }

        holder.gameBanner.setOnClickListener {
            viewButtonClickListener.onViewButtonClickListener(game)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.game_text_view)
        val removeButton: Button = view.findViewById(R.id.btn_remove)
        val addButton: Button = view.findViewById(R.id.btn_add)
        val gameBanner: ImageView = view.findViewById(R.id.game_banner)
    }


    interface RemoveButtonClickListener {
        fun onRemoveButtonClickListener(game: Game, view: View)
    }

    interface AddButtonClickListener {
        fun onAddButtonClickListener(game: Game, view: View)
    }

    interface ViewButtonClickListener {
        fun onViewButtonClickListener(game: Game)
    }
}