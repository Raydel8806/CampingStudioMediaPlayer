package com.campingstudio.campingstudiomediaplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import com.campingstudio.campingstudiomediaplayer.databinding.ActivityCampingStudioMediaPlayerBinding


class CampingStudioMediaPlayer : AppCompatActivity() {
    private lateinit var binding: ActivityCampingStudioMediaPlayerBinding

    private lateinit var vvCampingPlayer : VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCampingStudioMediaPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vvCampingPlayer = binding.vvCampingPlayer
        val mediaController = MediaController(this)
        mediaController.setAnchorView(vvCampingPlayer)
        vvCampingPlayer.setMediaController(mediaController)

        val mPos: Int? = savedInstanceState?.getInt("pos")
        intent.getStringExtra("video").let { itVideo ->
            vvCampingPlayer.setVideoURI(Uri.parse(itVideo))
            vvCampingPlayer.start()
            mPos.let {itProgress ->
                if (itProgress != null) {
                    vvCampingPlayer.requestFocus()
                    vvCampingPlayer.seekTo(itProgress)
                }
            }
        }
        vvCampingPlayer.setOnCompletionListener {onBackPressed()}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("pos", vvCampingPlayer.currentPosition)
    }
}

