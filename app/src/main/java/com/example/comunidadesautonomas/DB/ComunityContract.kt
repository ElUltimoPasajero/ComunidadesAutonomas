package com.example.comunidadesautonomas.DB

import android.provider.BaseColumns

class ComunityContract {
    companion object {

        val DB_NAME = "Comunidades"
        val VERSION = 1

        class Insert : BaseColumns {

            companion object {

                const val TABLENAME = "comunities"
                const val COLUMN_ID = "id"
                const val COLUMN_FLAG="imagen"
                const val COLUMN_NAME = "name"
                const val COLUMN_CAPITAL = "capital"
                const val COLUMN_INHABITANTS = "habitantes"
                const val COLUMN_LATITUDE = "latitud"
                const val COLUMN_LONGITUDE = "longitud"
                const val COLUMN_ICON = "icono"
                const val COLUMN_URI ="uri"


            }

        }

    }
}