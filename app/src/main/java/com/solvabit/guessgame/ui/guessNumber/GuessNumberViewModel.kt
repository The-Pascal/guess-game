package com.solvabit.guessgame.ui.guessNumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solvabit.guessgame.models.Tile


class GuessNumberViewModel: ViewModel() {

    private val _tilesList = MutableLiveData<List<Tile>>()
    val tilesList: LiveData<List<Tile>>
        get() = _tilesList

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean>
        get() = _showDialog

    private var remainingChances = 3

    init {
        _tilesList.value = List(9) {
            Tile(it, null, false)
        }
    }

    fun correctTileSelected(position: Int, chosenNumber: Int): String {
        _tilesList.value?.toMutableList()?.let {
            it[position].isSelected = true
            it[position].text = chosenNumber.toString()
            _tilesList.value = it
        }
        remainingChances = remainingChances.minus(1)
        startNavigation(true)
        return chosenNumber.toString()
    }

    fun wrongTileSelected(position: Int): String {
        _tilesList.value?.toMutableList()?.let {
            it[position].isSelected = true
            it[position].text = "Uh huh!"
            _tilesList.value = it
        }
        remainingChances = remainingChances.minus(1)
        if(remainingChances<=0) {
            startNavigation(false)
        }
        return "Uh huh!"
    }

    private fun startNavigation(isCorrect: Boolean) {
        _showDialog.value = isCorrect
    }
}