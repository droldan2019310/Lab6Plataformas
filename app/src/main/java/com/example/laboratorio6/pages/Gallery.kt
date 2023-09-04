package com.example.laboratorio6.pages

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.laboratorio6.PreferencesManager.Preferences

import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.laboratorio6.R


@SuppressLint("RememberReturnType")
@Composable
fun HeaderGallery(navController:NavHostController){





    val context = LocalContext.current
    val preferences = remember { Preferences(context) }

    Row(
        modifier = Modifier
            .background(Color(40, 75, 99))
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Row (
                modifier = Modifier
                    .padding(15.dp)
            ){
                Text(
                    text = "LABORATORIO #6",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .wrapContentSize(Alignment.CenterEnd)
        ) {
            Column(
                modifier = Modifier

            ){
                IconButton(

                    onClick = {
                        navController.navigate("login")
                        preferences.saveData("login","false")

                    },
                    modifier = Modifier


                ) {
                    Row{
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color(255,255,255)
                        )

                    }

                }
            }
        }

    }

}



var actualImage=0;



@Composable
fun Gallery(navController: NavHostController){
    val context = LocalContext.current as Activity

    BackHandler {
        context.finish()

    }
    var image  by remember { mutableStateOf( R.drawable.skyline)}
    var contentDescription by remember { mutableStateOf( "Skyline R34")}
    var title by remember { mutableStateOf(  "NISSAN SKYLINE R34") }
    var description   by remember { mutableStateOf("Motor RB26 - 6 cilindros en línea") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderGallery(navController)




        Spacer(modifier = Modifier.height(66.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)

        ){
            Image(painter = painterResource(id = image) , contentDescription = contentDescription)
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize().padding(15.dp)
        ) {
            Text(text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Text(text = description,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))



        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
        ){
            Row {
                Box (
                    modifier = Modifier.weight(1f).padding(15.dp)
                ){
                    IconButton(

                        onClick = {
                            previuos()
                            image = nextImages()
                            title = nextTitle()
                            contentDescription = nextTitle()
                            description = nextDescription()
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color(40, 75, 99)
                        ),
                    ) {
                        Row{
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Previous",
                                tint = Color(255,255,255)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Previous", color = Color.White)
                        }

                    }
                }


                Box (
                    modifier = Modifier.weight(1f).padding(15.dp)
                ){
                    IconButton(

                        onClick = {
                            next()
                            image = nextImages()
                            title = nextTitle()
                            contentDescription = nextTitle()
                            description = nextDescription()
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color(40, 75, 99)
                        ),
                    ) {
                        Row{
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Next",
                                tint = Color(255,255,255)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Next", color = Color.White)
                        }

                    }
                }
            }
        }
    }

}

fun next(){
    if(actualImage==9){
        actualImage=0
    }else{
        actualImage ++
    }
}

fun previuos(){
    if(actualImage==0){
        actualImage=9
    }else{
        actualImage --
    }
}

fun nextImages(): Int {

    var image = when (actualImage){
        0 ->R.drawable.skyline
        1 -> R.drawable.gtr
        2 -> R.drawable.porschegt3rs
        3 -> R.drawable.shelby
        4 -> R.drawable.tesla
        5 -> R.drawable.audir8
        6 -> R.drawable.f40
        7 -> R.drawable.hondatyper
        8 -> R.drawable.supra
        9 -> R.drawable.lexuslfa
        else -> R.drawable.skyline
    }
    return image
}


fun nextTitle(): String {

    var title = when (actualImage){
        0 ->"NISSAN SKYLINE R34"
        1 -> "NISSAN GTR R35"
        2 -> "PORSCHE 911 GT3RS"
        3 -> "MUSTANG SHELBY GT500"
        4 -> "TESLA S PLAID"
        5 -> "AUDI R8"
        6 -> "FERRARI F40"
        7 -> "HONDA TYPE R"
        8 -> "TOYOTA SUPRA MK4"
        9 -> "LEXUS LFA"
        else -> "NISSAN SKYLINE R34"
    }
    return title
}



fun nextDescription(): String {

    var description = when (actualImage){
        0 ->"Motor RB26 - 6 cilindros en línea"
        1 -> "Motor VR38DETT - V6 - 2 turbocompresores"
        2 -> "Motor Bóxer atmosférico -  6 cilindros"
        3 -> "Motor Predator -  V8 sobrealimentado"
        4 -> "Tres motores eléctricos con una autonomía de 390 millas "
        5 -> "Motor V10FSI - V10 aspirado naturalmente"
        6 -> "Motor F120B - V8 - Biturbo"
        7 -> "Motor K20C1 - 4 cilindros en línea - turboalimentado"
        8 -> "Motor 2JZ-GTE - 6 cilindros en línea - Biturbo"
        9 -> "Motor 1LR-GUE - V10 -  atmosférico"
        else -> "Motor RB26 - 6 cilindros en línea"
    }
    return description
}