package com.example.flutter_platform_view_app

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin

class WebViewPlugin : FlutterPlugin {

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        flutterPluginBinding.platformViewRegistry
                .registerViewFactory(
                        "plugins.example.views/webview",
                        WebViewFactory(flutterPluginBinding.binaryMessenger)
                )
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    }
}
