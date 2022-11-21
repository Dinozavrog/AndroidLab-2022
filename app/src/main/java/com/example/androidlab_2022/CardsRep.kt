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
            "https://cdn.optipic.io/site-539/upload/iblock/a18/a18dfbffbc0de3c06dfe247bf256df64.jpg"
        ),
        Models.Rekalama(
            "Reklama",
            "https://cdn.optipic.io/site-539/upload/iblock/990/9905b8fd7c7b61de1ccdb001e8796377.jpg"
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

