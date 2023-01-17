package com.wit.voguely.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentCartBinding
import com.wit.voguely.databinding.FragmentHomeBinding
import com.wit.voguely.ui.main.MainFragment


class HomeFragment : Fragment() {

    private var activity : MainFragment? = null
    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }


}