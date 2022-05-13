package com.solvabit.guessgame.ui.guessNumber

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.solvabit.guessgame.R
import com.solvabit.guessgame.databinding.CustomDialogBinding
import com.solvabit.guessgame.databinding.FragmentGuessNumberBinding
import com.solvabit.guessgame.databinding.TilesCardBinding
import com.solvabit.guessgame.models.Tile
import kotlin.random.Random


private const val TAG = "GuessNumberFragment"

class GuessNumberFragment : Fragment() {

    private val args: GuessNumberFragmentArgs by navArgs()
    private lateinit var binding: FragmentGuessNumberBinding
    private lateinit var viewModel: GuessNumberViewModel
    private val randPosition = Random.nextInt(1, 10)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuessNumberBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[GuessNumberViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = TilesRecycleAdapter()
        binding.tilesRecyclerView.adapter = adapter
        adapter.tileSelectedListener = object : TilesRecycleAdapter.TileSelectedListener {
            override fun onTileSelected(tile: Tile, tilesCardBinding: TilesCardBinding) {
                when (tile.position) {
                    randPosition -> {
                        onCorrectTileSelected(tile, tilesCardBinding)
                    }
                    else -> {
                        onWrongTileSelected(tile, tilesCardBinding)
                    }
                }
            }
        }

        viewModel.showDialog.observe(viewLifecycleOwner, Observer {
            it?.let { isCorrect ->
                showWinDialog(isCorrect, requireContext())
            }
        })

        return binding.root
    }

    private fun showWinDialog(isCorrect: Boolean, context: Context) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        val dialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(dialogBinding.root)
        when (isCorrect) {
            true -> {
                dialogBinding.headingDialog.text = resources.getString(R.string.you_win)
                dialogBinding.animationView.setAnimation(R.raw.trophy_animation)
            }
            false -> {
                dialogBinding.headingDialog.text = resources.getString(R.string.you_lose)
                dialogBinding.animationView.setAnimation(R.raw.sad_emoji_lose_animation)
            }
        }
        dialogBinding.buttonDialog.text = resources.getString(R.string.play_again)
        dialogBinding.buttonDialog.setOnClickListener {
            this.findNavController().popBackStack()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun onCorrectTileSelected(tile: Tile, tilesCardBinding: TilesCardBinding) {
        tilesCardBinding.tileCard.setBackgroundColor(resources.getColor(R.color.green))
        tilesCardBinding.textViewTile.text =
            viewModel.correctTileSelected(tile.position, args.chosenNumber)
    }

    private fun onWrongTileSelected(tile: Tile, tilesCardBinding: TilesCardBinding) {
        if(viewModel.tilesList.value?.get(tile.position)?.isSelected == true) {
            Snackbar.make(binding.root, "Please select some other tile!", Snackbar.LENGTH_SHORT)
                .show()
            Log.i(TAG, "onWrongTileSelected: ${viewModel.tilesList.value?.get(tile.position)?.isSelected}")
            return
        }
        tilesCardBinding.tileCard.setBackgroundResource(R.drawable.tile_active_background)
        tilesCardBinding.textViewTile.text = viewModel.wrongTileSelected(tile.position)
    }
}