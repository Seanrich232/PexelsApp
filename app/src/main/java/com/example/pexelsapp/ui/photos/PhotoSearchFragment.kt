package com.example.pexelsapp.ui.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pexelsapp.R
import com.example.pexelsapp.databinding.FragmentPhotoSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.pexelsapp.utils.Resource
import com.example.pexelsapp.utils.extensions.toast

@AndroidEntryPoint
class PhotoSearchFragment : Fragment(), PhotoAdapter.PhotoItemListener {

    private var binding: FragmentPhotoSearchBinding by autoCleared()
    private val viewModel: PhotoViewModel by viewModels()
    private lateinit var adapter: PhotoAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = PhotoAdapter(this)
        binding.rvPhoto.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPhoto.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.photos.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    if (!resource.data.isNullOrEmpty()) adapter.setPhotos(ArrayList(resource.data))
                }
                Resource.Status.ERROR -> {
                    context?.toast(resource.message)
                }
                Resource.Status.LOADING ->
                    context?.toast(resource.message)

            }
        })
    }

    override fun onClickedPhoto(photoId: Int) {
        findNavController().navigate(
            R.id.action_photoSearchFragment_to_photoDetailFragment,
            bundleOf("id" to photoId)
        )
    }

}
