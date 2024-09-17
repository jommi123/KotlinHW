package com.example.lab08

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab08.ui.theme.Lab08Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : ComponentActivity() {
    private val url = "https://users.metropolia.fi/~jarkkov/folderimage.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab08Theme {
                ShowImage(url = url)
            }
        }
    }
}

fun downloadBitmapFromURL(url: String): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        val imageUrl = URL(url)
        val connection: HttpURLConnection = imageUrl.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val inputStream: InputStream = connection.inputStream
        bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return bitmap
}


@Composable
fun ShowImage(url: String) {
    var image by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(url) {
        launch(Dispatchers.IO) {
            val bitmap = downloadBitmapFromURL(url)
            withContext(Dispatchers.Main) {
                // Set the bitmap as ImageBitmap once downloaded
                image = bitmap?.asImageBitmap()
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        image?.let { Image(bitmap = it, contentDescription = null,
            modifier = Modifier.align(Alignment.Center)) }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab08Theme {

    }
}