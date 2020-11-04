package com.example.travada.features.tabungan.detailtabungan.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.PageAdapter
import com.example.travada.features.tabungan.detailtabungan.presenter.DetailTabunganPresenter
import com.example.travada.features.tabungan.pojo.GetTabunganDetailResponse
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_detail_tabungan.*


class DetailTabunganActivity : AppCompatActivity(), DetailTabunganPresenter.Listener {
    lateinit var presenter: DetailTabunganPresenter
    val MyFragment = LoadingDialog()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tabungan)
        presenter = DetailTabunganPresenter(this)

        val intentId = intent.getIntExtra("TABUNGAN_ID", 3)

        presenter.getDetail(intentId)

//        val data = Bundle()
//        val myMessage =intentId
//        data.putInt("ID_DETAILTABUNGAN", myMessage)
//        val fragment = DetailTabunganFragment()
//        fragment.setArguments(data)
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.frameDetailTabungan, fragment)
//            .commit()


        val bundle = Bundle()
        bundle.putInt("ID_DETAILTABUNGAN", intentId)
        Log.d("YOGA","${intentId}")
        val fragment = DetailTabunganFragment()
        fragment.setArguments(bundle)

//        val bundle = Bundle()
//        bundle.putInt("ID_DETAILTABUNGAN", intentId)
//        val mPager = findViewById<View>(R.id.viewPagerMain) as ViewPager
//        val mPagerAdapter = PageAdapter(supportFragmentManager, bundle)
//        mPager.setAdapter(mPagerAdapter)

//
//        val bundle = Bundle()
//        val id = intentId
//        Log.d("TABUNGAN","${id}")
//        bundle.putInt("TABUNGAN", id)
//        val fragobj = DetailTabunganFragment()
//        fragobj.setArguments(bundle)


//        val bundle = Bundle()
//        val myMessage =intentId
//        bundle.putInt("TABUNGAN_ID", myMessage)
//        val fragInfo = DetailTabunganFragment()
//        fragInfo.setArguments(bundle)
//        transaction.replace(R.id.frag, fragInfo)
//        transaction.commit()



//        Fragment overviewFrag = OverViewFragment.newInstance();
//        overviewFrag.setArguments(<args_bundle>);
//        adapter.addFrag(overviewFrag, getString(R.string.overview));


        viewPagerMain.adapter = PageAdapter(supportFragmentManager,intentId)
        tabMain.setupWithViewPager(viewPagerMain)



        ivFormResultBack.setOnClickListener {
            finish()
        }
    }

    override fun showLoadingDialog() {
        val fm = supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun implementDetailTabungan(getTabungan: GetTabunganDetailResponse.Data) {
        tvTitleTabunganDetail.text = getTabungan.tujuan
    }

    override fun implementDetailTabunganFailure(errMessage: String) {
        Toast.makeText(this, "Error : $errMessage", Toast.LENGTH_LONG).show()
    }
}