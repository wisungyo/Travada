package com.example.travada.features.rencana.wisnu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.travada.R
import kotlinx.android.synthetic.main.fragment_dialog_konfirmasi_rencana.view.*

class DialogKonfirmasi: DialogFragment() {

    companion object {
        const val TAG = "DialogKonfirmasi"
        private const val KEY_TITLE = "KEY_TITLE"

        fun newInstance(title: String): DialogKonfirmasi {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            val fragment = DialogKonfirmasi()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_konfirmasi_rencana, container, false)
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
        view.tv_dialog_konfirmasi_title.text = arguments?.getString(KEY_TITLE)
    }

    private fun setupClickListeners(view: View) {
        view.btn_dialog_konfirmasi_btn_yes.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
        view.btn_dialog_konfirmasi_btn_no.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
    }
}