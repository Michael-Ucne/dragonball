package com.phantomshard.dragonball.domain.model

data class DragonBallCharacter(
    val id: Int,
    val name: String,
    val ki: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val maxKi: String
)
