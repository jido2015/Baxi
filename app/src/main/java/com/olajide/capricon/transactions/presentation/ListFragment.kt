package com.olajide.capricon.transactions.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.olajide.capricon.base.NetworkResult
import com.olajide.capricon.base.collectLatestLifecycleFlow
import com.olajide.capricon.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment: Fragment() {
    private val viewModel: ListViewModel by activityViewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        //Attach adapter to RecyclerView
        binding.recycler.adapter = adapter

        viewModel.getTrnxLsst("1")
        handleTrnxResponse()
        return binding.root
    }


    private fun handleTrnxResponse() {
        collectLatestLifecycleFlow(viewModel.state) {
            when (it) {
                is NetworkResult.Loading -> {
                    Log.d("LoginResponse", "loading on getting access token")

                    binding.loading.visibility = View.VISIBLE
                }
                is NetworkResult.Failure -> {
                    Log.d("LoginResponse", "failure on getting access token")
                    binding.loading.visibility = View.GONE

                }
                is NetworkResult.Success -> {
                    binding.loading.visibility = View.GONE
                    adapter = ListAdapter(it.data!!.data.data)
                }

                is NetworkResult.Empty -> {
                    binding.loading.visibility = View.GONE

                }
            }

        }
    }
}