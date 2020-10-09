package com.example.travada.fragmentnav.notifikasi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.fragmentnav.notifikasi.adapter.NotifikasiAdapter
import com.example.travada.fragmentnav.notifikasi.model.DataNotifikasi
import kotlinx.android.synthetic.main.fragment_notifikasi.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotifikasiFragment : Fragment(), NotifikasiFragmentPresenter.Listener {
    private lateinit var presenter: NotifikasiFragmentPresenter

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
        savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifikasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NotifikasiFragmentPresenter(this)
        presenter.fetchDataNotifikasi()
    }

    override fun showData(notifikasiAdapter: NotifikasiAdapter) {
        val layoutManagerLinear = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvNotifikasi.layoutManager = layoutManagerLinear
        rvNotifikasi.adapter = notifikasiAdapter
        // rvNotifikasi.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun showDetaiNotifikasi(dataNotifikasi: DataNotifikasi) {

        when(dataNotifikasi.kategori){
            "travasave" -> {

                val DetailNotifikasiTravasave = Intent(context, DetailNotifikasiTravasave::class.java)
                DetailNotifikasiTravasave.putExtra("notifikasi", dataNotifikasi)
                startActivity(DetailNotifikasiTravasave)
            }
            "travaplan"-> {
                val DetailNotifikasiTravaplan = Intent(context, DetailNotifikasiTravaplan::class.java)
                DetailNotifikasiTravaplan.putExtra("notifikasi", dataNotifikasi)
                startActivity(DetailNotifikasiTravaplan)
            }
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotifikasiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotifikasiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}