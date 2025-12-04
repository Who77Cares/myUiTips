package com.example.materialviewsdemo.anim

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.transition.Visibility
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

        binding.image2.postDelayed({
            startPulseAnimation()
        }, 4000)

        binding.image3.animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_from_left)
        binding.image3.animate()

        // Через некоторое время или по событию - анимация "выхода" (справа налево)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.image3.animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_to_left)
            binding.image3.animate()
            binding.image3.visibility = View.INVISIBLE
        }, 4000)


        binding.image4.animation = AnimationUtils.loadAnimation(context, R.anim.rotate_360)
        binding.image4.animate()


        // огика кривая но работает (скейлы - маштабы

        Handler(Looper.getMainLooper()).postDelayed({
            binding.image5.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up)
            binding.image5.animate()
        }, 2000)



        Handler(Looper.getMainLooper()).postDelayed({
            binding.image5.animation = AnimationUtils.loadAnimation(context, R.anim.scale_in)
            binding.image5.animate()
        }, 6000)


        ///



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