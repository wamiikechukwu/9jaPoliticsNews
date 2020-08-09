package wami.ikechukwu.kanu.view.overview.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import wami.ikechukwu.kanu.MoreFragmentViewModel
import wami.ikechukwu.kanu.R

class MoreFragment : Fragment() {

    companion object {
        fun newInstance() = MoreFragment()
    }

    private lateinit var viewModel: MoreFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.more_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoreFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}