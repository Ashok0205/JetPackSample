package com.example.jetpacksamplev1.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.network.WebResponse
import com.example.jetpacksamplev1.screen.home.model.MoviesResponseItem
import com.example.jetpacksamplev1.ui.compos_view.ShowErrorDialog


@Composable
fun HomeContent(
    viewModel: HomeViewModel
){
    viewModel.getMovies()
    val response by viewModel.userResponse.observeAsState()
    val showDialog = remember { mutableStateOf(false) }
    val errorMsg = remember { mutableStateOf("") }
    when(response)
    {
        is WebResponse.Success->{
            onInitView(response)
        }
        is WebResponse.Error->{
            AnimatedVisibility(showDialog.value){
                if (showDialog.value) {
                    ShowErrorDialog(msg = errorMsg.value,
                        showDialog = showDialog.value,
                        onDismiss = {showDialog.value = false})
                }
            }
        }
        is WebResponse.Loading -> {

        }
    }


}
@Composable
fun onInitView(response: WebResponse<List<MoviesResponseItem>>?)
{
    Surface(
       // modifier = Modifier.fillMaxSize(),color = MaterialTheme.colors.background  color = Color.Black.copy(alpha = 0.6f),
    ) {
        UsersList( response!!.data!!)
    }
}


@Composable
fun UsersList(userList:List<MoviesResponseItem>) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyColumn {

        itemsIndexed(items = userList) { index, item ->
            MovieItem(movie = item, index, selectedIndex) { i ->
                selectedIndex = i
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MovieItem() {
    val movie = MoviesResponseItem(
        "Coco",
        "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar",
        "https://howtodoandroid.com/images/coco.jpg",
        "Latest"
    )

    MovieItem(movie = movie, 0, 0) { i ->

    }
}


@Composable
fun MovieItem(movie: MoviesResponseItem, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {

    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .clickable { onClick(index) }
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface(color = backgroundColor) {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = movie.imageUrl,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.placeholder)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = movie.desc,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = movie.desc,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }

}

@Composable
fun HeaderTitle() {
    Text(
        text = stringResource(id = R.string.home),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            )
    )
}