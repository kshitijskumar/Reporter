package com.example.reporter.services

import android.app.PendingIntent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.example.reporter.utils.Constants.PENDING_INTENT_REQUEST_CODE
import com.example.reporter.utils.InjectorUtils.providesDataSourceFactory
import com.example.reporter.utils.InjectorUtils.providesExoplayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

private const val MEDIA_SESSION_TAG = "ReporterService"
class ReporterService : MediaBrowserServiceCompat() {

    private val dataSourceFactory by lazy {
        providesDataSourceFactory(this)
    }
    private val exoplayer by lazy {
        providesExoplayer(this)
    }

    private val job = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + job)

    private lateinit var mediaSession : MediaSessionCompat
    private lateinit var mediaSessionConnector : MediaSessionConnector


    override fun onCreate() {
        super.onCreate()

        val activityIntent = packageManager?.getLaunchIntentForPackage(packageName)?.let {
            PendingIntent.getActivity(this, PENDING_INTENT_REQUEST_CODE, it, 0)
        }

        mediaSession = MediaSessionCompat(this, MEDIA_SESSION_TAG).apply {
            setSessionActivity(activityIntent)
            isActive = true
        }
        sessionToken = mediaSession.sessionToken

        mediaSessionConnector = MediaSessionConnector(mediaSession)
        mediaSessionConnector.setPlayer(exoplayer)
    }
    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        TODO("Not yet implemented")
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}