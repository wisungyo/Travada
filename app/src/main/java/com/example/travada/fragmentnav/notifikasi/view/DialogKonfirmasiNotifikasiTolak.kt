package com.example.travada.fragmentnav.notifikasi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.travada.R
import kotlinx.android.synthetic.main.fragment_dialog_konfirmasi_notifikasi_tolak.view.*
import kotlinx.android.synthetic.main.fragment_dialog_konfirmasi_rencana.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogKonfirmasiNotifikasiTolak.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogKonfirmasiNotifikasiTolak : DialogFragment() {

    companion object {
        const val TAG = "DialogKonfirmasiTolak"
        private const val KEY_TITLE_TOLAK = "KEY_TITLE_TOLAK"
        private const val KEY_SUBTITLE_TOLAK = "KEY_SUBTITLE_TOLAK"

        fun newInstance(titleTolak: String, subtitleGabung: String): DialogKonfirmasiNotifikasiTolak {
            val args = Bundle()
            args.putString(KEY_TITLE_TOLAK, titleTolak)
            args.putString(KEY_SUBTITLE_TOLAK, subtitleGabung)
            val fragment = DialogKonfirmasiNotifikasiTolak()
            fragment.arguments = args
            return fragment
        }
    }


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
        return inflater.inflate(
            R.layout.fragment_dialog_konfirmasi_notifikasi_tolak,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView(view: View) {
        view.tv_dialog_tolak_title.text = arguments?.getString(DialogKonfirmasiNotifikasiTolak.KEY_TITLE_TOLAK)
        view.tv_dialog_tolak_subtitle.text = arguments?.getString(DialogKonfirmasiNotifikasiTolak.KEY_SUBTITLE_TOLAK)
    }

    private fun setupClickListeners(view: View) {
        view.btn_dialog_tolak_btn_yes.setOnClickListener {
            dismiss()
            (activity as DetailNotifikasiTravaplan).showKonfirmasiYesDialogTolak()
        }
        view.btn_dialog_tolak_btn_no.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
    }

}