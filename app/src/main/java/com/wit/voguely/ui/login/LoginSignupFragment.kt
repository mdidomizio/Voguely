package com.wit.voguely.ui.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentLoginSignupBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LoginSignupFragment : Fragment() {

    private lateinit var binding: FragmentLoginSignupBinding
    private lateinit var viewModel : LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel:: class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{
            viewModel.selectedTab.collectLatest { selectedTab ->
                setSelectedTabText(selectedTab)

            }
        }


        binding.tabs.addOnTabSelectedListener(
            object : OnTabSelectedListener {
                override fun onTabSelected(selectedTab: TabLayout.Tab) {
                    val isLoginTableSelected = binding.tabs.getTabAt(0)?.isSelected?: false
                    viewModel.onSelectedTab(if (isLoginTableSelected) SelectedTab.LOGIN else SelectedTab.SIGN_UP)


                }


            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            })


        }


    private fun setSelectedTabText (selectedTab: SelectedTab){

        binding.welcomeBackText.setText(selectedTab.welcomeMessage)
        binding.actionButton.setText(selectedTab.buttonText)

    }

}

/* if (selectedTab == SelectedTab.LOGIN) {
                binding.welcomeBackText.text = getString(R.string.SubtitleTextLogin)
                binding.actionButton.text = getString(R.string.loginButton)
            } else {
                binding.welcomeBackText.text = getString(R.string.SubtitleTextSignUp)
                binding.actionButton.text = getString(R.string.signUpButton)
            } */