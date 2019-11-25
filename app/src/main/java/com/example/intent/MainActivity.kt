package com.example.intent

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSend.setOnClickListener {
            sendMessage()
        }

    }

    private fun sendMessage() {
        //input validation
        if(editTextMessage.text.isEmpty()){
            editTextMessage.setError("Value Required")
            return
        }
        if (editTextLuckyNo.text.isEmpty()){
            editTextLuckyNo.setError(("Value Required"))
            return
        }
        //Explicit Intent:Component name must be provided
        val intent= Intent(this, SecondActivity::class.java)

        //Insert extra to the intent
        val message = editTextMessage.text.toString()
        intent .putExtra(EXTRA_MSG,message)

        val number =editTextLuckyNo.text.toString().toInt()
        intent.putExtra(EXTRA_LUCKY,number)

        //startActivity(intent)

        //Return value from the SecondActivity
        startActivityForResult(intent,REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(EXTRA_REPLY)
                //Display reply here

            }
        }
    }

    companion object{
        const val EXTRA_MSG="com.example.intent.EXTRA_MSG"
        const val EXTRA_LUCKY="com.example.intent.EXTRA_LUCKY"
        const val EXTRA_REPLY="com.example.intent.EXTRA_REPLY"
        const val REQUEST_CODE = 1
    }
}
