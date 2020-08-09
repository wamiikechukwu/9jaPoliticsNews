package wami.ikechukwu.kanu.view.overview.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.RecentFragmentViewModel

class RecentFragment : Fragment() {

    companion object {
        fun newInstance() = RecentFragment()
    }

    private lateinit var viewModel: RecentFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recent_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecentFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}