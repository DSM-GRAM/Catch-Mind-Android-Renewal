package com.gram.cmr.Util

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.gram.cmr.MainActivity
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject

class Event {
    val socket: Socket by lazy { SocketApplication.socket }

    init {
        start()
        receivePass()
        receiveLine()
    }

    fun sendLine(x: Float, y: Float, color: Int, eventName: String) =
            socket.emit("line", x as Double, y as Double, color, eventName)

    fun sendPass() = socket.emit("pass")

    fun start() {
        socket.emit("wait")
        socket.on("start", Start)
    }

    fun receivePass() = socket.on("pass", {
        // 그림 화면에 pass 알림 보냄
    })

    fun receiveLine() = socket.on("line", Line)

    val Start = Emitter.Listener { args ->

        var jsonObject = args.get(0) as JSONObject

        var word = jsonObject.getString("word")
        var player = jsonObject.getBoolean("player")

        // 대기 화면에 word와 player 전달
    }

    val Line = Emitter.Listener { args ->
        var jsonObject = args.get(0) as JSONObject

        var x: Float= jsonObject.getDouble("x") as Float
        var y: Float = jsonObject.getDouble("y") as Float
        var color: Int = jsonObject.getInt("color")
        var eventName: String = jsonObject.getString("eventName")

        // 그림 그리는 곳에 정보를 보냄
    }
}