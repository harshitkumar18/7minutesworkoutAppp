package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarHistoryActivity)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "History"
        }
        binding?.toolbarHistoryActivity?.setNavigationOnClickListener{
            onBackPressed()
        }
        val dao = (application as WorkOutApp).db.historyDAO()
        getalldates(dao)

    }
    private fun getalldates(historyDao: HistroyDao){
        lifecycleScope.launch {
            historyDao.fetchalldates().collect(){
                    allcompletedlist ->
                if(allcompletedlist.isNotEmpty()){
                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.INVISIBLE
                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    val dates = ArrayList<String>()
                    for(date in allcompletedlist){
                        dates.add(date.date)
                    }

                    val historyadapter = HistoryAdapter(dates)

                    binding?.rvHistory?.adapter = historyadapter


                }
                else{
                    binding?.tvHistory?.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE

                }
            }
        }
    }

}