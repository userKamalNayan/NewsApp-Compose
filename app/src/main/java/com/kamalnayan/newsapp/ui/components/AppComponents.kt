package com.kamalnayan.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kamalnayan.newsapp.R
import com.kamalnayan.newsapp.data.entity.headlines.Article
import com.kamalnayan.newsapp.data.entity.headlines.HeadlineResponse
import com.kamalnayan.newsapp.ui.theme.Purple40
import com.kamalnayan.newsapp.ui.theme.Purple80

/** @Author Kamal Nayan
Created on: 27/12/23
 **/

@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(16.dp), color = Purple40
        )
    }
}

@Composable
fun NewsList(response: HeadlineResponse) {
    LazyColumn {
        items(response.articles) {
            HeadingTextComponent(text = it.description ?: "")
        }
    }
}

@Composable
fun NormalTextComponent(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.SansSerif,
            color = Color.Gray
        ),
    )
}

@Composable
fun HeadingTextComponent(text: String, centered: Boolean = false) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        style = TextStyle(color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Medium),
        textAlign = if (centered) TextAlign.Center else TextAlign.Justify
    )
}

@Composable
fun EmptyStateView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_hourglass_top_24),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center,
        )
        HeadingTextComponent(text = "Waiting for news to be made.....", centered = true)
    }

}

@Composable
fun NewsRowComponent(page: Int, article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            model = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(20.dp))
        HeadingTextComponent(text = article.title.orEmpty())
        Spacer(modifier = Modifier.width(10.dp))
        NormalTextComponent(text = article.description.orEmpty())
        Spacer(modifier = Modifier.weight(1f))
        AuthorDetailsComponent(
            authorName = article.author.orEmpty(), sourceName = article.source?.name.orEmpty()
        )
    }
}

@Composable
fun AuthorDetailsComponent(authorName: String?, sourceName: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        authorName?.let {
            Text(text = it)

        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        sourceName?.let {
            Text(text = it)
        }
    }
}

@Composable
fun ErrorView(errorMessage: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.baseline_error), contentDescription = null)
        Spacer(modifier = Modifier.size(5.dp))
        errorMessage?.let {
            Text(
                text = errorMessage,
                textAlign = TextAlign.Center,
                style = TextStyle(color = Color.Red, fontWeight = FontWeight.Medium)
            )
        }
    }
}

@Preview
@Composable
fun NewsRowComponentPreview() {
    val article = Article(
        author = "Kamal Nayan",
        content = null,
        description = null,
        publishedAt = null,
        source = null,
        title = null,
        url = null,
        urlToImage = null
    )
    NewsRowComponent(page = 0, article = article)
}