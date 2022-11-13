package weatherapp.services.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import weatherapp.R
import weatherapp.services.model.Daily
import weatherapp.services.utils.Constants
import kotlin.math.roundToInt

class ViewPagerAdapter(private val context: Context, private val dailyList: ArrayList<Daily>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_pager_item_home_daily_forecast, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewPagerViewHolder, position: Int) {
        val daily: Daily = dailyList[position]
        Picasso.get().load(daily.weather[0].icon?.let { Constants.getImageApiUrl(it) }).into(holder.tempIv)
        holder.tempTv.text = daily.temp?.min?.roundToInt()?.toString() + context.applicationContext.resources.getString(R.string.degree_celsius) + " / "+ daily.temp?.max?.roundToInt()?.toString() + context.applicationContext.resources.getString(R.string.degree_celsius)
        holder.tempMainTv.text = daily.weather[0].main
        holder.tempDescriptionTv.text = daily.weather[0].description
        holder.windTv.text =
            daily.windSpeed.toString() + context.resources.getString(R.string.wind_unit)
        holder.humidityTv.text =
            daily.humidity.toString() + context.resources.getString(R.string.percentage)
        holder.uvIndexTv.text = daily.uvi.toString()
        holder.pressureTv.text = Constants.getFormattedPressure(daily.pressure) + context.applicationContext.resources.getString(R.string.pressure_unit)
        holder.dewPointTv.text = daily.dewPoint?.roundToInt()?.toString() + context.applicationContext.resources.getString(R.string.degree_celsius)
        holder.sunriseTv.text = daily.sunrise?.toString()?.let { Constants.unixToTimeConvert(it) }
        holder.sunsetTv.text = daily.sunset?.toString()?.let { Constants.unixToTimeConvert(it) }
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tempIv: ImageView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_weather_description_iv)
        val tempTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_weather_temp_tv)
        val tempMainTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_weather_main_tv)
        val tempDescriptionTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_weather_description_tv)
        val windTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_wind_value_tv)
        val humidityTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_humidity_value_tv)
        val uvIndexTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_uv_index_value_tv)
        val pressureTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_pressure_value_tv)
        val dewPointTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_dew_point_value_tv)
        val sunriseTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_sunrise_value_tv)
        val sunsetTv: TextView =
            itemView.findViewById(R.id.vp_item_home_daily_forecast_sunset_value_tv)
    }

}