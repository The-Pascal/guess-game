package com.solvabit.guessgame.ui.guessNumber

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solvabit.guessgame.databinding.TilesCardBinding
import com.solvabit.guessgame.models.Tile


class TilesRecycleAdapter : ListAdapter<Tile, TilesRecycleAdapter.ViewHolder>(TilesDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TilesRecycleAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TilesCardBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TilesRecycleAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: TilesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Tile) {
            binding.tile = item
            binding.tileCard.setOnClickListener {
                tileSelectedListener.onTileSelected(item, binding)
            }
        }

    }

    interface TileSelectedListener {
        fun onTileSelected(tile: Tile, tilesCardBinding: TilesCardBinding)
    }

    lateinit var tileSelectedListener: TileSelectedListener
}

class TilesDiffCallback : DiffUtil.ItemCallback<Tile>() {
    override fun areItemsTheSame(oldItem: Tile, newItem: Tile): Boolean {
        return oldItem.position == oldItem.position
    }

    override fun areContentsTheSame(oldItem: Tile, newItem: Tile): Boolean {
        return oldItem == newItem
    }

}