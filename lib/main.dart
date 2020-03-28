import 'package:flutter/material.dart';
import 'package:flutter_platform_view_app/text_view.dart';
import 'package:flutter_platform_view_app/web_view.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter PlatformView API',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter PlatformView Example'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text(widget.title)),
        body: Column(children: [
          Center(
              child: Container(
                  padding: EdgeInsets.symmetric(vertical: 10.0),
                  width: 200.0,
                  height: 40.0,
                  child: TextView(
                    onTextViewCreated: _onTextViewCreated,
                  )
              )
          ),
          Expanded(
              flex: 3,
              child: WebView(
                    onWebViewViewCreated: _onWebViewCreated,
              )
          )
        ])
    );
  }

  void _onTextViewCreated(TextViewController controller) {
    controller.setText('Android TextView and WebView example.');
  }

  void _onWebViewCreated(WebViewController controller) {
    controller.setUrl('https://www.baidu.com/');
  }
}
