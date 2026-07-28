package com.forthewy.packup.ui.screens.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Luggage
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.forthewy.packup.ui.theme.CategoryIconTint
import com.forthewy.packup.ui.theme.CategoryText

@Composable
fun HomeScreen(
    onStartClick: () -> Unit,
    onSettingClick: () -> Unit,
    onLicenseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons.Default.Luggage,
            contentDescription = null,
            modifier = Modifier.size(96.dp),
            tint = CategoryIconTint
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Pack Up",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            color = CategoryText,
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "여행 준비부터 시험 준비까지\n모든 준비를 한 곳에서.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(
                lineHeight = 24.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(48.dp))

        Button(
            onClick = onStartClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CategoryIconTint,
                contentColor = Color.White
            )
        ) {
            Text(
                "시작하기",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            TextButton(
                onClick = onSettingClick
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = CategoryText
                )
                Spacer(Modifier.width(4.dp))
                Text("Settings", style = TextStyle(color = CategoryText))
            }

            TextButton(
                onClick = onLicenseClick
            ) {
                Icon(
                    imageVector = Icons.Default.Policy, // 또는 Gavel
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = CategoryText
                )
                Spacer(Modifier.width(4.dp))
                Text("Licenses", style = TextStyle(color = CategoryText))
            }
        }
    }
}