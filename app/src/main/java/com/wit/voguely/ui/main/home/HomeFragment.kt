package com.wit.voguely.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentCartBinding
import com.wit.voguely.databinding.FragmentHomeBinding
import com.wit.voguely.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {

    val adapter = HomeAdapter()

    private var activity: MainFragment? = null
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = activity
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview?.adapter = adapter

        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("url", it.image )
            bundle.putString("itemName", it.name)
            bundle.putString("itemPrice", it.price.toString())
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        lifecycleScope.launchWhenResumed {
            viewModel.dataProduct.collectLatest {
                adapter.data = it
                adapter.notifyDataSetChanged()

            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.displayProgressBar.collectLatest {

                binding.progressBar.isVisible = it
            }

        }
    }


}