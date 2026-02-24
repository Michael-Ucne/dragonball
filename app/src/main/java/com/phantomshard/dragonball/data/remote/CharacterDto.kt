package com.phantomshard.dragonball.data.remote

import com.phantomshard.dragonball.domain.model.DragonBallCharacter

data class CharacterResponseDto(
    val items: List<CharacterDto>
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val ki: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val maxKi: String,
) {
    fun toDomain() = DragonBallCharacter(
        id, name, ki, race, gender, description, image, maxKi,
    )
}
