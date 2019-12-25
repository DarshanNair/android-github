package com.darshan.github.core.extensions

fun Throwable.isNetworkError() = this is java.io.IOException