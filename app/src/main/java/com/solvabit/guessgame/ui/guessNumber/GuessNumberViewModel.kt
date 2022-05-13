package com.solvabit.guessgame.ui.guessNumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solvabit.guessgame.models.Tile
import com.solvabit.guessgame.utils.Event


class GuessNumberViewModel: ViewModel() {

    private val _tilesList = MutableLiveData<List<Tile>>()
    val tilesList: LiveData<List<Tile>>
        get() = _tilesList

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean>
        get() = _showDialog

    private val _navigateUp = MutableLiveData<Event<Boolean>>()
    val navigateUp: LiveData<Event<Boolean>>
        get() = _navigateUp

    private val _remainingChances = MutableLiveData<Int>()
    val remainingChances: LiveData<Int>
        get() = _remainingChances

    init {
        _remainingChances.value = 3
        _tilesList.value = List(9) {
            Tile(it, null, false)
        }
    }

    fun correctTileSelected(position: Int, chosenNumber: Int): String {
        showDialog(true)
        _tilesList.value?.toMutableList()?.let {
            it[position].isSelected = true
            it[position].text = chosenNumber.toString()
            _tilesList.value = it
        }
        return chosenNumber.toString()
    }

    fun wrongTileSelected(position: Int): String {
        _remainingChances.value = _remainingChances.value?.minus(1)
        if(_remainingChances.value == 0) {
            showDialog(false)
        }
        _tilesList.value?.toMutableList()?.let {
            it[position].isSelected = true
            it[position].text = "Uh huh!"
            _tilesList.value = it
        }
        return "Uh huh!"
    }

    private fun showDialog(isCorrect: Boolean) {
        _showDialog.value = isCorrect
    }

    fun startNavigation() {
        _navigateUp.value = Event(true)
    }
}