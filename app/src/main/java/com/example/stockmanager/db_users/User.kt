package com.example.stockmanager.db_users

class User() {

    var id : Int = 0
    private var login = ""
    private var pwd : String = ""
    private var rights : Boolean = false

    constructor(i : Int, l: String, p: String, r : Boolean) : this() {
        id = i
        login = l
        pwd = p
        rights = r
    }

    override fun toString() : String {
        val string = StringBuilder()

        string.append("ID : " + id.toString() + "\n" +
            "Login : " + login + "\n" +
            "Password : " + pwd)

        return string.toString()
    }
}