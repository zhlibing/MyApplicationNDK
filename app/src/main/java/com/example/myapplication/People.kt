package com.example.myapplication

class People {
    var name = "张三"
    var sex = "男"

    constructor(sex: String) {
        this.sex = sex
    }

    constructor()

    constructor(name: String, sex: String) {
        this.name = name
        this.sex = sex
    }

    fun setSexMethod(sex: String): String {
        this.sex = sex
        return name
    }
}
