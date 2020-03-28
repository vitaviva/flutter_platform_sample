package com.example.flutter_platform_view_app

import android.content.Context
import android.view.View
import android.widget.TextView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.platform.PlatformView

class FlutterTextView internal constructor(context: Context?, messenger: BinaryMessenger?, id: Int)
    : PlatformView, MethodCallHandler {

    private val textView: TextView
    private val methodChannel: MethodChannel

    init {
        textView = TextView(context)
        methodChannel = MethodChannel(messenger, "plugins.example.views/textview_$id")
        methodChannel.setMethodCallHandler(this)
    }

    @Override
    override fun getView(): View {
        return textView
    }

    @Override
    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        when (methodCall.method) {
            "setText" -> setText(methodCall, result)
            else -> result.notImplemented()
        }
    }

    private fun setText(methodCall: MethodCall, result: MethodChannel.Result) {
        val text = methodCall.arguments as String
        textView.setText(text)
        result.success(null)
    }

    @Override
    override fun dispose() {
    }
}
