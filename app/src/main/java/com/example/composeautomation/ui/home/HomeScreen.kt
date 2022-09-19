package com.example.composeautomation.ui.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composeautomation.R
import com.example.composeautomation.ui.theme.ComposeAutomationTheme
import com.example.composeautomation.ui.theme.Purple500
import com.example.composeautomation.ui.theme.Purple700
import com.example.composeautomation.ui.theme.colorsList
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun Home(viewModel : HomeViewModel , navController: NavHostController){
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(Purple500)
    ) {
        var mainItemsList = listOf("مدیریت", "حسابداری", "فروش", "پشتیبانی", "منابع انسانی")
        var currentButton by remember {
            mutableStateOf(value = "مدیریت")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Icon(Icons.Default.Info, contentDescription = null, tint = Color.White, modifier = Modifier.padding(horizontal = 15.dp))
                Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White, modifier = Modifier.padding(horizontal = 5.dp))
            }
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White, modifier = Modifier.padding(horizontal = 5.dp))

        }

        //******************** Top Row Items


        // *********************** BackDrop
        val scope = rememberCoroutineScope()
        val backdropState = rememberBackdropScaffoldState(BackdropValue.Concealed)
        val frontLayoutHeight = LocalConfiguration.current.screenHeightDp / 10
        val halfHeightPx = with(LocalDensity.current) { frontLayoutHeight.dp.toPx() }
        val offset by backdropState.offset

        LaunchedEffect(backdropState){
            backdropState.reveal()
        }



        BackdropScaffold(
            appBar = {  },
            backLayerContent = { Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .alpha(offset / halfHeightPx)
                    .horizontalScroll(rememberScrollState())
            ) {
                mainItemsList.forEach{ title ->
                    TopRowItem(title = title, currentButton){
                        currentButton = it
                    }
                }
            } },
            scaffoldState = backdropState,
            frontLayerContent = {
                ShownBackDrop(currentButton, mainItemsList, navController)
            },
            headerHeight = frontLayoutHeight.dp,
            frontLayerShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            peekHeight = 0.dp,
            frontLayerScrimColor = Color.Unspecified,
        )



    }
}

@Composable
fun ShownBackDrop(currentButton: String, mainItemsList : List<String>,navController: NavHostController){
    Card(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {

        val managementList = listOf("بازاریابی", "حکم کارها", "خدمات پشتیبانی", "گزارش لیدها", "گزارش کار", "گردش حکم کارها", "فروش سخت افزار", "فروش نرم افزار")
        val iconList = listOf<Int>(R.drawable.social_marketing, R.drawable.completed_task, R.drawable.customer_support, R.drawable.survey, R.drawable.clipboard, R.drawable.report, R.drawable.hardware, R.drawable.software)

        val managementList1 = listOf("aa", "bb", "cc", "dd", "ee", "ff")
        val managementList2 = listOf("3a", "3b", "3c", "3d")


        Crossfade(
            targetState = currentButton,
            animationSpec = tween(1000)
        ) {
            when(it){
                mainItemsList[0] -> GridContent(currentButton, managementList, iconList, navController)
                mainItemsList[1] -> GridContent(currentButton, managementList2, iconList, navController)
                mainItemsList[2] -> GridContent(currentButton, managementList1, iconList, navController)
                mainItemsList[3] -> GridContent(currentButton, managementList2, iconList, navController)
                else -> GridContent(currentButton, managementList, iconList, navController)
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridContent(
    currentButton : String,
    contentList : List<String>,
    icon: List<Int>,
    navController: NavHostController
    ){
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = currentButton,
                style = MaterialTheme.typography.h3,
                color = Purple700,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Icon(imageVector = Icons.Default.Dashboard, contentDescription = null, tint = Purple500)
        }


        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 110.dp)){
            items(contentList.size){ index ->
                    GridItem(contentList[index], icon[index], index, colorsList[index]){ navController.navigate(it) }
            }
        }
    }
}

@Composable
fun TopRowItem(title : String, currentButton : String, clickHandler : (String) -> Unit){


    val btnWidth by animateDpAsState(
        if (title == currentButton) 150.dp else 105.dp
    )

    Button(
        onClick = {
            clickHandler(title)
        },
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(5.dp)
            .width(btnWidth),
        border = BorderStroke(2.5.dp, color = Color.White),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (currentButton == title) Color.White else Purple500
        ),
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 7.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title,
                color = if (currentButton == title) Purple700 else Color.White,
                style = MaterialTheme.typography.h5
            )
            if (title == currentButton) Icon(Icons.Default.SelectAll, contentDescription = null, tint = Purple500, modifier = Modifier.padding(start = 13.dp))
        }
    }
}

@Composable
fun GridItem(title : String, icon : Int, index : Int, color : Color,clickHandler: (String) -> Unit){
    Column(
        modifier = Modifier.padding(vertical = 7.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .size(height = 100.dp, width = 100.dp)
                .padding(5.dp),
            shape = CircleShape,
            elevation = 3.dp
        ) {
            Image(
                painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                        clickHandler("report$index")
                    }
                    .size(50.dp)
            )

        }

        Text(text = title, color = Color.Black.copy(.8f))

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAutomationTheme {

    }
}