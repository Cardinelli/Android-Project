package com.example.androidproject.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidproject.R

class HomeRecyclerViewAdapter(
    private val list: ArrayList<GameInfo>,
    private val context: Context,
    private val addButtonClickListener: AddButtonClickListener
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = list[position]

        holder.gameTitle.text = game.name

        Glide.with(context)
            .load(game.background_image)
            .into(holder.itemView.findViewById(R.id.game_banner))

        holder.add_btn.setOnClickListener {
            Log.d("55555", "some shit here")
        }

    }

    override fun getItemCount(): Int = list.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val add_btn: Button = view.findViewById(R.id.btn_add)
        var gameTitle: TextView = view.findViewById(R.id.home_text_view)
    }

    interface AddButtonClickListener {
        fun onAddButtonClickListener(game: GameInfo)
    }
}