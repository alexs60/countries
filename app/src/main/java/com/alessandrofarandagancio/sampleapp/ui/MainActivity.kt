package com.alessandrofarandagancio.sampleapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.alessandrofarandagancio.sampleapp.R
import com.alessandrofarandagancio.sampleapp.databinding.ActivityMainBinding
import com.alessandrofarandagancio.sampleapp.ui.list.CountryListFragment
import com.alessandrofarandagancio.sampleapp.ui.list.CountryViewModel
import com.alessandrofarandagancio.sampleapp.ui.list.produceViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CountryViewModel by viewModels(factoryProducer = { produceViewModelFactory() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CountryListFragment.newInstance())
            .commitNow()

        binding.loadItem.setOnClickListener {
            viewModel.dispatch(CountryViewModel.Intent.GetCountry())
        }
    }
}