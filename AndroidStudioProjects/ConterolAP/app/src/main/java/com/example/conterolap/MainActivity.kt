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

    var editText: EditText? = null
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //edit text
        editText = findViewById(R.id.editTextTextPersonName)

          //running the server
        ReciveCommand().execute()

        //The SSID and Password Required !!!
       //The SSID and Password Required !!!
        connectAP("XyyZ", "welcome1234")
    }


    //connect wifi
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun connectAP(ssid: String, password: String) {

        val builder = WifiNetworkSpecifier.Builder()

        builder.setSsid(ssid)
        builder.setWpa2Passphrase(password)
        val wifiNetworkSpecifier = builder.build()
        val networkRequestBuilder1 = NetworkRequest.Builder()
        networkRequestBuilder1.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        networkRequestBuilder1.setNetworkSpecifier(wifiNetworkSpecifier)
        val nr = networkRequestBuilder1.build()
        val cm = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCallback: NetworkCallback = object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d("Net", "onAvailable:$network")
                cm.bindProcessToNetwork(network)
            }
        }

        cm.requestNetwork(nr, networkCallback)


    }

    fun sendData(view: View) {
        SendCommand().execute(editText!!.text.toString())
        editText!!.setText("")

    }
}