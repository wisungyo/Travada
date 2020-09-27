package com.example.travada.features.rencana.searchpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.features.rencana.searchpage.room.ItemSearch
import kotlinx.android.synthetic.main.activity_t_p_search_page.*
import kotlinx.android.synthetic.main.fragment_tp_search_suggest.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TpSearchSuggestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TpSearchSuggestFragment : Fragment(), TPSearchSuggestPresenter.Listener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var presenter: TPSearchSuggestPresenter


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
        return inflater.inflate(R.layout.fragment_tp_search_suggest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        val main = activity as TPSearchPageActivity

        DatabaseItem.getInstance(main)?.let {
            presenter =
                TPSearchSuggestPresenter(it, this)
        }

        presenter.fetchData()

        tv_delete_all.setOnClickListener {
            presenter.deleteAll()
        }

        chip_korea.setOnClickListener {
            presenter.onQuerySearch("Korea")
        }

        chip_tokyo.setOnClickListener {
            presenter.onQuerySearch("Tokyo")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TpSearchSuggestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TpSearchSuggestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showQueryHistory(listItem: List<ItemSearch>) {
        activity?.runOnUiThread {
            rv_last_result.layoutManager=
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rv_last_result.adapter = SearchHistoryAdapter(listItem, presenter)
        }
    }

    override fun onDelete(item: ItemSearch) {
        activity?.runOnUiThread {
            presenter.fetchData()
        }
    }

    override fun showSearchResult() {
        val activityMethod = activity as TPSearchPageActivity
        activityMethod.showSearchResult()

    }


}