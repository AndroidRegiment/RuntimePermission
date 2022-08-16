package com.xtremedevx.permissionhandling.utils

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentlyDeclined(isFirstTime: Boolean) =
    !this.status.isGranted && !this.status.shouldShowRationale && !isFirstTime

