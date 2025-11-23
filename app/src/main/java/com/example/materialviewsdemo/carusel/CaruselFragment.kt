package com.example.materialviewsdemo.carusel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialviewsdemo.R
import com.example.materialviewsdemo.databinding.FragmentCaruselBinding
import com.google.android.material.carousel.CarouselLayoutManager

class CaruselFragment: Fragment() {

    private var _binding: FragmentCaruselBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CaruselAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = CaruselAdapter(
            onItemClick = { caruselItem ->

                Toast.makeText(
                    requireContext(),
                    requireContext().getString(caruselItem.descriptionRes),
                    Toast.LENGTH_LONG).show()

            }
        )

     _binding = FragmentCaruselBinding.inflate(inflater, container, false)
     return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            carouselRecyclerView.layoutManager = CarouselLayoutManager()
            carouselRecyclerView.setHasFixedSize(true)
            carouselRecyclerView.adapter = adapter
        }

        adapter.setItems(CaruselData.getItems())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}