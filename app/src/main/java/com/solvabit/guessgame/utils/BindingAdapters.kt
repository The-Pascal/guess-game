package com.solvabit.guessgame.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
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

@BindingAdapter("bind_remaining", "bind_position")
fun bindHeartImage(imageView: ImageView, remaining: Int?, position: Int) {
    remaining?.let {
        if(position>remaining)
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.grey))
        else
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.red_800))
    }
}
