package com.example.comunidadesautonomas.Entities

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.widget.SimpleCursorAdapter.CursorToStringConverter
import com.example.comunidadesautonomas.DB.ComunityContract
import com.example.comunidadesautonomas.DB.DBOpenHelper

class ComunityDAO {

    fun loadList(context: Context?): MutableList<Comunity> {

        lateinit var result: MutableList<Comunity>
        lateinit var c: Cursor

        try {
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase

            val sql = "SELECT * FROM comunities"

            c = db.rawQuery(sql, null)

            result = mutableListOf()

            while (c.moveToNext()) {

                val new = Comunity(
                    c.getInt(0), c.getInt(1), c.getString(2),
                    c.getString(3), c.getInt(4), c.getDouble(5),
                    c.getDouble(6), c.getInt(7), c.getString(8)
                )

                //CUIDADO! Si el id es autoincrementad el 0 se reserva para el id!!
                result.add(new)
            }


        } finally {
            c?.close()
        }
        return result

    }

    fun getComunity(context: Context?, id: Int): Comunity {

        lateinit var res: Comunity
        lateinit var c: Cursor

        try {
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase

            val columnas = arrayOf(
                ComunityContract.Companion.Insert.COLUMN_ID,
                ComunityContract.Companion.Insert.COLUMN_FLAG,
                ComunityContract.Companion.Insert.COLUMN_NAME,
                ComunityContract.Companion.Insert.COLUMN_CAPITAL,
                ComunityContract.Companion.Insert.COLUMN_INHABITANTS,
                ComunityContract.Companion.Insert.COLUMN_LATITUDE,
                ComunityContract.Companion.Insert.COLUMN_LONGITUDE,
                ComunityContract.Companion.Insert.COLUMN_ICON,
                ComunityContract.Companion.Insert.COLUMN_URI,
            )
            val identificador = id.toString()
            val valores = arrayOf(identificador)
            c = db.query(
                ComunityContract.Companion.Insert.TABLENAME,
                columnas, "id=?", valores, null, null, null
            )
            // Leer resultados del cursor e insertarlos en la lista
            while (c.moveToNext()) {
                res = Comunity(
                    c.getInt(0), c.getInt(1), c.getString(2),
                    c.getString(3), c.getInt(4), c.getDouble(5),
                    c.getDouble(6), c.getInt(7), c.getString(8)
                )
            }
        } finally {
            c.close()
        }
        return res
    }


    fun uploadComunity(context: Context?, c: Comunity, index: Int) {

        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        db.execSQL(
            "UPDATE COMUNITIES " +
                    "SET name='${c.name}', imagen=${c.flag} " +
                    "WHERE ID=$index;"
        )

    }


    fun deleteComunity(context: Context?, name: String) {

        val db = DBOpenHelper.getInstance(context)!!.writableDatabase

        db.execSQL("DELETE FROM comunities WHERE name='$name';")


    }


    /**
     *Añade una comunidad a la base de datos
     */


    fun addComunity(context: Context?, com: Comunity) {

        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        db.execSQL(
            "INSERT INTO COMUNITIES (" +

                    "${ComunityContract.Companion.Insert.COLUMN_FLAG}, " +
                    "${ComunityContract.Companion.Insert.COLUMN_NAME} ) VALUES ( ${com.flag} , '${com.name}' );"
        )


    }


    fun loadEmpty(context: Context?): MutableList<Comunity> {
        var result = mutableListOf<Comunity>()

        return result
    }


    fun uploadBBDD(context: Context?, com: Comunity) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase

        val values = ContentValues()
        values.put(ComunityContract.Companion.Insert.COLUMN_ID,com.id)
        values.put(ComunityContract.Companion.Insert.COLUMN_FLAG,com.flag)
        values.put(ComunityContract.Companion.Insert.COLUMN_NAME,com.name)
        values.put(ComunityContract.Companion.Insert.COLUMN_CAPITAL,com.capital)
        values.put(ComunityContract.Companion.Insert.COLUMN_INHABITANTS,com.inhabitants)
        values.put(ComunityContract.Companion.Insert.COLUMN_LATITUDE,com.latitude)
        values.put(ComunityContract.Companion.Insert.COLUMN_LONGITUDE,com.longitude)
        values.put(ComunityContract.Companion.Insert.COLUMN_ICON,com.icon)
        values.put(ComunityContract.Companion.Insert.COLUMN_URI,com.uri)
        db.update(ComunityContract.Companion.Insert.TABLENAME,values,"id=?",arrayOf(com.id.toString()))
        db.close()
    }

}
