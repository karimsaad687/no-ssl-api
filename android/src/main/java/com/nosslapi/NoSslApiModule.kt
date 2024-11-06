package com.nosslapi

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import okhttp3.OkHttpClient
import org.json.JSONObject
import java.security.SecureRandom
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
    val context: ReactApplicationContext = getReactApplicationContext()
    AndroidNetworking.initialize(context, getUnsafeOkHttpClient())
    AndroidNetworking.get(url)
      .addHeaders("Accept","application/json")
      .addHeaders("Authorization","Bearer AAIgN2U0MzQzMTkzMzk2MDVhZjA1OTI3NDQ3Y2Q2NThhZGUdgOhdnH6Dd5GQ0H5dbVw0tPQw1CejK0IKk4o0ieQt4E6wlAOvVm7FDQTRMSczg9I73W4dmVMyRmhxQouOgMajmqe0bGxTN6IjzCJlanrO1EttQltaz1Xhchl5RUkodQE")
      .addHeaders("apiKey","eyJ4NXQiOiJPREUzWTJaaE1UQmpNRE00WlRCbU1qQXlZemxpWVRJMllqUmhZVFpsT0dJeVptVXhOV0UzWVE9PSIsImtpZCI6ImdhdGV3YXlfY2VydGlmaWNhdGVfYWxpYXMiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJhcHBsaWNhdGlvbiI6eyJvd25lciI6ImFkbWluIiwidGllclF1b3RhVHlwZSI6bnVsbCwidGllciI6IjIwUGVyTWluIiwibmFtZSI6IlNESyIsImlkIjoxMTUsInV1aWQiOiJlNjgxYzZjNS1kNjBiLTRiMGItYWY2MC1jMDY2YzQwYjRhYzgifSwiaXNzIjoiaHR0cHM6XC9cL2FtLXdzbzItbm9ucHJvZC5hcHBzLm50LW5vbi1vY3AubmVvdGVrLnNhOjQ0M1wvb2F1dGgyXC90b2tlbiIsInRpZXJJbmZvIjp7IlVubGltaXRlZCI6eyJ0aWVyUXVvdGFUeXBlIjoicmVxdWVzdENvdW50IiwiZ3JhcGhRTE1heENvbXBsZXhpdHkiOjAsImdyYXBoUUxNYXhEZXB0aCI6MCwic3RvcE9uUXVvdGFSZWFjaCI6dHJ1ZSwic3Bpa2VBcnJlc3RMaW1pdCI6MCwic3Bpa2VBcnJlc3RVbml0IjpudWxsfX0sImtleXR5cGUiOiJQUk9EVUNUSU9OIiwicGVybWl0dGVkUmVmZXJlciI6IiIsInN1YnNjcmliZWRBUElzIjpbeyJzdWJzY3JpYmVyVGVuYW50RG9tYWluIjoiY2FyYm9uLnN1cGVyIiwibmFtZSI6InFhZW1hLW9wZW4tYmFua2luZyIsImNvbnRleHQiOiJcL3FhZW1hXC9vYlwvdjEiLCJwdWJsaXNoZXIiOiJhZG1pbiIsInZlcnNpb24iOiJ2MSIsInN1YnNjcmlwdGlvblRpZXIiOiJVbmxpbWl0ZWQifSx7InN1YnNjcmliZXJUZW5hbnREb21haW4iOiJjYXJib24uc3VwZXIiLCJuYW1lIjoiU0RLcyIsImNvbnRleHQiOiJcL3Nka1wvMS4wIiwicHVibGlzaGVyIjoiYWRtaW4iLCJ2ZXJzaW9uIjoiMS4wIiwic3Vic2NyaXB0aW9uVGllciI6IlVubGltaXRlZCJ9XSwidG9rZW5fdHlwZSI6ImFwaUtleSIsInBlcm1pdHRlZElQIjoiIiwiaWF0IjoxNzMwMjM3ODA2LCJqdGkiOiIxZGY4NzcwMi1hMTZhLTQxNjQtODNjZS04MjFlYjRlN2JmNTUifQ==.p3Ml3uqczvMuolcfH79P970jq1boz45gDMXqn4AO6lTmuWKxTzFX9fUMgud3FgGBVLsCsSxknXXPCu4LSRRjC9ZS0MSC_Qe8SvNLujX4GM0SrbXW4v4seqrFsbnmzOrnm9Ej3cmMUWNgL1iKH7skNMfZxhRtUWJb0BJ0H3mihQ0-K-bXLVigxDoj3vB_xc_CxSPU7HqNV9ISNIaS0c_nWOKfo563OPCA05tp5QqA-v3-B3OzyV6LBNOBsrq1Lhkh_GZ5uu6iWflKYQfXFgiSuARwac4QX85fNA8W32ttRdIdIR1RVDR4PhohvftUlPK4ca3W7P4nzqrGbSnFVBWUNg==")
      .setTag("GET INVOICE DETAILS")
      .setPriority(Priority.HIGH)
      .build().getAsJSONObject(object: JSONObjectRequestListener{
      override fun onResponse(response: JSONObject?) {
        promise.resolve(response.toString())
      }

      override fun onError(anError: ANError?) {
        Log.i("datadata",anError?.message+"")
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
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

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

      return builder.build()
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}
