package edu.karolinawidz.bestwishes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.karolinawidz.bestwishes.adapter.WishItemAdapter
import edu.karolinawidz.bestwishes.data.WishDatasource
import edu.karolinawidz.bestwishes.databinding.ActivityWishBinding

class WishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishBinding.inflate(layoutInflater)
        val data = WishDatasource().loadWishes()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = WishItemAdapter(this, data)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        setContentView(binding.root)
    }
}