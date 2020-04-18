package com.zuhaibahmad.netflixgriddemo.data

sealed class BrowseItem(val id: String) {
    class Banner(id: String, val title: String, val imageUrl: String) : BrowseItem(id)
    class Section(id: String, val category: String, val items: List<Thumbnail>) : BrowseItem(id)
    class Actions(id: String, val items: List<Icon>, val header: String = "Actions") : BrowseItem(id)
}

class Thumbnail(
    id: String,
    val label: String,
    var description: String?,
    var imageUrl: String?
) : BrowseItem(id)

class Icon(
    id: String,
    val label: String,
    var resId: Int
) : BrowseItem(id)
