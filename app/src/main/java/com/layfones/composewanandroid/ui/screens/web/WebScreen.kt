package com.layfones.composewanandroid.ui.screens.web

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.*
import com.layfones.composewanandroid.navigation.LocalNavController
import com.layfones.composewanandroid.ui.components.BackButton
import com.layfones.composewanandroid.util.WebViewManager
import com.layfones.composewanandroid.util.injectVConsoleJs

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebScreen(
    originalUrl: String,
    title: String = ""
) {
    val context = LocalContext.current
    var webView by remember { mutableStateOf<WebView?>(null) }
    val state = rememberWebViewState(originalUrl)
    val navigator = rememberWebViewNavigator()
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        var injectState by remember { mutableStateOf(false) }
        //注入VConsole以便于H5调试
        val injectVConsole by remember { mutableStateOf(false) }
        var progress by remember { mutableStateOf(0f) }
        val client = object : AccompanistWebViewClient() {
            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                return super.shouldInterceptRequest(view, request)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (view != null && request != null && request.url != null) {
                    if ("http" != request.url.scheme && "https" != request.url.scheme) {
                        try {
                            view.context.startActivity(Intent(Intent.ACTION_VIEW, request.url))
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        return true
                    }
                }
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                injectState = false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                injectState = false
            }
        }

        val chromeClient = object : AccompanistWebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progress = (newProgress / 100f).coerceIn(0f, 1f)
                if (newProgress > 80 && injectVConsole && !injectState) {
                    view?.apply { evaluateJavascript(context.injectVConsoleJs()) {} }
                    injectState = true
                }
            }
        }
        val navHostController = LocalNavController.current
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            BackButton {
                navHostController.popBackStack()
            }
            Text(
                text = title,
                Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }
        WebView(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            captureBackPresses = false,
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            onDispose = { WebViewManager.recycle(it) },
            client = client,
            chromeClient = chromeClient,
            factory = { context -> WebViewManager.obtain(context).also { webView = it } }
        )
        AnimatedVisibility(visible = (progress > 0f && progress < 1f)) {
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}