package com.forthewy.packup.ui.screens.checklist.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.forthewy.packup.R
import com.forthewy.packup.data.model.Category

@Composable
fun SuggestionDialog(
    category: Category,
    onDismiss: () -> Unit,
    onAdd: (List<String>) -> Unit
) {
    val context = LocalContext.current

    val suggestions = remember(category) {
        when (category) {
            Category.TRAVEL ->
                context.resources.getStringArray(R.array.travel_suggestions)

            Category.STUDY ->
                context.resources.getStringArray(R.array.study_suggestions)

            Category.SHOPPING ->
                context.resources.getStringArray(R.array.shopping_suggestions)

            Category.MOVE ->
                context.resources.getStringArray(R.array.move_suggestions)

            Category.FITNESS ->
                context.resources.getStringArray(R.array.fitness_suggestions)

            Category.DAILY ->
                context.resources.getStringArray(R.array.daily_suggestions)

            Category.WORK ->
                context.resources.getStringArray(R.array.work_suggestions)

            Category.ETC ->
                context.resources.getStringArray(R.array.etc_suggestions)
        }
    }

    val selected =
        remember {
            mutableStateListOf<Boolean>().apply {
                repeat(suggestions.size) {
                    add(true)
                }
            }
        }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(stringResource(R.string.suggestions))
        },
        text = {

            LazyColumn {

                itemsIndexed(suggestions) { index, item ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = selected[index],
                            onCheckedChange = {
                                selected[index] = it
                            }
                        )

                        Text(item)
                    }
                }
            }
        },
        confirmButton = {

            TextButton(
                onClick = {

                    val result =
                        suggestions.filterIndexed { index, _ ->
                            selected[index]
                        }

                    onAdd(result.toList())
                }
            ) {
                Text(stringResource(R.string.add))
            }
        },
        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    )

}