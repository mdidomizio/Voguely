package com.wit.voguely.ui.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentCartBinding
import com.wit.voguely.databinding.FragmentHomeBinding
import com.wit.voguely.ui.login.LoginEvent
import com.wit.voguely.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

        adapter.onItemClick = { productClicked(it) }

        adapter.onSeeMoreClicked = :: onSeeMoreClicked

        lifecycleScope.launch {
            viewModel.event.collectLatest { event ->
                setEvent(event)
            }
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
        popUpMenu.menuInflater.inflate(R.menu.pop_up_menu, popUpMenu.menu)
        popUpMenu.show()
        popUpMenu.setOnMenuItemClickListener {
            if(it.itemId == R.id.add_to_cart_popup){
                viewModel.addToCart(product)
                //Toast.makeText( this@HomeFragment.requireActivity(), "Your product has been successfully added to the cart", Toast.LENGTH_SHORT).show()
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun setEvent(event: AddToCartEvent) {
        when (event) {
            is AddToCartEvent.AddToCartFailed -> Toast.makeText(
                requireContext(),
                event.localizedMessage,
                Toast.LENGTH_SHORT
            ).show()

            is AddToCartEvent.AddToCartSuccessful -> Toast.makeText(
                requireContext(),
                event.cartMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }





}