package com.example.conterolap

import android.os.AsyncTask
import android.util.Log
import java.io.PrintWriter
import java.net.Socket

class SendCommand : AsyncTask<String?, Void?, Void?>() {


    val IP_ADDRESS = "192.168.10.120"
    val PORT = 8080


    override fun doInBackground(vararg p0: String?): Void? {
        try {
            val socket = Socket(IP_ADDRESS, PORT)
            Log.e("Socket Value ", socket.toString())
            val outWrite = PrintWriter(socket.getOutputStream())
            outWrite.write(p0[0]!!)
            outWrite.flush()
            outWrite.close()
            Log.e("Socket Written ", "end here")
        } catch (e: Exception) {
            Log.e("Exception", e.message)
        }

        return null

    }


}


