package com.phantomshard.dragonball.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phantomshard.dragonball.data.remote.Resource
import com.phantomshard.dragonball.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListUiState())
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.UpdateFilters -> {
                _state.update {
                    it.copy(
                        filterName = event.name,
                        filterGender = event.gender,
                        filterRace = event.race
                    )
                }
            }
            ListEvent.Search -> loadCharacters()
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val current = _state.value

            val result = getCharactersUseCase(
                name = current.filterName.takeIf { it.isNotBlank() },
                gender = current.filterGender.takeIf { it.isNotBlank() },
                race = current.filterRace.takeIf { it.isNotBlank() }
            )

            when (result) {
                is Resource.Success ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            characters = result.data ?: emptyList()
                        )
                    }

                is Resource.Error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }

                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
            }
        }
    }
}
