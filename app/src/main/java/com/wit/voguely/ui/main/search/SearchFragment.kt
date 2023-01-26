package com.wit.voguely.ui.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentSearchBinding
import com.wit.voguely.ui.main.MainFragment
import com.wit.voguely.ui.main.home.HomeAdapter
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.flow.collectLatest

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

        adapter.onItemClick = { productClicked(it) }

        adapter.onSeeMoreClicked = :: onSeeMoreClicked

        /*adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("url", it.image)
            bundle.putString("itemName", it.name)
            bundle.putString("itemPrice", it.price.toString())
            findNavController().navigate(R.id.action_searchFragment_to_cartFragment)
        }*/

        lifecycleScope.launchWhenResumed {
            viewModel.itemSearched.collectLatest {

                adapter.data = it
                adapter.notifyDataSetChanged()

            }
        }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
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

    private fun productClicked(product: Product){
        val bundle = Bundle()
        bundle.putString("id", product.id )
        parentFragment
            ?.parentFragment
            ?.findNavController()
            ?.navigate(R.id.action_mainFragment2_to_productDetailsFragment, bundle)
    }

    private fun onSeeMoreClicked (product: Product, view: View){
        val popUpMenu = PopupMenu(requireContext(), view)
        val inflater = popUpMenu.menuInflater
        inflater.inflate(R.menu.pop_up_menu, popUpMenu.menu)
        popUpMenu.show()


    }
}
