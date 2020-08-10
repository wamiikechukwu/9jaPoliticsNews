package wami.ikechukwu.kanu.view.overview.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import wami.ikechukwu.kanu.HomeFragmentViewModel
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.databinding.HomeFragmentLayoutBinding

class HomeFragment : Fragment() {

//    companion object {
//        fun newInstance() = HomeFragment()
//    }

    //    DATA BINDING
    private lateinit var binding: HomeFragmentLayoutBinding

    //    VIEW MODEL
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//  IMPLEMENTING DATA BINDING
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment_layout, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}