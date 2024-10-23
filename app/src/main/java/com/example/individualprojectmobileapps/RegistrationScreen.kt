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

fun nameIsValid(name: String): Boolean {
    if (name.length >= 3 && name.length <= 30) { // checks if name length is between 3 and 30
        return true
    }
    return false
}

fun isValidDob(input: String): Boolean {
    val regex = Regex("""^\d{2}/\d{2}/\d{4}$""") // regex for validating date of birth format
    return regex.matches(input) // returns true if input matches the format
}
@Composable
fun RegistrationPage(navController: NavController) {
    var firstName by remember { mutableStateOf("") } // stores first name input
    var lastName by remember { mutableStateOf("") } // stores last name input
    var dateOfBirth by remember { mutableStateOf("") } // stores date of birth input
    var email by remember { mutableStateOf("") } // stores email input
    var password by remember { mutableStateOf("") } // stores password input

    // checks if all fields follow requirements
    val isFormValid =
        isValidDob(dateOfBirth) && // checks if date of birth is valid
                nameIsValid(password) && // checks if password is valid
                Patterns.EMAIL_ADDRESS.matcher(email).matches() && // ensures email field is valid
                nameIsValid(firstName) && // checks if first name is valid
                nameIsValid(lastName) // checks if last name is valid

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center) // centers content within the box
    ) {

        Column( // column that houses various fields
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp) // adds padding around the column
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription ="" )
            Spacer(modifier = Modifier.height(16.dp))
            TextField( // first name field
                value = firstName,
                onValueChange = { firstName = it }, // updates first name as user types
                label = { Text("First Name") }, // field label
                modifier = Modifier.fillMaxWidth(), // field fills the entire width
            )
            Spacer(modifier = Modifier.height(16.dp)) // adds space between fields
            TextField( // last name field
                value = lastName,
                onValueChange = { lastName = it }, // updates last name as user types
                label = { Text("Last Name") }, // field label
                modifier = Modifier.fillMaxWidth(), // field fills the entire width
            )
            Spacer(modifier = Modifier.height(16.dp)) // adds space between fields
            TextField( // dob field
                value = dateOfBirth,
                onValueChange = { dateOfBirth = it }, // updates date of birth as user types
                label = { Text("Date of Birth (mm/dd/yyyy)") }, // field label
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), // sets keyboard type
                modifier = Modifier.fillMaxWidth(), // field fills the entire width
            )
            Spacer(modifier = Modifier.height(16.dp)) // adds space between fields
            TextField( // email field
                value = email,
                onValueChange = { email = it }, // updates email as user types
                label = { Text("Email") }, // field label
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), // sets keyboard type to email
                modifier = Modifier.fillMaxWidth(), // field fills the entire width
            )
            Spacer(modifier = Modifier.height(16.dp)) // adds space between fields
            TextField( // password field
                value = password,
                onValueChange = { password = it }, // updates password as user types
                label = { Text("Password") }, // field label
                visualTransformation = PasswordVisualTransformation(), // hides password input
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // sets keyboard type to password
                modifier = Modifier.fillMaxWidth(), // field fills the entire width
            )
            Spacer(modifier = Modifier.height(24.dp)) // adds space before register button
            Button( // back button to login page
                onClick = { navController.navigate("login_screen") }, // clicking the button brings you back
                enabled = isFormValid, // button is disabled if form is not valid
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }
            Button(
                onClick = { navController.navigate("login_screen") }, // triggers the callback function to go back to the login page
                modifier = Modifier.fillMaxWidth() // button fills the entire width of the screen
            ){
                Text("Back to login")
            }
        }
    }
}