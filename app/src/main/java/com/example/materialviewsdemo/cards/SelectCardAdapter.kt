package com.example.materialviewsdemo.cards

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.example.materialviewsdemo.R
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.channels.ReceiveChannel

class SelectCardAdapter(
    private val onItemClick: (Int) -> Unit
): RecyclerView.Adapter<SelectCardAdapter.SelectCardViewHolder>()

 {
     private val items = mutableListOf<String>()


     // Следит за тем, какие карточки выбраны, запоминает их и сообщает когда выбор меняется.
     var selectionTracker: SelectionTracker<Long?>? = null

     fun setItems(newItems: List<String>) {
         items.clear()
         items.addAll(newItems)
         notifyDataSetChanged()
     }

     override fun onCreateViewHolder(
         parent: ViewGroup,
         viewType: Int
     ): SelectCardViewHolder {
         val view = LayoutInflater.from(parent.context)
             .inflate(R.layout.item_card, parent, false)
         return SelectCardViewHolder(view)
     }

     override fun onBindViewHolder(
         holder: SelectCardViewHolder,
         position: Int
     ) {
         holder.bind(items[position])

         holder.itemView.setOnClickListener {
             onItemClick(position)
         }



         selectionTracker?.let { tracker ->
             holder.updateSelectionState(tracker.isSelected(position.toLong()))
         }
     }


     override fun getItemCount(): Int = items.size

     inner class SelectCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         private val cardView: MaterialCardView = itemView.findViewById(R.id.item_card)
         private val titleView: TextView = itemView.findViewById(R.id.cat_card_title)


         fun bind(itemText: String) {
             titleView.text = itemText
         }

         fun updateSelectionState(isSelected: Boolean) {
             cardView.isChecked = isSelected
         }

         fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> = object : ItemDetailsLookup.ItemDetails<Long>() {

             override fun getPosition(): Int = bindingAdapterPosition

             override fun getSelectionKey(): Long = bindingAdapterPosition.toLong()

             override fun inSelectionHotspot(e: MotionEvent): Boolean = false // Говорит системе "НЕ выделяй меня при обычном тапе, жди долгого нажатия".
             override fun inDragRegion(e: MotionEvent): Boolean = false

         }
    }

     //  Когда ты касаешься экрана, этот класс определяет - по какой именно карточке ты тапнул.
     inner class DetailsLookup(private val recyclerView: RecyclerView): ItemDetailsLookup<Long>() {
         override fun getItemDetails(e: MotionEvent): ItemDetailsLookup.ItemDetails<Long>? {
             val view = recyclerView.findChildViewUnder(e.x, e.y)
             val holder = view?.let { recyclerView.getChildViewHolder(it) as? SelectCardViewHolder }
             return holder?.getItemDetails()
         }

     }

     // Дает каждой карточке уникальный номер (0, 1, 2, 3...). Без этого система не поймет, какую именно карточку ты выбрал.
     inner class KeyProvider : ItemKeyProvider<Long?>(SCOPE_MAPPED) {
         override fun getKey(position: Int): Long? = position.toLong()
         override fun getPosition(key: Long?): Int = key?.toInt() ?: -1
     }

}

/**
 * 🔄 Как это работает вместе:
 * Ты долго жмешь на карточку
 *
 * DetailsLookup определяет какая карточка затронута
 *
 * KeyProvider говорит "Это карточка №3"
 *
 * SelectionTracker запоминает "Карточка №3 выбрана"
 *
 * Observer видит изменение и показывает "Selected: 1"
 */