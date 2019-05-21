package com.example.appName.common.extension

import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun TextView.createTextChangesObservable(): Observable<String> =
        RxTextView.textChanges(this)
                .map { it.toString() }
                .skip(1)
                .debounce(100L, TimeUnit.MILLISECONDS)