package com.wit.voguely


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.wit.voguely.databinding.FragmentLoginSignupBinding


class LoginSignupFragment : Fragment() {

    private lateinit var binding: FragmentLoginSignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        binding.tabs.addOnTabSelectedListener(
            object : OnTabSelectedListener {
                override fun onTabSelected(selectedTab: TabLayout.Tab) {
                    val loginTab = binding.tabs.getTabAt(0)
                    if(loginTab != null){
                        if (loginTab.isSelected) {
                            binding.welcomeBackText.text = getString(R.string.SubtitleTextLogin)
                            binding.actionButton.text = getString(R.string.loginButton)
                        } else {
                            binding.welcomeBackText.text = getString(R.string.SubtitleTextSignUp)
                            binding.actionButton.text = getString(R.string.signUpButton)
                        }
                    }

                }


            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            }
        )


        }





}