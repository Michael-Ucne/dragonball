package com.phantomshard.dragonball.data.repository

import com.phantomshard.dragonball.data.remote.DragonBallApi
import com.phantomshard.dragonball.data.remote.Resource
import com.phantomshard.dragonball.domain.model.DragonBallCharacter
import com.phantomshard.dragonball.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: DragonBallApi
) : CharacterRepository {

    override suspend fun getCharacters(
        page: Int, limit: Int, name: String?, gender: String?, race: String?
    ): Resource<List<DragonBallCharacter>> {
        return try {
            val response = api.getCharacters(page, limit, name, gender, race)
            
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.items.map { it.toDomain() }
                Resource.Success(data)
            } else {
                Resource.Error("Error servidor: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Error conexión: ${e.localizedMessage}")
        }
    }

    override suspend fun getCharacterDetail(id: Int): Resource<DragonBallCharacter> {
        return try {
            val response = api.getCharacterDetail(id)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!.toDomain())
            } else {
                Resource.Error("Personaje no encontrado")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }
}
