package com.patinfly

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patinfly.data.datasource.UserDataSource
import com.patinfly.data.model.UserModel
import com.patinfly.data.repository.UserRepository
import com.patinfly.ui.theme.PatinflyTheme
import java.util.Date
import java.util.UUID

class ProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uuidString = intent.getStringExtra("userUUID")
            val uuid = UUID.fromString(uuidString)
            ProfileScreen(uuid)
        }
    }
    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun ProfileScreen(uuid : UUID) {
        val context = LocalContext.current
        val userRepository = UserRepository(UserDataSource.getInstance(applicationContext))
        val user = userRepository.getUserByUUID(uuid)
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Fondo
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                // Icono de perfil
                Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color.Black, modifier = Modifier.size(100.dp))
                Spacer(modifier = Modifier.height(16.dp))
                // Información del perfil (simulada)
                if (user != null) {
                    Text("E-mail ${user.email}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Username: ${user.username}")
                    Spacer(modifier = Modifier.height(16.dp))
                }


                // Campos editables para actualizar la información del perfil

                var username by remember { mutableStateOf("") }
                CustomTextField(hint = "Nuevo username", onTextChange = { username = it })
                Spacer(modifier = Modifier.height(16.dp))

                var email by remember { mutableStateOf("") }
                CustomEmailField(hint = "Nuevo email", onTextChange = { email = it })
                Spacer(modifier = Modifier.height(16.dp))

                var password by remember { mutableStateOf("") }
                CustomPassField(hint = "Nueva contraseña", onTextChange = { password = it })
                Spacer(modifier = Modifier.height(16.dp))

                var confirmPassword by remember { mutableStateOf("") }
                CustomPassField(hint = "Confirmar nueva contraseña", onTextChange = { confirmPassword = it })
                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(
                    onClick = {
                        if(!comprobarRegisterInputs(username,password, email, confirmPassword, context))
                            return@CustomButton

                        if (user != null && confirmPassword == password) {
                            if(userRepository.modifyUser(UserModel(uuid, username, email, user.isRenting, user.scooterRented, user.creationDate, user.numberOfRents, password)))
                                Toast.makeText(context, "Cambios realizados", Toast.LENGTH_LONG).show()
                                context.startActivity(Intent(context, ProfileActivity::class.java).putExtra("userUUID", uuid.toString()))
                        }


                    },
                    text = "Guardar cambios"
                )
            }

        }
    }
}



private fun comprobarRegisterInputs(username: String, pass: String, email: String,confirmPass:String, context: Context): Boolean {
    val emptyFields = listOf(username, pass, email,confirmPass).count { it.isBlank() }

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
        confirmPass.isBlank() ->{
            Toast.makeText(context, "Rellena el campo de confirmar nueva contraseña", Toast.LENGTH_LONG).show()
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