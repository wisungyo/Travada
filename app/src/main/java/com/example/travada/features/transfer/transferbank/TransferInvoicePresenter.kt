package com.example.travada.features.transfer.transferbank

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class TransferInvoicePresenter(val listener:Listener) {


    interface Listener {
        fun goToTransferMenu()
        fun setInformation(bundle: Bundle)
    }
}