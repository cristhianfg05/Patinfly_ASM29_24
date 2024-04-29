package com.patinfly

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patinfly.data.datasource.ScooterDataSource
import com.patinfly.data.model.ScooterModel
import com.patinfly.data.repository.ScooterRepository
import java.util.UUID


class ScooterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uuidString = intent.getStringExtra("userUUID")
            val uuid = UUID.fromString(uuidString)
            ScooterListScreen(uuid)

        }
    }

}

@Composable
fun ScooterListScreen(uuid:UUID) {

    val context = LocalContext.current
    val scooterRepository = ScooterRepository(ScooterDataSource.getInstance(context.applicationContext))
    val scooterList = scooterRepository.getAllScooters()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent

            ) {
                Spacer(Modifier.weight(1f, true))
                FloatingActionButton(
                    onClick = {
                        val intent = Intent(context, ProfileActivity::class.java)
                        intent.putExtra("userUUID", uuid.toString()) // Asegúrate de convertir UUID a String.
                        context.startActivity(intent)
                    },
                    containerColor = Color.LightGray
                ) {
                    Icon(Icons.Filled.Face, contentDescription = "Perfil")
                }
                Spacer(Modifier.weight(1f, true))
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(text = "Hola userDemo")
            ScooterList(scooterList)
        }
    }
}

@Composable
fun ScooterList(scooters: List<ScooterModel>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(scooters) { scooter ->
            ScooterItem(scooter)
        }
    }
}

@Composable
fun ScooterItem(scooter: ScooterModel) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray), // Establece el color de fondo aquí
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_scooter_image),
                contentDescription = "Scooter Image",
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = scooter.model,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = if (scooter.vacant) "Disponible" else "No Disponible",
                color = if (scooter.vacant) Color.Green else Color.Red,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                enabled = scooter.vacant
            ) {
                Text("Alquilar")
            }
        }
    }
}





