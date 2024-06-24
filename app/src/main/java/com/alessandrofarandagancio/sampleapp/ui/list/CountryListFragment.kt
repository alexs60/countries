package com.alessandrofarandagancio.sampleapp.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.alessandrofarandagancio.sampleapp.databinding.FragmentCountriesListBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryListFragment : Fragment() {

    companion object {
        fun newInstance() = CountryListFragment()
    }

    private lateinit var binding: FragmentCountriesListBinding

    private val viewModel: CountryViewModel by activityViewModels<CountryViewModel> { produceViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountriesListBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            viewModel.countryListStateStateFlow.collectLatest {
                if (it.isLoading) {
                    binding.loader.visibility = View.VISIBLE
                } else {
                    binding.loader.visibility = View.GONE
                }

                if (it.countries.isEmpty()) {
                    binding.emptyList.root.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.emptyList.root.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }

                it.countries.also { list ->
                    if (binding.recyclerView.adapter == null) {
                        binding.recyclerView.adapter = CountryAdapter(list)
                    } else {
                        (binding.recyclerView.adapter as CountryAdapter).updateData(list)
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.countryListStateStateFlow.collectLatest {
                if (it.errors.isNotEmpty()) {
                    Snackbar.make(binding.root, it.errors.pop(), Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

}