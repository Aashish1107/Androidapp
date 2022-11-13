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
import weatherapp.services.model.Hourly
import weatherapp.services.utils.Constants
import kotlin.math.roundToInt

class HourlyRvAdapter(private val context: Context, private val hourlyList : ArrayList<Hourly>) : RecyclerView.Adapter<HourlyRvAdapter.HourlyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_home_hourly_forecast, parent, false);
        return HourlyViewHolder(view);
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        Picasso.get()
            .load(hourlyList[position].weather[0].icon?.let { Constants.getImageApiUrl(it) })
            .into(holder.iconIv);
        holder.timeTv.text = Constants.unixToTimeConvert(hourlyList[position].dt.toString())
        holder.tempTv.text = hourlyList[position].temp?.roundToInt().toString() + context.resources.getString(R.string.degree_celsius)
        holder.mainWeatherTv.text = hourlyList[position].weather[0].main
        holder.weatherDescTv.text = hourlyList[position].weather[0].description
    }

    override fun getItemCount(): Int {
        return hourlyList.size;
    }

    class HourlyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val iconIv : ImageView = itemView.findViewById(R.id.rv_item_hourly_icon)
        val timeTv : TextView = itemView.findViewById(R.id.rv_item_hourly_time_tv)
        val tempTv : TextView = itemView.findViewById(R.id.rv_item_hourly_temp_tv)
        val mainWeatherTv : TextView = itemView.findViewById(R.id.rv_item_hourly_main_weather_tv)
        val weatherDescTv : TextView = itemView.findViewById(R.id.rv_item_hourly_weather_description_tv)
    }
}