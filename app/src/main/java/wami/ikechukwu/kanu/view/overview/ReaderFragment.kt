package wami.ikechukwu.kanu.view.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import wami.ikechukwu.kanu.R

class ReaderFragment : Fragment() {

    companion object {
        fun newInstance() = ReaderFragment()
    }

    private lateinit var viewModel: ReaderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reader_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReaderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}