package com.example.waetherapp.ui.kurt.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waetherapp.R
import com.example.waetherapp.data.entity.FavouriteForecast
import kotlinx.android.synthetic.main.item_favourite.view.*
import com.example.waetherapp.ui.kurt.favourite.FavouriteAdapter.MiniForecastHolder
import com.example.waetherapp.utils.extension.withSign

class FavouriteAdapter(private val onLocationChange: (FavouriteForecast) -> Unit) :
    RecyclerView.Adapter<MiniForecastHolder>() {
    private val data = ArrayList<FavouriteForecast>()

    fun updateData(data: List<FavouriteForecast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class MiniForecastHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(favouriteForecast: FavouriteForecast) = with(itemView) {
            temperature.text = favouriteForecast.temperature.value.withSign()
            description.text = favouriteForecast.description
            locationName.text = favouriteForecast.locationName
            weatherIcon.setImageResource(favouriteForecast.imageId)
            card.setOnClickListener {
                onLocationChange(favouriteForecast)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiniForecastHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favourite, parent, false)
        return MiniForecastHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MiniForecastHolder, position: Int) {
        holder.bind(data[position])
    }
}