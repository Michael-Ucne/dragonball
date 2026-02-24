package com.phantomshard.dragonball.util

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object List : Screen

    @Serializable
    data class Detail(val id: Int) : Screen
}
