package com.wit.voguely.ui.main.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wit.voguely.databinding.FragmentCartBinding
import com.wit.voguely.ui.main.MainFragment
import com.wit.voguely.ui.main.home.AddToCartEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CartFragment : Fragment() {

    val adapter = CartAdapter(::onCancelClick)

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
       viewModel.loadCartItems()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewCart?.adapter = adapter
        viewModel.loadCartItems()

        lifecycleScope.launch {
            viewModel.event.collectLatest { event ->
                setEvent(event)
            }
        }

        lifecycleScope.launchWhenResumed {

            viewModel.itemsInCart.collectLatest {
                adapter.dataCart = it
                adapter.notifyDataSetChanged()

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
                //binding.checkMarkOrder.isInvisible = it
            }
        }
/*
        lifecycleScope.launchWhenResumed {
            viewModel.displayFullCart.collectLatest {

                binding.cartIcon.isInvisible = it
                binding.cartEmptyText.isInvisible = it
                binding.priceAmount.isVisible  = it
                binding.buyButtonCart.isVisible = it
                binding.priceLabel.isVisible = it
                binding.greyArea.isVisible = it
                binding.checkMarkOrder.isInvisible = it
            }
        }

       lifecycleScope.launchWhenResumed {
            viewModel.displayOrderSuccessful.collectLatest {

                binding.cartIcon.isInvisible = it
                binding.cartEmptyText.isInvisible = it
                binding.priceAmount.isInvisible  = it
                binding.buyButtonCart.isInvisible = it
                binding.priceLabel.isInvisible = it
                binding.greyArea.isInvisible = it
                binding.checkMarkOrder.isVisible = it
            }
        }*/

        binding.buyButtonCart?.setOnClickListener {
            viewModel.buyItemsInCart()
            //Toast.makeText( this@CartFragment.requireActivity(), "Your order is successful, you will receive it soon ", Toast.LENGTH_SHORT).show()
        }
    }
    private fun onCancelClick(item: CartItem){
        viewModel.deleteItemFromCart(item.product.id)
    }

    private fun setEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.OrderFailed -> Toast.makeText(
                requireContext(),
                event.localizedMessage,
                Toast.LENGTH_SHORT
            ).show()

            is OrderEvent.OrderConfirmed -> Toast.makeText(
                requireContext(),
                event.orderMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}