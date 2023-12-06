package com.amoon.bowlinggame.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoon.bowlinggame.domain.usecase.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class ScoresViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    private val _userScores = databaseUseCase.getAllScores().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val userScores = _userScores

}
