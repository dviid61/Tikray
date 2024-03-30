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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
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
import com.iagodavit.tikray.screens.ui.theme.progressBar
import kotlin.io.encoding.Base64

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            myApp()
        }
    }
}


fun checkTextFields(
    name: String,
    surname: String,
    email: String,
    password: String,
    confirmPassword: String
): Boolean {
    if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
        return true

    } else {
        return false
    }

}

@Composable


fun myApp(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color(19, 18, 69))
) {

    var confirmPassword by remember { mutableStateOf("") }
    var typeNoPasswordd = convertTypePassword(confirmPassword)
    var password by remember { mutableStateOf("") }
    var typeNoPassword = convertTypePassword(password)
    var email by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    var lista = progressBar(password)
    var progres = lista[0].toString().toFloat()
    var colorr = lista[1].toString().toInt()

    val textInformationSecurityPasswd = when (colorr) {
        1 -> "the password is very simple"
        2 -> "The password is not secure enough"
        3 -> "The password is secure"
        else -> " "
    }





    ConstraintLayout(modifier = modifier) {

        val (logo, entryFields, btns, progressLine) = createRefs()

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(all = 20.dp)

            .constrainAs(logo) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end),
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
                    text = "SIGN UP",
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

            val colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                colors = colors,
                maxLines = 1,
                singleLine = true
            )

            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text(text = "Surname") },
                colors = colors,
                maxLines = 1,
                singleLine = true
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                colors = colors,
                maxLines = 1,
                singleLine = true
            )


            OutlinedTextField(

                value = typeNoPassword,
                onValueChange = { password = it },
                label = { Text(text = "Create password") },
                colors = colors,
                maxLines = 1,
                singleLine = true


            )

            LinearProgressIndicator(
                progress = progres,
                color = when (colorr) {
                    1 -> Color.Red
                    2 -> Color.Yellow
                    3 -> Color.Green
                    else -> Color.Gray
                },
                trackColor = when (progres) {
                    in 0.0f..0.09f -> Color.Transparent
                    in 0.1f..100f -> Color.White
                    else -> Color.Gray
                },


                )
            Text(
                text = textInformationSecurityPasswd, color = when (colorr) {
                    1 -> Color.Red
                    2 -> Color.Yellow
                    3 -> Color.Green
                    else -> Color.Gray
                },
            )


            OutlinedTextField(
                value = typeNoPasswordd,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm password") },
                colors = colors,
                maxLines = 1,
                singleLine = true

            )

        }
        val buttonEnabledOrDisabled =
            checkTextFields(name, surname, email, password, confirmPassword)
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Gray,
                containerColor = Color.White,
                contentColor = Color(18, 19, 68)
            ),
            enabled = !buttonEnabledOrDisabled,
            modifier = Modifier
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