package com.example.contactsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactsapp.R

data class Contact(val name: String, val phoneNumber: String, val imageRes: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsApp() {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contacts App") },
                actions = {
                    IconButton(onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:+201012345678")
                        }
                        context.startActivity(intent)
                    })
                    {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_call),
                            contentDescription = "Call Home"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        ContactList(Modifier.padding(paddingValues))
    }
}

@Composable
fun ContactList(modifier: Modifier = Modifier) {
    val contacts = listOf(
        Contact("Auntie", stringResource(R.string.auntie_phone), R.drawable.auntie),
        Contact("Brother", stringResource(R.string.brother_phone), R.drawable.brother),
        Contact("Daughter", stringResource(R.string.daughter_phone), R.drawable.daughter),
        Contact("friend 1", "+20134567901", R.drawable.friend_1),
        Contact("friend 2", "+201456789012", R.drawable.friend_2),
        Contact("grandfather", "+2015567890123", R.drawable.grandfather),
        Contact("granny", "+201678901234", R.drawable.granny),
        Contact("neighbour", "+201789012345", R.drawable.neigbour),
        Contact("sister", "+201890123456", R.drawable.sister),
        Contact("son", "+201234420890", R.drawable.son),
        Contact("uncle", "+201234567420", R.drawable.uncle),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxSize()
    ) {
        items(contacts) { contact ->
            ContactCard(contact)
        }
    }
}

@Composable
fun ContactCard(contact: Contact) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .clickable {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${contact.phoneNumber}")
                }
                context.startActivity(intent)
            }
            .background(Color(0xffdde3ea)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = contact.imageRes),
            contentDescription = contact.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .fillMaxSize()
        )
        Text(
            text = contact.name,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        SelectionContainer {
            Text(
                modifier = Modifier
                    .padding(4.dp),
                text = contact.phoneNumber,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContactsApp()
}
