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
import com.olajide.capricon.transactions.data.model.DataX
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment: Fragment() {
    private val viewModel: ListViewModel by activityViewModels()


    private lateinit var adapter: ListAdapter
    private var transactions = mutableListOf<DataX>()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)


        adapter = ListAdapter(transactions)

        //Attach adapter to RecyclerView
        binding.recycler.adapter = adapter

        viewModel.getTrnxList("1")
        handleTrnxResponse()
        return binding.root
    }


    private fun handleTrnxResponse() {

        binding.loading.visibility = View.VISIBLE
        collectLatestLifecycleFlow(viewModel.state) { networkResult ->
            when (networkResult) {
                is NetworkResult.Loading -> {
                    Log.d("LoginResponse", "Loading.....")

                }
                is NetworkResult.Failure -> {
                    Log.d("LoginResponse", "failure on getting data")
                    binding.loading.visibility = View.GONE

                }
                is NetworkResult.Success -> {
                    Log.d("LoginResponse", networkResult.data!!.data.data.toString())
                    binding.loading.visibility = View.GONE
                    //transactions = it.data.data.data as ArrayList<DataX>

                    transactions.clear()
                    binding.loading.visibility = View.INVISIBLE
                    networkResult.data.let {
                        transactions.addAll(it.data.data)
                        adapter.notifyDataSetChanged()
                    }
                }

                is NetworkResult.Empty -> {
                    binding.loading.visibility = View.GONE

                }
            }

        }
    }
}