package weatherapp.services.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class Constants {

    //http://api.openweathermap.org/img/wn/50d@2x.png
    //https://openweathermap.org/img/wn/50d@2x.png



    //https://api.openweathermap.org/data/2.5/onecall?lat=23.7743889&lon=90.3669191&appid=3d6c443f18312d21ac8146de13fc5c98&units=metric
    //http://openweathermap.org/img/wn/50d@2x.png

    companion object{
        private const val WEATHER_API_BASE_URL = "https://api.openweathermap.org"
        private const val WEATHER_API_IMAGE_BASE_URL = "https://openweathermap.org/"
        private const val WEATHER_API_KEY = "3d6c443f18312d21ac8146de13fc5c98"

        public fun getApiUrl(latitude:Double, longitude : Double) : String{
            return "$WEATHER_API_BASE_URL/data/2.5/onecall?lat=$latitude&lon=$longitude&appid=$WEATHER_API_KEY&units=metric"
        }

        public fun getImageApiUrl(iconCode : String) : String{
            return "$WEATHER_API_IMAGE_BASE_URL/img/wn/$iconCode@2x.png"
        }

        public fun getUserLocation(context: Context, latitude: Double, longitude:Double) : List<String?>{
            val geocoder: Geocoder = Geocoder(context, Locale.getDefault())

            val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            val division = addresses[0].adminArea
            val city: String ?= addresses[0].locality

            val list : List<String?> = listOf(city,division)

            Log.v("TAG", "addresses = ${addresses.size}")
            Log.v("TAG", "addresses = ${addresses.toString()}")
            // val address: String = addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            // val city: String = addresses[0].locality
            // val state: String = addresses[0].adminArea
            // val country: String = addresses[0].countryName
            //val postalCode: String = addresses[0].postalCode
            // Log.v("TAG", "country = $country")
            return list;
        }

        public fun unixToDateConvert(date: Int) : String{
            val d = Date(date.toLong() * 1000)
            val sdf = SimpleDateFormat("d\nEEE")
            return sdf.format(d);
        }

        public fun unixToDateConvertFullDate(date: Int) : String{
            val d = Date(date.toLong() * 1000)
            val sdf = SimpleDateFormat("EEE, MMM d")
            return sdf.format(d);
        }

        public fun unixToTimeConvert(date : String) : String{
            val d = Date(date.toLong() * 1000)
            val sdf = SimpleDateFormat("hh:mm a")
            return sdf.format(d);
        }

        public fun getFormattedPressure(pressure: Int?) : String{
            val p : Int? = (pressure?.times(0.02953))?.roundToInt() //1 hpa = 0.02953 inhg
            val df = DecimalFormat("#.##")
            return df.format(p).toString()
        }

        public fun getFormattedVisibility(visibility: Int?) : String{
            return ((visibility?.div(1.609))?.div(1000))?.roundToInt().toString() // 1 mi = 1.609 km
        }

        public fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }

    }

}