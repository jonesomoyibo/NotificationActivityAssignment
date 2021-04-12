package com.decagon.jones.notificationactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class NotifiedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notified)
        init()

    }

    fun init(){

        val statusTextView: TextView = findViewById<TextView>(R.id.status_text_view).apply {
            setText(getIntent().getStringExtra("status"))
            if(getIntent().getStringExtra("status")=="Inactive")
            setTextColor(resources.getColor(R.color.red))
            else if(getIntent().getStringExtra("status")=="Active"){
                setTextColor(resources.getColor(R.color.teal_700))
            }

        }

        val backButton : Button = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener(
                {
                    val intent: Intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
        )

    }
}