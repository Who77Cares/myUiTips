package com.example.materialviewsdemo.carusel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialviewsdemo.databinding.FragmentCaruselBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy

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
            onItemClick = { position, caruselItem ->

                binding.carouselRecyclerView.smoothScrollToPosition(position)

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
            carouselRecyclerView.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
            carouselRecyclerView.setHasFixedSize(true) // Использовать setHasFixedSize(true) когда: Размер RecyclerView фиксирован (например, 200dp);  Размер НЕ зависит от содержимого;  Размер НЕ меняется при добавлении/удалении элементов
            carouselRecyclerView.adapter = adapter



            CarouselSnapHelper().attachToRecyclerView(carouselRecyclerView)
        }

        adapter.setItems(CaruselData.getItems())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}