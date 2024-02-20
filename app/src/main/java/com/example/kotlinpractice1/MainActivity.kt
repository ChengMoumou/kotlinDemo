package com.example.kotlinpractice1

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.concurrent.thread

class MainActivity : BaseActivity() {
    private val contactsList = ArrayList<String>()
    lateinit var  adapter : ArrayAdapter<String>
    lateinit var manager : NotificationManager
    lateinit var downloadBinder: MyService.DownloadBinder

    val connection = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            TODO("Not yet implemented")
            downloadBinder = service as MyService.DownloadBinder
            Log.d(TAG, "onServiceConnected: 服务链接成功")
            downloadBinder.onProgress()
            downloadBinder.startDownloan()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
            Log.d(TAG, "onServiceDisconnected: 服务链接失败")
        }

    }
    val handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                1 -> Log.d(TAG, "handleMessage: 接收到了消息了"+msg.obj)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 获取 NotificationManager
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        btntest.setOnClickListener {
           /* val intent = Intent("com.homurax.broadcastbestpractice.FORCE_OFFLINE")
            sendBroadcast(intent)
            Log.d(TAG, "onCreate: 发送广播")*/
//            onWriteFile()
//            readFile()
//            information()
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection,Context.BIND_AUTO_CREATE)
            val currentNightMode = this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (currentNightMode) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    Log.d(TAG, "onCreate: 浅色模式")
                } // Night mode is not active, we're using the light theme.
                Configuration.UI_MODE_NIGHT_YES -> {
                    Log.d(TAG, "onCreate: 深色模式")
                } // Night mode is active, we're using dark theme.
            }
        }
        cancel.setOnClickListener {
//            manager.cancel(2)
/*            thread {
                val msg = Message()
                msg.what = 1
                msg.obj = "传递的消息"
                handler.handleMessage(msg)
            }*/
//            unbindService(connection)
            startActivity(Intent(this, MainActivity2::class.java))
        }
//        init()

    }

    fun information(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 使用 NotificationChannel 构建通知渠道
            val channel = NotificationChannel("abc", "okkk", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, Intent.FILL_IN_ACTION or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, "abc")
            .setContentTitle("This is content title") // 标题内容
            .setContentText("This is content text") // 正文内容
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("大头大头下雨不愁，别人有伞我有大头，Big head big head rain don't worry, others have an umbrella I have big head")
            )
            .setSmallIcon(R.drawable.small_icon) // 小图标 显示在系统状态栏
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.large_icon
                )
            ) // 大图标 下拉系统状态栏时可以看到
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()

        manager.notify(2, notification)
    }
    fun init(){
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsList)
        list_view.adapter = adapter

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1)
        }else{
            readContact()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContact()
            } else {
                Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 写入文件
    fun onWriteFile(){
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val stream = BufferedWriter(OutputStreamWriter(output))
            stream.use {
                it.write("aaaaaaaaaaaaaaaaaa")
            }
        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    fun readContact(){
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null)?.apply {
            while (moveToNext()) {
                val displayName = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsList.add("$displayName\n$number")
            }
            adapter.notifyDataSetChanged()
            close()
        }
    }

    fun readFile(){
        try {
            val output = openFileInput("data")
            val stream = BufferedReader(InputStreamReader(output))
            stream.use {
                it.forEachLine {
                    Log.d(TAG, "readFile: ======"+it.toString())
                }
            }
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }
}
