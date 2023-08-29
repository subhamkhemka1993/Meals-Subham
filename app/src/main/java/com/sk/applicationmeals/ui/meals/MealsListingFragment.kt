package com.sk.applicationmeals.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sk.applicationmeals.R
import com.sk.applicationmeals.common.ItemDecorator
import com.sk.applicationmeals.databinding.FragmentCategoryListingBinding
import com.sk.applicationmeals.databinding.FragmentMealsListingBinding
import com.sk.applicationmeals.ui.category.CategoryAdapter
import com.sk.applicationmeals.ui.category.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [MealsListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MealsListingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    private val mealsViewModel: MealsViewModel by viewModels()

    private var binding: FragmentMealsListingBinding? = null
    private val mealsAdapter = MealsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealsListingBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            rvMeals.apply {
                layoutManager = GridLayoutManager(context, 2)
                if (itemDecorationCount == 0){
                    addItemDecoration(ItemDecorator(context.resources.getDimensionPixelSize(R.dimen.dimen_8dp)))
                }
                adapter = mealsAdapter
            }
        }
        initObserver()
        mealsViewModel.fetchMeals(arguments)
    }


    private fun initObserver() {
        mealsViewModel.mealsUIState.observe(viewLifecycleOwner) { mealsUIState ->
            when (mealsUIState) {
                is MealsUIState.MealsList -> {
                    mealsAdapter.setItems(mealsUIState.listOfMeals)
                }

                is MealsUIState.MealsListError -> {}
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MealsListingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MealsListingFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}