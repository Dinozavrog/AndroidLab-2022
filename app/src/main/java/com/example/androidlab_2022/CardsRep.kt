package com.example.androidlab_2022

object CardsRep {
    var dataList: List<Models> = listOf()

    private val randomGeneralItems: List<Models> = listOf(
        Models.Card(
            "Wands are fire. They describe the volitional dimension.",
            "WANDS"
        ),
        Models.Card(
            "This lasso represents innocence.",
            "Jester"
        ),
        Models.Card(
            "Swords are air and the mental dimension.",
            "SWORDS"
        ),
        Models.Card(
            "Cups - the element of water, it corresponds to the emotional dimension",
            "BOWLS"
        )

    )
    private val randomAdvertisementItems: List<Models> = listOf(
        Models.Rekalama(
            "Reklama",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/RWS_Tarot_00_Fool.jpg/150px-RWS_Tarot_00_Fool.jpg"
        ),
        Models.Rekalama(
            "Reklama",
            "http://sun9-42.userapi.com/impg/c855436/v855436611/209705/uCm41PAuEp4.jpg?size=350x600&quality=96&sign=849716546be8c94b8fc922c18728895c&type=album"
        ),
        Models.Rekalama(
            "Reklama",
            "http://sun9-37.userapi.com/impg/YrMzoxo8bC90IrrBBLt3tzultcXw-KfBb_nwAw/CFeokCeKb-8.jpg?size=350x600&quality=96&sign=9afa492a30e39549442b2e532d0f8c99&type=album"
        )
    )

    fun generateList(size: Int) {
        val list = mutableListOf<Models>()
        var k = 0
        for (i in 0 until size) {
            if (i % 6 == 0) {
                k = 0
                val item =
                    randomAdvertisementItems[(randomAdvertisementItems.indices).random()] as Models.Rekalama
                val newItem = item.copy()
                list.add(newItem)
            } else {
                val item =
                    (randomGeneralItems[(randomGeneralItems.indices).random()] as Models.Card)
                val newItem = item.copy()
                newItem.title = k.toString() + " " + newItem.title
                list.add(newItem)
            }
            k++
        }
        dataList = list

    }

    fun addItem(position: Int, item: Models.Card) {
        val list = dataList.toMutableList()
        if (position >= dataList.size -1 ) {
            list.add(item)
        } else {
            list.add(position, item)
            for (i in position .. dataList.size) {
                if (list[i] is Models.Rekalama && i%6 !=0) {
                    list[i] = list[i-1].also {
                        list[i-1] = list[i]
                    }
                }
            }
        }
        dataList = list.toList()
    }

    fun deleteItem(position: Int) {
        val list = dataList.toMutableList()
        list.removeAt(position)
        for (i in position until dataList.size - 2) {
            if (list[i] is Models.Rekalama && i % 6 != 0) {
                list[i] = list[i + 1].also {
                    list[i + 1] = list[i]
                }
            }
            if (list[list.size - 1] is Models.Rekalama) {
                list.removeAt(list.size - 1)
            }
        }
        dataList = list.toList()
    }
}

