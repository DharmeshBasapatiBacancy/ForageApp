package com.kudos.forageapp.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.kudos.forageapp.databinding.CustomAlertDialogBinding

object DialogUtils {

    fun Context.showCustomAlertDialog(
        layoutInflater: LayoutInflater, onPositiveButtonClick: () -> Unit,
        onNegativeButtonClick: () -> Unit
    ): AlertDialog {
        lateinit var alertDialog: AlertDialog
        val alertDialogBuilder = AlertDialog.Builder(this)
        val view = CustomAlertDialogBinding.inflate(layoutInflater)
        view.apply {
            positiveButtonTextView.setOnClickListener {
                alertDialog.dismiss()
                onPositiveButtonClick()
            }

            negativeButtonTextView.setOnClickListener {
                alertDialog.dismiss()
                onNegativeButtonClick()
            }
        }
        alertDialogBuilder.setView(view.root)
        alertDialog = alertDialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(alertDialog.window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        alertDialog.window!!.attributes = layoutParams

        return alertDialog
    }


}