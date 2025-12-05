package com.example.materialviewsdemo.anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.materialviewsdemo.R
import com.example.materialviewsdemo.databinding.FragmentAnimBinding

class AnimFragment: Fragment() {

    private var _binding: FragmentAnimBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // ростое появаление картинки
        binding.imageCat.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.imageCat.animate()

        // ростое появаление картинки
        binding.imageCat.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)
        binding.imageCat.animate()

    }
}