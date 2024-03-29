package com.example.comunidadesautonomas.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.comunidadesautonomas.R
import com.google.android.material.textfield.TextInputLayout

class EditComunityActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_comunity_activity)

        //Sacamos el cajetin para introducir el nuevo texto
        val editname = findViewById<TextInputLayout>(R.id.txtbanderanuevo)

        //Sacamos la img para trabajar con ella
        val imgedit = findViewById<ImageView>(R.id.imgedit)

        //Del intent recibimos la imagen
        val img = intent.getIntExtra("img", 0)

        //Del intent recibimos el nombre de la comunidad seleccionada
        val comunityname = intent.getStringExtra("comunityname")
        //Lo usamos en editText como hint
        editname.hint = "Nuevo Nombre"

        //Se la ponemos a la imagen de la vista actual
        imgedit.setImageResource(img)

        val btncancel = findViewById<Button>(R.id.btncancelar)

        //Traemos el boton cambiar
        val btnchange = findViewById<Button>(R.id.btncambiar)

        //Listener para el boton que devuelve el nombre que hemos escrito de vuelta a la otra activity
        btnchange.setOnClickListener {
            val intent = Intent()

            //Pasamos a texto el cajetin y lo metemos a una variable
            val nombre = editname.editText?.text.toString()

            //La devolvemos de este modo
            intent.putExtra("nombre", nombre)

            //Devolvemos el resultado y cerramos la aplicacion
            setResult(RESULT_OK, intent)
            finish()
        }

        //Listener para el boton cancelar vuelva a la activity anterior
        btncancel.setOnClickListener {
            finish()
        }


    }
}