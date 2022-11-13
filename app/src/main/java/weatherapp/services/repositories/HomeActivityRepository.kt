package weatherapp.services.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import weatherapp.services.api.VolleySingleton
import weatherapp.services.model.WeatherInfo
import weatherapp.services.utils.Constants
import java.nio.charset.StandardCharsets

class HomeActivityRepository constructor(context: Context){
    private val TAG: String = HomeActivityRepository::class.java.simpleName

    private var weatherInfoLiveData : MutableLiveData<WeatherInfo> = MutableLiveData()
    private val requestQueue = VolleySingleton.getInstance(context).requestQueue

    fun getWeatherInfo(latitude: Double, longitude: Double) :  MutableLiveData<WeatherInfo>{
        val url  = Constants.getApiUrl(latitude, longitude)
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val gson = Gson()
                val weatherInfo = gson.fromJson(response, WeatherInfo::class.java)
                weatherInfoLiveData.postValue(weatherInfo)
            },
            { error ->
                //var errorResponse = String(error.networkResponse.data, StandardCharsets.UTF_8)
                Log.v(TAG, "Error Response : $error")
            }
        )
        requestQueue.add(stringRequest)
        return weatherInfoLiveData
    }

}