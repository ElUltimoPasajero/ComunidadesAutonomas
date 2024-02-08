package com.example.comunidadesautonomas.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.comunidadesautonomas.Entities.Comunity
import com.example.comunidadesautonomas.R

class DBOpenHelper private constructor(context: Context?) :
    SQLiteOpenHelper(context, ComunityContract.DB_NAME, null, ComunityContract.VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        try {

            //Crea la tabla principal
            val consultation = ("CREATE TABLE ${ComunityContract.Companion.Insert.TABLENAME}"
                    + "(${ComunityContract.Companion.Insert.COLUMN_ID} INTEGER NOT NULL"
                    + ",${ComunityContract.Companion.Insert.COLUMN_FLAG} INTEGER NOT NULL"
                    + ",${ComunityContract.Companion.Insert.COLUMN_NAME} NVARCHAR (50) NOT NULL"
                    + ",${ComunityContract.Companion.Insert.COLUMN_CAPITAL} NVARCHAR (100)"
                    + ",${ComunityContract.Companion.Insert.COLUMN_INHABITANTS} INTEGER NOT NULL"
                    + ",${ComunityContract.Companion.Insert.COLUMN_LATITUDE} REAL NOT NULL"
                    + ",${ComunityContract.Companion.Insert.COLUMN_LONGITUDE} REAL NOT NULL"
                    + ",${ComunityContract.Companion.Insert.COLUMN_ICON} INTEGER NOT NULL"
                    + ",${ComunityContract.Companion.Insert.COLUMN_URI} NVARCHAR (100) NOT NULL);")


            sqLiteDatabase?.execSQL(consultation)

            initializeDB(sqLiteDatabase, ComunityContract.Companion.Insert.TABLENAME)


        } catch (e: Exception) {

            e.printStackTrace()
        }
    }


    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, p1: Int, p2: Int) {

        sqLiteDatabase!!.execSQL("DROP TABLE IF EXISTS ${ComunityContract.Companion.Insert.TABLENAME};")

    }


    fun initializeDB(db: SQLiteDatabase, tablename: String) {

        try {
            val list = loadComunities()

            for (c in list) {
                db.execSQL(
                    "INSERT INTO $tablename( " +
                            "${ComunityContract.Companion.Insert.COLUMN_ID}," +
                            "${ComunityContract.Companion.Insert.COLUMN_FLAG}," +
                            "${ComunityContract.Companion.Insert.COLUMN_NAME}," +
                            "${ComunityContract.Companion.Insert.COLUMN_CAPITAL}," +
                            "${ComunityContract.Companion.Insert.COLUMN_INHABITANTS}," +
                            "${ComunityContract.Companion.Insert.COLUMN_LATITUDE}," +
                            "${ComunityContract.Companion.Insert.COLUMN_LONGITUDE}," +
                            "${ComunityContract.Companion.Insert.COLUMN_ICON}," +
                            "${ComunityContract.Companion.Insert.COLUMN_URI})" +
                            " VALUES (${c.id},${c.flag},'${c.name}','${c.capital}',${c.inhabitants},${c.latitude},${c.longitude},${c.icon},'${c.uri}');")



            }

        } catch (e: Exception) {

            e.printStackTrace()

        }

    }

    fun loadComunities(): MutableList<Comunity> {

        return mutableListOf(
            Comunity(
                1,
                R.drawable.andalucia,
                "Andalucía",
                "Sevilla",
                8472407,
                37.56640275933285,
                -4.7406737719892265,
                R.drawable.andalucia_icon,
                ""
            ),
            Comunity(
                2,
                R.drawable.asturias,
                "Asturias",
                "Oviedo",
                1011792,
                43.45998093597627,
                -5.864665888274809,
                R.drawable.asturias_icon,
                ""
            ),
            Comunity(
                3,
                R.drawable.baleares,
                "Baleares",
                "Palma de Mallorca",
                1173008,
                39.57880491837696,
                2.904506700284016,
                R.drawable.baleares_icon,
                ""
            ),
            Comunity(
                4,
                R.drawable.canarias,
                "Canarias",
                "Las Palmas de GC y SC de Tenerife",
                2172944,
                28.334567287944736,
                -15.913870062646897,
                R.drawable.canarias_icon,
                ""
            ),
            Comunity(
                5,
                R.drawable.cantabria,
                "Cantabria",
                "Santander",
                584507,
                43.36511077650701,
                -3.8398424912727958,
                R.drawable.cantabria_icon,
                ""
            ),
            Comunity(
                6,
                R.drawable.castillaleon,
                "Castilla León",
                "No tiene (Valladolid)",
                2383139,
                41.82966675375594,
                -4.841538702082391,
                R.drawable.castillaleon_icon,
                ""
            ),
            Comunity(
                7,
                R.drawable.castillamancha,
                "Castilla la Mancha",
                "No tiene (Toledo)",
                2049562,
                39.42393852713387,
                -3.4784057150456764,
                R.drawable.castillamancha_icon,
                ""
            ),
            Comunity(
                8,
                R.drawable.catalunya,
                "Cataluña",
                "Barcelona",
                7763362,
                42.07542633707148,
                1.5197485699265891,
                R.drawable.catalunya_icon,
                ""
            ),
            Comunity(
                9,
                R.drawable.ceuta,
                "Ceuta",
                "Ceuta",
                83517,
                35.90091766842379,
                -5.309980167928874,
                R.drawable.ceuta_icon,
                ""
            ),
            Comunity(
                10,
                R.drawable.extremadura,
                "Extremadura",
                "Mérida",
                1059501,
                9.05050233766541,
                -6.351254430283863,
                R.drawable.extremadura_icon,
                ""
            ),
            Comunity(
                11,
                R.drawable.galicia,
                "Galícia",
                "Santiago de Compostela",
                2695645,
                42.789055617025404,
                -7.996440102093343,
                R.drawable.galicia_icon,
                ""
            ),
            Comunity(
                12,
                R.drawable.larioja,
                "La Rioja",
                "Logroño",
                319796,
                42.568072855089895,
                -2.470916178908127,
                R.drawable.larioja_icon,
                ""
            ),
            Comunity(
                13,
                R.drawable.madrid,
                "Madrid",
                "Madrid",
                6751251,
                40.429642598652,
                -3.76167856716930,
                R.drawable.madrid_icon,
                ""
            ),
            Comunity(
                14,
                R.drawable.melilla,
                "Melilla",
                "Melilla",
                86261,
                35.34689811596408,
                -2.957162284523383,
                R.drawable.melilla_icon,
                ""
            ),
            Comunity(
                15,
                R.drawable.murcia,
                "Murcia",
                "Murcia",
                1518486,
                38.088904824462176,
                -1.4100155858243844,
                R.drawable.murcia_icon,
                ""
            ),
            Comunity(
                16,
                R.drawable.navarra,
                "Navarra",
                "Pamplona",
                661537,
                42.71764719490406,
                -1.657559057849277,
                R.drawable.navarra_icon,
                ""
            ),
            Comunity(
                17,
                R.drawable.paisvasco,
                "País Vasco",
                "Vitoria",
                2213993,
                43.11260202399828,
                -2.594687915428055,
                R.drawable.paisvasco_icon,
                ""
            ),
            Comunity(
                18,
                R.drawable.valencia,
                "Valencia",
                "Valencia",
                5058138,
                39.515011403926145,
                -0.6939076854376838,
                R.drawable.valencia_icon,
                ""

            )


        )

    }


    companion object {

        private var dbOpen: DBOpenHelper? = null
        fun getInstance(context: Context?): DBOpenHelper? {
            if (dbOpen == null) dbOpen = DBOpenHelper(context)
            return dbOpen


        }

    }


}