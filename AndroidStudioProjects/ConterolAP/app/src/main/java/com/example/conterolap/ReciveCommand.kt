package com.example.conterolap

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket

class ReciveCommand : AsyncTask<Void?, Void?, Void?>() {
    //this class will be used when the app is used as server so it only needs port 
    val PORT = 8080
    //creating socket recieving socket from client 
    var socket: Socket? = null
    //server socket object declaration 
    private var stringData: String? = null
    //this function shows this code is running as background thread so it will not mamke the app to stack 
    override fun doInBackground(vararg p0: Void?): Void?{
        try {
            //creating the server socket 
            val serverSocket = ServerSocket(PORT)
            //while users are connected 
            while (true) {
                //recieve client socket 
                socket = serverSocket.accept()
                //read text on the socket 
                val input = BufferedReader(InputStreamReader(socket!!.getInputStream()))
                stringData = input.readLine()
                //if data read ..
                if (stringData != null) {
                    //display the data send from client to Logcat 
                    Log.e("Message", stringData)
                    //exit the loop
                    break
                }
            }
            //close the client and server connection 
            socket!!.close()
            serverSocket.close()
        } catch (e: Exception) {
            //on error ..dispplay the error 
            Log.e("Exception", e.message)
        }
        return null
    }


}
