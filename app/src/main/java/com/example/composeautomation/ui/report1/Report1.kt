package com.example.composeautomation.ui.report1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeautomation.ui.theme.ComposeAutomationTheme

@Composable
fun Report1(){
    Scaffold(
        Modifier
            .fillMaxSize()
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAutomationTheme {
        Report1()
    }
}