package com.example.composeautomation.ui.report0

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeautomation.ui.datepicker.PersianDatePicker
import com.example.composeautomation.ui.theme.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Report0(navHostController: NavHostController){
    Scaffold(
        Modifier.fillMaxSize(),
        backgroundColor = Purple200.copy(0.09f)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Purple700,
                                Purple500,
                            )
                        )
                    )
                    .padding(horizontal = 15.dp, vertical = 0.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                    Row(Modifier.padding(horizontal = 15.dp)) {
                        Text(
                            text = "گزارش شماره 1",
                            style = MaterialTheme.typography.h4,
                            color = Color.White,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Icon(imageVector = Icons.Default.Flag, contentDescription = null, tint = Color.White)
                    }
                }
                TimeRow()
            }
            ContentColumn()
        }
    }
}

@Composable
fun TimeRow(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .offset(y = 30.dp)
        .padding(horizontal = 8.dp, vertical = 6.dp),
        shape = RoundedCornerShape(30.dp),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {

        var clicked by remember {
            mutableStateOf(true)
        }

        var startDate by remember {
            mutableStateOf(mapOf<String, String>())
        }
        var endDate : Map<String, String>  = mutableMapOf<String, String>()

        if (!clicked){
            PersianDatePicker(onDismiss = {clicked = true}, setDate = {startDate = it})
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(onClick = {  }) {
                Icon(Icons.Default.Search, contentDescription = null, tint = Purple700)
            }

            Row() {

                Row() {
                    Text(
                        text = "${startDate["year"]}/${startDate["month"]}/${startDate["day"]}",
                        style = MaterialTheme.typography.body1 ,
                        color = Purple500,
                        modifier = Modifier.clickable {
                            clicked = !clicked
                        }
                    )
                    Text(text = "تا ", color = Color.Black, style = MaterialTheme.typography.body1, modifier = Modifier.padding(start = 5.dp))
                }
                Row() {
                    Text(
                        text = "1401/06/23",
                        style = MaterialTheme.typography.body1,
                        color = Purple500,
                        modifier = Modifier.clickable {  }
                    )
                    Text(text = "از ", color = Color.Black, style = MaterialTheme.typography.body1, modifier = Modifier.padding(start = 5.dp))
                }
            }



        }
    }
}

@Composable
fun ContentColumn(){
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(8f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Report,  contentDescription = null, tint = Orange, modifier = Modifier.size(50.dp))
        Text(text = "هیچ داده ای یافت نشد")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAutomationTheme {

    }
}