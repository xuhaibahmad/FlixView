package com.zuhaibahmad.netflixgriddemo

import com.zuhaibahmad.flixview.data.Category
import com.zuhaibahmad.flixview.data.Content

object FakeDataFactory {

    fun getCategorizedContent(): List<Category> {
        return (1..5).map {
            Category(
                id = it.toString(),
                name = "Category # $it",
                items = fakeThumbnails.shuffled()
            )
        }
    }

    private val fakeThumbnails
        get() = listOf(
            Content(
                id = "1",
                label = "Westworld",
                description = "Set at the intersection of the near future and the reimagined past, explore a world in which every human appetite can be indulged without consequence.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTRmYzNmOTctZjMwOS00ODZlLWJiZGQtNDg5NDY5NjE3MTczXkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
                featuredImageUrl = "https://cdn.wallpapersafari.com/72/49/UkSfoJ.png"
            ),

            Content(
                id = "2",
                label = "Stranger Things",
                description = "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BZGExYjQzNTQtNGNhMi00YmY1LTlhY2MtMTRjODg3MjU4YTAyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
                featuredImageUrl = "https://i.redd.it/u5tmm8aks9n41.jpg"
            ),

            Content(
                id = "3",
                label = "Black Mirror",
                description = "An anthology series exploring a twisted, high-tech multiverse where humanity's greatest innovations and darkest instincts collide.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BYTM3YWVhMDMtNjczMy00NGEyLWJhZDctYjNhMTRkNDE0ZTI1XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
                featuredImageUrl = "https://bloody-disgusting.com/wp-content/uploads/2017/08/black-mirror--e1543849244707.jpg"
            ),

            Content(
                id = "4",
                label = "Rick and Morty",
                description = "An animated series that follows the exploits of a super scientist and his not-so-bright grandson.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjRiNDRhNGUtMzRkZi00MThlLTg0ZDMtNjc5YzFjYmFjMmM4XkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_UY268_CR2,0,182,268_AL_.jpg",
                featuredImageUrl = "https://images.hdqwalls.com/download/rick-and-morty-uy-2560x1080.jpg"
            ),

            Content(
                id = "5",
                label = "Dark",
                description = "A family saga with a supernatural twist, set in a German town, where the disappearance of two young children exposes the relationships among four families.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMmIyZjA3NGUtYjlhNS00ZDlkLWI0MDgtMDc2YWNjNGMwYWZhXkEyXkFqcGdeQXVyMzY0MTE3NzU@._V1_UX182_CR0,0,182,268_AL_.jpg",
                featuredImageUrl = "https://honknews.com/wp-content/uploads/2020/04/Dark-Season-3-Release-Date-Cast-Plot-and-other-detail-1200x675.jpg"
            ),

            Content(
                id = "6",
                label = "Avengers: Endgame",
                description = "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_UX182_CR0,0,182,268_AL_.jpg",
                featuredImageUrl = "https://i.redd.it/j1erfulvvvs21.jpg"
            ),

            Content(
                id = "7",
                label = "Altered Carbon",
                description = "Set in a future where consciousness is digitized and stored, a prisoner returns to life in a new body and must solve a mind-bending murder to win his freedom.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BNjIxMWMzMzctYmZkYy00OTkzLWFlMWUtMjc3ZDFmYzQ3YmVkXkEyXkFqcGdeQXVyNjU2ODM5MjU@._V1_UX182_CR0,0,182,268_AL_.jpg",
                featuredImageUrl = "https://television.mxdwn.com/wp-content/uploads/2020/01/Altered-Carbon_-Season-2-_-Cast-Announcement-HD-_-Netflix-0-26-screenshot.png"
            ),

            Content(
                id = "8",
                label = "Inception",
                description = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg",
                featuredImageUrl = "https://d13ezvd6yrslxm.cloudfront.net/wp/wp-content/images/zz2aedc0c7.jpg"
            ),

            Content(
                id = "9",
                label = "Doctor Who",
                description = "The further adventures in time and space of the alien adventurer known as the Doctor and their companions from planet Earth.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BZWJhYjFmZDEtNTVlYy00NGExLWJhZWItNTAxODY5YTc3MDFmXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UY268_CR4,0,182,268_AL_.jpg",
                featuredImageUrl = "https://a-static.besthdwallpaper.com/the-doctor-of-dr-who-ke-inspired-by-the-antifa-feminism-guy-wallpaper-5120x2160-11859_16.jpg"
            )
        )
}