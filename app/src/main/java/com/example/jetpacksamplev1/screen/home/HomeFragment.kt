package com.example.jetpacksamplev1.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.jetpacksamplev1.Screen
import com.example.jetpacksamplev1.navigate
import com.example.jetpacksamplev1.screen.navgraph.MainNavGraph
import com.example.jetpacksamplev1.ui.theme.JetPackSampleV1Theme
import com.example.jetpacksamplev1.util.Utility
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // return super.onCreateView(inflater, container, savedInstanceState)
        return ComposeView(requireContext()).apply {
            setContent {
                JetPackSampleV1Theme {
                    ProvideWindowInsets{
                        MainNavGraph(
                            /*onLoginFragment = {
                                val action =HomeFragmentDirections.goToLoginFragment()
                                findNavController().navigate(action)
                            },
                            onIngredientSearch = {
                                *//*val action =
                                    HomeFragmentDirections.goToSearchScreen(query, SearchType.QUERY)*//*
                                val action =HomeFragmentDirections.goToLoginFragment()
                                findNavController().navigate(action)
                            },*/
                            onExplore = {
                                Utility.clearAllPre()
                                navigate(Screen.Login)
                               // findNavController().navigate(HomeFragmentDirections.goToLoginFragment())
                               // findNavController().popBackStack()
                            },
                            onBack={
                                activity?.onBackPressedDispatcher?.onBackPressed()
                            }
                        )
                    }
                }}}
    }
}