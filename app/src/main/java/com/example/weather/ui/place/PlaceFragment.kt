package com.example.weather.ui.place

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.MainActivity
import com.example.kotlinpractice1.R
import com.example.weather.ui.weather.WeatherActivity
import com.homurax.sunnyweather.util.showToast
import kotlinx.android.synthetic.main.fragment_place.bgImageView
import kotlinx.android.synthetic.main.fragment_place.recyclerView
import kotlinx.android.synthetic.main.fragment_place.searchPlaceEdit

class PlaceFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 当 PlaceFragment 被嵌入 MainActivity 中且已经存在被选中的城市时才跳转 WeatherActivity
        // 避免 WeatherActivity 中切换城市时无限循环跳转
        if (activity is MainActivity && viewModel.isPlaceSaved()){
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply{
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PlaceAdapter(this, viewModel.placeList)
        var adapter = recyclerView.adapter
        searchPlaceEdit.addTextChangedListener {editable ->
            if (editable.toString().isNotEmpty()){
                viewModel.searchPlaces(editable.toString())
            }else{
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter?.notifyDataSetChanged()
            }
        }

        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            val places = result.getOrNull()
            if (places != null) {
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter?.notifyDataSetChanged()
            } else {
                "未能查询到任何地点".showToast()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}