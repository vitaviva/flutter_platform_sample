package com.example.flutter_platform_view_app

import android.content.Context
import android.view.View
import android.webkit.WebView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.platform.PlatformView

class FlutterWebView internal constructor(context: Context?, messenger: BinaryMessenger?, id: Int)
    : PlatformView, MethodCallHandler {

    private val webView: WebView
    private val methodChannel: MethodChannel

    init {
        webView = WebView(context)
        webView.apply {
            settings.apply {
                // enable Javascript
                javaScriptEnabled = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false // no zoom button
                loadWithOverviewMode = true
                useWideViewPort = true
                domStorageEnabled = true
            }
        }

        methodChannel = MethodChannel(messenger, "plugins.example.views/webview_$id")
        methodChannel.setMethodCallHandler(this)
    }

    @Override
    override fun getView(): View {
        return webView
    }

    @Override
    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        when (methodCall.method) {
            "setUrl" -> setUrl(methodCall, result)
            else -> result.notImplemented()
        }
    }

    private fun setUrl(methodCall: MethodCall, result: MethodChannel.Result) {
        val url = methodCall.arguments as String
        webView.loadUrl(url)
        result.success(null)
    }

    @Override
    override fun dispose() {
        webView.destroy()
    }
}
