package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkoutapp.databinding.ActivityFinishBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    private var binding:ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity )
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener{
            onBackPressed()
        }
        binding?.btnFinish?.setOnClickListener {
            finish()
        }
        val dao = (application as WorkOutApp).db.historyDAO()
        addDatetoDatabase(dao)
    }
    private fun addDatetoDatabase(histroyDao: HistroyDao){

        val c = Calendar.getInstance()
        val datetime = c.time

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())

        val date = sdf.format(datetime)
        lifecycleScope.launch {
            histroyDao.insert(HistroyEntity(date))
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}