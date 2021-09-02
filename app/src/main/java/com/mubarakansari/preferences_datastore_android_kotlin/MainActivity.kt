package com.mubarakansari.preferences_datastore_android_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.coroutineScope
import com.mubarakansari.preferences_datastore_android_kotlin.Repository.DataStoreRepository
import com.mubarakansari.preferences_datastore_android_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataStoreRepository: DataStoreRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataStoreRepository = DataStoreRepository(this)

        lifecycle.coroutineScope.launchWhenStarted {
            dataStoreRepository.getData().collect {
                binding.tvView.text = it.toString()

            }
        }

        binding.btnSave.setOnClickListener {
            CoroutineScope(IO).launch {
                dataStoreRepository.storeData(
                    binding.edtText.text.toString().trim()
                )
            }
        }
    }
}