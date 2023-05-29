package com.fcm.basiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoundedApp()
        }
    }
}

@Composable
fun RoundedApp()
{
    val navController = rememberNavController()

    // Nav host between navController and navGraph

    NavHost(navController = navController, startDestination = "gyms_screen")
    {
        composable(route = "gyms_screen")
        {
            GymsScreen{id ->
                navController.navigate("gym/$id")
            }
        }

        composable(route = "gym/{gym_id}", arguments = listOf(navArgument("gym_id")
        {
            type = NavType.IntType
        }))
        {
          //  val gymId = it.arguments?.getInt("gym_id")
            GymDetailsScreen()
        }
    }
}


@Preview(showBackground = true, name = "Preview Text")
@Composable
fun MyText()
{
    Text(text = "First text test with compose",
        style = TextStyle(fontSize = 15.sp, color = Color.Red)
    )
}

@Preview(showBackground = true, name = "Preview Button")
@Composable
fun MyButton()
{
    var buttonIsEnabled by remember { mutableStateOf(true) }

    Button(onClick = {buttonIsEnabled = false},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White,
            disabledBackgroundColor = Color.LightGray
        ),
        enabled = buttonIsEnabled)
    {
        Text(text = if(buttonIsEnabled) "Click me" else "I`m disabled")
    }
}

@Preview(showBackground = true,
    name = "PreviewTextField")
@Composable
fun MyTextField()
{
    var emailAddress by remember { mutableStateOf("") }

    TextField(value =emailAddress ,
        onValueChange = {emailAddress = it} ,
    label = { Text(text = "Email address")})
}

@Composable
fun MyImage() {
    Image(painter = painterResource(id = R.drawable.image),
        contentDescription = "Aunt")
}

@Composable
fun PreviewMyImage()
{
    MyImage()
}