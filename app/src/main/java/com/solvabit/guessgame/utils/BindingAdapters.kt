package com.solvabit.guessgame.utils

import android.util.Log
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solvabit.guessgame.R
import com.solvabit.guessgame.models.Tile
import com.solvabit.guessgame.ui.guessNumber.TilesRecycleAdapter

private const val TAG = "BindingAdapters"

@BindingAdapter("tilesList")
fun bindTilesRecyclerView(recyclerView: RecyclerView, data: List<Tile>?) {
    val adapter = recyclerView.adapter as TilesRecycleAdapter
    adapter.submitList(data)
}

@BindingAdapter("cardBackground")
fun bindCardBackground(cardView: CardView, tile: Tile?) {
    tile?.let {
//        cardView.setCardBackgroundColor(R.color.teal_700)
        Log.i(TAG, "bindCardBackground: ${tile.position} + ${tile.isSelected}")
    }
}