package com.example.travada.features.rencana.detailrencana

import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse

class GambarWisataPresenter(val listener: Listener) {


    interface Listener {
        fun implementGambar(getDestinasi: GetDestinasiDetailResponse.Data) {}
    }
}