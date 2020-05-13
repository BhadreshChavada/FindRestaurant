package com.findRestaurant.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.findRestaurant.R
import com.findRestaurant.Utils.AppUtils
import com.findRestaurant.core.AppDatabase
import com.findRestaurant.core.BindingFragment
import com.findRestaurant.databinding.FragmentMapRestaurantBinding
import com.findRestaurant.model.Restaurant
import com.findRestaurant.model.RestaurantData
import com.findRestaurant.viewmodel.RestaurantMapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class RestaurantMapFragment : BindingFragment<FragmentMapRestaurantBinding>(), OnMapReadyCallback {


    var mMap: GoogleMap? = null
    override fun getLayoutResId() = R.layout.fragment_map_restaurant
    var viewModel: RestaurantMapViewModel<RestaurantData>? = null
    var zoomLevel = 10f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapRestaurantBinding.inflate(inflater, container, false)
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        AppUtils.setActivtiy(activity!!)

        return binding.root
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0
        binding.txtNoData.visibility = View.GONE
        binding.llMap.visibility = View.VISIBLE
        initViewModel()
    }

    fun initViewModel() {

        viewModel = RestaurantMapViewModel(activity!!, RestaurantData::class.java)

        viewModel?.liveData?.observe(this, Observer { t ->

            if (t.size > 0) {
                val resData = t.get(0)
                val position =
                    CameraPosition.Builder().target(LatLng(resData.lat!!, resData.lng!!))
                        .zoom(zoomLevel).bearing(19f).tilt(30f)
                        .build()
                mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(position))

                for (item in t) {
                    createMarker(item)
                }
            }

        })


    }

    protected fun createMarker(
        item: Restaurant
    ): Marker? {
        return mMap?.addMarker(
            MarkerOptions()
                .position(LatLng(item.lat!!, item.lng!!))
                .anchor(0.5f, 0.5f)
                .title(item.name)
                .snippet(item.address)
        )
    }

}