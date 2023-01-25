package com.wit.voguely.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentMainBinding
import com.wit.voguely.ui.MainViewModel
import com.wit.voguely.ui.main.cart.CartFragment
import com.wit.voguely.ui.main.home.HomeFragment
import com.wit.voguely.ui.main.search.SearchFragment


class MainFragment : Fragment() {


    lateinit var binding : FragmentMainBinding
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel:: class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentNavigation) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNav.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController)

        signOutToolbar()

    }

    private fun signOutToolbar(){
        binding.toolbar.inflateMenu(R.menu.toolbar_menu)
        binding.toolbar.menu.findItem(R.id.signOut).setOnMenuItemClickListener {
            viewModel.signOut()
            findNavController().navigate(R.id.action_mainFragment2_to_loginSignupFragment2)
            return@setOnMenuItemClickListener false
        }
    }


}