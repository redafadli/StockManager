package com.example.stockmanager.db_devices

class Device() {

    var id : Int = 0
    var type = ""
    var marque_modele : String = ""
    var num_ref : String = ""
    var site_web : String = ""
    var qr_code : String = ""

    constructor(i : Int, t: String, m: String, s: String, q: String, r : String) : this() {
        id = i
        type = t
        marque_modele = m
        num_ref = r
        site_web = s
        qr_code = q
    }

    override fun toString() : String {
        val string = StringBuilder()

        string.append("ID : " + id.toString() + "\n" +
            "Type : " + type + "\n" +
            "Marque et model : " + marque_modele + "\n" +
            "N° réf : " + marque_modele + "\n" +
            "Site web : " + site_web + "\n" +
            "Qr code : " + qr_code)

        return string.toString()
    }
}