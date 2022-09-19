package com.example.persian_date_picker_compose

import android.widget.GridLayout
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch

@Composable
fun PersianDatePicker(
    showDialog : Boolean,
    hideDialog : (Boolean) -> Unit,
    setDate : (Map<String, String>) -> Unit
){


    var selectedPart by remember {
        mutableStateOf("main")
    }
    var mMonth by remember {
        mutableStateOf("شهریور")
    }

    var mYear by remember {
        mutableStateOf("1401")
    }

    var mDay by remember {
        mutableStateOf("27")
    }

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp * .5

    Dialog(onDismissRequest = { hideDialog(false) }) {
        Card(
            modifier = Modifier
                .size(width = width.dp, height = height.dp)
                .padding(5.dp),
            shape = RoundedCornerShape(25.dp),
            elevation = 4.dp,
            backgroundColor = Color(0xffe3f2fd)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 0.dp)
                ,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(MaterialTheme.colors.primaryVariant)
                )
                Crossfade(targetState = selectedPart) { selected ->
                    when (selected){
                        "main" -> Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            MainContent(hideDialog, mMonth, mYear, mDay, setDate){selectedPart = it}
                        }
                        "month" -> Months(mMonth,{selectedPart = "main"} ){mMonth = it}
                        "day" -> Days(mMonth, mDay, {mDay = it}){selectedPart = it}
                        "year" -> Years(mYear, setYear = {mYear = it}, changeSelectedPart = {selectedPart = "main"})
                        else ->
                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                MainContent(hideDialog, mMonth, mYear, mDay, setDate){selectedPart = it}
                            }
                    }
                }


            }

        }
    }
}

@Composable
fun MainContent(
    hideDialog : (Boolean) -> Unit,
    mMonth: String,
    mYear : String,
    mDay : String,
    setDate: (Map<String, String>) -> Unit,
    setSelected : (String) -> Unit
){

    val width = LocalConfiguration.current.screenWidthDp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(vertical = 18.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = mMonth, style = MaterialTheme.typography.h4, color = MaterialTheme.colors.onPrimary)
        Text(text = mDay, style = MaterialTheme.typography.h1, color = MaterialTheme.colors.onPrimary)
        Text(text = mYear, style = MaterialTheme.typography.h4, color = MaterialTheme.colors.onPrimary)
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .height(40.dp)
                .width((width / 6).dp)
                .clickable {
                    setSelected("year")
                },
            color = MaterialTheme.colors.primary.copy(.1f),
            shape = RoundedCornerShape(5.dp)
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = mYear, style = MaterialTheme.typography.h5, color = Color.Black)
            }
        }
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .height(40.dp)
                .width((width / 6).dp)
                .clickable {
                    setSelected("month")
                },
            color = MaterialTheme.colors.primary.copy(.1f),
            shape = RoundedCornerShape(5.dp)
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = mMonth, style = MaterialTheme.typography.h5, color = Color.Black)
            }
        }
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .height(40.dp)
                .width((width / 6).dp)
                .clickable {
                    setSelected("day")
                },
            color = MaterialTheme.colors.primary.copy(.1f),
            shape = RoundedCornerShape(5.dp)
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = mDay, style = MaterialTheme.typography.h5, color = Color.Black)
            }
        }

    }

    val monthsList = listOf("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند",)

    Text(
        text = "تایید",
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.h4,
        modifier = Modifier
            .clickable {
                hideDialog(false)
                setDate(mapOf("day" to mDay, "month" to (monthsList.indexOf(mMonth) + 1).toString(), "year" to mYear))
            }
            .padding(top = 8.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Months(mMonth : String, setSelected: () -> Unit ,setMonth : (String) -> Unit){

    val monthsList = listOf("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند",)

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(23.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ){
        items(monthsList){
            Surface(
                modifier = Modifier
                    .padding(5.dp)
                    .size(75.dp)
                    .clip(CircleShape)
                    .clickable {
                        setMonth(it)
                        setSelected()
                    }
                ,
                shape = CircleShape,
                color = if (mMonth == it) MaterialTheme.colors.primary else Color.Transparent,

                ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = it, color = if (mMonth == it) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary, style = MaterialTheme.typography.h5)
                }
            }
        }
    }


}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Days(mMonth: String,mDay : String , setDay : (String) -> Unit, changeSelectedPart : (String) -> Unit){

    val monthsList = listOf("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند",)

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ){
        items(if (monthsList.indexOf(mMonth) > 5 ) 30 else 31){
            Surface(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable {
                        changeSelectedPart("main")
                        setDay((it + 1).toString())
                    },
                shape = CircleShape,
                color = if (mDay == (it + 1).toString()) MaterialTheme.colors.primary else Color.Transparent
            ) {
                Row(Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = (it + 1).toString(), style = MaterialTheme.typography.h5, color = if (mDay == (it + 1).toString()) Color.White else MaterialTheme.colors.primary)
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Years(mYear: String, setYear : (String) -> Unit, changeSelectedPart: (String) -> Unit){

    var years = mutableListOf<Int>()
    for (y in 1430 downTo 1350){
        years.add(y)
    }

    var gridState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = 1){
        scope.launch {
            gridState.scrollToItem(28)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        state = gridState
    ){
        items(years){
            Surface(
                modifier = Modifier
                    .padding(15.dp)
                    .size(60.dp)
                    .clip(CircleShape)
                    .clickable {
                        changeSelectedPart("main")
                        setYear(it.toString())
                    },
                shape = CircleShape,
                color = if (mYear == it.toString()) MaterialTheme.colors.primary else Color.Transparent
            ) {
                Row(Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = it.toString(), style = MaterialTheme.typography.h5, color = if (mYear == it.toString()) Color.White else MaterialTheme.colors.primary)
                }
            }
        }
    }

}











