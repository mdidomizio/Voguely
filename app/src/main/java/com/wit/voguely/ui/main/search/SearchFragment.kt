package com.wit.voguely.ui.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentCartBinding
import com.wit.voguely.databinding.FragmentSearchBinding
import com.wit.voguely.ui.main.MainFragment
import com.wit.voguely.ui.main.home.HomeAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    private var activity: MainFragment? = null
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    val adapter = HomeAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = activity
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewSearch?.adapter = adapter
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("url", it.urls )
            bundle.putString("itemName", it.itemName)
            bundle.putString("itemPrice", it.price)
            findNavController().navigate(R.id.action_searchFragment_to_cartFragment)
        }

        lifecycleScope.launchWhenResumed {
            viewModel.itemSearched.collectLatest {
                adapter.data = it
                adapter.notifyDataSetChanged()

            }
        }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {

                viewModel.searchItem(s ?: "")
            }

            override fun afterTextChanged(s: Editable?) {}

        })
        binding.recyclerviewSearch.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.displayNoResultFound.collectLatest {
                binding.warningIcon.isVisible = it
                binding.warningText.isVisible = it
            }
        }


    }


}






