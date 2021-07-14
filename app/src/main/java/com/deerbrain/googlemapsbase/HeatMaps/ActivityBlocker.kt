package com.deerbrain.googlemapsbase.HeatMaps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.deerbrain.googlemapsbase.R

class ActivityBlocker(val mContext: Context) {
    var waitAlertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(mContext)
    var alertView: View = LayoutInflater.from(mContext).inflate(R.layout.dialog_wait_alert, null, false)
    var waitProgress: ProgressBar
    var waitText: TextView
    var waitAlertDialog: AlertDialog

    init {
        alertView.layoutParams = ViewGroup.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        waitAlertDialogBuilder.setView(alertView)
        waitProgress = alertView.findViewById<ProgressBar>(R.id.loadingProgress)
        waitText = alertView.findViewById<TextView>(R.id.loadingText)
        waitText.text = "Loading"
        waitProgress.isIndeterminate = true
        waitAlertDialogBuilder.setCancelable(false)
        waitAlertDialog = waitAlertDialogBuilder.create()
    }

    fun showWithText(text: String){
        waitText.text = text
        waitAlertDialog.show()
    }

    fun changeTextTo(text: String){
        waitText.text = text
    }

    fun remove(){
        waitAlertDialog.dismiss()
    }
}