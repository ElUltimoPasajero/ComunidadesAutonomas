package com.example.comunidadesautonomas.Activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comunidadesautonomas.databinding.ActivityMapViewBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.OverlayItem


class MapViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapViewBinding
    private lateinit var map: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(applicationContext, this.getPreferences(Context.MODE_PRIVATE))
        binding = ActivityMapViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

       map = binding.vMap
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        val mapController = map.controller
        mapController.setZoom(9.5)

        val name = intent.getStringExtra("nombre")
        val capital = intent.getStringExtra("capital")
        val latitude = intent.getDoubleExtra("latitud", 0.0)
        val longitude = intent.getDoubleExtra("longitud", 0.0)

        val items: ArrayList<OverlayItem> = ArrayList()

        items.add(OverlayItem("Andalucía", "Andalucía-Capital: Sevilla", "Habitantes: 8472407",GeoPoint(37.56640275933285, -4.7406737719892265)))
        items.add(OverlayItem("Aragón", "Aragón-Capital: Zaragoza", "Habitantes: 1326261" , GeoPoint(41.61162981125681, -0.9738034948937436)))
        items.add(OverlayItem("Asturias", "Asturias-Capital: Oviedo", "Habitantes: 1011792" , GeoPoint(43.45998093597627, -5.864665888274809)))
        items.add(OverlayItem("Baleares", "Baleares-Capital: Palma de Mallorca", "Habitantes: 1173008" , GeoPoint(39.57880491837696, 2.904506700284016)))
        items.add(OverlayItem("Canarias", "Canarias-Capital: Las Palmas de GC y SC de Tenerife", "Habitantes: 2172944" , GeoPoint(28.334567287944736, -15.913870062646897)))
        items.add(OverlayItem("Cantabria", "Cantabria-Capital: Santander", "Habitantes: 584507" , GeoPoint(43.36511077650701, -3.8398424912727958)))
        items.add(OverlayItem("Castilla y León", "Castilla y León-Capital: No tiene (Valladolid)", "Habitantes: 2383139" , GeoPoint(41.82966675375594, -4.841538702082391)))
        items.add(OverlayItem("Castilla La Mancha", "Castilla la Mancha-Capital: No tiene (Toledo)", "Habitantes: 2049562" , GeoPoint(39.42393852713387, -3.4784057150456764)))
        items.add(OverlayItem("Cataluña", "Cataluña-Capital: Barcelona", "Habitantes: 7763362" , GeoPoint(42.07542633707148, 1.5197485699265891)))
        items.add(OverlayItem("Ceuta", "Ceuta-Capital: Ceuta", "Habitantes: 83517" , GeoPoint(35.90091766842379, -5.309980167928874)))
        items.add(OverlayItem("Extremadura", "Extremadura-Capital: Mérida", "Habitantes: 1059501" , GeoPoint(39.05050233766541, -6.351254430283863)))
        items.add(OverlayItem("Galicia", "Galicia-Capital: Santiago de Compostela", "Habitantes: 2695645" , GeoPoint(42.789055617025404, -7.996440102093343)))
        items.add(OverlayItem("La Rioja", "La Rioja-Capital: Logroño", "Habitantes: 319796" , GeoPoint(42.568072855089895, -2.470916178908127)))
        items.add(OverlayItem("Madrid", "Madrid-Capital: Madrid", "Habitantes: 6751251" , GeoPoint(40.429642598652, -3.76167856716930)))
        items.add(OverlayItem("Melilla", "Melilla-Capital: Melilla", "Habitantes: 86261" , GeoPoint(35.34689811596408, -2.957162284523383)))
        items.add(OverlayItem("Murcia", "Murcia-Capital: Murcia", "Habitantes: 1518486" , GeoPoint(38.088904824462176, -1.4100155858243844)))
        items.add(OverlayItem("Navarra", "Navarra-Capital: Pamplona", "Habitantes: 661537" , GeoPoint(42.71764719490406, -1.657559057849277)))
        items.add(OverlayItem("País Vasco", "País Vasco-Capital: Vitoria", "Habitantes: 2213993" , GeoPoint(43.11260202399828, -2.594687915428055)))
        items.add(OverlayItem("Valencia", "Valencia-Capital: Valencia", "Habitantes: 5058138" , GeoPoint(39.515011403926145, -0.6939076854376838)))


        val mOverlay: ItemizedOverlayWithFocus<OverlayItem> =
            ItemizedOverlayWithFocus(
                items,
                object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
                    override fun onItemSingleTapUp(index: Int, item: OverlayItem?): Boolean {
                        return true
                    }

                    override fun onItemLongPress(index: Int, item: OverlayItem?): Boolean {
                        return false
                    }


                }, applicationContext
            )
        mOverlay.setFocusItemsOnTap(true)
        map.overlays.add(mOverlay)
        mapController.setCenter(GeoPoint(latitude, longitude))

    }
    public override fun onResume(){
        super.onResume()
        map.onResume()
    }

    public override fun onPause(){
        super.onPause()
        map.onPause()
    }
}

