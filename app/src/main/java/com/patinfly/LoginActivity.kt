package com.patinfly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patinfly.data.datasource.UserDataSource
import com.patinfly.data.repository.UserRepository

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            loginForm()
        }
    }
}

@Composable
@Preview
fun loginForm() {
    val context = LocalContext.current

    var usernameInput by remember {
        mutableStateOf("")
    }

    var passwordInput by remember {
        mutableStateOf("")
    }

    val userDataSource = UserDataSource(context)
    val userRepository = UserRepository(userDataSource)

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.patinfly),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(15.dp))
            // Campos de texto
            CustomTextField(
                hint = "Nombre de usuario",
                onTextChange = { newText ->
                    usernameInput = newText
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomPassField(
                hint = "Contraseña",
                onTextChange = { newText ->
                    passwordInput = newText
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                onClick = {
                    if (!comprobarLoginInputs(context,usernameInput, passwordInput)) {
                        return@CustomButton
                    }


                    val loginUser = userRepository.login(usernameInput,passwordInput)
                    if(loginUser == null){
                        Toast.makeText(context, "Datos introducidos incorrectos", Toast.LENGTH_LONG).show()
                        return@CustomButton
                    }

                    Toast.makeText(context, "El usuario $usernameInput existe", Toast.LENGTH_LONG).show()
                    context.startActivity(Intent(context, ScooterActivity::class.java))
                },
                text = "Inicia sesión"
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "¿Eres nuevo aquí?",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                onClick = {
                    context.startActivity(Intent(context, RegisterActivity::class.java))
                },
                text = "Regístrate"
            )


        }
    }

}
fun comprobarLoginInputs(context: Context, username: String, pass: String): Boolean {
    // Contar cuántos campos están vacíos
    val emptyFields = listOf(username, pass).count { it.isEmpty() }

    when {
        emptyFields == 2 -> {
            Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_LONG).show()
            return false
        }
        username.isEmpty() -> {
            Toast.makeText(context, "Rellena el nombre de usuario", Toast.LENGTH_LONG).show()
            return false
        }
        pass.isEmpty() -> {
            Toast.makeText(context, "Rellena la clave", Toast.LENGTH_LONG).show()
            return false
        }
    }
    return true
}


