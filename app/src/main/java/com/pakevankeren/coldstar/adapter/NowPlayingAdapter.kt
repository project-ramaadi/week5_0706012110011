package com.pakevankeren.coldstar.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pakevankeren.coldstar.R
import com.pakevankeren.coldstar.config.RetrofitConfig
import com.pakevankeren.coldstar.databinding.CardNowPlayingBinding
import com.pakevankeren.coldstar.model.NowPlayingResult
import com.pakevankeren.coldstar.view.MovieDetailActivity

class NowPlayingAdapter(private val nowPlayingDataset: ArrayList<NowPlayingResult>) :
    RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val metaTextView: TextView
        val nowPlayingCard: CardView

        private val movieCard: CardNowPlayingBinding = CardNowPlayingBinding.bind(view)

        init {
            titleTextView = movieCard.nowPlayingMovieTitle
            metaTextView = movieCard.nowPlayingMetaText
            nowPlayingCard = movieCard.nowPlayingCardView
        }

        fun openDetailView(context: Context) {
            val intent = Intent(context, MovieDetailActivity::class.java)
                .putExtra("movieid", nowPlayingDataset[adapterPosition].id)
            context.startActivity(intent)
        }

        fun loadImage(view: View) {
            Glide.with(view.context)
                .load(RetrofitConfig.IMG_BASE_URL + nowPlayingDataset[adapterPosition].poster_path)
                .into(movieCard.nowPlayingPosterImage)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_now_playing, viewGroup, false)

        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {


        viewHolder.titleTextView.text = nowPlayingDataset[position].title
        viewHolder.metaTextView.text =
            "${nowPlayingDataset[position].release_date} - Airing now"
        viewHolder.loadImage(viewHolder.itemView)
        viewHolder.nowPlayingCard.setOnClickListener {
            viewHolder.openDetailView(it.context)
        }

    }

    override fun getItemCount() = nowPlayingDataset.size
}
