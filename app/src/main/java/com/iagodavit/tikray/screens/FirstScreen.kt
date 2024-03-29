package com.iagodavit.tikray.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.iagodavit.tikray.R
import com.iagodavit.tikray.R.color.black
import com.iagodavit.tikray.R.color.tikrayColor1
import com.iagodavit.tikray.R.drawable.logo_empresa
import com.iagodavit.tikray.screens.ui.theme.TikrayTheme
import org.intellij.lang.annotations.JdkConstants

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = tikrayColor1))
    ) {
        val (title, logo, subtitle, mail, passwd, button, button1) = createRefs()
        val marginTop = createGuidelineFromTop(0.1f)
        val marginForLogin = createGuidelineFromTop(0.35f)
        var mailText by rememberSaveable {
            mutableStateOf(" ")
        }
        var passwordText by rememberSaveable {
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

        OutlinedTextField(
            value = mailText,
            onValueChange = { mailText = it },
            label = { Text(text = "Mailaaaaaaaaaaaa") },
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

            )
        )
        OutlinedTextField(
            value = passwordText,
            onValueChange = { passwordText = it },
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

            )
        )
    }


}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    MainScreen()

}
