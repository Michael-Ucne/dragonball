package com.phantomshard.dragonball.domain.repository

import com.phantomshard.dragonball.data.remote.Resource
import com.phantomshard.dragonball.domain.model.DragonBallCharacter

interface CharacterRepository {
    suspend fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,   
        gender: String?, 
        race: String?,    
    ): Resource<List<DragonBallCharacter>>

    suspend fun getCharacterDetail(id: Int): Resource<DragonBallCharacter>
}
