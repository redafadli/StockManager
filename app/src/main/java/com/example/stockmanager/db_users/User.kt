package com.example.stockmanager.db

class User() {

    var id : Int = 0
    var login = "null"
    var pwd : String = "null"

    constructor(i : Int, l: String, p: String) : this() {
        id = i
        login = l
        pwd = p
    }

    override fun toString() : String {
        val string = StringBuilder()

        string.append("ID : " + id.toString() +
            "\n" +
            "Login : " + login + "\n" +
            "Password : " + pwd)

        return string.toString()
    }
}