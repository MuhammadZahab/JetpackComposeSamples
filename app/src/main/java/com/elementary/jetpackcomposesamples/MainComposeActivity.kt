package com.elementary.jetpackcomposesamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elementary.jetpackcomposesamples.ui.theme.JetpackComposeSamplesTheme

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeSamplesTheme {
                // A surface container using the 'background' color from the theme
                MyApp()

            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier){
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
private fun Greetings(modifier: Modifier = Modifier,names: List<String> = listOf("World", "Compose")) {

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = modifier.padding(vertical = 4.dp)) {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }
}

@Composable
private fun Greeting(name: String) {

    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = "$name!")
            }
            ElevatedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }


        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked:()-> Unit,modifier:Modifier = Modifier ){
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
     ) {

        Text(text = "Welcome to the Jetpack Compose")

        Button(
            modifier = Modifier.padding(24.dp),
            onClick = onContinueClicked ) {
            Text(text = "Continue")
        }

    }



}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetpackComposeSamplesTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetpackComposeSamplesTheme {
        OnboardingScreen(onContinueClicked = {})
    }


}

@Preview
@Composable
fun MyAppPreview() {
    JetpackComposeSamplesTheme {
        MyApp(Modifier.fillMaxSize())
    }
}