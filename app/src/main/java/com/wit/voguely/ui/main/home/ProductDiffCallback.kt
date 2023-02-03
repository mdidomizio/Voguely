package com.wit.voguely.ui.main.home

import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.database.annotations.Nullable


class ProductDiffCallback(
    private val mOldProductList: List<Product>,
    private val mNewProductList: List<Product>
    ) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return mOldProductList.size
    }

    override fun getNewListSize(): Int {
        return mNewProductList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldProductList[oldItemPosition].id == mNewProductList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return mOldProductList[oldItemPosition].name == mNewProductList[newItemPosition].name
    }

   /* @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }*/
}






