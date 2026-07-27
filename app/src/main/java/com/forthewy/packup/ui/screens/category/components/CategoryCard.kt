package com.forthewy.packup.ui.screens.category.components

import com.forthewy.packup.ui.theme.CategoryIconBackground
import com.forthewy.packup.ui.theme.CategoryIconTint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.forthewy.packup.ui.extensions.icon
import com.forthewy.packup.ui.extensions.titleRes
import com.forthewy.packup.ui.screens.category.CategoryItemUiState
import com.forthewy.packup.ui.theme.ProgressComplete

@Composable
fun CategoryCard(
    categoryItem: CategoryItemUiState,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(CategoryIconBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = categoryItem.category.icon(),
                            contentDescription = null,
                            tint = CategoryIconTint,
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = stringResource(categoryItem.category.titleRes()),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${categoryItem.itemCount} items",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Text(
                    text = "${categoryItem.progress}%",
                    style = MaterialTheme.typography.titleMedium,
                    color = CategoryIconTint
                )
            }
            Spacer(Modifier.height(12.dp))
            ProgressBlocks(categoryItem.progress)

            if (categoryItem.progress == 100) {
                Spacer(Modifier.height(8.dp))

                Text(
                    text = "준비 완료!",
                    modifier = Modifier.align(Alignment.End),
                    color = ProgressComplete,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}