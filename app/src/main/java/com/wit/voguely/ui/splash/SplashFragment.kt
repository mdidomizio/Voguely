package com.wit.voguely.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding : FragmentSplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logo.animate()
            .alpha(1.0F).setDuration(2000).setInterpolator(DecelerateInterpolator())
            .withEndAction{

                    val action = when (Firebase.auth.currentUser == null){
                        true -> R.id.action_splashFragment_to_loginSignupFragment
                        false -> R.id.action_splashFragment_to_mainFragment2
                    }
                    findNavController().navigate(action)
                }
                    .start()

    }

}