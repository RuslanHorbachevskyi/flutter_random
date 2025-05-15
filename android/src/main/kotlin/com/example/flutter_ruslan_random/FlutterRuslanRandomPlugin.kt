package com.example.flutter_ruslan_random

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import androidx.annotation.NonNull
import kotlin.random.Random

/** FlutterRuslanRandomPlugin */
class FlutterRuslanRandomPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_ruslan_random")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: MethodChannel.Result) {
    if (call.method == "getRandomNumber") {
      val min = call.argument<Int>("min") ?: run {
        result.error("INVALID_INPUT", "Missing 'min'", null)
        return
      }
      val max = call.argument<Int>("max") ?: run {
        result.error("INVALID_INPUT", "Missing 'max'", null)
        return
      }
      if (min >= max) {
        result.error("INVALID_INPUT", "min must be < max", null)
        return
      }
      val rnd = Random.nextInt(min, max + 1)
      result.success(rnd)
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
