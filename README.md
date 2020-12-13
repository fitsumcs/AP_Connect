# AP_Connect
Kotlin version of Wifi AP

##To Replace Log.e with Toast 

###Here are the Instructions 

1. //In SendCommand class you will change it to accep contex 

    class SendCommand(val mcontext: Context): AsyncTask<String?, Void?, Void?>()
 
    
2. //when you create SendCommand in Main Acitivt pass the conetex 

    SendCommand(this).execute(editText!!.text.toString())
  
  
 4. //now you already have context in SendCommand Class so you can call Toast 
  
   Toast.makeText(mcontext, "Hello Javatpoint",Toast.LENGTH_SHORT).show()


