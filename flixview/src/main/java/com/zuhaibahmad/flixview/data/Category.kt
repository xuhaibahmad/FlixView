package com.zuhaibahmad.flixview.data

class Category(val id: String, val name: String, val items: List<Content>)

class Content(
    val id: String,
    val label: String,
    var description: String?,
    var imageUrl: String?,
    var featuredImageUrl: String?
)