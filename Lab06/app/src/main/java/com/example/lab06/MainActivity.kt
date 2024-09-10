package com.example.lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab06.ui.theme.Lab06Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab06Theme {
                MemberInfo()
            }
        }
    }
}

@Composable
fun MemberInfo() {
    var imageId by remember { mutableIntStateOf((1..3).random()) }
    val memberInfo = MemberData.members[imageId - 1]

    val imageRes = when (imageId) {
        1 -> R.drawable.anders
        2 -> R.drawable.haavisto
        else -> R.drawable.petteri_orpo
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = imageRes), contentDescription = "Parliament member",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(512.dp)
                .padding(top = 48.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "${memberInfo.name} \n" +
                    "${memberInfo.party} \n" +
                    "Seat number: ${memberInfo.seatNumber}",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Button(
            onClick = { imageId = (1..3).random() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Next random MP")
        }
    }
}

data class ParliamentMember(val id: Int, val seatNumber: Int, val name: String, val party: String)

object MemberData {
    val members = listOf(
        ParliamentMember(
            id = 1,
            seatNumber = 83,
            name = "Anders Adlercreutz",
            party = "Swedish People's Party of Finland"
        ),
        ParliamentMember(
            id = 2,
            seatNumber = 6,
            name = "Pekka Haavisto",
            party = "Green League"
        ),
        ParliamentMember(
            id = 3,
            seatNumber = 57,
            name = "Petteri Orpo",
            party = "National Coalition Party",
        )
    )
}

@Preview(showBackground = true)
@Composable
fun MemberInfoPreview() {
    Lab06Theme {
        MemberInfo()
    }
}