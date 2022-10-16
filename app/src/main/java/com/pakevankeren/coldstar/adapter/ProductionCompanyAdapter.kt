package com.pakevankeren.coldstar.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pakevankeren.coldstar.R
import com.pakevankeren.coldstar.databinding.CardGenreBinding
import com.pakevankeren.coldstar.databinding.CardProductionCompanyBinding
import com.pakevankeren.coldstar.model.Genre
import com.pakevankeren.coldstar.model.ProductionCompany
import com.pakevankeren.coldstar.utils.GlideUtils

class ProductionCompanyAdapter (private val companies: List<ProductionCompany>, private val context: Context) :
    RecyclerView.Adapter<ProductionCompanyAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyLogo: ImageButton


        private val companyLogoCardView: CardProductionCompanyBinding = CardProductionCompanyBinding.bind(view)

        init {
            companyLogo = companyLogoCardView.productionCompanyLogo
        }


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_production_company, viewGroup, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val company = companies[position]

        Glide
            .with(context)
            .load(GlideUtils.companyImage(company.logo_path, company.name))
            .into(holder.companyLogo)

        holder.companyLogo.setOnClickListener {
            Toast.makeText(context, company.name, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount() = companies.size
}



