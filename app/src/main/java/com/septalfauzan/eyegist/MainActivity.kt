package com.septalfauzan.eyegist

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.AsyncTask
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.septalfauzan.eyegist.data.VideoStream
import com.septalfauzan.eyegist.helper.LANDeviceDiscovery
import com.septalfauzan.eyegist.helper.MqttHandler
import com.septalfauzan.eyegist.helper.NetworkServiceDiscovery
import com.septalfauzan.eyegist.helper.Screen
import com.septalfauzan.eyegist.ui.theme.EyeGistTheme
import com.septalfauzan.eyegist.ui.views.dashboard.DashboardScreen
import com.septalfauzan.eyegist.ui.views.detail.DetailScreen
import com.septalfauzan.eyegist.ui.views.preview.PreviewScreen
import com.septalfauzan.eyegist.ui.views.savedDevices.SavedDevicesScreen
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import info.mqtt.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.net.InetAddress

class MainActivity : ComponentActivity() {

    private val mSocket: Socket = IO.socket("http://192.168.100.66:3000")
    private val SOCKET_EVENT_NAME = "onVideoStream"
    private lateinit var nsdHelper: NetworkServiceDiscovery
    private val serverURI = "tcp://free.mqtt.iyoti.id:1883"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mqttHandler = MqttHandler()
        mqttHandler.connect(serverURI, "my_client")
        mqttHandler.subscribe("eyegist/1")
        mqttHandler.listen()

        try {
            val lanDeviceDiscovery = LANDeviceDiscovery()
            val myIp = lanDeviceDiscovery.getIpAddress(this)
            myIp?.let {
                lanDeviceDiscovery.getReachableIpAddress(it)
            }
        } catch (e: Exception) {
            Log.d("TAG", "onCreate: $e")
        }

        setContent {
            EyeGistTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            elevation = 0.dp,
                            backgroundColor = Color.Transparent,
                            title = {},
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "menu drawer",
                                    modifier = Modifier.clickable {
                                        scope.launch {
                                            scaffoldState.drawerState.open()
                                        }
                                    })
                            },
                        )
                    },
                    drawerContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(128.dp)
                                .background(MaterialTheme.colors.primary)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(Modifier.padding(horizontal = 16.dp)) {
                            Text(text = "Dashboard", modifier = Modifier.clickable {
                                navController.navigate(Screen.Home.route)
                                scope.launch { scaffoldState.drawerState.close() }
                            })
                            Text(text = "Perangkat Disimpan", modifier = Modifier.clickable {
                                navController.navigate(Screen.SavedDevice.route)
                                scope.launch { scaffoldState.drawerState.close() }
                            })
                        }
                    },
                    drawerScrimColor = Color.Black.copy(
                        alpha = 0.4f
                    ),
                    drawerGesturesEnabled = true
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colors.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Home.route
                        ) {
                            composable(route = Screen.Home.route) {
                                DashboardScreen(onClick = { id ->
                                    navController.navigate(
                                        Screen.Detail.createRoute(
                                            id
                                        )
                                    )
                                })
                            }
                            composable(route = Screen.Detail.route, arguments = listOf(
                                navArgument("ip") { type = NavType.StringType }
                            )) {
                                val ip = it.arguments?.getString("ip") ?: ""
                                DetailScreen(
                                    ip = ip,
                                    onClickAction = { ip ->
                                        navController.navigate(
                                            Screen.DetailPreview.createRoute(ip)
                                        )
                                    })
                            }
                            composable(route = Screen.DetailPreview.route) {}
                            composable(route = Screen.SavedDevice.route) {
                                SavedDevicesScreen()
                            }
                            composable(route = Screen.DetailPreview.route, arguments = listOf(
                                navArgument("ip") { type = NavType.StringType }
                            )) {
                                val ip = it.arguments?.getString("ip") ?: ""
                                PreviewScreen(ip = ip)
                            }
                            composable(route = Screen.RemoteDetailPreview.route) {}
                        }
                    }
                }
            }
        }

//        val serverURI = "tcp://broker.hivemq.com:1883"
//        val mqttClient = MqttAndroidClient(this, serverURI, "kotlin_client")
//            mqttClient.setCallback(object : MqttCallback {
//                override fun messageArrived(topic: String?, message: MqttMessage?) {
//                    Log.d("this", "Receive message: ${message.toString()} from topic: $topic")
//                }
//
//                override fun connectionLost(cause: Throwable?) {
//                    Log.d("TAG", "Connection lost ${cause.toString()}")
//                }
//
//                override fun deliveryComplete(token: IMqttDeliveryToken?) {
//
//                }
//            })
//            val options = MqttConnectOptions()
//            try {
//                mqttClient.connect(options, null, object : IMqttActionListener {
//                    override fun onSuccess(asyncActionToken: IMqttToken?) {
//                        Log.d("TAG", "Connection success")
//                    }
//
//                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
//                        Log.d("TAG", "Connection failure ${exception?.printStackTrace()}")
//                    }
//                })
//            } catch (e: MqttException) {
//                e.printStackTrace()
//            }
    }


    private val onNewMessage = Emitter.Listener { args ->
        val gson = Gson()
        val jsonStringMessage = args[0]
        val onMessageObj: VideoStream =
            gson.fromJson(jsonStringMessage.toString(), VideoStream::class.java)
//        val messageObj: OnMessage = gson.fromJson(onMessageObj.body, OnMessage::class.java)
        Log.d("OnNewMessage", ": ${onMessageObj.data}")
    }

    override fun onDestroy() {
        super.onDestroy()
        nsdHelper.stopDiscovery()
    }
}
