package com.example.conterolap

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiNetworkSpecifier
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    
    //Declaring the edit text the recive tbe user command input in the andoroid 
    var editText: EditText? = null
    
    //this is annotation to indicate the target device is api28 and above 
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //edit text that recive the command to send to the Device 
        editText = findViewById(R.id.editTextTextPersonName)

          //running the ap as a server [ReciveCommand.ktx]
        ReciveCommand().execute()

        //The SSID and Password Required !!!
       //The SSID and Password Required !!!
        connectAP("XyyZ", "welcome1234")
    }


    //connect wifi for api 28 and above 
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun connectAP(ssid: String, password: String) {
        
        //wifi network bulider object that allowse you add new access point 
        val builder = WifiNetworkSpecifier.Builder()

        //access point ssid and password configuration 
        builder.setSsid(ssid)
        builder.setWpa2Passphrase(password)
        //wifi bulider object 
        val wifiNetworkSpecifier = builder.build()
        val networkRequestBuilder1 = NetworkRequest.Builder()
        networkRequestBuilder1.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        networkRequestBuilder1.setNetworkSpecifier(wifiNetworkSpecifier)
        val nr = networkRequestBuilder1.build()
        val cm = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        //check netwok availablity and infrom your with pop up to connect if AP available 
        val networkCallback: NetworkCallback = object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d("Net", "onAvailable:$network")
                cm.bindProcessToNetwork(network)
            }
        }

        cm.requestNetwork(nr, networkCallback)


    }

    //this is a function that handles the button click event 
    fun sendData(view: View) {
        //sending data to the server by recieving data from edit text
        SendCommand().execute(editText!!.text.toString())
        //clear edit text once the user click the send button
        editText!!.setText("")

    }
}
