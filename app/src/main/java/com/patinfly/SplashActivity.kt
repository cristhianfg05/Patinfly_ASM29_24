package com.patinfly

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.os.Handler
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashView()
        }
    }

    @Composable
    @Preview
    fun SplashView() {
        val context = LocalContext.current
        Box(modifier = Modifier.fillMaxSize()) {
            // Establece el fondo desde un drawable
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null, // Descripción para accesibilidad
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Ajusta la escala del contenido para llenar el tamaño del contenedor
            )
            // Logo centrado
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(painter = painterResource(R.drawable.patin), contentDescription = "Logo", Modifier.size(200.dp))
            }

            // Navegación después de un retraso
            LaunchedEffect(key1 = true) {
                delay(2000)
                context.startActivity(Intent(context, LoginRegisterActivity::class.java))
                finish()
            }
        }
    }

}