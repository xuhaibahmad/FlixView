package com.zuhaibahmad.netflixgriddemo.utils

import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.data.BrowseItem
import com.zuhaibahmad.netflixgriddemo.data.Icon
import com.zuhaibahmad.netflixgriddemo.data.Thumbnail

object FakeDataFactory {

    fun getFakeData(): MutableList<BrowseItem> {
        val data = mutableListOf<BrowseItem>()
        val sections = (1..5).map {
            BrowseItem.Section(
                id = it.toString(),
                category = "Category # $it",
                items = fakeThumbnails.shuffled()
            )
        }
        val actions = BrowseItem.Actions(
            id = "actions",
            items = fakeIcons
        )
        data.add(fakeBanner)
        data.addAll(sections)
        data.add(actions)
        return data
    }

    private val fakeBanner
        get() = BrowseItem.Banner(
            id = "banner",
            title = "Daredevil",
            imageUrl = "https://serial.everyeye.it/public/immagini/30052015/daredevil-netflix-series-season-1-banner-poster.jpg"
        )

    private val fakeThumbnails
        get() = listOf(
            Thumbnail(
                id = "1",
                label = "Westworld",
                description = "Set at the intersection of the near future and the reimagined past, explore a world in which every human appetite can be indulged without consequence.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTRmYzNmOTctZjMwOS00ODZlLWJiZGQtNDg5NDY5NjE3MTczXkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "2",
                label = "The Mandalorian",
                description = "The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMWI0OTJlYTItNzMwZi00YzRiLWJhMjItMWRlMDNhZjNiMzJkXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "3",
                label = "The Invisible Man",
                description = "When Cecilia's abusive ex takes his own life and leaves her his fortune, she suspects his death was a hoax. As a series of coincidences turn lethal, Cecilia works to prove that she is being hunted by someone nobody can see.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BZjFhM2I4ZDYtZWMwNC00NTYzLWE3MDgtNjgxYmM3ZWMxYmVmXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "4",
                label = "Stranger Things",
                description = "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BZGExYjQzNTQtNGNhMi00YmY1LTlhY2MtMTRjODg3MjU4YTAyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "5",
                label = "Underwater",
                description = "A crew of oceanic researchers working for a deep sea drilling company try to get to safety after a mysterious earthquake devastates their deepwater research and drilling facility located at the bottom of the Mariana Trench.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BNzM0OGZiZWItYmZiNC00NDgzLTg1MjMtYjM4MWZhOGZhMDUwXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "6",
                label = "Star Wars: Episode IX - The Rise of Skywalker",
                description = "The surviving members of the resistance face the First Order once again, and the legendary conflict between the Jedi and the Sith reaches its peak bringing the Skywalker saga to its end.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMDljNTQ5ODItZmQwMy00M2ExLTljOTQtZTVjNGE2NTg0NGIxXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "7",
                label = "Star Trek: Picard",
                description = "Follow-up series to Star Trek: The Next Generation (1987) and Star Trek: Nemesis (2002) that centers on Jean-Luc Picard (Sir Patrick Stewart) in the next chapter of his life.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjAzYmQ4NTUtMGVjOS00OWRhLTlmYjktZDlkZTk2OGQ2YjE5XkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_UY268_CR3,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "8",
                label = "Black Mirror",
                description = "An anthology series exploring a twisted, high-tech multiverse where humanity's greatest innovations and darkest instincts collide.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BYTM3YWVhMDMtNjczMy00NGEyLWJhZDctYjNhMTRkNDE0ZTI1XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "9",
                label = "Bloodshot",
                description = "Ray Garrison, a slain soldier, is re-animated with superpowers.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BYjA5YjA2YjUtMGRlNi00ZTU4LThhZmMtNDc0OTg4ZWExZjI3XkEyXkFqcGdeQXVyNjUyNjI3NzU@._V1_UY268_CR16,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "10",
                label = "Rick and Morty",
                description = "An animated series that follows the exploits of a super scientist and his not-so-bright grandson.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjRiNDRhNGUtMzRkZi00MThlLTg0ZDMtNjc5YzFjYmFjMmM4XkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_UY268_CR2,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "11",
                label = "Dark",
                description = "A family saga with a supernatural twist, set in a German town, where the disappearance of two young children exposes the relationships among four families.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMmIyZjA3NGUtYjlhNS00ZDlkLWI0MDgtMDc2YWNjNGMwYWZhXkEyXkFqcGdeQXVyMzY0MTE3NzU@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "12",
                label = "Avengers: Endgame",
                description = "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "13",
                label = "The Flash",
                description = "After being struck by lightning, Barry Allen wakes up from his coma to discover he's been given the power of super speed, becoming the Flash, fighting crime in Central City.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BNTM4YThiMzktMDRlNi00NzAyLWI1YmQtNTdkMTNiN2Q0NzU1XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UY268_CR16,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "14",
                label = "Future Man",
                description = "Josh Futturman, a janitor by day and a gamer by night, is recruited by mysterious visitors to travel through time to prevent the extinction of humanity.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMWQ5MjE3NjMtOGZhOS00YmUxLWFhOTUtYjQ5YjUxOTZkNjBjXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "15",
                label = "Altered Carbon",
                description = "Set in a future where consciousness is digitized and stored, a prisoner returns to life in a new body and must solve a mind-bending murder to win his freedom.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BNjIxMWMzMzctYmZkYy00OTkzLWFlMWUtMjc3ZDFmYzQ3YmVkXkEyXkFqcGdeQXVyNjU2ODM5MjU@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "16",
                label = "The Handmaid's Tale",
                description = "Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTU0MTI0MDAyM15BMl5BanBnXkFtZTgwMDg5MzYyNTM@._V1_UY268_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "17",
                label = "Star Wars: The Clone Wars",
                description = "Jedi Knights lead the Grand Army of the Republic against the droid army of the Separatists.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BZWFlNzRmOTItZjY1Ni00ZjZkLTk5MDgtOGFhOTYzNWFhYzhmXkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "18",
                label = "Inception",
                description = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "19",
                label = "Arrow",
                description = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BMTI0NTMwMDgtYTMzZC00YmJhLTg4NzMtMTc1NjI4MWY4NmQ4XkEyXkFqcGdeQXVyNTY3MTYzOTA@._V1_UY268_CR16,0,182,268_AL_.jpg"
            ),

            Thumbnail(
                id = "20",
                label = "Doctor Who",
                description = "The further adventures in time and space of the alien adventurer known as the Doctor and their companions from planet Earth.",
                imageUrl = "https://m.media-amazon.com/images/M/MV5BZWJhYjFmZDEtNTVlYy00NGExLWJhZWItNTAxODY5YTc3MDFmXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UY268_CR4,0,182,268_AL_.jpg"
            )
        )

    private val fakeIcons
        get() = (1..10)
            .map {
                Icon(
                    id = it.toString(),
                    label = "Action # $it",
                    resId = if (it % 2 == 0) R.drawable.ic_movies else R.drawable.ic_music
                )
            }
            .toList()

}