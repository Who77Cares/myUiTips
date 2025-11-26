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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materialviewsdemo.databinding.FragmentCardsBinding


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

}