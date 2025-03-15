package com.book.dictionaryappmvvm.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchView(
    label: String = "Search Repository",
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onSearch : () -> Unit
) {
    OutlinedTextField(
        modifier = modifier, value = value, onValueChange = {
        onValueChange.invoke(it)
    }, trailingIcon = {
            IconButton(onClick = {
                onSearch.invoke()
            }){
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
    },
        label = {
            Text(text = label)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SearchView(value = "Abc", onValueChange = {}, onSearch = {})
}