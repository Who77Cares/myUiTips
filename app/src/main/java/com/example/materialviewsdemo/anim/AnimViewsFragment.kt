package com.example.materialviewsdemo.anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.materialviewsdemo.R
import com.example.materialviewsdemo.databinding.FragmentAnimationsBinding

class AnimViewsFragment: Fragment() {

    private var _binding: FragmentAnimationsBinding? = null
    private val binding get() = _binding!!

    private var isPulsating = false
    private val images = mutableListOf<ImageView>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentAnimationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.image1.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        binding.image1.animate()

        binding.image3.postDelayed({
            startPulseAnimation()
        }, 4000)
    }

    private fun startPulseAnimation() {
        if (isPulsating) return // уже пульсирует

        isPulsating = true

        // Функция для одного цикла пульсации
        fun pulseCycle() {
            binding.image2.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(300)
                .withEndAction {
                    binding.image2.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(300)
                        .withEndAction {
                            // Если все еще пульсируем - повторяем
                            if (isPulsating) {
                                pulseCycle()
                            }
                        }
                }
        }

        pulseCycle() // запускаем первый цикл
    }

}