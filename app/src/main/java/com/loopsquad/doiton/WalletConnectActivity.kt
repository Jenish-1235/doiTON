package com.loopsquad.doiton

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WalletConnectActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var sharedPreferences: SharedPreferences

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

        // Enable JavaScript and DOM storage in WebView
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true


        // Add JavaScript interface to the WebView
        webView.addJavascriptInterface(WebAppInterface(), "AndroidInterface")

        // Set WebViewClient to handle redirects and loading
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

        // Load the Ton Connect webpage
        webView.loadUrl("https://elegant-conkies-fe557f.netlify.app/") // Replace with your URL
    }

    // JavaScript Interface to receive wallet address
    private inner class WebAppInterface {
        @JavascriptInterface
        fun saveWalletAddress(walletAddress: String) {
            Log.d("WalletConnect1235", "Received wallet address: $walletAddress")

            // Save the wallet address in SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("wallet_address", walletAddress)
            editor.apply()

            Log.d("WalletConnect1235", "Wallet address saved in SharedPreferences.")

            val intent = Intent(this@WalletConnectActivity, MainActivity::class.java)
            startActivity(intent)

            // Finish WalletConnectActivity so it no longer remains in the back stack
            finish()
        }
    }
}
