package com.example.materialviewsdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.materialviewsdemo.databinding.FragmentContainerBinding

class ContainerFragment: Fragment() {

    private var _binding: FragmentContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goToCaruselButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_containerFragment_to_caruselFragment
            )
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}