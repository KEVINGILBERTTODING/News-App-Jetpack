package com.example.newsappjetpack.home.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsappjetpack.R
import com.example.newsappjetpack.home.model.PostModel
import com.example.newsappjetpack.home.viewmodels.HomeViewModel
import com.example.newsappjetpack.home.viewmodels.UiState
import com.example.newsappjetpack.ui.theme.NewsAppJetpackTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppJetpackTheme {

                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)) {
                    Column(modifier = Modifier.padding(top = 16.dp)) {

                        Banner()
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        NewsScreen(context = this@MainActivity)
                    }

                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(16.dp)
    )
}

@Composable
private fun Banner() {
    Text(text = stringResource(id = R.string.intro),
        style = TextStyle(
        color = MaterialTheme.colorScheme.tertiary,
        fontSize = 10.sp,



    ))
    Text(text = stringResource(id = R.string.H1),
        style = TextStyle(

            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            brush = Brush.linearGradient(
                colors = listOf(MaterialTheme.colorScheme.inversePrimary, MaterialTheme.colorScheme.primary)
            )
        ))

}

@Composable
private fun NewsScreen(viewModel: HomeViewModel = viewModel(), context: Context ) {
    val newsState by viewModel.newsState.collectAsState()

    when (newsState) {
        is UiState.Loading -> {
            LoadingIndicator()
        }

        is UiState.Success -> {

            DisplayData(data = (newsState as UiState.Success).news, context)



        }

        is UiState.Error -> {
            Log.d("NewsScreen", "error: ", (newsState as UiState.Error).exception)



        }
    }
}



@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Text(text = "Loading...")
    }
}

@Composable
fun DisplayData(data: List<PostModel>, context: Context) {
    LazyColumn {
        items(data) { item ->
            Surface(
                onClick =  {
                   openLink(context, item.link!!)
                },
                shape = MaterialTheme.shapes.medium,

                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.background
            ) {
                Card {

                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(100.dp),
                            model = item.thumbail, contentDescription = item.title, )

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(text = item.title, style =
                            TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),)

                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text = item.pubDate, style =
                            TextStyle(
                                fontSize = 10.sp
                            ))
                        }


                    }
                }
                
            }
        }
    }
}

fun openLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppJetpackTheme {
        Greeting("Android")
    }
}