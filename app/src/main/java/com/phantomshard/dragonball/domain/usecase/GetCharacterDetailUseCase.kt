package com.phantomshard.dragonball.domain.usecase

import com.phantomshard.dragonball.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int) = repository.getCharacterDetail(id)
}
