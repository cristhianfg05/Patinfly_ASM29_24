package com.patinfly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.patinfly.data.model.UserModel
import com.patinfly.data.repository.UserRepository
import java.util.Date
import java.util.UUID

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterView()
        }
    }
}

@Composable
@Preview
fun RegisterView() {
    val context = LocalContext.current

    var usernameInput by remember {
        mutableStateOf("")
    }

    var passwordInput by remember {
        mutableStateOf("")
    }

    var emailInput by remember {
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
                hint = "Username",
                onTextChange = { newText ->
                    usernameInput = newText
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomPassField(
                hint = "Password",
                onTextChange = { newText ->
                    passwordInput = newText
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomEmailField(
                hint = "E-mail",
                onTextChange = { newText ->
                    emailInput = newText
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                onClick = {
                    if (!comprobarRegisterInputs(usernameInput, passwordInput, emailInput, context)){

                        return@CustomButton
                    }
                    val registerUser = userRepository.getUserByUsername(usernameInput)
                    if(registerUser != null){
                        Toast.makeText(context, "El usuario ya existe. Inicia la sesión", Toast.LENGTH_LONG).show()
                        return@CustomButton
                    }
                    val newUser = UserModel(UUID.randomUUID(),usernameInput,emailInput,false, null, Date(), 0, passwordInput )
                    if(!userRepository.registerUser(newUser)) {
                        Toast.makeText(context, "El usuario ya existe. Inicia la sesión", Toast.LENGTH_LONG).show()
                        return@CustomButton
                    }
                    Toast.makeText(context, "Usuario Registrado", Toast.LENGTH_LONG).show()
                    Log.d("logs", newUser.uuid.toString())
                    context.startActivity(Intent(context, LoginActivity::class.java))


                },
                text = "Regístrate"
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "¿Tienes cuenta?",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                onClick = {
                    context.startActivity(Intent(context, LoginActivity::class.java))
                },
                text = "Inicia Sesión"
            )


        }
    }
}

private fun comprobarRegisterInputs(username: String, pass: String, email: String, context: Context): Boolean {
    // Contar cuántos campos están vacíos
    val emptyFields = listOf(username, pass, email).count { it.isBlank() }

    when {
        emptyFields > 1 -> {
            Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_LONG).show()
            return false
        }
        username.isBlank() -> {
            Toast.makeText(context, "Rellena el campo de usuario", Toast.LENGTH_LONG).show()
            return false
        }
        pass.isBlank() -> {
            Toast.makeText(context, "Rellena el campo de contraseña", Toast.LENGTH_LONG).show()
            return false
        }
        email.isBlank() -> {
            Toast.makeText(context, "Rellena el campo de email", Toast.LENGTH_LONG).show()
            return false
        }
    }

    // Verificar que el email cumple con el patrón de correo electrónico básico
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"
    if (!Regex(emailPattern).matches(email)) {
        Toast.makeText(context, "El email no tiene un formato válido", Toast.LENGTH_LONG).show()
        return false
    }

    return true
}
