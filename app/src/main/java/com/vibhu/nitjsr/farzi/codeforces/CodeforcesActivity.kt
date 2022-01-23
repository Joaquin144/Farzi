package com.vibhu.nitjsr.farzi.codeforces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vibhu.nitjsr.farzi.R
import com.vibhu.nitjsr.farzi.databinding.ActivityCodeforcesBinding
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

const val TAG = "#### CodeforcesActivity"
class CodeforcesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCodeforcesBinding
    private lateinit var contestAdapter: ContestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeforcesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        //todo : Call the API
        lifecycleScope.launchWhenCreated {
            binding.progressBar.visibility= View.VISIBLE
            val response = try{
                RetrofitInstance.api.getContests()
            }catch (e: IOException){
                binding.progressBar.visibility= View.GONE
                Log.e(TAG,"IO Exception occurred : "+e.printStackTrace())
                return@launchWhenCreated
            }catch (e: HttpException){
                binding.progressBar.visibility= View.GONE
                Log.e(TAG,"Http Exception occurred : "+e.printStackTrace())
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                contestAdapter.contests=response.body()!!
                Log.d(TAG,"Response got successfully")
            }else{
                Log.e(TAG,"Response not successful")
            }
            binding.progressBar.visibility=View.GONE
        }
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        contestAdapter=ContestAdapter()
        adapter=contestAdapter
        layoutManager=LinearLayoutManager(this@CodeforcesActivity)
    }
}