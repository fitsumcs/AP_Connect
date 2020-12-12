package com.example.conterolap

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket

class ReciveCommand : AsyncTask<Void?, Void?, Void?>() {
    val PORT = 8080
    var socket: Socket? = null
    private var stringData: String? = null
    override fun doInBackground(vararg p0: Void?): Void?{
        try {
            val serverSocket = ServerSocket(PORT)
            while (true) {
                socket = serverSocket.accept()
                val input = BufferedReader(InputStreamReader(socket!!.getInputStream()))
                stringData = input.readLine()
                if (stringData != null) {
                    Log.e("Message", stringData)
                    break
                }
            }
            socket!!.close()
            serverSocket.close()
        } catch (e: Exception) {
            Log.e("Exception", e.message)
        }
        return null
    }


}