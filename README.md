# AP_Connect
Kotlin version of Wifi AP

##To Replace Log.e with Toast 

###Here are the Instructions 

1. //In SendCommand class you will creat avairbale 

    var mContext: Context? = null
  
2. //creating constructer 

   constructor(mContext: Context?) {
        this.mContext = mContext
    }
    
3. //when you create SendCommand in Main Acitivt pass the conetex 

    SendCommand(this).execute(editText!!.text.toString())
  
  
 4. //now you already have context in SendCommand Class so you can call Toast 
  
   Toast.makeText(mContext, "Hello Javatpoint",Toast.LENGTH_SHORT).show()


