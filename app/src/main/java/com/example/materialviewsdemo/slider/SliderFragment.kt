package com.example.materialviewsdemo.slider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialviewsdemo.databinding.FragmentSliderBinding

class SliderFragment: Fragment() {

    private var _binding: FragmentSliderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSliderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем элементы
        val slider = binding.slider
        val switchButton = binding.switchButton

//        // Устанавливаем начальное значение
//        slider.value = 9f



        // Добавляем слушатель для переключателя
        switchButton.setOnCheckedChangeListener { _, isChecked ->
            // Включаем/выключаем слайдер в зависимости от состояния переключателя
            slider.isEnabled = isChecked

            // Меняем текст переключателя
            if (isChecked) {
                binding.valueText.text = "Слайдер включен"
            } else {
                binding.valueText.text = "Слайдер выключен"
            }
        }

        // Кнопка для сброса значения
//        binding.resetButton.setOnClickListener {
//            slider.value = 5f
//            binding.valueText.text = "Значение сброшено: 5"
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}