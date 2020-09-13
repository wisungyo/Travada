package com.example.travada.features.tabungan.detailtabungan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.DetailTabunganAdapter
import kotlinx.android.synthetic.main.fragment_detail_tabungan.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailTabunganFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailTabunganFragment : Fragment() {

    private val detailList = arrayListOf(
        DataDetailTabungan("Enno bening", "12000", "73647364734", "EB"),
        DataDetailTabungan("Enno bening", "12000", "73647364734", "EB"),
        DataDetailTabungan("Enno bening", "12000", "73647364734", "EB"),
        DataDetailTabungan("Enno bening", "12000", "73647364734", "EB")
    )

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
        return inflater.inflate(R.layout.fragment_detail_tabungan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            rvDetailTabungan.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter =
                    DetailTabunganAdapter(
                        detailList
                    )
            }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailTabunganFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}