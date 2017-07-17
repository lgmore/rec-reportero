/**
 * Created by lg_more on 25/06/17
 */

package reportero.src.main.kotlin

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop

object Personal: Table() {
    val nrodoc = varchar("nrodoc", 15).primaryKey()
    val nombres = varchar("nombres", 40)
    val apellidos = varchar("apellidos", 40)
    val estcivil = varchar("estcivil", 1)
    val fechanac = date("fechanac")
    val telefpart = varchar("telefpart", 12)
    val sexo = varchar("sexo", 1)
    val codarea = varchar("codarea", 10)
    val direccion = varchar("direccion", 40)
    val nacionalidad = varchar("nacionalidad", 3)
    val tiporemuneracion = varchar("tiporemuneracion", 1)
    val estado = varchar("estado", 1)
    val fecingreso = datetime("fecingreso")
    val fecsalida = datetime("fecsalida")
    val codareaseg = varchar("codareaseg", 10)
    val control = varchar("control", 1)

    val cod_marcacion = integer("cod_marcacion")
    val rubro = varchar("rubro", 1)
}


fun main(args: Array<String>) {
    val connect = Database.connect("jdbc:postgresql://sbd2.rec.una.py:5432/rh_rec?currentSchema=rh", driver = "org.postgresql.Driver",user="lmore",password="info1042")

    transaction {
        for (personal in Personal.selectAll()) {
            //println("${personal[Personal.nrodoc]}: ${personal[Personal.apellidos]}, ${personal[Personal.nombres]}")
            println("${personal[Personal.nrodoc]}:")
        }
    }

        /*create (Cities, Users)

        val saintPetersburgId = Cities.insert {
            it[name] = "St. Petersburg"
        } get Cities.id

        val munichId = Cities.insert {
            it[name] = "Munich"
        } get Cities.id

        Cities.insert {
            it[name] = "Prague"
        }

        Users.insert {
            it[id] = "andrey"
            it[name] = "Andrey"
            it[cityId] = saintPetersburgId
        }

        Users.insert {
            it[id] = "sergey"
            it[name] = "Sergey"
            it[cityId] = munichId
        }

        Users.insert {
            it[id] = "eugene"
            it[name] = "Eugene"
            it[cityId] = munichId
        }

        Users.insert {
            it[id] = "alex"
            it[name] = "Alex"
            it[cityId] = null
        }

        Users.insert {
            it[id] = "smth"
            it[name] = "Something"
            it[cityId] = null
        }

        Users.update({ Users.id eq "alex"}) {
            it[name] = "Alexey"
        }

        Users.deleteWhere{ Users.name like "%thing"}

        println("All cities:")

        for (city in Cities.selectAll()) {
            println("${city[Cities.id]}: ${city[Cities.name]}")
        }

        println("Manual join:")
        (Users innerJoin Cities).slice(Users.name, Cities.name).
                select {(Users.id.eq("andrey") or Users.name.eq("Sergey")) and
                        Users.id.eq("sergey") and Users.cityId.eq(Cities.id)}.forEach {
            println("${it[Users.name]} lives in ${it[Cities.name]}")
        }

        println("Join with foreign key:")


        (Users innerJoin Cities).slice(Users.name, Users.cityId, Cities.name).
                select { Cities.name.eq("St. Petersburg") or Users.cityId.isNull()}.forEach {
            if (it[Users.cityId] != null) {
                println("${it[Users.name]} lives in ${it[Cities.name]}")
            }
            else {
                println("${it[Users.name]} lives nowhere")
            }
        }

        println("Functions and group by:")

        ((Cities innerJoin Users).slice(Cities.name, Users.id.count()).selectAll().groupBy(Cities.name)).forEach {
            val cityName = it[Cities.name]
            val userCount = it[Users.id.count()]

            if (userCount > 0) {
                println("$userCount user(s) live(s) in $cityName")
            } else {
                println("Nobody lives in $cityName")
            }
        }

        drop (Users, Cities)

    }*/
}