package com.example.conterolap

import android.os.AsyncTask
import android.util.Log
import java.io.PrintWriter
import java.net.Socket

//this class runs in background 
class SendCommand : AsyncTask<String?, Void?, Void?>() {


    //server IP and port to connect from the app 
    val IP_ADDRESS = "192.168.10.120"
    val PORT = 8080

    //this means the sending process is done on background thread so that the app does not stack 
    override fun doInBackground(vararg p0: String?): Void? {
        try {
            //creaitng the socket object with the servers ip and port
            val socket = Socket(IP_ADDRESS, PORT)
            //tesing the socket is connected to server ...you can remove this log if you dont like it :) 
            Log.e("Socket Value ", socket.toString())
            //creating a printstream inorder to write to the socket 
            val outWrite = PrintWriter(socket.getOutputStream())
            //writing to the socket where the string comes from the edit text and assigned in p0 array , and we access the first element which is the text
            outWrite.write(p0[0]!!)
            //clear 
            outWrite.flush()
            //close the socket after sending 
            outWrite.close()
            Log.e("Socket Written ", "end here")
        } catch (e: Exception) {
            //if there is any error log it 
            Log.e("Exception", e.message)
        }

        return null

    }


}


