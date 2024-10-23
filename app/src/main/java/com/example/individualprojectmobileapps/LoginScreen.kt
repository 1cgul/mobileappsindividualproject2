package com.example.individualprojectmobileapps
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun LoginPage(navController: NavController) {
    var username by remember { mutableStateOf("") } // stores username input
    var password by remember { mutableStateOf("") } // stores password input
    val areFieldsValid = nameIsValid(username) && nameIsValid(password) // checks if fields are valid for enabling login button

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center) // centers content within the box
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp) // adds padding around the column
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription ="" )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = username,
                onValueChange = { username = it }, // updates username as user types
                label = { Text("Email") }, // field label
                modifier = Modifier.fillMaxWidth(), // field fills the entire width
            )
            Spacer(modifier = Modifier.height(16.dp)) // adds space between the fields
            TextField(
                value = password,
                onValueChange = { password = it }, // updates password as user types
                label = { Text("Password") }, // field label
                visualTransformation = PasswordVisualTransformation(), // hides password input
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // sets keyboard type to password
                modifier = Modifier.fillMaxWidth(), // field fills the entire width
            )
            Spacer(modifier = Modifier.height(24.dp)) // adds space before buttons
            Button(
                onClick = { navController.navigate("main_screen") }, // triggers successful login
                enabled = areFieldsValid, // button is enabled only if fields are valid
                modifier = Modifier.fillMaxWidth() // button fills the entire width
            ) {
                Text("Login") // button text
            }
            Spacer(modifier = Modifier.height(16.dp)) // adds space between buttons
            Button(
                onClick = { navController.navigate("registration_screen") }, // triggers registration page navigation
                modifier = Modifier.fillMaxWidth() // button fills the entire width
            ) {
                Text("Register") // button text
            }
        }
    }
}