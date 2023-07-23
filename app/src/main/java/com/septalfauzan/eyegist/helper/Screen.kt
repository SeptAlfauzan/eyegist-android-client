package com.septalfauzan.eyegist.helper

sealed class Screen(val route: String) {
    object Home : Screen(route = "home")
    object SavedDevice : Screen(route = "saved_device")
    object Detail : Screen(route = "detail/{ip}"){
        fun createRoute(ip: String) = "detail/$ip"
    }
    object DetailPreview : Screen(route = "detail_preview/{ip}") {
        //when user in same local area network
        fun createRoute(ip: String) = "detail_preview/$ip"
    }
    object RemoteDetailPreview : Screen(route = "remote_detail_preview/{ip}") {
        //when user in same local area network
        fun createRoute(ip: String) = "remote_detail_preview/$ip"
    }
}