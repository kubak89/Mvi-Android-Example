package com.example.appName.common.extension

import io.reactivex.Completable
import io.reactivex.Observable

fun <T> Completable.toObservable(returnValueSupplier: () -> T): Observable<T> =
        this.toSingle(returnValueSupplier).toObservable()