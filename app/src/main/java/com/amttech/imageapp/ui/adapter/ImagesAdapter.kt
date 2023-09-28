package com.amttech.imageapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.amttech.imageapp.R
import com.amttech.imageapp.data.entity.Images
import com.amttech.imageapp.databinding.CardDesignSearchBinding
import com.amttech.imageapp.ui.fragment.ListingFragmentDirections
import com.squareup.picasso.Picasso

class ImagesAdapter(private var imagesList: List<Images>) :
    RecyclerView.Adapter<ImagesAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(binding: CardDesignSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: CardDesignSearchBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardDesignSearchBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.card_design_search, parent, false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val image = imagesList[position]

        with(holder.binding){
            imageObject = image

            Picasso.get().load(image.previewURL).into(imageViewRv)
            Log.e("image",image.previewURL)

            cardViewImage.setOnClickListener {
                image.id.let { id ->
                    val transition = ListingFragmentDirections.detailTransition(image = id.toString())
                    Navigation.findNavController(it).navigate(transition)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}