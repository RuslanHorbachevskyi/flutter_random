import 'package:flutter/services.dart';

class FlutterRuslanRandom {
  static const MethodChannel _channel = MethodChannel('flutter_ruslan_random');

  static Future<int> getRandomNumber(int min, int max) async {
      final result = await _channel.invokeMethod('getRandomNumber', {
      'min': min,
      'max': max,
    });
    return result as int;
  }

}
