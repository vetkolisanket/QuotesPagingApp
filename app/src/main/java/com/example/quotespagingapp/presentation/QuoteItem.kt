package com.example.quotespagingapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quotespagingapp.domain.Quote
import com.example.quotespagingapp.ui.theme.QuotesPagingAppTheme

@Composable
fun QuoteItem(
    quote: Quote,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                imageVector = Icons.Filled.FormatQuote,
                contentDescription = "Quote",
                colorFilter = ColorFilter.tint(Color.White),
                alignment = Alignment.TopStart,
                modifier = Modifier
                    .size(64.dp)
                    .rotate(180F)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Genre - ${quote.quoteGenre}",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = quote.quoteText,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(1.dp)
                        .background(Color.Black)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "- ${quote.quoteAuthor}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}

@Preview
@Composable
private fun QuoteItemPreview() {
    QuotesPagingAppTheme {
        QuoteItem(
            quote = Quote(
                id = 9,
                quoteAuthor = "abc",
                quoteGenre = "was",
                quoteText = "asadsad asdasdas asdasdsa"
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}