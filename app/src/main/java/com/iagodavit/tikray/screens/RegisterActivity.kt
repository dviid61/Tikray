package com.iagodavit.tikray.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.iagodavit.tikray.R
import com.iagodavit.tikray.screens.ui.theme.TikrayTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TikrayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    myApp()
                }
            }
        }
    }
}

@Composable
fun myApp(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color(19, 18, 69))
) {

    ConstraintLayout(modifier = modifier) {

        val (logo, entryFields, btns) = createRefs()

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(all = 20.dp)

            .constrainAs(logo) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                val (logo, title) = createRefs()

                Image(painter = painterResource(
                    id = R.drawable.logo_empresa
                ),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(logo) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(title.start)
                        }
                )

                Text(
                    text = "Sign up",
                    style = TextStyle(color = Color.White, fontSize = 42.sp, FontWeight.ExtraBold),
                    modifier = Modifier
                        .constrainAs(title) {
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                            start.linkTo(logo.end)

                        }
                        .padding(start = 20.dp)
                )

            }

        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(horizontal = 20.dp)
                .constrainAs(entryFields) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {

            var name by remember { mutableStateOf("") }
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
            )

            var surname by remember { mutableStateOf("") }
            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text(text = "Surname") })

            var email by remember { mutableStateOf("") }
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") })

            var date by remember { mutableStateOf("") }
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text(text = "Birthday") })

            var phone by remember { mutableStateOf("") }
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(text = "Phone") })

        }

        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .constrainAs(btns) {
                top.linkTo(entryFields.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .size(150.dp, 60.dp)) {
            Text(text = "Continue", fontSize = 20.sp)
        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun prevApp() {
    myApp()
}