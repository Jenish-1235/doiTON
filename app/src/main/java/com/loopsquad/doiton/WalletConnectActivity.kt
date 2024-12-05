package com.loopsquad.doiton

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WalletConnectActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var sharedPreferences: SharedPreferences
    private var intent2 = intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wallet_connect)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        webView = findViewById(R.id.tonConnectWebView)
        sharedPreferences = getSharedPreferences("TON_Wallet_Prefs", MODE_PRIVATE)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.addJavascriptInterface(WebAppInterface(this),"Android")

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.d("WebView", "Loading URL: $url")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d("WebView", "Page finished loading: $url")
            }
        }

        webView.webChromeClient = WebChromeClient()

        webView.loadUrl("https://elegant-conkies-fe557f.netlify.app/")

    }

    class WebAppInterface(private val mContext: Context) {
        @JavascriptInterface
        fun showToast(toast: String) {
            val sharedPreferences = mContext.getSharedPreferences("WalletAddress", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("WalletAddress", toast)
            editor.apply()
            val mainActivity = Intent(mContext, MainActivity::class.java)
            mContext.startActivity(mainActivity)
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }
}
