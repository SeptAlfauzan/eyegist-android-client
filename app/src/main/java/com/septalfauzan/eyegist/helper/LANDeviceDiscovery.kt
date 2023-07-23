package com.septalfauzan.eyegist.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.AsyncTask
import android.text.format.Formatter
import android.util.Log
import kotlinx.coroutines.withContext
import java.net.InetAddress

class LANDeviceDiscovery {
    fun getIpAddress(context: Context): String? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        if (capabilities != null &&
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        ) {
            val wm = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val connectionInfo = wm.connectionInfo
            val ipAddress = connectionInfo.ipAddress
            Log.d("TAG", "getIpAddress: $ipAddress")
            return Formatter.formatIpAddress(ipAddress)
        }
        return null
    }
    fun getReachableIpAddress(myIp: String){
        object : AsyncTask<Void, Void, Unit>(){
            override fun doInBackground(vararg params: Void?) {
                val prefix = myIp.substring(0, myIp.lastIndexOf(".") + 1)
                for (i in 0..254) {
                    val testIp: String = prefix + i.toString()
                    val address: InetAddress = InetAddress.getByName(testIp)
                    val reachable: Boolean = address.isReachable(3000)
                    val hostName: String = address.canonicalHostName
                    if (reachable) Log.i("Get IP", "Host: $hostName($testIp) is reachable!")
                }
            }
        }.execute()
    }
}