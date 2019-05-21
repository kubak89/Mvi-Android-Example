package com.example.appName.common.extension

import android.app.Activity
import com.example.appName.presentation.random.RandomActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

fun Activity.goToMainScreen() {
    startActivity<RandomActivity>()
}

fun Activity.goToRegisterActivity() {
    toast("Open register activity")
}