package com.example.appName.common.extension

import android.content.Context
import com.bumptech.glide.load.HttpException
import com.example.appName.R

fun Throwable.getMessage(context: Context) =
        when (this) {
            is HttpException -> context.getString(R.string.exception_message_http)
            else -> this.localizedMessage ?: context.getString(R.string.exception_message_generic,
                    "$this")
        }

