package com.xtremedevx.permissionhandling.ui.screen

import android.Manifest
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen() {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
    ) { paddingValue ->
        HandleScreen(
            paddingValue = paddingValue,
            permissionState = permissionState,
            showSnackbar = { msg ->
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(message = msg, actionLabel = "OK")
                }
            },
            requestPermission = { permissionState.launchPermissionRequest() }
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HandleScreen(
    permissionState: PermissionState,
    paddingValue: PaddingValues,
    showSnackbar: (String) -> Unit,
    requestPermission: () -> Unit,

    ) {
    when (permissionState.status) {
        PermissionStatus.Granted -> {
            CameraScreen()
        }

        else -> {
            HomeScreen(
                permissionState = permissionState,
                showSnackbar = showSnackbar,
                requestPermission = requestPermission
            )
        }
    }

}