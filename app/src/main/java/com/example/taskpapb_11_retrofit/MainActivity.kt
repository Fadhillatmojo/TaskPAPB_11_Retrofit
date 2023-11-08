package com.example.taskpapb_11_retrofit

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskpapb_11_retrofit.ItemLazdayAdapter.Lazday
import com.example.taskpapb_11_retrofit.ItemLazdayAdapter.LazdayItemAdapter
import com.example.taskpapb_11_retrofit.databinding.ActivityMainBinding
import com.example.taskpapb_11_retrofit.model.LazdayData
import com.example.taskpapb_11_retrofit.model.LazdayModel
import com.example.taskpapb_11_retrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterLazday: LazdayItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getLazday()
        val lazdayDataList = ArrayList<LazdayData>() // data yang mau dimasukkan

        response.enqueue(object : Callback<LazdayModel> {
            override fun onResponse(call: Call<LazdayModel>, response: Response<LazdayModel>) {
                val thisResponse = response.body()
                val data = thisResponse?.result ?: emptyList()
                // pengecekan member
                if (data.isNotEmpty()){
                    for (item in data){
                        val itemLazdayData = LazdayData(
                            item.id,
                            item.title,
                            item.image
                        )
                        lazdayDataList.add(item)
                    }
                    adapterLazday = LazdayItemAdapter(lazdayDataList) { lazday ->
                        Toast.makeText(this@MainActivity, "anjay", Toast.LENGTH_SHORT).show()
                    }
                    with(binding){
                        rvItemLazday.apply {
                            adapter = adapterLazday
                            layoutManager = LinearLayoutManager(this@MainActivity)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LazdayModel>, t: Throwable) {
                Log.d("error", "" + t.stackTraceToString())
            }

        })

    }
}