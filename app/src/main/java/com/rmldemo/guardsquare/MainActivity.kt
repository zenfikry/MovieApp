package com.rmldemo.guardsquare

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rmldemo.guardsquare.api.ApiService
import com.rmldemo.guardsquare.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    lateinit var mainAdapter: MainAdapter

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        activityMainBinding = ActivityMainBinding.inflate( LayoutInflater.from(applicationContext) )
        setContentView(activityMainBinding.root)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(arrayListOf())

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }

    private fun getDataFromApi() {
        activityMainBinding.progressBar.visibility = View.VISIBLE

        ApiService.endpoint.getData()
            .enqueue(object : Callback<MainModel> {
                override fun onResponse(p0: Call<MainModel>, p1: Response<MainModel>) {
                    activityMainBinding.progressBar.visibility = View.GONE

                    if (p1.isSuccessful) {
                        showData(p1.body()!!)
                    }
                }

                override fun onFailure(p0: Call<MainModel>, p1: Throwable) {
                    activityMainBinding.progressBar.visibility = View.GONE
                    Log.d(TAG, p1.toString())
                }

            })
    }

    private fun showData(data: MainModel) {
        val results = data.result
        mainAdapter.setData(results)
    }
}
