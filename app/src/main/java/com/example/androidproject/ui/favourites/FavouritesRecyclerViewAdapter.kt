package com.example.androidproject.ui.favourites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidproject.R
import com.example.androidproject.ui.data.Game

class FavouritesRecyclerViewAdapter(
    private val list: List<Game>,
    private val context: Context
) : RecyclerView.Adapter<FavouritesRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_favourites_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val game = list[position]

//        Glide.with(context)
//            .load(game.background_image)
//            .into(holder.itemView.findViewById(R.id.game_banner))


//        holder.itemView.findViewById<ImageView>(R.id.favourites_game_banner).setOnClickListener {
//            holder.itemView.context.startActivity(
//                Intent(holder.itemView.context, HomeInfoViewHolder::class.java).apply {
//                    putExtra("game_pos", position.toString())
//                }
//            )
//        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.favourites_text_view)
    }
}