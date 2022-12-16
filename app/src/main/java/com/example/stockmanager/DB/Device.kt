package com.example.stockmanager.DB

class Device() {

    var type : String = ""
    var marque_et_modele : String = ""
    var num_ref : String = ""
    var site_web : String = ""
    var qr_code : String = ""

    constructor(t : String, marque : String, num : String, site : String, qr : String):this(){
        type = t
        marque_et_modele = marque
        num_ref = num
        site_web = site
        qr_code = qr
    }

    override fun toString(): String {
        return super.toString()
    }
}