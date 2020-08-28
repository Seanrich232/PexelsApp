package com.example.pexelsapp.ui.photos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pexelsapp.utils.autoCleared
import com.example.pexelsapp.R
import com.example.pexelsapp.databinding.FragmentPhotoSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.pexelsapp.utils.Resource
import com.example.pexelsapp.utils.extensions.showView
import com.example.pexelsapp.utils.extensions.toast
import kotlinx.android.synthetic.main.toolbar_snippet.*
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class PhotoSearchFragment : Fragment(), PhotoAdapter.PhotoItemListener {

    private var binding: FragmentPhotoSearchBinding by autoCleared()
    private val viewModel: PhotoViewModel by viewModels()
    private lateinit var adapter: PhotoAdapter
    var query: CharSequence? = ""

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            // NO  OPERATION
        }

        override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // NO  OPERATION
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val isBackSpace = query?.length ?: -1 >= s?.length ?: -1
            query = s.toString()
            iv_Clear?.showView(true)

            s?.let {
                if (it.isNotEmpty() && !isBackSpace) {
                    Timber.i(it.toString())
                    viewModel.fetchPhoto("${it.toString()}")
                }
            }
        }
    } //end textWatcher


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentPhotoSearchBinding.inflate(inflater, container, false).apply {
        RecyclerView.LayoutManager = LinearLayoutManager(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
        activity?.et_toolbarSearch?.addTextChangedListener(textWatcher)

//        iv_Clear.setOnClickListener{
//
//            et_toolbarSearch.text?.clear()
//            iv_Clear.showView(false)
//        }
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
                    binding.progressBar.visibility = View.GONE
                    if (!resource.data.isNullOrEmpty()) adapter.setPhotos(ArrayList(resource.data))
                }
                Resource.Status.ERROR -> {
                    context?.toast(resource.message)
                }
                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
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
