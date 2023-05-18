package com.fcm.basiccompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
        items(vm.getGyms()){gym->
            GymItem(gym)
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
fun GymItem(gym: Gym) {
    Card(elevation = 10.dp, modifier = Modifier.padding(8.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)) {
            GymIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            GymDetails(gym,Modifier.weight(0.85f))
        }
    }
}

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

@Composable
fun GymIcon(place: ImageVector, modifier: Modifier) {
    Image(
        imageVector = place, contentDescription = "Gym icon", modifier = modifier,
        colorFilter = ColorFilter.tint(Color.DarkGray)
    )
}


//@Preview(showBackground = true)
//@Composable
//fun GymsScreenPreview() {
//    GymItem()
//}

