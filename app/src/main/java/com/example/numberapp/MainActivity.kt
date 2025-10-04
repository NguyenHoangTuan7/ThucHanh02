package com.example.numberapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberapp.ui.theme.NumberAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            NumberAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    PracticeScreen()
                }
            }
        }
    }
}


@Composable
fun PracticeScreen() {

    var textInput by remember { mutableStateOf("") }

    var numberItems by remember { mutableStateOf<List<Int>>(emptyList()) }

    var errorMessage by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Thực hành 02",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedTextField(
                value = textInput,
                onValueChange = { textInput = it },
                label = { Text("Nhập vào số lượng") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )


            Button(
                onClick = {

                    if (textInput.isBlank()) {

                        errorMessage = "Vui lòng nhập số!"
                        numberItems = emptyList()
                    } else {
                        val number = textInput.toIntOrNull()
                        if (number == null || number <= 0) {

                            errorMessage = "Dữ liệu bạn nhập không hợp lệ"
                            numberItems = emptyList()
                        } else {

                            errorMessage = ""
                            numberItems = (1..number).toList()
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Tạo", modifier = Modifier.padding(vertical = 8.dp))
            }
        }


        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        if (numberItems.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(numberItems) { number ->
                    ListItem(number.toString())
                }
            }
        }
    }
}


@Composable
fun ListItem(text: String) {
    Text(
        text = text,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE53935), shape = RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumberAppTheme {
        PracticeScreen()
    }
}
