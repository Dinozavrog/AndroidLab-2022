package com.example.androidlab_2022

object SongRep {
    private var id = 0
    val songs = arrayListOf(
        Song(
            id++,
            "Awful Things",
            "Lil peep",
            130,
            R.raw.awful,
            "Depression",
            "https://tvmag.ru/upload/iblock/390/lilpeep_.jpg"
        ),
        Song(
            id++,
            "Benz Truck",
            "Lil peep",
            130,
            R.raw.benztruck,
            "Depression",
            "https://n1s1.hsmedia.ru/5f/26/91/5f269136fb2b6d2412e0d421bfd3372f/600x600_1_0cd285c4b631b85d70ae6d67a1b04f64@750x750_0xac120003_6754773361651364990.jpeg"
        ),
        Song(
            id++,
            "Yesterday",
            "Lil peep",
            130,
            R.raw.yesterday,
            "Depression",
            "https://cdn.ananasposter.ru/image/cache/catalog/poster/music/89/5199-1000x830.jpg"
        ),
        Song(
            id++,
            "Star Shopping",
            "Lil peep",
            130,
            R.raw.star,
            "Depression",
            "https://images.genius.com/9d70106ca856b0b3860a40979436ccf2.999x999x1.png"
        ),
        Song(
            id++,
            "Save That Shit",
            "Lil peep",
            130,
            R.raw.save,
            "Depression",
            "https://is5-ssl.mzstatic.com/image/thumb/Music124/v4/59/d3/fb/59d3fbd0-08d3-fa76-5d9f-bbcd7ed5cb61/5054526393868_1.jpg/1200x1200bf-60.jpg"
        ),
        Song(
            id++,
            "Falling Down",
            "Lil peep",
            130,
            R.raw.falling,
            "Depression",
            "https://lastfm.freetls.fastly.net/i/u/ar0/95e269c9d38f5139849d9e20807f4164.jpg"
        ),
        Song(
            id++,
            "Sportlight",
            "Lil peep",
            130,
            R.raw.sport,
            "Depression",
            "https://avatars.yandex.net/get-music-content/143117/e9c158c6.a.4966796-1/m1000x1000"
        )

    )

    fun findSongById(songId: Int): Song? {
        return if (songId in songs.indices)
            songs[songId]
        else
            null
    }
}