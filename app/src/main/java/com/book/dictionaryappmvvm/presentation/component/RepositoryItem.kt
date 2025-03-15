package com.book.dictionaryappmvvm.presentation.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.book.dictionaryappmvvm.R

@Composable
fun RepositoryItem(image : String, repoName : String, repoDetails : String) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier.size(70.dp).clip(shape = RoundedCornerShape(size = 10.dp)),
                error = painterResource(R.drawable.image_placeholder),
                placeholder = painterResource(R.drawable.image_placeholder)
            )
            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Text(
                    text = repoName,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )
                Text(
                    text = repoDetails,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light)
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    RepositoryItem(
        image = "",
        repoName = "Kotlin",
        repoDetails = "Bangladesh, to the east of India on the Bay of Bengal, is a South Asian country marked by lush greenery and many waterways. Its Padma (Ganges)"
    )
}