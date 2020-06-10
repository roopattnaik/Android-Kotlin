package com.example.loginNav.view.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide


import com.example.loginNav.R
import com.example.loginNav.model.WeatherResponse
import com.example.loginNav.network.WeatherService
import kotlinx.android.synthetic.main.fragment_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    private var weatherData: TextView? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v: View = inflater.inflate(R.layout.fragment_details, container, false)

        var title: TextView = v.findViewById(R.id.titleText)
        var genre: TextView = v.findViewById(R.id.genreText)
        var year: TextView = v.findViewById(R.id.yearText)
        var imageurl: ImageView = v.findViewById(R.id.finalimage)
        var gobackButton: Button = v.findViewById(R.id.goback)
        var addUserButton : Button = v.findViewById(R.id.additem)
        var weatherButton: Button = v.findViewById(R.id.weatherButton)
        weatherData= v.findViewById(R.id.weatherView)

        var bundle: Bundle? = this.arguments
        var gameName: String? = bundle?.getString("title")
        var gameYear: String? = bundle?.getString("year")
        var gameGenre: String? = bundle?.getString("genre")
        var gameImageurl: String? = bundle?.getString("imageurl")

        title.text = gameName
        genre.text = gameGenre
        year.text = gameYear
        Glide.with(this).load(gameImageurl).into(imageurl)

        gobackButton.setOnClickListener {
            findNavController().popBackStack()

        }

        addUserButton.setOnClickListener{
            findNavController().navigate(R.id.action_detailsFragment_to_realmFragment)
        }
        weatherButton.setOnClickListener {

            getCurrentData()
        }
        title.setOnLongClickListener{
            Toast.makeText(view?.context,"long press",Toast.LENGTH_SHORT)
            return@setOnLongClickListener true

        }

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

            super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.settings -> {
                Toast.makeText(context, "Settings selected", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.logout -> {
                findNavController().navigate(R.id.action_detailsFragment_to_loginFragment)
                return true
            }
            R.id.exit -> {
                activity?.finishAffinity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure

                    weatherData!!.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherData!!.text = t.message
            }
        })
    }

    companion object {
        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "2e65127e909e178d0af311a81f39948c"
        var lat = "35"
        var lon = "139"

        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
