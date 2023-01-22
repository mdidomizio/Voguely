package com.wit.voguely.ui.main.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentCartBinding
import com.wit.voguely.ui.main.MainFragment
import kotlinx.coroutines.flow.collectLatest


class CartFragment : Fragment() {

    val adapter = CartAdapter()
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
            findNavController().navigate(
               // R.id.action_searchFragment_to_cartFragment,
                R.id.action_homeFragment_to_cartFragment,
            bundle)
        }
        lifecycleScope.launchWhenResumed {
            viewModel.displayEmptyCart.collectLatest {
                binding.cartIcon.isInvisible = it
                binding.cartEmptyText.isInvisible = it
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.totalPrice.collectLatest {
               //TODO:set the total price to display=>
            binding.priceAmount.text = ""
            }
        }

        /*
        this part is in the adapter
        lifecycleScope.launchWhenResumed {
            viewModel.quantityOfSelectedItems.collectLatest {
                //TODO:set the quantity of items to buy=> binding.itemQuantity.text = ""
            }
        }*/

        binding.buyButtonCart?.setOnClickListener {
            viewModel.buyItemsInCart()
        }
    }


}