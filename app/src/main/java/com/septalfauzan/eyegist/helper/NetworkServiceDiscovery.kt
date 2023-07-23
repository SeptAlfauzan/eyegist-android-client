package com.septalfauzan.eyegist.helper

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log

class NetworkServiceDiscovery(private val context: Context) {
    private val nsdManager: NsdManager by lazy { context.getSystemService(Context.NSD_SERVICE) as NsdManager}

    fun discoveryServices(){
        Log.d("TAG", "discoveryServices: ")
        nsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener)
    }

    fun stopDiscovery(){
        nsdManager.stopServiceDiscovery(discoveryListener)
    }

    private val discoveryListener = object : NsdManager.DiscoveryListener {
        override fun onDiscoveryStarted(serviceType: String) {}

        override fun onDiscoveryStopped(serviceType: String) {
            Log.d(this::class.java.simpleName, "discoveryServices error: $serviceType")
        }

        override fun onServiceFound(serviceInfo: NsdServiceInfo) {
            // A service was found, you can get its information here.
            // For example, serviceInfo.serviceName and serviceInfo.host
            Log.d(this::class.java.simpleName, "discoveryServices: $serviceInfo")
        }

        override fun onServiceLost(serviceInfo: NsdServiceInfo) {}

        override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.d(this::class.java.simpleName, "discoveryServices error: $serviceType")
        }

        override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {}
    }

    companion object {
        private const val SERVICE_TYPE = "_http._tcp."
    }
}