package weatherapp.services.internet

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import weatherapp.services.utils.Constants

class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val noInternetListener = context as NoInternetListener
        if (Constants.isOnline(context)) {
            noInternetListener.noInternet(true)
        } else {
            noInternetListener.noInternet(false)
        }
    }
}