package com.wit.voguely.ui.main.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentCartBinding
import com.wit.voguely.ui.MainActivity
import com.wit.voguely.ui.main.MainFragment
import com.wit.voguely.ui.main.home.HomeAdapter


class CartFragment : Fragment() {



    private var activity : MainFragment? = null
    private lateinit var binding : FragmentCartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater,container, false)
        return binding.root

    }


}