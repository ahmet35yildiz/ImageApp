package com.amttech.imageapp.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.amttech.imageapp.R
import com.amttech.imageapp.databinding.FragmentListingBinding
import com.amttech.imageapp.ui.adapter.ImagesAdapter
import com.amttech.imageapp.ui.viewmodel.ListingFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListingFragment : Fragment(),SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentListingBinding
    private val viewModel: ListingFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container,false)
        (activity as? AppCompatActivity)?.findViewById<View>(R.id.bottomNav)!!.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarSearch)
        setHasOptionsMenu(true)
        binding.toolbarSearchTitle = getString(R.string.listingTitle)
        observe()
    }

    private fun observe() {
        viewModel.imagesList.observe(viewLifecycleOwner) {
            with(binding){
                if (it.isNullOrEmpty()) {
                    animationViewNoData.visibility = View.VISIBLE
                    animationViewArrow.visibility = View.GONE
                    imagesAdapter = null
                } else {
                    val adapter = ImagesAdapter(it)
                    imagesAdapter = adapter
                    animationViewNoData.visibility = View.GONE
                    animationViewArrow.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)

        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.showImages(query)
        hideKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}