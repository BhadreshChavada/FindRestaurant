package com.findRestaurant.activities

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.findRestaurant.R
import com.findRestaurant.base.BaseBindingAdapter
import com.findRestaurant.core.BindingActivity
import com.findRestaurant.databinding.ActivityHomeTabBinding
import com.findRestaurant.fragment.RestaurantListFragment
import com.findRestaurant.fragment.RestaurantMapFragment
import com.findRestaurant.model.Restaurant
import com.google.android.material.tabs.TabLayout

class HomeTabActivity : BindingActivity<ActivityHomeTabBinding>() {
    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_home_tab

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.toolbar?.title?.text = getString(R.string.resturant)
        setupTabLayout()
        createTabIcons()
        bindWidgetsWithAnEvent()

        replaceFragmentInTab(
            RestaurantListFragment(),
            "RestaurantListFragment",
            false
        )
    }

    private fun setupTabLayout() {
        mBinding.tabs.addTab(mBinding.tabs.newTab().setText(getString(R.string.resturant)), true)
        mBinding.tabs.addTab(mBinding.tabs.newTab().setText(getString(R.string.gmap)))

        val root: View = mBinding.tabs.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.colorPrimaryDark))
            drawable.setSize(2, 1)
            root.dividerPadding = 5
            root.dividerDrawable = drawable
        }
    }

    private fun createTabIcons() {

        val resturant =
            LayoutInflater.from(activity!!).inflate(R.layout.custom_tab, null) as TextView
        resturant.text = getString(R.string.resturant)
        resturant.setTextColor(ContextCompat.getColor(activity!!, R.color.tab_selected_color))
        setTextViewSelectedDrawableColor(resturant)
        mBinding.tabs.getTabAt(0)!!.customView = resturant

        val gmap =
            LayoutInflater.from(activity!!).inflate(R.layout.custom_tab, null) as TextView
        gmap.text = getString(R.string.gmap)
        setTextViewUnSelectedDrawableColor(gmap)
        mBinding.tabs.getTabAt(1)!!.customView = gmap


    }

    private fun setTextViewSelectedDrawableColor(textView: TextView) {
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(activity!!, R.color.tab_selected_color),
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun setTextViewUnSelectedDrawableColor(textView: TextView) {
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(activity!!, R.color.tab_unselected_color),
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun bindWidgetsWithAnEvent() {
        mBinding.tabs.getTabAt(0)!!.select()

        mBinding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setCurrentTabFragment(tab.position)
                (tab.customView as TextView).setTextColor(
                    ContextCompat.getColor(
                        activity!!,
                        R.color.tab_selected_color
                    )
                )
                setTextViewSelectedDrawableColor((tab.customView as TextView?)!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                (tab.customView as TextView).setTextColor(
                    ContextCompat.getColor(
                        activity!!,
                        R.color.tab_unselected_color
                    )
                )
                setTextViewUnSelectedDrawableColor((tab.customView as TextView?)!!)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setCurrentTabFragment(tabPosition: Int) {
        when (tabPosition) {
            0 -> replaceFragmentInTab(
                RestaurantListFragment(),
                "RestaurantListFragment",
                false
            )
            1 ->
                replaceFragmentInTab(
                    RestaurantMapFragment(),
                    "RestaurantMapFragment",
                    false
                )



        }
    }



}