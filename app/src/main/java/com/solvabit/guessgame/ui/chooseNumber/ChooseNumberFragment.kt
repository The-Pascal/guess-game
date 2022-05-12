package com.solvabit.guessgame.ui.chooseNumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.solvabit.guessgame.R
import com.solvabit.guessgame.databinding.FragmentChooseNumberBinding


class ChooseNumberFragment : Fragment() {

    private lateinit var binding: FragmentChooseNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseNumberBinding.inflate(inflater)

        initializeNumberPicker()

        initializeButtonClicks()

        return binding.root
    }

    private fun initializeButtonClicks() {
        binding.startGameButton.setOnClickListener {
            this.findNavController().navigate(
                ChooseNumberFragmentDirections.actionChooseNumberFragmentToGuessNumberFragment(
                    binding.numberPicker.value
                )
            )
        }
    }

    private fun initializeNumberPicker() {
        binding.numberPicker.apply {
            minValue = 0
            maxValue = 100
        }
    }
}