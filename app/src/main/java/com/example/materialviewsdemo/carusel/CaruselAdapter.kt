package com.example.materialviewsdemo.carusel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialviewsdemo.R


class CaruselAdapter(
    private val onItemClick: (CaruselItem) -> Unit
) : RecyclerView.Adapter<CaruselAdapter.CaruselViewHolder>() {

    private val items = mutableListOf<CaruselItem>()

    fun setItems(newItems: List<CaruselItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CaruselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.carousel_item, parent, false)
        return CaruselViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: CaruselViewHolder,
        position: Int
    ) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            onItemClick(items[position])
        }
    }

    override fun getItemCount(): Int = items.size


    inner class CaruselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.carousel_image_view)

        fun bind(item: CaruselItem) {
            imageView.setImageResource(item.imgRes)
            imageView.contentDescription = itemView.context.getString(item.descriptionRes)


        }

    }

}

