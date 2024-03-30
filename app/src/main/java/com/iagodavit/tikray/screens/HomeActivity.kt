package com.iagodavit.tikray.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.iagodavit.tikray.R
import com.iagodavit.tikray.screens.ui.theme.TikrayTheme
import kotlin.math.log

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TikrayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    homeScreen(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun homeScreen(modifier: Modifier = Modifier.fillMaxSize(), navController: NavController) {

    ConstraintLayout(
        modifier = modifier
            .background(colorResource(id = R.color.tikrayColor1))
    ) {

        val (image, title, login, register) = createRefs()

        val topMargin = createGuidelineFromTop(0.1f)

        Image(
            painter = painterResource(id = R.drawable.logo_empresa),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(topMargin)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "TIKRAY",
            style = TextStyle(color = Color.White, fontSize = 50.sp, FontWeight.ExtraBold),
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        val mid = createGuidelineFromTop(0.5f)

        Button(
            onClick = {
                      navController.navigate(route = Screen.Login.route)
            },
            modifier = Modifier
                .constrainAs(login) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(mid)
                    bottom.linkTo(register.top)
                }
                .size(200.dp, 70.dp),
            colors = ButtonDefaults.buttonColors(
                Color.White
            )
        ) {
            Text(
                text = "LOG IN",
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }


        Button(onClick = {
                         navController.navigate(route = Screen.Register.route)
        },
            modifier = Modifier
                .constrainAs(register) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(login.bottom)
                }
                .padding(top = 30.dp)
                .size(120.dp, 45.dp),
            colors = ButtonDefaults.buttonColors(
                Color.White
            )
        ) {
            Text(
                text = "Sign Up",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )

        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun prevhomeScreen() {
    homeScreen(navController = rememberNavController())
}