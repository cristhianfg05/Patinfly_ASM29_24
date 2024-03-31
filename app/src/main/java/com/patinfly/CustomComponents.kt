package com.patinfly

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF1E2145))
    ) {
        Text(text = text, color = Color.White)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(hint: String, onTextChange: (String) -> Unit){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            onTextChange(newText) // Llama a onTextChange cada vez que el texto cambie
        },
        label = { Text(hint) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "userIcon") },
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = if (text.isNotEmpty()) Color(0xFFBBDEFB) else Color(0xFFCCCCCC),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp), // Color del texto y tamaño personalizado
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color(0xFFBBDEFB),
            unfocusedBorderColor = Color(0xFFCCCCCC),
            focusedLabelColor = Color(0xFFBBDEFB),
            unfocusedLabelColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEmailField(hint: String, onTextChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            onTextChange(newText) // Llama a onTextChange cada vez que el texto cambie
        },
        label = { Text(hint) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = if (text.isNotEmpty()) Color(0xFFBBDEFB) else Color(0xFFCCCCCC),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp), // Color del texto y tamaño personalizado
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color(0xFFBBDEFB),
            unfocusedBorderColor = Color(0xFFCCCCCC),
            focusedLabelColor = Color(0xFFBBDEFB),
            unfocusedLabelColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPassField(hint: String, onTextChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            onTextChange(newText) // Llama a onTextChange cada vez que el texto cambie
        },
        label = { Text(hint) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "passIcon") },
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = if (text.isNotEmpty()) Color(0xFFBBDEFB) else Color(0xFFCCCCCC),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        visualTransformation =  PasswordVisualTransformation(),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp), // Color del texto y tamaño personalizado
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color(0xFFBBDEFB),
            unfocusedBorderColor = Color(0xFFCCCCCC),
            focusedLabelColor = Color(0xFFBBDEFB),
            unfocusedLabelColor = Color.Gray
        )
    )
}