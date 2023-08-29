package com.sk.applicationmeals.ui.meals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sk.applicationmeals.data.MealsUIModel
import com.sk.applicationmeals.ui.category.CategoryAdapter
import com.sk.applicationmeals.databinding.ItemLayoutCategoryBinding
import com.sk.applicationmeals.databinding.ItemLayoutMealBinding

class MealsAdapter : RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {

    private val mealsItemList = ArrayList<MealsUIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val itemLayoutMealBinding = ItemLayoutMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealsViewHolder(itemLayoutMealBinding)
    }

    override fun getItemCount() = mealsItemList.size

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        val mealsUIModel = mealsItemList[position]
        holder.setData(mealsUIModel)
    }

    fun setItems(listOfMeals: List<MealsUIModel>) {
        mealsItemList.clear()
        mealsItemList.addAll(listOfMeals)
        notifyDataSetChanged()
    }

    class MealsViewHolder(private val itemLayoutMealBinding: ItemLayoutMealBinding) : RecyclerView.ViewHolder(itemLayoutMealBinding.root) {

        fun setData(mealsUIModel: MealsUIModel) {
            Glide.with(itemLayoutMealBinding.root.context).load(mealsUIModel.mealImageURL).centerCrop().into(itemLayoutMealBinding.ivMeal)
            itemLayoutMealBinding.tvMealName.text = mealsUIModel.mealName
        }

    }
}