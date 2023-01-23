package edu.karolinawidz.bestwishes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import edu.karolinawidz.bestwishes.adapter.ItemAdapter
import edu.karolinawidz.bestwishes.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataset = Datasource().loadWishes()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this,dataset)
        recyclerView.setHasFixedSize(true)

    }
}