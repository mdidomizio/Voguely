package com.wit.voguely.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.wit.voguely.databinding.FragmentProductDetailsBinding
import com.wit.voguely.ui.main.home.AddToCartEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductDetailsFragment : Fragment() {

    companion object{
        const val PRODUCT_ID_ARG = "PRODUCT_ID_ARG"
    }

    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var viewModel : ProductDetailsViewModel

    private val adapter = ImagesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArguments()?.getString("id")?.let { viewModel.loadDetails(it) }

        lifecycleScope.launch {
            viewModel.event.collectLatest { event ->
                setEvent(event)
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.dataProduct.collectLatest {product->

                if (product != null){

                    binding.nameItemProductDetails.text = product.name
                    binding.priceProductDetails.text = "${product.currency} ${product.price.toString()}"
                    binding.descriptionProductDetails.text = product.description
                    binding.rateProductDetails.text = product.rating.toString()

                    adapter.data = listOf(product.image, product.image, product.image, product.image, product.image)
                    adapter.notifyDataSetChanged()

                   /* Glide.with(requireContext())
                        .load(product.image)
                        .into(binding.picDetailsProduct)*/

                }

            }

        }

        binding.productImageViewPager.adapter = adapter
        binding.productImageViewPager.setPageTransformer(ZoomOutPageTransformer())


        binding.addToCartButton.setOnClickListener{
            viewModel.addToCart()
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






