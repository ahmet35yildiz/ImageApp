package com.amttech.imageapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.amttech.imageapp.R
import com.amttech.imageapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private lateinit var binding:FragmentFavoriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite,container,false)
        (activity as? AppCompatActivity)?.findViewById<View>(R.id.bottomNav)!!.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarFavoriteTitle = getString(R.string.favoritesTitle)

        //observe()
    }
}