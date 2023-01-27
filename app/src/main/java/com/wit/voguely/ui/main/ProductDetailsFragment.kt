package com.wit.voguely.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.FragmentProductDetailsBinding
import kotlinx.coroutines.flow.collectLatest

class ProductDetailsFragment : Fragment() {



    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var viewModel : ProductDetailsViewModel

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


        lifecycleScope.launchWhenResumed {
            viewModel.dataProduct.collectLatest {product->

                if (product != null){

                    binding.nameItemProductDetails.text = product.name
                    binding.priceProductDetails.text = "${product.currency} ${product.price.toString()}"
                    binding.descriptionProductDetails.text = product.description
                    binding.rateProductDetails.text = product.rating.toString()

                    Glide.with(requireContext())
                        .load(product.image)
                        .into(binding.picDetailsProduct)

                }

            }
        }

        binding.addToCartButton.setOnClickListener{
            viewModel.addToCart()
        }

    }


}



