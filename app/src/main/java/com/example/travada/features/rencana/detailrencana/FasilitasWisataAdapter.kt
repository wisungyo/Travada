//package com.example.travada.features.rencana.detailrencana
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.travada.R
//import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
//import kotlinx.android.synthetic.main.activity_detail_rencana.*
//import kotlinx.android.synthetic.main.activity_detail_rencana.view.*
//import kotlinx.android.synthetic.main.list_fasilitas_perjalanan.view.*
//
//class FasilitasWisataAdapter(
//    val listFasilitasWisata: List<GetDestinasiDetailResponse.Data>,
//    val presenter: DetailRencanaPresenter) :
//    RecyclerView.Adapter<FasilitasWisataAdapter.ViewHolder>() {
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.activity_detail_rencana, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return listFasilitasWisata.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.tvTitleDetailRencana.text = listFasilitasWisata[position].namaTrip
//        holder.itemView.tvFasilitasRencana.text = listFasilitasWisata[position].fasilitas[1]
//    }
//}