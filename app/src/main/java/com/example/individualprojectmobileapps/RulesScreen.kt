package com.example.individualprojectmobileapps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun RulesScreen(navController: NavController){
    Box(
        modifier= Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Rules of the game: Select or type the correct answer!")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("main_screen")},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("I Understand")
            }
        }
    }
}