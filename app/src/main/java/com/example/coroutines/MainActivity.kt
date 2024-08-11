package com.example.coroutines

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.coroutines.ui.theme.CoroutinesTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : ItemsViewModel by viewModels()
        setContent {
            CoroutinesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Content(viewModel)
                }
            }
        }
    }
}


@Composable
fun Content(viewModel: ItemsViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
//        ButtonColor()

        if(viewModel.isLoading){
            CircularProgressIndicator()
        }else {
            ItemsView(viewModel)
        }

//        Button(onClick = { viewModel.fetchData()}) {
//
//            Text("Call API")
//
//        }

    }
}

@Composable
fun ButtonColor() {

    var color by remember { mutableStateOf(false) }

    Button(
        onClick = { color = !color },
        colors = ButtonDefaults.buttonColors(containerColor = if (color) Color.Blue else Color.Red)
    ) {
        Text("Change color")
    }
}


@Composable
fun ItemsView(viewModel: ItemsViewModel){

    val itemsList = viewModel.itemsList
     val list by viewModel.list.collectAsState()
    
//    LaunchedEffect(Unit) {
//        viewModel.fetchData()
//    }
    Column {

        if(viewModel.isLoading){
            CircularProgressIndicator()

        }else {
            LazyColumn {
                items(list){ item ->

                    Text(item.name)

                }
            }
        }
    }

}

