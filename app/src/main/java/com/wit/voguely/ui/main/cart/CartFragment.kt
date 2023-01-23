package com.wit.voguely.ui.main.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentCartBinding
import com.wit.voguely.ui.main.MainFragment
import com.wit.voguely.ui.main.home.HomeAdapter
import kotlinx.coroutines.flow.collectLatest


class CartFragment : Fragment() {

    val adapter = CartAdapter()
    val homeAdapter = HomeAdapter()
    private var activity : MainFragment? = null
    private lateinit var binding : FragmentCartBinding
    private lateinit var viewModel : CartViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = activity
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewCart?.adapter = adapter
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("url", it.urls)
            bundle.putString("Item name", it.itemName)
            bundle.putString("Item price", it.price)

            //TODO=> connect the search screen and the home screen to the cart, at the moment, with navigate, I can only have one connection
            findNavController().navigate(
              // R.id.action_searchFragment_to_cartFragment,
                R.id.action_homeFragment_to_cartFragment,
            bundle)
        }

        lifecycleScope.launchWhenResumed {
            viewModel.itemsInCart.collectLatest {
                adapter.dataCart = it
                adapter.notifyItemInserted(it.size)
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.displayEmptyCart.collectLatest {
                //TODO remove the hard code here with a function in viewmodel
                binding.cartIcon.isVisible = it
                binding.cartEmptyText.isVisible = it
             /*   binding.priceAmount.isVisible  = it
                binding.buyButtonCart.isVisible = it
                binding.priceLabel.isVisible = it
                binding.priceAmount.isVisible = it */
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.totalPrice.collectLatest {
                //TODO correct the value to display as total price
                binding.priceAmount.text = "EUR ${viewModel.totalPrice}"
            }
        }

        /*
        this part is in the adapter
        lifecycleScope.launchWhenResumed {
            viewModel.quantityOfSelectedItems.collectLatest {
                binding.itemQuantity.text = "x ${viewModel.quantityOfSelectedItems}"
            }
        }*/

        binding.buyButtonCart?.setOnClickListener {
            viewModel.buyItemsInCart()
        }
    }


}