package com.forthewy.packup.ui.screens.category.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import com.forthewy.packup.data.model.Category
import com.forthewy.packup.ui.extensions.icon
import com.forthewy.packup.ui.extensions.titleRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.unit.sp

import com.forthewy.packup.ui.theme.CategoryCardBackground
import com.forthewy.packup.ui.theme.CategoryCardBorder
import com.forthewy.packup.ui.theme.CategoryIconTint

@Composable
fun GridCategoryCard(
    category: Category,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CategoryCardBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),

        border = BorderStroke(
            1.dp,
            CategoryCardBorder
        )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = category.icon(),
                contentDescription = null,
                tint = CategoryIconTint,
                modifier = Modifier.size(28.dp)
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = stringResource(category.titleRes()),
                fontSize = 11.sp,
            )
        }
    }
}