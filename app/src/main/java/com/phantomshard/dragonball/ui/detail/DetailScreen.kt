package com.phantomshard.dragonball.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.phantomshard.dragonball.domain.model.DragonBallCharacter
import com.phantomshard.dragonball.ui.theme.DragonBallTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Personaje") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        state.character?.let { character ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {

                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                Text(character.name)
                Text("Raza: ${character.race}")
                Text("Género: ${character.gender}")
                Text("Ki: ${character.ki}")
                Text("Máx Ki: ${character.maxKi}")
                Text(character.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val sampleCharacter = DragonBallCharacter(
        id = 1,
        name = "Goku",
        ki = "60.000.000",
        race = "Saiyan",
        gender = "Male",
        description = "The main protagonist of the series.",
        image = "",
        maxKi = "90.000.000.000"
    )
    val state = DetailUiState(
        character = sampleCharacter
    )

    DragonBallTheme {
        Surface {
            DetailScreen(
                state = state,
                onBack = {}
            )
        }
    }
}
