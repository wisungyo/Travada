package com.example.travada.features.rencana.searchpage

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.travada.R
import com.example.travada.features.rencana.detailrencana.view.DetailRencanaActivity
import com.example.travada.features.rencana.pojo.GetDestinasiAllResponse
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.fragment_tp_search_result.*
import kotlinx.android.synthetic.main.item_t_p_search_page.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TpSearchResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TpSearchResultFragment : Fragment(), TPSearchResultPresenter.Listener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var presenter: TPSearchResultPresenter = TPSearchResultPresenter(this)
    val LoadingDialog = com.example.travada.util.loadingdialog.LoadingDialog()


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
        return inflater.inflate(R.layout.fragment_tp_search_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        presenter.checkResult()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TpSearchResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TpSearchResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showDestinasiResult(List: MutableList<GetDestinasiAllResponse.Data>) {
        rv_tp_searchresult.adapter = SearchResultAdapter(List, presenter)
    }

    override fun showLoadingDialog() {
//        LoadingDialog.isCancelable = false
//        fragmentManager?.let { LoadingDialog.show(it, "Fragment") }

            shimmer_view_container.visibility = VISIBLE
            shimmer_view_container.startShimmerAnimation()


    }

    override fun hideLoadingDialog() {
//        LoadingDialog.dismiss()
            shimmer_view_container.visibility = INVISIBLE
            shimmer_view_container.stopShimmerAnimation()


    }

    override fun showToast(text: String) {
        Toast.makeText(
            context, text,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showErrorImage() {
        iv_error_image.visibility = VISIBLE
        Glide
            .with(this)
            .load(R.drawable.ic_search_not_found)
            .into(iv_error_image)

    }

    override fun hideErrorImage() {
        iv_error_image.visibility = INVISIBLE
    }

    override fun showClickedItem(id: Int) {
        val intentDetailRencanaActivity = Intent(activity, DetailRencanaActivity::class.java)
        intentDetailRencanaActivity.putExtra("DESTINASI_ID", id)
        startActivity(intentDetailRencanaActivity)
    }
}