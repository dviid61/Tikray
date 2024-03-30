package com.iagodavit.tikray.screens

import android.graphics.ColorMatrix
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Visibility
import com.iagodavit.tikray.R
import com.iagodavit.tikray.R.color.tikrayColor1
import com.iagodavit.tikray.R.drawable.logo_empresa
import com.iagodavit.tikray.screens.ui.theme.TikrayTheme

class FirstScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TikrayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}



fun convertTypePassword(texto: String): String {
    val asterisc: String = "*"
    val operation = asterisc.repeat(texto.length)
    return operation
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = tikrayColor1))
    ) {
        val (title, logo, subtitle, mail, passwd, button, button1, lineProgress, forgotpass) = createRefs()
        val marginTop = createGuidelineFromTop(0.1f)
        val marginForLogin = createGuidelineFromTop(0.35f)
        var mailText by rememberSaveable {
            mutableStateOf(" ")
        }
        var passwordText by remember {
            mutableStateOf("")
        }
        Text(
            text = "TIKRAY",
            modifier = Modifier.constrainAs(title) {
                top.linkTo(marginTop)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            },
            style = TextStyle(color = Color.White, fontSize = 42.sp, FontWeight.ExtraBold),

            )
        Image(
            painter = painterResource(id = logo_empresa),
            contentDescription = "logo de la empresa",
            modifier = Modifier
                .size(width = 75.dp, height = 75.dp)
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(title.bottom)
                },
        )

        val (icoMail, icoPass, icoVis) = createRefs()

        OutlinedTextField(
            value = mailText,
            onValueChange = { mailText = it },
            label = { Text(text = "Mail") },
            modifier = Modifier.constrainAs(mail) {
                top.linkTo(logo.bottom, margin = 100.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)


            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White

            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

        )

        var showPass by remember { mutableStateOf(false) }
        var realPass by remember { mutableStateOf("") }
        val isEmpty = fieldNotEmpty(mailText,realPass)


        OutlinedTextField(
            value = if (!showPass) {
                convertTypePassword(realPass)
            } else {
                realPass
            },
            onValueChange = {
                if (it.length <= 25) {
                    realPass += it.last()
                }
            },
            label = { Text(text = "Password") },
            modifier = Modifier.constrainAs(passwd) {
                top.linkTo(mail.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White

            ),
            maxLines = 1,
            singleLine = true,
        )


        Image(
            painter = if (showPass == false) {
                painterResource(id = R.drawable.visibility_off)
            } else {
                painterResource(id = R.drawable.visibility)
            },
            contentDescription = null,
            modifier = Modifier
                .constrainAs(icoVis) {
                    top.linkTo(passwd.top)
                    bottom.linkTo(passwd.bottom)
                    end.linkTo(passwd.end)
                }
                .padding(end = 10.dp, top = 8.dp)
                .size(30.dp)
                .clickable {
                    showPass = !showPass
                }

        )

        var lista = progressBar(passwordText)
        var progres = lista[0].toString().toFloat()
        var colorr = lista[1].toString().toInt()
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

            modifier = Modifier
                .constrainAs(lineProgress) {
                    top.linkTo(passwd.bottom, margin = 20.dp)
                    start.linkTo(passwd.start)
                    end.linkTo(passwd.end)


                },


            )

        Button(
            modifier = Modifier.constrainAs(button) {
                top.linkTo(passwd.bottom, margin = 65.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            },
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = tikrayColor1),
                containerColor = Color.White,
                disabledContainerColor = Color.Gray



            ),
            enabled = isEmpty



            ) {
            Text(text = "LOGIN")


        }


        ClickableText(
            text = (AnnotatedString("Have you forgotten your pasword?")),
            onClick = {/* TODO */},
            modifier = Modifier
                .constrainAs(forgotpass) {
                    top.linkTo(button.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 19.dp),
               style = TextStyle(Color.DarkGray)

        )

    }


}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    MainScreen()

}
