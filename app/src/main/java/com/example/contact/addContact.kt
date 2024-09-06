package com.example.contact

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contact.ui.theme.ContactTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider

class addContact : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            ContactTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column (
//                        modifier = Modifier.fillMaxSize().padding(innerPadding)
//                    ){
//                        MyTextFieldUI()
//                        Button(onClick = {//callback
//                            //todo smt
//
//                        }) {
//                            Text("Add", fontSize = 16.sp)
//                        }
//                    }
//
//                }
//            }
            val viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
            val contacts = viewModel.getContacts()
            var name by remember {
                mutableStateOf("")
            }
            var phoneNumber by remember {
                mutableStateOf("")
            }
            val mcontext = LocalContext.current

            Column() {
                TextField(value = name, onValueChange = {name = it},label = { Text("Name")})
                TextField(value = phoneNumber, onValueChange = {phoneNumber = it}, label = { Text("Phone number")})
                Button(onClick = {
                    Toast.makeText(mcontext, name, Toast.LENGTH_LONG).show()
                    viewModel.addContacts(name, phoneNumber)
                    Log.d("name", name)
                    Log.d("phoneNumber", phoneNumber)
                    Log.d("list", contacts.size.toString())
                }) {
                    Text(text = "Add")
                }
            }

//            Column() {
//
//            }
//            Column() {
//
//            }
        }
    }
}

@Composable
fun MainScreen(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisplayTextFieldAndButton()
    }
}

@Composable
fun DisplayTextFieldAndButton() {
    var name by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    val mcontext = LocalContext.current

    Column() {
        TextField(value = name, onValueChange = {name = it},label = { Text("Name")})
    }

    Column() {
        TextField(value = phoneNumber, onValueChange = {phoneNumber = it}, label = { Text("Phone number")})
    }
    Column() {
        Button(onClick = {
            Toast.makeText(mcontext, name, Toast.LENGTH_LONG).show()
            Log.d("name", name)
            Log.d("phoneNumber", phoneNumber)
        }) {
            Text(text = "Add")
        }
    }
}

@Composable
fun MyTextFieldUI() {
    var text by rememberSaveable { mutableStateOf("Text") }
//    Column(
//        modifier = Modifier.fillMaxSize()
//            .padding(innerPaddingValues)
//    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Phone number") }
        )
    }


@Preview(showBackground = true)
@Composable
fun PreviewMyTextFieldUI() {
    MyTextFieldUI()
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ContactTheme {
        Greeting2("Android")
    }
}