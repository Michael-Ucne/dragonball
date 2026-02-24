package com.phantomshard.dragonball.ui.list

import com.phantomshard.dragonball.domain.model.DragonBallCharacter

data class ListUiState(
    val isLoading: Boolean = false,
    val characters: List<DragonBallCharacter> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterGender: String = "",
    val filterRace: String = ""
)
