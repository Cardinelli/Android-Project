package com.example.androidproject.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidproject.R

class HomeRecyclerViewAdapter(
    private val list: ArrayList<GameInfo>,
    private val context: Context,
) : RecyclerView.Adapter<HomeInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_item, null)
        return HomeInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeInfoViewHolder, position: Int) {
        holder.setContent(list[position])

        Glide.with(context)
            .load(list[position].background_image)
            .into(holder.itemView.findViewById(R.id.game_banner))

        holder.itemView.findViewById<ImageView>(R.id.game_banner).setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, HomeInfoViewHolder::class.java).apply {
                    putExtra("game_pos", position.toString())
                }
            )
        }
    }

    override fun getItemCount(): Int = list.size
}


class HomeInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView = itemView.findViewById<TextView>(R.id.home_text_view)

    fun setContent(gameInfo: GameInfo) {
        with(gameInfo) {
            nameTextView.text = name
        }
    }
}