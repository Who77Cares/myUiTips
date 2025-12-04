package com.example.materialviewsdemo.slider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.materialviewsdemo.databinding.FragmentSliderIconBinding


class IconSliderFragment: Fragment() {

    private var _binding: FragmentSliderIconBinding? = null
    private val binding get() = _binding!!

    // Флаг для отслеживания ориентации
    private var isVertical = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSliderIconBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupSliders()
        setupSwitch()



    }

    private fun setupSwitch() {
        binding.switchEnable.setOnCheckedChangeListener { _, isChecked ->
            // Включаем/выключаем все слайдеры
            binding.sliderIconStart.isEnabled = isChecked
            binding.sliderIconEnd.isEnabled = isChecked
            binding.sliderIconStartEnd.isEnabled = isChecked
            binding.sliderIconCustom.isEnabled = isChecked

            binding.tvStatus.text = if (isChecked) "Слайдеры включены" else "Слайдеры выключены"
        }
    }




    private fun setupSliders() {
        // Настройка первого слайдера с иконкой в начале
        binding.sliderIconStart.apply {
            addOnChangeListener { _, value, fromUser ->
                if (fromUser) {
                    binding.tvValue1.text = "Начало: ${String.format("%.1f", value)}"
                }
            }
        }

        // Настройка второго слайдера с иконкой в конце
        binding.sliderIconEnd.apply {
            addOnChangeListener { _, value, fromUser ->
                if (fromUser) {
                    binding.tvValue2.text = "Конец: ${String.format("%.1f", value)}"
                }
            }
        }

        // Настройка третьего слайдера с иконками с обеих сторон
        binding.sliderIconStartEnd.apply {
            addOnChangeListener { _, value, fromUser ->
                if (fromUser) {
                    binding.tvValue3.text = "Обе стороны: ${String.format("%.1f", value)}"
                }
            }
        }

        // Настройка четвертого слайдера (кастомные цвета)
        binding.sliderIconCustom.apply {
            addOnChangeListener { _, value, fromUser ->
                if (fromUser) {
                    binding.tvValue4.text = "Кастомный: ${String.format("%.1f", value)}"
                }
            }
        }

        // Кнопка переворота

        }

}