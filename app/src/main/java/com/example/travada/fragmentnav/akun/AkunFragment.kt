package com.example.travada.fragmentnav.akun

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.travada.R
import com.example.travada.features.rencana.detailrencana.view.DetailRencanaActivity
import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.features.topup.TopUpInputActivity
import com.example.travada.features.topup.TopUpInputPinActivity
import com.example.travada.fragmentnav.akun.changepass.AkunChangePassActivity
import com.example.travada.fragmentnav.akun.changepin.AkunInputNewPinActivity
import com.example.travada.fragmentnav.akun.network.AkunApiClient
import com.example.travada.fragmentnav.akun.pojo.GetUserMeResponse
import com.example.travada.fragmentnav.akun.pojo.coba
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.util.util
import com.example.travada.welcomepage.onboarding.OnboardingEndActivity
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_akun.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AkunFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AkunFragment : Fragment(), AkunFragmentPresenter.Listener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var presenter: AkunFragmentPresenter
    val LoadingDialog = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        presenter = AkunFragmentPresenter(this)
        presenter.getMe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_changepass.setOnClickListener {
            goToChangePassword()
        }

        btn_changepin.setOnClickListener {
//            goToChangePin()
            val intentActivity = Intent(activity, TopUpInputActivity::class.java)
            startActivity(intentActivity)
        }

        btn_logout.setOnClickListener {
            DatabaseItem.getInstance(activity as MainPageActivity)?.let {
                presenter.logout(it)
            }
        }

//        var token = Hawk.get(util.SF_TOKEN, "0")
//
//
//        AkunApiClient.AKUN_API_SERVICES.getcoba(token, 18)
//            .enqueue(object : Callback<coba> {
//                override fun onFailure(call: Call<coba>, t: Throwable) {
//                    showToast(t.message.toString())
//                    Log.d(
//                        "checkerr", "error ${t.message.toString()}"
//                    )
//                }
//
//                override fun onResponse(call: Call<coba>, response: Response<coba>) {
//                    response.body()?.data?.let {
//                        Log.d(
//                            "checkerr", "berhasil ${it.pemesanan.id}"
//                        )
//                        Log.d(
//                            "checkerr", "berhasil ${it.destinasi.id}"
//                        )
//                        showToast(it.toString())
//
//                    }
//
//                }
//
//            })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AkunFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AkunFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun goToChangePassword() {
        val intentActivity = Intent(activity, AkunChangePassActivity::class.java)
        startActivity(intentActivity)
    }

    override fun goToChangePin() {
//        val intentActivity = Intent(activity, AkunInputNewPinActivity::class.java)
//        startActivity(intentActivity)
        Toast.makeText(
            context,
            "Under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun goToMenuLogin() {
//        val intentActivity = Intent(activity, OnboardingEndActivity::class.java)
//        startActivity(intentActivity)
        Toast.makeText(
            context,
            "Under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showLoadingDialog() {
        LoadingDialog.isCancelable = false
        fragmentManager?.let { LoadingDialog.show(it, "Fragment") }
    }

    override fun hideLoadingDialog() {
        LoadingDialog.dismiss()
    }

    override fun showToast(text: String) {
        Toast.makeText(
            context, text,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun setResponse(text1: String, text2: String) {
        tv_accountname.text = text1
        tv_accountnumb.text = text2
    }
}