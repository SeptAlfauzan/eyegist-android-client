//package com.septalfauzan.eyegist.helper
//
//import android.content.Context
//import android.util.Log
//import info.mqtt.android.service.MqttAndroidClient
//import org.eclipse.paho.client.mqttv3.*
//
//class MQTTClient(
//    private val context: Context,
//    private val serverUri: String,
//    private val clientID: String
//) : MqttAndroidClient(context, serverUri, clientID) {
//
//    fun connectBroker() {
//        this.setCallback(object : MqttCallback {
//            override fun connectionLost(cause: Throwable?) {
//                Log.d(TAG, "Connection lost ${cause.toString()}")
//            }
//
//            override fun messageArrived(topic: String?, message: MqttMessage?) {
//                Log.d(TAG, "Receive message: ${message.toString()} from topic: $topic")
//            }
//
//            override fun deliveryComplete(token: IMqttDeliveryToken?) {}
//
//        })
//
//        val options = MqttConnectOptions()
//
//        try {
//            this.connect(options, null, object : IMqttActionListener {
//                override fun onSuccess(asyncActionToken: IMqttToken?) {
//                    Log.d(TAG, "Connection success")
//                }
//
//                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
//                    Log.d(TAG, "Connection failure")
//                }
//            })
//        } catch (e: Exception) {
//            Log.d(TAG, "connectBroker: ${e.message}")
////            e.printStackTrace()
//        }
//    }
//
//    fun subscribeTopic(topic: String, qos: Int = 1) {
//        try {
//            this.subscribe(topic, qos, null, object : IMqttActionListener {
//                override fun onSuccess(asyncActionToken: IMqttToken?) {
//                    Log.d(TAG, "Subscribed to $topic")
//                }
//
//                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
//                    Log.d(TAG, "Failed to subscribe $topic")
//                }
//            })
//        } catch (e: MqttException) {
//            e.printStackTrace()
//        }
//    }
//
//    fun unsubscribeTopic(topic: String) {
//        try {
//            this.unsubscribe(topic, null, object : IMqttActionListener {
//                override fun onSuccess(asyncActionToken: IMqttToken?) {
//                    Log.d(TAG, "Unsubscribed to $topic")
//                }
//
//                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
//                    Log.d(TAG, "Failed to unsubscribe $topic")
//                }
//            })
//        } catch (e: MqttException) {
//            e.printStackTrace()
//        }
//    }
//
//    fun publishMessage(topic: String, msg: String, qos: Int = 1, retained: Boolean = false) {
//        try {
//            val message = MqttMessage()
//            message.payload = msg.toByteArray()
//            message.qos = qos
//            message.isRetained = retained
//            this.publish(topic, message, null, object : IMqttActionListener {
//                override fun onSuccess(asyncActionToken: IMqttToken?) {
//                    Log.d(TAG, "$msg published to $topic")
//                }
//
//                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
//                    Log.d(TAG, "Failed to publish $msg to $topic")
//                }
//            })
//        } catch (e: MqttException) {
//            e.printStackTrace()
//        }
//    }
//
//    fun disconnectBroker() {
//        try {
//            this.disconnect(null, object : IMqttActionListener {
//                override fun onSuccess(asyncActionToken: IMqttToken?) {
//                    Log.d(TAG, "Disconnected")
//                }
//
//                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
//                    Log.d(TAG, "Failed to disconnect")
//                }
//            })
//        } catch (e: MqttException) {
//            e.printStackTrace()
//        }
//    }
//
//
//    override fun removeMessage(token: IMqttDeliveryToken?): Boolean {
//        // TODO: "Not yet implemented"
//        return true
//    }
//
//    override fun reconnect() {
//        // TODO:  "Not yet implemented"
//    }
//
//    companion object {
//        val TAG = this::class.java.simpleName
//
//        @Volatile
//        private var INSTANCE: MQTTClient? = null
//
//        fun getInstance(context: Context, serverUri: String, clientId: String): MQTTClient = INSTANCE ?: synchronized(this){
//            MQTTClient(context, serverUri, clientId).apply {
//                INSTANCE = this
//            }
//        }
//
//    }
//}