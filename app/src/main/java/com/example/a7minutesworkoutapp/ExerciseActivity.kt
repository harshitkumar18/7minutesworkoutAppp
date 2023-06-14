package com.example.a7minutesworkoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore.Audio.Media
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.a7minutesworkoutapp.databinding.ActivityExerciseBinding
import com.example.a7minutesworkoutapp.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityExerciseBinding? = null

    private var restTimer:CountDownTimer? =null
    private var restProgress = 0

    private var exerciseTimer:CountDownTimer? =null
    private var exerciseProgress = 0

    private var exerciselist : ArrayList<ExerciseModel>? = null
    private var currentExercisePos = -1

    private var tts: TextToSpeech? = null

    private var player : MediaPlayer? = null

    private var exerciseadapter: ExerciseStatusAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise )
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarExercise?.setNavigationOnClickListener{
            onBackPressed()
        }
        exerciselist = Constants.defaultExerciseList()

        tts = TextToSpeech(this,this)



        setuprestview()
        setuoexerciserecyclerview()
    }

    override fun onBackPressed() {
        customDialogForBackButton()
//        super.onBackPressed()
    }
    private  fun customDialogForBackButton(){
        val customDialog = Dialog(this)
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.tvyes.setOnClickListener {
                this@ExerciseActivity.finish()
                customDialog.dismiss()
        }
        dialogBinding.tvno.setOnClickListener {
             customDialog.dismiss()
        }
        customDialog.show()
    }
    private fun setuoexerciserecyclerview(){
        binding?.rvexercisestatus?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

         exerciseadapter = ExerciseStatusAdapter(exerciselist!!)

        binding?.rvexercisestatus?.adapter = exerciseadapter

    }
    private fun setuprestview(){

//        try{
//            val sounduri = Uri.parse("android.resource://com.example.a7minutesworkoutapp" + R.)
//            player = MediaPlayer.create(applicationContext,sounduri)
//            player?.isLooping = false
//            player?.start()
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
        binding?.flrestview?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility= View.VISIBLE
        binding?.tvExercise?.visibility = View.INVISIBLE
        binding?.flexerciseview?.visibility = View.INVISIBLE
        binding?.ivimage?.visibility = View.INVISIBLE
        binding?.tvupcominglabel?.visibility = View.VISIBLE
        binding?.tvupcomingexercisename?.visibility = View.VISIBLE
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
        }

        binding?.tvupcomingexercisename?.text = exerciselist!![currentExercisePos+1].getname()
        setRestprogressbar()

    }
    private fun setupexerciseview(){
        binding?.flrestview?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility= View.INVISIBLE
        binding?.tvExercise?.visibility = View.VISIBLE
        binding?.flexerciseview?.visibility = View.VISIBLE
        binding?.ivimage?.visibility = View.VISIBLE
        binding?.tvupcominglabel?.visibility = View.INVISIBLE
        binding?.tvupcomingexercisename?.visibility = View.INVISIBLE
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress= 0
        }
        speakout(exerciselist!![currentExercisePos].getname())
        binding?.ivimage?.setImageResource(exerciselist!![currentExercisePos].getimage())
        binding?.tvExercise?.text = exerciselist!![currentExercisePos].getname()

        setexerciseprogressbar()

    }
    private fun setRestprogressbar(){
        binding?.progressbar?.progress = restProgress
        restTimer = object : CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressbar?.progress = 10 - restProgress
                binding?.tvtimer?.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePos++
                exerciselist!![currentExercisePos].setisselected(true)
                exerciseadapter!!.notifyDataSetChanged()
                setupexerciseview()
            }

        }.start()
    }


    private fun setexerciseprogressbar(){
        binding?.progressbarexercise?.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressbarexercise?.progress = 30 - exerciseProgress
                binding?.tvtimerexercise?.text = (30-exerciseProgress).toString()
            }

            override fun onFinish() {
                exerciselist!![currentExercisePos].setisselected(false)
                exerciselist!![currentExercisePos].setiscompleted(true)
                exerciseadapter!!.notifyDataSetChanged()

                if(currentExercisePos< exerciselist?.size!!-1){

                    setuprestview()
                }else{
                    finish()
                    val intent  = Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }

        }.start()
    }
    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
        }

        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress= 0
        }
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player!=null){
          player!!.stop()
        }
        binding = null
    }

    override fun onInit(status: Int) {
        if(status ==TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The language is not supported")
            }
        }
        else{
            Log.e("TTS","Initialization Failed!")
        }
    }
    private fun speakout(text: String){
       tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
}