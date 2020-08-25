package com.example.pexelsapp.ui.photosdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pexelsapp.databinding.FragmentPhotoDetailBinding
import com.example.pexelsapp.utils.Resource
import com.example.pexelsapp.data.model.Photo
import com.example.pexelsapp.utils.autoCleared
import com.example.pexelsapp.utils.extensions.loadUrl
import com.example.pexelsapp.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment : Fragment() {

    private var binding: FragmentPhotoDetailBinding by autoCleared()
    private val viewModel: PhotoDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.photo.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    bindPhoto(resource.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.photoCl.visibility = View.VISIBLE

                }
                Resource.Status.ERROR -> {
                    context?.toast(resource.message)
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.photoCl.visibility = View.GONE
                }

            }
        })
    }

    private fun bindPhoto(photo: Photo) {
        binding.photographer.text = photo.photographer
        binding.liked.text = photo.liked.toString()
        binding.IvPhoto.loadUrl(photo.src?.original, 200, 200)
    }

}