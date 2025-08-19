package com.example.vklenta.samples

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SideEffectTest(myNumber: MyNumber){
    LazyColumn {
        repeat(5){
            item {
                Text(text = "Number: ${myNumber.a}")
            }
        }
    }
}

data class MyNumber(var a: Int)