package com.example.travada.features.rencana.detailrencana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import com.example.travada.R
import com.example.travada.features.rencana.detailrencana.DetailRencanaPresenter.Companion.INTENT_PARCELABEL
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_gambar_wisata.*
import kotlin.random.Random.Default.Companion

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GambarWisataFragment : Fragment() {
    private lateinit var result: DataGambarWisata

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gambar_wisata, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gambar = ivGambarWisata
        val bundle = arguments
        if (bundle != null) {
            val parseData = bundle.getParcelable<DataGambarWisata>(INTENT_PARCELABEL)
            gambar.setImageResource(parseData?.gambar!!)

        }

}

companion object {
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
        GambarWisataFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
}
}