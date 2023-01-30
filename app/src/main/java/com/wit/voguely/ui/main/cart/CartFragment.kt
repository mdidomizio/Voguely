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

    override fun onResume() {
        super.onResume()
       // viewModel.loadCartItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewCart?.adapter = adapter
        viewModel.loadCartItems()


        lifecycleScope.launchWhenResumed {

            viewModel.itemsInCart.collectLatest {
                adapter.dataCart = it
                adapter.notifyItemInserted(it.size)

                binding.priceAmount.text = "${viewModel.getTotalPrice(it)} EUR"
            }
        }



        lifecycleScope.launchWhenResumed {
            viewModel.displayEmptyCart.collectLatest {
                binding.cartIcon.isVisible = it
                binding.cartEmptyText.isVisible = it
                binding.priceAmount.isInvisible  = it
                binding.buyButtonCart.isInvisible = it
                binding.priceLabel.isInvisible = it
                binding.greyArea.isInvisible = it



            }
        }

        /*
        lifecycleScope.launchWhenResumed {
            viewModel.priceTotal.collectLatest {
                //TODO correct the value to display as total price
               // binding.priceAmount.text = "EUR ${viewModel.totalPrice}"
                binding.priceAmount.text = "EUR XXX"
            }
        }*/

        binding.buyButtonCart?.setOnClickListener {
            viewModel.buyItemsInCart()
        }
    }


}