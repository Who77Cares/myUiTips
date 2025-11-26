package com.example.materialviewsdemo.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialviewsdemo.databinding.FragmentCardsBinding
import java.util.Collections


class CardsFragment: Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SelectCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = SelectCardAdapter(
            onItemClick = { position ->
                Toast.makeText(
                    requireContext(),
                    "clicked",
                    Toast.LENGTH_LONG
                ).show()
            }
        )

        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

            // ВСЁ! Добавляем перетаскивание ↓
            ItemTouchHelper(SimpleItemTouchCallback()).attachToRecyclerView(recyclerView)
        }

        setupSelectionTracker()
        adapter.setItems(
            listOf(
                "Первый",
                "Second",
                "Third",
                "Fourth",
                "Five"
            )
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSelectionTracker() {
        val selectionTracker = SelectionTracker.Builder(
            "card_selection",
            binding.recyclerView,
            adapter.KeyProvider(),
            adapter.DetailsLookup(binding.recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .build()

        adapter.selectionTracker = selectionTracker


        selectionTracker.addObserver(
            object : SelectionTracker.SelectionObserver<Long?>() {
                override fun onSelectionChanged() {
                    val selectedCount = selectionTracker.selection.size()
                    if (selectedCount > 0) {
                        Toast.makeText(
                            requireContext(),
                            "Selected: $selectedCount",
                            Toast.LENGTH_SHORT
                        ).show()
                    } } } )
    }

    // Callback для перетаскивания
    inner class SimpleItemTouchCallback : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, // разрешаем двигать вверх/вниз
         ItemTouchHelper.RIGHT // ← ВКЛЮЧАЕМ свайпы влево-вправо
    ) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            // Меняем элементы местами в списке
            val fromPos = viewHolder.bindingAdapterPosition
            val toPos = target.bindingAdapterPosition
            Collections.swap((recyclerView.adapter as SelectCardAdapter).items, fromPos, toPos)
            recyclerView.adapter?.notifyItemMoved(fromPos, toPos)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // Получаем recyclerView из viewHolder
            val recyclerView = viewHolder.itemView.parent as RecyclerView
            val adapter = recyclerView.adapter as SelectCardAdapter
            val position = viewHolder.bindingAdapterPosition

            // Удаляем элемент при свайпе
            adapter.items.removeAt(position)
            adapter.notifyItemRemoved(position)

            Toast.makeText(viewHolder.itemView.context, "Удалено", Toast.LENGTH_SHORT).show()
        }
    }

}