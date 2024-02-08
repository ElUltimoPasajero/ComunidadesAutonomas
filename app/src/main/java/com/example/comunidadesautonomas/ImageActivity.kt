package com.example.comunidadesautonomas

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import coil.load
import com.example.comunidadesautonomas.Entities.ComunityDAO
import com.example.comunidadesautonomas.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.extras!!.getInt("id")
        Log.d("Integer",id.toString() )
        val miDAO = ComunityDAO()
        val comunity = miDAO.getComunity(this, id)
        if (comunity.uri.isNotEmpty()) {
            val uri = Uri.parse(comunity.uri)
            binding.wholeImage.load(uri)
        } else {
            Toast.makeText(this,"${comunity.name} no tiene una foto asociada", Toast.LENGTH_SHORT).show()
        }
    }
    }
