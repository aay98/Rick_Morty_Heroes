package com.example.rickandmortyheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyheroes.databinding.ActivityMainBinding
import com.example.rickandmortyheroes.model.MainFactory
import com.example.rickandmortyheroes.model.MyViewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    lateinit var viewModel: MyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)



        viewModel = ViewModelProvider(this, MainFactory(application))
            .get(MyViewModel::class.java)

        val ERROR_IN_NAME: String = resources.getText(R.string.no_name).toString()
        viewModel.nameLiveData.observe(this ){
            if (viewModel.nameLiveData.value == null)
                Toast.makeText(applicationContext, ERROR_IN_NAME, Toast.LENGTH_SHORT).show()
            else binding!!.recyclerView.adapter =
                CustomRecyclerAdapter(viewModel.nameLiveData.value!!)
        }
        binding!!.upd.setOnClickListener{
            viewModel.getData()
        }

    }
}