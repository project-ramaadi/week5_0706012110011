package com.pakevankeren.coldstar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pakevankeren.coldstar.R
import com.pakevankeren.coldstar.databinding.CardSpokenLanguageBinding
import com.pakevankeren.coldstar.model.SpokenLanguage

class SpokenLanguageAdapter(private val spokenLanguage: List<SpokenLanguage>) :
    RecyclerView.Adapter<SpokenLanguageAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val spokenLanguageText: TextView
        val spokenLanguageLanguageCode: TextView

        private val languageCard: CardSpokenLanguageBinding = CardSpokenLanguageBinding.bind(view)

        init {
            spokenLanguageText = languageCard.spokenLanguageLanguageText
            spokenLanguageLanguageCode = languageCard.spokenLanguageLanguageCode
        }


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_spoken_language, viewGroup, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.spokenLanguageText.text = spokenLanguage[position].english_name
        holder.spokenLanguageLanguageCode.text = spokenLanguage[position].iso_639_1.uppercase()
    }

    override fun getItemCount() = spokenLanguage.size
}



