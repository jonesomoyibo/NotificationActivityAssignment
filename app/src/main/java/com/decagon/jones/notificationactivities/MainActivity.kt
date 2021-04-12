package com.decagon.jones.notificationactivities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "KEY"
    lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationChannel()
        init()

    }

    fun init(){

        val notifyButton: Button = findViewById<Button>(R.id.notify_button)
        notifyButton.setOnClickListener({


            val intent:Intent = Intent(this,NotifiedActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra("status","Active")
            }

            val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0,intent,0)

            val notificationBuilder = NotificationCompat.Builder(this,CHANNEL_ID).apply {
                setSmallIcon(R.drawable.ic_baseline_notifications_24)
                setContentTitle("Activation Alert")
                setContentText("Click me To Go To Activity: \"NotifiedActivity\"")
                setPriority(NotificationCompat.PRIORITY_DEFAULT)
                setContentIntent(pendingIntent)
                setAutoCancel(true)

            }
            with(NotificationManagerCompat.from(this)){
                notify(0,notificationBuilder.build())
            }

        })

        val moveButton: Button = findViewById<Button>(R.id.move_button)

        moveButton.setOnClickListener({
            val intent: Intent = Intent(this, NotifiedActivity::class.java)
            intent.putExtra("status", "Inactive")
            startActivity(intent)
        })






    }

    fun notificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name ="channel name"
            val descriptionText ="channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply { description = descriptionText }


        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
       notificationManager.createNotificationChannel(channel)

    }
    }


}