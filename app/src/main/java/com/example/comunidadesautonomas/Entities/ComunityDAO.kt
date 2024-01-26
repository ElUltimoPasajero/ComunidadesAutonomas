package com.example.comunidadesautonomas.Entities

import android.content.Context
import android.database.Cursor
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
                    c.getInt(1), c.getString(2),
                    c.getString(3), c.getInt(4), c.getDouble(5),
                    c.getDouble(6), c.getInt(7)
                )

                //CUIDADO! Si el id es autoincrementad el 0 se reserva para el id!!
                result.add(new)
            }


        } finally {
            c?.close()
        }
        return result

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

}
