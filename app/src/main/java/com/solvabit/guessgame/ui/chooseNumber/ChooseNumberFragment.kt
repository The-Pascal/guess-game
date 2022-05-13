package com.solvabit.guessgame.ui.chooseNumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        binding.lifecycleOwner = this

        initializeButtonClicks()

        return binding.root
    }

    private fun initializeButtonClicks() {
        binding.addButton.setOnClickListener {
            if(binding.editTextView.text.toString().isNotEmpty()) {
                val number = binding.editTextView.text.toString().toInt().plus(1)
                binding.editTextView.setText(number.toString())
            }
            else
                binding.editTextView.setText("0")
        }

        binding.minusButton.setOnClickListener {
            if(binding.editTextView.text.toString().isNotEmpty()) {
                val number = binding.editTextView.text.toString().toInt().minus(1)
                binding.editTextView.setText(number.toString())
            }
            else
                binding.editTextView.setText("0")
        }

        binding.startGameButton.setOnClickListener {
            if(binding.editTextView.text.toString().isNotEmpty()) {
                this.findNavController().navigate(
                    ChooseNumberFragmentDirections.actionChooseNumberFragmentToGuessNumberFragment(
                        binding.editTextView.text.toString().toInt()
                    )
                )
            }
            else {
                Toast.makeText(context, "Please select a number first!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}