package com.sk.applicationmeals.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sk.applicationmeals.R
import com.sk.applicationmeals.common.ItemDecorator
import com.sk.applicationmeals.databinding.FragmentCategoryListingBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class CategoryListingFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }


    private val categoryViewModel: CategoryViewModel by viewModels()

    private var fragmentCategoryListingBinding: FragmentCategoryListingBinding? = null
    private val categoryAdapter = CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        fragmentCategoryListingBinding = FragmentCategoryListingBinding.inflate(inflater, container, false)
        return fragmentCategoryListingBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentCategoryListingBinding?.apply {
            rvCategory.apply {
                layoutManager = LinearLayoutManager(context)
                if (itemDecorationCount == 0){
                    addItemDecoration(ItemDecorator(context.resources.getDimensionPixelSize(R.dimen.dimen_8dp)))
                }
                adapter = categoryAdapter.also {
                    it.setOnItemClick { position ->
                        categoryViewModel.onItemClick(position)
                    }
                }
            }
        }
        fetchCategories()
    }

    private fun fetchCategories() {
        categoryViewModel.fetchCategories()
    }

    private fun initObservers() {
        categoryViewModel.categoryUIState.observe(this) { categoryUIState ->
            when (categoryUIState) {
                is CategoryUIState.CategoryList -> {
                    fragmentCategoryListingBinding?.progressBar?.isVisible = false
                    categoryAdapter.setItems(categoryUIState.listOfCategories)
                }

                is CategoryUIState.CategoryListError -> {
                    fragmentCategoryListingBinding?.progressBar?.isVisible = false
                    showToast(categoryUIState.errorMessage)
                }
                is CategoryUIState.OnCategorySelected ->{
                    val bundleOf = bundleOf()
                    bundleOf.putString("Category Name", categoryUIState.categoryName)
                    findNavController().navigate(R.id.action_to_meals, bundleOf)
                }
                is CategoryUIState.ShowProgress ->{
                    fragmentCategoryListingBinding?.progressBar?.isVisible = true
                }
            }
        }
    }

    private fun showToast(errorMessage: String) {

    }

}