@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.laboratorio6.pages


import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.laboratorio6.PreferencesManager.Preferences
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext


val realUsername  = "admin";
val realPassword = "123";

var isLoggedIn =false;





@SuppressLint("RememberReturnType")
@Composable
fun Header(){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(40, 75, 99))
                .padding(15.dp)
        ){
                Text(
                    text = "LABORATORIO #6 - Davis Roldan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
        }


}




@Composable
fun Login(navController: NavHostController){
    val context1 = LocalContext.current as Activity

    BackHandler {
        context1.finish()

    }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    val context = LocalContext.current

    val preferences = remember { Preferences(context) }
    val data = remember { mutableStateOf(preferences.getData("login", "false")) }






    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Header();
        Row(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .wrapContentSize(Alignment.Center)
        ){
            Column(
                modifier = Modifier.padding(15.dp,0.dp,15.dp,0.dp)
            ){
                TextField(
                    value = username,
                    onValueChange = {
                            newText -> username = newText
                    },
                    label = {
                        Text("Ingrese Username")
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = password,
                    onValueChange = { newPassword -> password = newPassword },
                    label = { Text("Ingrese password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(16.dp))
                IconButton(

                    onClick = {

                        validateLogin(username, password)
                        if(isLoggedIn){
                            Toast.makeText(context, "Logueado exitosamente", Toast.LENGTH_LONG).show()
                            navController.navigate("gallery")
                            preferences.saveData("login","true")
                        }else{
                            Toast.makeText(context, "Las credenciales son incorrectas", Toast.LENGTH_LONG).show()
                            preferences.saveData("login","false")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(40, 75, 99)
                    ),
                ) {
                    Row{
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Login",
                            tint = Color(255,255,255)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Login", color = Color.White)
                    }

                }
            }
        }
    }


}

fun validateLogin(username: String, password: String){
    if(realPassword.equals(password)  && realUsername.equals(username)){
        isLoggedIn =true;
    }else{
        isLoggedIn=false;
    }
}