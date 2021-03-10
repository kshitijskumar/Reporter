package com.example.reporter.utils

import android.content.Context
import com.example.reporter.model.retrofit.NewsInterface
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

//this class is acting like a DI package, and all the major dependencies are created here
object InjectorUtils {

    fun providesApi() = NewsInterface.getInstance()

    private fun providesAudioAttributes() = AudioAttributes.Builder()
            .setContentType(C.CONTENT_TYPE_SPEECH)
            .setUsage(C.USAGE_MEDIA)
            .build()

    fun providesExoplayer(
            context: Context,
            audioAttributes: AudioAttributes = providesAudioAttributes()
    ) = SimpleExoPlayer.Builder(context.applicationContext)
            .setAudioAttributes(audioAttributes, true)
            .setHandleAudioBecomingNoisy(true)
            .build()

    fun providesDataSourceFactory(
            context: Context
    ) = DefaultDataSourceFactory(context.applicationContext, Util.getUserAgent(context.applicationContext, "Reporter"))
}