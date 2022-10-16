package com.pakevankeren.coldstar.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import com.pakevankeren.coldstar.R
import com.pakevankeren.coldstar.databinding.CardGenreBinding
import com.pakevankeren.coldstar.model.Genre
import com.pakevankeren.coldstar.view.MovieDetailActivity


class GenreAdapter(private val genreList: List<Genre>) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreTitle: TextView


        private val genreCardView: CardGenreBinding = CardGenreBinding.bind(view)

        init {
            genreTitle = genreCardView.genreTitle
        }


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_genre, viewGroup, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genreTitle.text = genreList[position].name
    }

    override fun getItemCount() = genreList.size
}



