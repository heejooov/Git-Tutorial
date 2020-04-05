package com.example.myapplication;

data class Bokji(
    var cate_top: String,
    var cate_mid: String,
    var cate_low: String,
    var serv_name: String,
    var serv_brief: String,
    var target: String,
    var contents: String,
    var url: String,
    var is_patriot: String
)

fun getdemoItem(): Bokji {
    return Bokji(
        "progress"
        , "progress"
        , "progress"
        , "progress"
        , "progress"
        , "progress"
        , "progress"
        , "progress"
        , "progress"
    )
}