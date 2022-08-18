package com.example.jetpacksamplev1.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import com.example.jetpacksamplev1.MyApplication
import com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.network.utils.Success
import com.example.jetpacksamplev1.screen.login_section.login.LoginViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object Utility {

    fun isOnline(): Boolean {
        val connectivityManager =
            MyApplication.INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    fun showToast(msg:String?){
    // MyApplication.INSTANCE.getString(R.string.email)
        Toast.makeText(MyApplication.INSTANCE,msg, Toast.LENGTH_SHORT).show()
    }
    fun showToast(value:Int){
        Toast.makeText(MyApplication.INSTANCE,MyApplication.INSTANCE.getString(value), Toast.LENGTH_SHORT).show()
    }
    fun showValidation(value:Int){
        Toast.makeText(MyApplication.INSTANCE,MyApplication.INSTANCE.getString(value), Toast.LENGTH_SHORT).show()
    }
    fun showValidation(msg:String?):String{
        Toast.makeText(MyApplication.INSTANCE,msg, Toast.LENGTH_SHORT).show()
        return msg.toString()
    }
    fun showAlert( message:String?){
        val alertDialog = android.app.AlertDialog.Builder(MyApplication.INSTANCE)
        alertDialog.setTitle("Message")
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener(){
                dialog: DialogInterface?, which: Int ->
            dialog?.cancel()
        })
        alertDialog.create()
        alertDialog.show()

    }

    /***Error dailgo****/

     fun showAlertResponse( msg: String?) {
        MaterialAlertDialogBuilder(MyApplication.INSTANCE, R.style.AlertDialogTheme)
            .setTitle("Error")
            .setMessage(msg)
            .setCancelable(false)
            .setPositiveButton(
                "Ok"
            ) { dialogInterface, i ->

                //logoutCode(true)

//                FService().removeLocationUpdates()
//                (context as MainActivity).refresh()
                // findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())//navController.navigate(R.id.navigation_login)
            }
            .setNeutralButton(
              "Cancel"
            ) { dialogInterface, i -> }
            .show()
    }

    fun hideKeyboard(activity: FragmentActivity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.

        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun clearAllPre()
    {
        MyApplication.INSTANCE.preferencesHelper.clearAll()
    }
}