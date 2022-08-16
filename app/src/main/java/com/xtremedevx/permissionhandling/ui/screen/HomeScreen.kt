package com.xtremedevx.permissionhandling.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.xtremedevx.permissionhandling.utils.isPermanentlyDeclined


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    permissionState: PermissionState,
    showSnackbar: (String) -> Unit,
    requestPermission: () -> Unit
) {
    var isFirstTime by rememberSaveable {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home Screen")

        Button(onClick = {
            if (permissionState.isPermanentlyDeclined(isFirstTime)) {
                showSnackbar("Permission revoked permanently \nGrant permission from System setting")
            } else {
                requestPermission()
            }
        }
        ) {
            Text(text = "Get Permission")
        }
    }
    when {
        permissionState.status.shouldShowRationale -> {
            showSnackbar("App need permission to use \nCamera Feature")
            isFirstTime = false
        }
        permissionState.isPermanentlyDeclined(isFirstTime = isFirstTime) -> {
            showSnackbar("Permission revoked permanently \nGrant permission from System setting")
        }


    }

}