package com.forthewy.packup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.forthewy.packup.ui.navigation.PackUpNavGraph
import com.forthewy.packup.ui.theme.PackUpTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PackUpTheme {
                PackUpNavGraph()
            }
        }
    }
}