package com.fcm.basiccompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GymDetailsScreen()
{
    val vm:GymsDetailsViewModel = viewModel()

    val item = vm.state.value

    item?.let {
        gym->
        
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DefaultIcon(icon = Icons.Filled.Place,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, top = 32.dp) ,
                contentDescription = "")

            GymDetails(gym = item, modifier = Modifier.padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally)

            Text(text = if (item.isOPen) "Gym is open" else "Gym closed",
                color = if(item.isOPen) Color.Green else Color.Red)
        }
    }

}