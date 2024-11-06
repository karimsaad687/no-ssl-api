package com.nosslapi

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import okhttp3.OkHttpClient
import org.json.JSONObject
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class NoSslApiModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun multiply(a: Double, b: Double, promise: Promise) {
    promise.resolve(a * b)
  }

  @ReactMethod
  fun noSslApi(url: String, promise: Promise) {

    AndroidNetworking.initialize(reactContext, getUnsafeOkHttpClient())
    AndroidNetworking.get(url).build().getAsJSONObject(object: JSONObjectRequestListener{
      override fun onResponse(response: JSONObject?) {
        promise(response.toString())
      }

      override fun onError(anError: ANError?) {
        promise.resolve(anError?.errorDetail.toString())
      }

    })

  }

  companion object {
    const val NAME = "NoSslApi"
  }

  private fun getUnsafeOkHttpClient(): OkHttpClient {
    try {
      // Create a trust manager that does not validate certificate chains
      val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        @Throws(CertificateException::class)
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
          return arrayOf()
        }
      }
      )

      // Install the all-trusting trust manager
      val sslContext = SSLContext.getInstance("SSL")
      sslContext.init(null, trustAllCerts, SecureRandom())
      // Create an ssl socket factory with our all-trusting manager
      val sslSocketFactory = sslContext.socketFactory

      val builder: OkHttpClient.Builder = OkHttpClient.Builder()
      builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
      builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })

      val okHttpClient: OkHttpClient = builder.build()
      return okHttpClient
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}
