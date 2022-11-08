package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts:TextToSpeech?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        tts = TextToSpeech(this, this)

        btnSpeak.setOnClickListener {
            if(editText.text.isEmpty()){
                Toast.makeText(this,"Please Enter something",Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                speakOut(editText.text.toString())
            }
        }
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.US)
            if(result==TextToSpeech.LANG_MISSING_DATA ||
                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("Ayaaz","The language specified is not supported")
            }
        }
        else
            Log.e("Ayaaz","Initialization failed")
    }

    private fun speakOut(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        super.onDestroy()
        if(tts!=null){
            tts?.stop()
            tts?.shutdown()
        }
    }
}