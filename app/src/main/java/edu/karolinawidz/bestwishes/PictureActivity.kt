package edu.karolinawidz.bestwishes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.karolinawidz.bestwishes.adapter.ItemAdapter
import edu.karolinawidz.bestwishes.data.Datasource
import edu.karolinawidz.bestwishes.databinding.ActivityPictureBinding

class PictureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPictureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataset = Datasource().loadWishes()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = ItemAdapter(this, dataset)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true)
        binding.nextButton.setOnClickListener {
            val intent = Intent(this, WishActivity::class.java)
            this.startActivity(intent)
        }

    }
}