package com.example.calculatorcompose

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorcompose.ui.theme.CalculatorComposeTheme
import com.example.calculatorcompose.ui.theme.MediumGray

class MainActivity : ComponentActivity() {

    private val TAG = "ActivityLifecycle" // Consistent tag for logging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
        setContent {
            // Display splash screen initially
            SplashScreen {
                navigateToCalculatorScreen()
            }
        }
    }

    // Function to navigate to the calculator screen after splash screen
    private fun navigateToCalculatorScreen() {
        setContent {
            CalculatorComposeTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacing = 8.dp
                Calculator(
                    state = state,
                    onAction = viewModel::onAction,
                    buttonSpacing = buttonSpacing,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MediumGray)
                        .padding(16.dp)
                )
            }
        }
    }

    // Splash screen composable
    @Composable
    fun SplashScreen(onSplashScreenFinished: () -> Unit) {
        // Use a Handler to delay switching to the calculator screen
        Handler(Looper.getMainLooper()).postDelayed({
            onSplashScreenFinished()
        }, SPLASH_SCREEN_DELAY)


        Surface(
            color = Color.Black,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash),
                contentDescription = "Splash Screen Image",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
    companion object {
        private const val SPLASH_SCREEN_DELAY = 2000L // Delay in milliseconds
    }
}
