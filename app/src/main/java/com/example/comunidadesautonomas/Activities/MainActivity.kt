package com.example.comunidadesautonomas.Activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comunidadesautonomas.RecyclerView.ComunityAdapter
import com.example.comunidadesautonomas.Entities.Comunity
import com.example.comunidadesautonomas.Entities.ComunityDAO
import com.example.comunidadesautonomas.ImageActivity
import com.example.comunidadesautonomas.R
import com.example.comunidadesautonomas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Tabla para rellenar con las comunidades
    private lateinit var comunityList: List<Comunity>

    //Tabla vacia para cuando limpiemos la lista en la opcion del menu
    private lateinit var emptyList: List<Comunity>

    //Declaracion de dao para cuando sea necesario
    lateinit var comunityDAO: ComunityDAO

    //Inicializamos el inicio de nuestro intent
    private lateinit var intentLaunch: ActivityResultLauncher<Intent>

    //Nuevo nombre de la comunidad
    private var newname = "Sin Nombre"

    //Variable en la que nos traemos la comunidad afectada por la pulsacion contextual
    lateinit var com: Comunity

    private lateinit var comunityAdapter : ComunityAdapter



    /**
     * OnCreate de la actividad principal, aqui va el codigo relacionado con la main activity
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }

        })
        //Inicializa el DAO
        comunityDAO = ComunityDAO()

        //Cargamos la lista con las comunidades
        comunityList = comunityDAO.loadList(this)

        comunityAdapter= ComunityAdapter(comunityList){
            comunity -> onItemSelected(comunity)
        }

        binding.rvComunities.layoutManager = LinearLayoutManager(this)
        binding.rvComunities.adapter = ComunityAdapter(comunityList) { comunity ->
            onItemSelected(comunity)
        }

//Realizacion del IntentLaunch
        intentLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {

            //Segun el resultado, si esta OK
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {

                //Nos traemos la devolucion de los datos a una variable
                newname = result.data?.extras?.getString("nombre").toString()

                //Metemos en la variable auxiliar com, el nombre nuevo de la comunidad que queremos ponerle
                com.name = newname

                //Tambien podrias haber enviado el indice de la comunidad afectada, asi podrias haberlo
                //Recibido aqui saltandote este paso
                //Sacamos en que indice esta la comunidad afectada gracias a la variable com
                val index = comunityList.indexOf(com)

                //Y a esa comunidad le cambiamos el nombre en la base de DATOS
                comunityDAO.uploadComunity(this, com, index + 1)

                //Recargamos la lista de la base de datos
                comunityList = comunityDAO.loadList(this)

                //indicamos al adaptador que se ha modificado el objeto para que muestre el cambio
                binding.rvComunities.adapter!!.notifyItemChanged(index)


            }
        }


    }



    /**
     * Metodo OnCreate del menu de opciones desplegable
     *Inflas y poner el titulo
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        setTitle("Comunidades autónomas")
        return true
    }




    /**
     * Se especifica que hace cada opcion del menu
     */


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Limpiar -> {
                cleanList()
                true
            }

            R.id.Recargar -> {
                reload()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    /**
     * Metodo para que el toast muestre un texto
     */
    fun onItemSelected(comunity: Comunity) {
        Toast.makeText(
            this,
            "Soy de  ${comunity.name}",
            Toast.LENGTH_SHORT
        ).show()

        lateinit var mapIntent: Intent
        mapIntent = Intent(this, MapViewActivity::class.java)
        mapIntent.putExtra("nombre", comunity.name)
        mapIntent.putExtra("capital", comunity.capital)
        mapIntent.putExtra("latitud", comunity.latitude)
        mapIntent.putExtra("longitud", comunity.longitude)
        startActivity(mapIntent)
    }


    /**
     * Metodo para limpiar la lista, le pasa la lista vacia
     */

    private fun cleanList() {
        emptyList = comunityDAO.loadEmpty(this)
        binding.rvComunities.adapter = ComunityAdapter(emptyList) { comunity ->
            onItemSelected(comunity)
        }

    }


    /**
     * Metodo para recargar la lista de comunidades
     */

    private fun reload() {
        //Recargamos la lista de la base de datos
        comunityList = comunityDAO.loadList(this)

        //Se la asignamos actualizada al adaptador
        binding.rvComunities.adapter = ComunityAdapter(comunityList) { comunity ->
            onItemSelected(comunity)
        }


    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        //Creamos una variable para traer la comunidad que tenemos afectada
        lateinit var comunityAfected: Comunity

        //Una variable para la vista que vamos a lanzar
        lateinit var miIntent: Intent

        //Nos traemos la lista de comunidades agrupadas por el ID
        comunityAfected = comunityList[item.groupId]

        //Switch para distinguir la opcion del menu contextual pulsada
        when (item.itemId) {

            //Si el boton que pulsamos es el de Id 0
            0 -> {

                //Creamos un dialogo de alerta, con un titulo,un mensaje y dos botones Cerrar y Aceptar
                val alert =
                    AlertDialog.Builder(this).setTitle("Eliminar ${comunityAfected.name}")
                        .setMessage("¿Estas seguro de que quieres eliminar ${comunityAfected.name}")
                        .setNeutralButton("Cerrar", null).setPositiveButton("Aceptar") { _, _ ->

                            //Llamamos al metodo del snackbar para que muestre el mensajito abajo si pulsamos aceptar
                            sneacker("Se ha eliminado ${comunityAfected.name}")

                            //-------REALIZACION DE ELIMINAR CON BASE DE DATOS--------

                            //MUY IMPORTANTE, a la hora de eliminar, trata de hacerlo con un campo que tenga el objeto,
                            //Si el objeto no tiene un id NO intentes hacerlo con el itemId porque da error
                            //Eliminamos la comunidad pulsada a traves del id del item

                            comunityList=comunityList.minus(comunityAfected)
                            comunityAdapter.updateList(comunityList)

                            comunityDAO.deleteComunity(this, comunityAfected.name)

                            //Recargamos la lista de la base de datos
                            comunityList = comunityDAO.loadList(this)


                            //Se la asignamos actualizada al adaptador
                            binding.rvComunities.adapter =
                                ComunityAdapter(comunityList) { comunity ->
                                    onItemSelected(comunity)
                                }

                            //indicamos al adaptador que se ha eliminado el objeto, para que no lo muestre
                           // binding.rvComunities.adapter!!.notifyItemRemoved(item.groupId)
                            //Indicamos al adaptador que la lista tiene un objeto menos
                           // binding.rvComunities.adapter!!.notifyItemRangeChanged(
                              //  item.groupId,
                              //  comunityList.size
                           // )

                            //Despues de la llave llamamos al metodo create para que se cree la alerta
                        }.create()

                //Enseñamos la alerta por pantalla con este metodo
                alert.show()
            }

            1 -> {

                //Nos llevamos la comunidad afectada a una variable externa
                com = comunityAfected

                //Le pasamos la lista en la que vamos a trabajar ya que anteriormente tiene otra asignada
                binding.rvComunities.adapter = ComunityAdapter(comunityList) { comunity ->
                    onItemSelected(comunity)
                }

                //Creamos el intent, con los dos valores que lanzamos a la otra aplicacion
                val intent = Intent(this, EditComunityActivity::class.java)
                //Si llegamos a enviar la posicion de la comunidad afectada nos habria sido util en la otra parte
                intent.putExtra("nombre", newname)
                intent.putExtra("img", comunityAfected.flag)
                intent.putExtra("comunityname", comunityAfected.name)

                //Lanzamos el intent para crear una nueva activity
                intentLaunch.launch(intent)

            }

            2 ->{
                val intent = Intent(this, PhotoActivity::class.java)
                intent.putExtra("id",comunityAfected.id)
                this.startActivity(intent)

            }

            3->{
                val intent = Intent(this,ImageActivity::class.java)
                intent.putExtra("id", comunityAfected.id)
                this.startActivity(intent)
            }
            else -> return super.onContextItemSelected(item)


        }
        return true



    }


    private fun sneacker(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }


}