package com.forthewy.packup.ui.screens.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CategoryScreen(
    onCategoryClick: (Int) -> Unit // 클릭 이벤트를 람다식(콜백)으로 상위(NavGraph)로 전달합니다.
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { onCategoryClick(5) }) { // 임의의 ID인 5를 보냄
            Text("5번 카테고리로 이동")
        }
    }
}