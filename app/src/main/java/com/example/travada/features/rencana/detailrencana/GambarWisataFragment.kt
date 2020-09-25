package com.example.travada.features.rencana.detailrencana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.detailriwayat.AdapterDetailRiwayatActivity
import com.example.travada.detailriwayat.DetailRiwayatActivityPresenter
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_detail_rencana.*
import kotlinx.android.synthetic.main.fragment_gambar_wisata.*
import kotlinx.android.synthetic.main.list_gambar_wisata.view.*
import kotlin.random.Random.Default.Companion

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GambarWisataFragment : Fragment(), GambarWisataPresenter.Listener {

    private lateinit var presenter: GambarWisataPresenter
    private lateinit var result: GetDestinasiDetailResponse.Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        presenter = GambarWisataPresenter(this)

        val bundle = arguments
        bundle?.getParcelable<GetDestinasiDetailResponse.Data>("rencana")?.let {
            result = it
        }

      //ivGambarWisata.setImageResource(result.gambarList)

            Glide.with(this).load(result.gambarList)
                .into(ivGambarWisata)
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