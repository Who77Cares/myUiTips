package com.example.materialviewsdemo.carusel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.materialviewsdemo.databinding.FragmentCaruselBinding
import com.example.materialviewsdemo.firebase.ItemContent
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CaruselFragment: Fragment() {

    private var _binding: FragmentCaruselBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CaruselAdapter

    private lateinit var firebaseData: ItemContent

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

        lifecycleScope.launch {
            firebaseData = getFirebaseData()

            binding.apply {
                firstTextView.text = firebaseData.description ?: "нет"
                secondTextView.text = firebaseData.youtubeUrl ?: "нет"
                thirdTextView.text = firebaseData.imgUrl ?: "нет"


                Log.d("Data resived", "${firebaseData.description} -- ${firebaseData.imgUrl} -- ${firebaseData.youtubeUrl}")
            }

        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun getFirebaseData(): ItemContent {
        return withContext(Dispatchers.IO) {

            val db = Firebase.firestore

            suspendCoroutine { continuation ->
                db.collection("myStonke")
                    .document("swl92E3cHRb15eL60Gre")
                    .get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            val item = document.toObject<ItemContent>()

                            Log.d("--", "получили $item")

                            continuation.resume(item ?: ItemContent())
                        } else {

                            Log.d("--", "докумен тпуст")

                            continuation.resume(ItemContent())
                        }
                    }
                    .addOnFailureListener { exception ->

                        Log.d("--", "получили exception: ${exception.toString()}")

                        continuation.resume(ItemContent())
                    }
            }
        }
    }

}