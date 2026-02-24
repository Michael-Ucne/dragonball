package com.phantomshard.dragonball.ui.detail

import com.phantomshard.dragonball.domain.model.DragonBallCharacter

data class DetailUiState(
    val isLoading: Boolean = false,
    val character: DragonBallCharacter? = null,
    val error: String? = null
)
