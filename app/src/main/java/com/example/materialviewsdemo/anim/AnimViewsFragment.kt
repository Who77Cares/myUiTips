package com.example.materialviewsdemo.anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.materialviewsdemo.R
import com.example.materialviewsdemo.databinding.FragmentAnimationsBinding

class AnimViewsFragment: Fragment() {

    private var _binding: FragmentAnimationsBinding? = null
    private val binding get() = _binding!!

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

        binding.image.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        binding.image.animate()
    }

}