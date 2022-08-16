package com.xtremedevx.permissionhandling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.xtremedevx.permissionhandling.ui.screen.MainScreen
import com.xtremedevx.permissionhandling.ui.theme.PermissionHandlingTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PermissionHandlingTheme {
                MainScreen()
            }
        }

    }

}

