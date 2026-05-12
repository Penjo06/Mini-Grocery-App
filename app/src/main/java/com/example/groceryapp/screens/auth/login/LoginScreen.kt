package com.example.groceryapp.screens.auth.login

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.groceryapp.navigation.Screens
import com.example.groceryapp.utils.NotificationHelper
import com.example.groceryapp.viewmodel.LoginViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    Surface (modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier.padding(top = 50.dp, start = 10.dp, end = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Mini Grocery",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onBackground)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Grocery at your doorstep",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground)

            Spacer(modifier = Modifier.height(40.dp))

            Surface (modifier = Modifier.padding(4.dp)
                .size(130.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                shadowElevation = 6.dp,
                tonalElevation = 6.dp
            ){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    Image(painter = painterResource(com.example.groceryapp.R.drawable.app_icon2), "app icon",
                        modifier = Modifier.clip(CircleShape)
                            .size(115.dp))
                }
            }

            Spacer(modifier = Modifier.height(120.dp))

            LoginForm(navController, viewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun LoginForm(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ){}
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Verify your Mobile Number to Login",
            color = MaterialTheme.colorScheme.onBackground)
        OutlinedTextField(
            value = viewModel.phoneNumber,
            onValueChange = {
                viewModel.onNumberChange(it)
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Mobile Number",
                color = MaterialTheme.colorScheme.onBackground) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        if (viewModel.isValid){
            OutlinedTextField(
                value = viewModel.otp,
                onValueChange = {
                    viewModel.onOtpChange(it)
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("Enter OTP",
                    color = MaterialTheme.colorScheme.onBackground) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true)
        }else{
            Box{}
        }
        Spacer(modifier = Modifier.height(8.dp))

        if(viewModel.error.isNotEmpty()){
            Text(text = viewModel.error,
                color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        if(viewModel.isValid){
            Button(onClick = {
                val isVerified = viewModel.validateOtp()
                if(isVerified){
                    navController.navigate(Screens.HomeScreen.name)
                    Toast.makeText(context, "OTP verified successfully", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Verify",
                    color = MaterialTheme.colorScheme.onBackground)
            }

        }else{
            Button(onClick = @androidx.annotation.RequiresPermission(Manifest.permission.POST_NOTIFICATIONS) {
                val isValid = viewModel.validatePhoneNumber()
                if(isValid){
                    viewModel.isValid = true
                    if(ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    )!= PackageManager.PERMISSION_GRANTED){
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }

                    NotificationHelper(context)
                        .showOtpNotification()

                    Toast.makeText(
                        context,
                        "OTP sent successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text("Continue",
                    color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

