package com.blaze.core.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.blaze.core.ui.ui.theme.ClassicBeatTheme
import kotlinx.coroutines.delay
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess



class ErrorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val stackTrace = remember { mutableStateOf("") }
            val errorMessage = remember { mutableStateOf("") }
            try {
                errorMessage.value = intent.getStringExtra("error_message") ?: ""
                stackTrace.value = intent.getStringExtra("stack_trace") ?: ""
            } catch (e: Exception) {
                //do nothing
            }
            ClassicBeatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val errorLottie =
                            rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.working))
                        val progress by animateLottieCompositionAsState(
                            composition = errorLottie.value,
                            restartOnPlay = true,
                            iterations = Int.MAX_VALUE
                        )

                        LaunchedEffect(key1 = true) {
                            delay(30000)
                            this@ErrorActivity.finishAffinity()
                        }

                        // region ui animation
                        LottieAnimation(
                            composition = errorLottie.value,
                            progress = progress,
                            modifier = Modifier.size(200.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Sorry For Inconvenience, Something Went Wrong, Please Try Again Later",
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontWeight = FontWeight(500),

                                )
                        }

                        //end region change ok test

                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .width(120.dp),
                            onClick = { this@ErrorActivity.finishAffinity() },
                            colors = ButtonDefaults.buttonColors(
                                Color.Green, Color.White
                            )
                        ) {
                            Text(
                                text = "OK",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}


fun Activity.crashHandler() {
    Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
        val sw = StringWriter()
        Log.e("crashHandler", "crashHandler: $sw")
        throwable.printStackTrace(PrintWriter(sw))
        val intent = Intent(this@crashHandler, ErrorActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("error_message", "Whoops. An unexpected error occurred")
        intent.putExtra("stack_trace", sw.toString())

        startActivity(intent)
        finish()
        Process.killProcess(Process.myPid())
        exitProcess(2)
    }
}