package com.gram.cmr.Util

import android.graphics.Color
import com.gram.cmr.Model.DrawModel
import com.gram.cmr.Model.PlayerModel
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject

object Event {

    val socket: Socket = SocketApplication.socket
    val drawModel:  DrawModel by lazy { DrawModel }
    val playerModel: PlayerModel by lazy { PlayerModel }

    fun sendLine(x: Float, y: Float, color: Int, width: Float, eventName: String) =
            socket.emit("line", x.toDouble(), y.toDouble(), color, width.toDouble(),eventName)

    fun sendPass() = socket.emit("pass")

    fun start() {
        socket.emit("wait")
        socket.on("start", Start)
    }

    fun receivePass() = socket.on("pass", {
        // 그림 화면에 pass 알림 보냄
    })

    fun receiveLine(): Boolean {
        socket.on("line", Line)
        return true
    }

    val Start = Emitter.Listener { args ->

        var jsonObject = args.get(0) as JSONObject

        playerModel.word = jsonObject.getString("word")
        playerModel.player = jsonObject.getBoolean("player")
    }

    val Line = Emitter.Listener { args ->
        var jsonObject = args.get(0) as JSONObject

        drawModel.x = jsonObject.getDouble("x").toFloat()
        drawModel.y = jsonObject.getDouble("y").toFloat()
        drawModel.color = jsonObject.getInt("color")
        drawModel.width = jsonObject.getDouble("width").toFloat()
        drawModel.eventName = jsonObject.getString("eventName")
        return@Listener
    }
}