package com.forthewy.packup.ui.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.forthewy.packup.ui.theme.CategoryIconTint
import com.forthewy.packup.ui.theme.ProgressComplete

@Composable
fun ProgressBlocks(
    progress : Int
) {
    val progressColor =
        if (progress == 100)
            ProgressComplete
        else
            CategoryIconTint

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        repeat(10) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        if (index < progress / 10)
                            progressColor
                        else
                            MaterialTheme.colorScheme.outlineVariant
                    )
            )
        }
    }
}