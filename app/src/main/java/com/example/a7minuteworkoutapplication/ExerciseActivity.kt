package com.example.a7minuteworkoutapplication

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkoutapplication.databinding.ActivityCustomConfirmationBackDialogboxBinding
import com.example.a7minuteworkoutapplication.databinding.ActivityEndPageBinding.inflate
import com.example.a7minuteworkoutapplication.databinding.ActivityExerciseBinding
import com.example.a7minuteworkoutapplication.databinding.ItemExerrciseStatusBinding.inflate
import java.net.URI
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding:ActivityExerciseBinding?=null

    private var resttimer:CountDownTimer?=null
    private var restprogress =0
    private var exercisetimer:CountDownTimer?=null
    private var exerciseprogress =0
    private var exerciselist:ArrayList<exercisemodel>?=null
    private var currentExercisePosition=-1
    private var tts :TextToSpeech?=null
    private var player:MediaPlayer?=null
    private var exerciseadapter:exercisestatusAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarexercise)
        if(supportActionBar !=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciselist=constants.defaultExerciseList()
        tts= TextToSpeech(this,this)

        binding?.toolbarexercise?.setNavigationOnClickListener {
           onBackPressed()



        }
        setupRestview()
        setupExerciseStatusRecyclerView()

    }


    private fun setupExerciseStatusRecyclerView(){
        binding?.rvexxerciseStatus?.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseadapter=exercisestatusAdapter(exerciselist!!)
        binding?.rvexxerciseStatus?.adapter=exerciseadapter


    }




    private fun setupRestview(){


        try{
            val sounduri=Uri.parse("android.resource://com.example.a7minuteworkoutapplication/" + R.raw.press_start)
            player=MediaPlayer.create(applicationContext,sounduri)
            player?.isLooping=false
            player?.start()

        }catch (e:Exception){
            e.printStackTrace()
        }
        binding?.flrestview?.visibility= View.VISIBLE
        binding?.tvtitle?.visibility=View.VISIBLE
        binding?.exerciseview?.visibility=View.INVISIBLE
        binding?.tvexercisename?.visibility=View.INVISIBLE
        binding?.ivimage?.visibility=View.INVISIBLE
        binding?.tvUpcomingExerciseLabel?.visibility=View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility=View.VISIBLE
        binding?.rvexxerciseStatus?.visibility=View.INVISIBLE


        if(resttimer !=null){
            resttimer?.cancel()
            restprogress=0
        }
        binding?.tvUpcomingExerciseName?.text=exerciselist!![currentExercisePosition+1].getname()
        setRestProgressBar()

    }
    private fun setupexerciseview(){
        binding?.flrestview?.visibility= View.INVISIBLE
        binding?.tvtitle?.visibility=View.INVISIBLE
        binding?.exerciseview?.visibility=View.VISIBLE
        binding?.tvexercisename?.visibility=View.VISIBLE
        binding?.ivimage?.visibility=View.VISIBLE
        binding?.tvUpcomingExerciseLabel?.visibility=View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility=View.INVISIBLE
        binding?.rvexxerciseStatus?.visibility=View.VISIBLE


        if(exercisetimer !=null){
            exercisetimer?.cancel()
            exerciseprogress=0
        }

        speakout(exerciselist!![currentExercisePosition].getname())

        binding?.ivimage?.setImageResource(exerciselist!![currentExercisePosition].getimage())
        binding?.tvexercisename?.text=exerciselist!![currentExercisePosition].getname()
        setexerciseProgressBar()
    }


    private fun setRestProgressBar(){
        binding?.progressbar?.progress=restprogress

        resttimer=object :CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                restprogress++
                binding?.progressbar?.progress=10-restprogress
                binding?.tvtimer?.text=(10-restprogress).toString()

            }


            override fun onFinish() {
                //Toast.makeText(this@ExerciseActivity,"start the exercise",Toast.LENGTH_SHORT).show()
                currentExercisePosition++

                exerciselist!![currentExercisePosition].setisSelected(true)
                exerciselist!![currentExercisePosition].setisCompleted(false)
                exerciseadapter!!.notifyItemChanged(currentExercisePosition)
                setupexerciseview()

            }
        }.start()
    }

    private fun setexerciseProgressBar(){
        binding?.progressbarexercise?.progress=exerciseprogress

        exercisetimer=object :CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                exerciseprogress++

                binding?.progressbarexercise?.progress=30-exerciseprogress
                binding?.tvtimerexercise?.text=(30-exerciseprogress).toString()

            }


            override fun onFinish() {


                if(currentExercisePosition <exerciselist?.size!! -1){
                    exerciselist!![currentExercisePosition].setisSelected(false)
                    exerciselist!![currentExercisePosition].setisCompleted(true)
                    exerciseadapter!!.notifyItemChanged(currentExercisePosition)
                    setupRestview()
                }else{
                    speakout("exercise completed")
                    finish()
                    val intent=Intent(this@ExerciseActivity,EndPage::class.java)
                    startActivity(intent)
                }


            }
        }.start()
    }



    override fun onDestroy() {
        super.onDestroy()
        if(resttimer !=null){
            resttimer?.cancel()
            restprogress=0
        }

        if(exercisetimer !=null){
            exercisetimer?.cancel()
            exerciseprogress=0
        }
        if(tts !=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player !=null){
            player!!.stop()

       }

        binding=null

    }
    override fun onInit(status: Int) {
        if(status ==TextToSpeech.SUCCESS){
            val result =tts?.setLanguage(Locale.UK)

            if(result== TextToSpeech.LANG_MISSING_DATA || result ==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","the language specified is not supported")
            }else{
                Log.e("TTS","intialization failed")
            }
        }
    }
    private fun speakout(text:String){

        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")

    }




}