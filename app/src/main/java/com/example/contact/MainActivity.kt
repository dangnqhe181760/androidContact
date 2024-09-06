package com.example.contact

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contact.ui.theme.ContactTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
data class Contact(var name: String, var phoneNumber: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactTheme {
                MainScreen1()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {//callback
                        //todo smt
                        val intent = Intent(this@MainActivity, addContact::class.java)\
                        intent.putExtra("name", "John Doe")
                        startActivity(intent)

                    }) {
                        Text("Add contact", fontSize = 16.sp)
                    }
                }

            }
        }
    }
}

@Composable
fun MainScreen1(){

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val contacts = remember { mutableStateListOf<Contact>()}
        contacts.add(Contact("John Doe", "123-456-7890"))
        contacts.add(Contact("Jane Smith", "987-654-3210"))
        contacts.add(Contact("Bob Johnson", "555-555-5555"))
        DisplayList(contacts)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactTheme {
        Greeting("Android")
    }
}

fun addToList(name: String, phoneNumber: String) {
    addToList(contacts, name, phoneNumber)
}

fun addToList(contacts: MutableList<Contact>, name: String, phoneNumber: String) {
    contacts.add(Contact(name, phoneNumber))
    Log.d("list", contacts.toString())
}

@Composable
fun DisplayList(contacts: MutableList<Contact>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Contacts",
            modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                    color = Color.Black,
            fontSize = TextUnit(value = 20.0F, type = TextUnitType.Sp)
        ),
            fontWeight = FontWeight.Black
        )
        LazyColumn {
            items(contacts.size) { index ->
                Text(
                    text = contacts[index].name + " - " + contacts[index].phoneNumber,
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit(value = 20.0F, type = TextUnitType.Sp)
                    ),
                    fontWeight = FontWeight.Black
                )
                HorizontalDivider()
            }
        }
    }
}