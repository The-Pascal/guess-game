package com.solvabit.guessgame.models

data class Tile(
    var position: Int,
    var text: String?,
    var isSelected: Boolean = false
)