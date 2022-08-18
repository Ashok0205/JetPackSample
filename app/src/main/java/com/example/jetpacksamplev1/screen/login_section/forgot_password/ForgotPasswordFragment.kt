package com.example.jetpacksamplev1.screen.login_section.forgot_password

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.Screen
import com.example.jetpacksamplev1.databinding.FragmentForgotPasswordBinding
import com.example.jetpacksamplev1.navigate
import com.example.jetpacksamplev1.network.WebResponse
import com.example.jetpacksamplev1.screen.login_section.login.LoginViewModel
import com.example.jetpacksamplev1.util.LogUtils
import com.example.jetpacksamplev1.util.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
   /* companion object {
        fun newInstance() = ForgotPasswordFragment()
    }*/

    private val TAG: String = "RegistrationFragment"
    private var _binding:FragmentForgotPasswordBinding?=null
    private val binding get() = _binding!!

  //  private lateinit var viewModel: ForgotPasswordViewModel
    private val viewModel: ForgotPasswordViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// here is the fix
    //    val inflater = requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _binding=FragmentForgotPasswordBinding.inflate(inflater,container,false)
        val root: View = binding.root
      //  return inflater.inflate(R.layout.fragment_forgot_password, container, false)
        //viewModel= ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
        binding.dataModel = viewModel.request.value

        binding.btnSubmit.setOnClickListener {
            if (isValid()) {
               // viewModel.callForgotPasswordApi()

                Utility.showToast("calling btn")
               //  navigate(Screen.Login)
                requireActivity().onBackPressed()
            }
        }


        viewModel.serverResponse.observe(viewLifecycleOwner, Observer {response->
            when(response) {
                is WebResponse.Success -> {
                    navigate(Screen.Login)
                }
                is WebResponse.Error->{
                    LogUtils.showToast(response.errorMessage.toString())
                }
                is WebResponse.Loading -> TODO()
            }
        })
        return  root
    }


    fun isValid():Boolean
    {
        if (viewModel.request.value!!.email.isNullOrEmpty())
        {
            Utility.showToast("Please enter mail")
            return  false
        }

        return  true
    }

}