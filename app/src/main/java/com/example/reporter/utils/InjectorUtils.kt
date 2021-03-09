package com.example.reporter.utils

import com.example.reporter.model.retrofit.NewsInterface

object InjectorUtils {

    fun providesApi() = NewsInterface.getInstance()
}