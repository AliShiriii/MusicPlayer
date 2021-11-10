package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, progres: Int, fromUser: Boolean) {

                if (mediaPlayer != null && fromUser) {

                    mediaPlayer.seekTo(progres)

                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }


        })

        binding.playMusic.setOnClickListener {

            mediaPlayer.start()
            addSeekBar()

        }

    }


    private fun addSeekBar() {

        binding.seekBar.max = mediaPlayer.duration
        binding.endTime.text = convertTime(mediaPlayer.duration / 1000)

        val handler = Handler()

        runOnUiThread(object : Runnable {
            override fun run() {

                if (mediaPlayer != null) {

                    val mediaPosition = mediaPlayer.currentPosition
                    binding.seekBar.progress = mediaPosition
                    binding.startTime.text = convertTime(mediaPosition / 1000)

                }

                handler.postDelayed(this, 1000)

            }

        })

    }

    fun convertTime(time: Int): String {

        val min = time / 60
        val sec = time % 60

        return "$min : $sec"

    }
}