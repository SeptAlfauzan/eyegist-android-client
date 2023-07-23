package com.septalfauzan.eyegist.helper

import android.util.Log
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class MqttHandler {
    private var client: MqttClient? = null
    fun connect(brokerUrl: String?, clientId: String?) {
        try {
            // Set up the persistence layer
            val persistence = MemoryPersistence()

            // Initialize the MQTT client
            client = MqttClient(brokerUrl, clientId, persistence)

            // Set up the connection options
            val connectOptions = MqttConnectOptions()
            connectOptions.isCleanSession = true

            // Connect to the broker
            client!!.connect(connectOptions)
        } catch (e: MqttException) {
            Log.d(this::class.java.simpleName, "connect mqtt error: ${e.message}")
            e.printStackTrace()
        }
    }

    fun disconnect() {
        try {
            client!!.disconnect()
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun publish(topic: String?, message: String) {
        try {
            val mqttMessage = MqttMessage(message.toByteArray())
            client!!.publish(topic, mqttMessage)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun subscribe(topic: String?) {
        try {
            client!!.subscribe(topic)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun listen(){
        try {
            client?.setCallback(object: MqttCallback{
                override fun connectionLost(cause: Throwable?) {
                    Log.d(this::class.java.simpleName, "connectionLost: $cause")
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    Log.d(this::class.java.simpleName, "messageArrived from $topic message: $message")
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {
                    Log.d(this::class.java.simpleName, "deliveryComplete: $token")
                }
            })
        }catch (e: MqttException){
            e.printStackTrace()
        }
    }
}