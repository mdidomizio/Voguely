package com.wit.voguely.ui.main.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentCartBinding
import com.wit.voguely.databinding.FragmentSearchBinding
import com.wit.voguely.ui.main.MainFragment


class SearchFragment : Fragment() {

    private var activity : MainFragment? = null
    private lateinit var binding : FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container, false)
        return binding.root
    }


}