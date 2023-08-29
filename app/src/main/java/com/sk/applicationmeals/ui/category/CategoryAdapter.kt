package com.sk.applicationmeals.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sk.applicationmeals.data.CategoryUIModel
import com.sk.applicationmeals.databinding.ItemLayoutCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryItemList = ArrayList<CategoryUIModel>()

    private var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemLayoutCategoryBinding = ItemLayoutCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemLayoutCategoryBinding)
    }

    override fun getItemCount() = categoryItemList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryUIModel = categoryItemList[position]
        holder.setData(categoryUIModel)
    }

    inner class CategoryViewHolder(private val itemLayoutCategoryBinding: ItemLayoutCategoryBinding)
        : RecyclerView.ViewHolder(itemLayoutCategoryBinding.root) {

        init {
            itemLayoutCategoryBinding.root.setOnClickListener{
                onItemClick?.invoke(adapterPosition)
            }
        }

        fun setData(categoryUIModel: CategoryUIModel) {
            Glide.with(itemLayoutCategoryBinding.root.context).load(categoryUIModel.categoryImageURL).centerCrop().into(itemLayoutCategoryBinding.ivCategory)
            itemLayoutCategoryBinding.tvCategoryName.text = categoryUIModel.categoryName
            itemLayoutCategoryBinding.tvCategoryDesc.text = categoryUIModel.categoryDescription
        }

    }

    fun setItems(categoryList: List<CategoryUIModel>) {
        categoryItemList.clear()
        categoryItemList.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setOnItemClick(onClick: (Int) -> Unit) {
        onItemClick = onClick
    }
}