package com.fcm.basiccompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fcm.basiccompose.ui.theme.Purple200

@Composable
fun GymsScreen() {

    val vm:GymsViewModel = viewModel()

    // Lazy column for show list
    LazyColumn()
    {
        items(vm.state){gym->
            GymItem(gym){
                vm.toggleFavouriteState(it)
            }
        }
    }
    // Column iteration for show list
//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        listOfGyms.forEach {
//            GymItem(it)
//        }
//    }
}

@Composable
fun GymItem(gym: Gym,onClick:(Int) -> Unit) {

  //  var isFavouriteState by rememberSaveable { mutableStateOf(false) }

    val icon = if(gym.isFavourite)
    {
        Icons.Filled.Favorite
    }
    else
    {
        Icons.Filled.FavoriteBorder
    }

    Card(elevation = 10.dp, modifier = Modifier.padding(8.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)) {
            DefaultIcon(icon = Icons.Filled.Place,
                modifier = Modifier.weight(0.15f),
                contentDescription = "Place icon")
           // GymIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            GymDetails(gym,Modifier.weight(0.70f))
            DefaultIcon(icon,Modifier.weight(0.15f),"Favourite icon"){
                onClick(gym.id)
            }
        }
    }
}

@Composable
fun DefaultIcon(
    icon:ImageVector,
    modifier: Modifier,
    contentDescription: String,
    onClick:() -> Unit = {},
    )
{
    Image(imageVector = icon, contentDescription = contentDescription,
    modifier = modifier
        .padding(8.dp)
        .clickable {
            onClick()
        },
        colorFilter = ColorFilter.tint(Color.DarkGray))
}

//@Composable
//fun GymIcon(place: ImageVector, modifier: Modifier) {
//    Image(
//        imageVector = place, contentDescription = "Gym icon", modifier = modifier,
//        colorFilter = ColorFilter.tint(Color.DarkGray)
//    )
//}


@Composable
fun GymDetails(gym: Gym,modifier: Modifier) {
    Column(modifier = modifier)
    {

        Text(text = gym.name,
            style = MaterialTheme.typography.h6
        , color = Purple200)
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium)
        {
            Text(text = gym.place, style = MaterialTheme.typography.body2)

        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun GymsScreenPreview() {
//    GymItem()
//}

