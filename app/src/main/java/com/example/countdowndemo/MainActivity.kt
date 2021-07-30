package com.example.countdowndemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var countDownTimer:CountDownTimer? = null
    private var timeDuration:Long = 60000
    private var pauseOffset:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer.text = "${(timeDuration/1000).toString()}"
        btnStart.setOnClickListener {
            startTimer(pauseOffset)
        }

        btnStop.setOnClickListener {
            pauseTimer()
        }
        btnReset.setOnClickListener {
            resetTimer()
        }




    }

    private fun startTimer(pauseOffsetL:Long){
        countDownTimer = object : CountDownTimer(timeDuration- pauseOffsetL, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timeDuration - millisUntilFinished
                tvTimer.setText("seconds remaining: " + millisUntilFinished / 1000)
            }


            override fun onFinish() {
                tvTimer.setText("done!")
            }
        }.start()
    }

    private fun pauseTimer(){
        if(countDownTimer!=null){
            countDownTimer!!.cancel()

        }
    }

    private fun resetTimer(){
        if(countDownTimer!=null){
            countDownTimer!!.cancel()
            tvTimer.text = "${(timeDuration/1000).toString()}"
            countDownTimer=null
            pauseOffset =0

        }
    }
}