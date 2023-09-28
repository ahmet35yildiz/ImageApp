package com.amttech.imageapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amttech.imageapp.R
import com.amttech.imageapp.databinding.FragmentDetailBinding
import com.amttech.imageapp.ui.viewmodel.DetailFragmentViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        (activity as? AppCompatActivity)?.findViewById<View>(R.id.bottomNav)!!.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: DetailFragmentArgs by navArgs()
        binding.toolbarDetailTitle = getString(R.string.detailTitle)

        viewModel.imageAllDetails.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.imageDetailObject = it[0]
                Picasso.get().load(it[0].previewURL).into(binding.imageViewDetail)
            }
        }
        bundle.image.let {
            viewModel.showImageDetail(it)
        }
    }
}