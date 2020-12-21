package com.github.overlaytest.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.overlaytest.R
import com.github.overlaytest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = GroupListAdapter(this, this, viewModel)
        binding.groupList.layoutManager = LinearLayoutManager(this)
        binding.groupList.adapter = adapter
        viewModel.groupList.observe(this, { list -> adapter.submitList(list) })
        viewModel.refresh()
    }

}