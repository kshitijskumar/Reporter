package com.example.reporter.model.responses

import com.example.reporter.utils.Constants.AUDIO_API
import com.example.reporter.utils.Constants.AUDIO_BASE_URL

data class Articles(
    val author : String? = null,
    val title : String? = null,
    val description: String? = null,
    val url : String? = null,
    val urlToImage: String? = null
){
    val audioUrl = "$AUDIO_BASE_URL?key=$AUDIO_API&hl=en-in&v=Ajit&c=MP3&f=16khz_16bit_stereo&src=$description"
}