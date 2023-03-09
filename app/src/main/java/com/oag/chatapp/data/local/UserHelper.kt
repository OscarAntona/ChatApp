package com.oag.chatapp.data.local

import com.oag.chatapp.R
import com.oag.chatapp.domain.model.Chat
import com.oag.chatapp.domain.model.User


object UserHelper {
    val usersList = listOf(
        User(
            id = "1",
            image = R.drawable.asset_antman,
            name = "Antman"
        ),
        User(
            id = "2",
            image = R.drawable.asset_hulk,
            name = "Hulk"
        ),
        User(
            id = "3",
            image = R.drawable.asset_thor,
            name = "Thor"
        ),
        User(
            id = "4",
            image = R.drawable.asset_wanda,
            name = "Wanda"
        )
    )
    val antmanChatList = listOf(
        Chat(
            id = "11",
            userImage = R.drawable.asset_hulk,
            userOne = "Antman",
            userTwo = "Hulk",
            userOneId = "1",
            userTwoId = "2"
        ),
        Chat(
            id = "22",
            userImage = R.drawable.asset_thor,
            userOne = "Antman",
            userTwo = "Thor",
            userOneId = "1",
            userTwoId = "3"
        ),
        Chat(
            id = "33",
            userImage = R.drawable.asset_wanda,
            userOne = "Antman",
            userTwo = "Wanda",
            userOneId = "1",
            userTwoId = "4"
        ),
    )

    val hulkChatList = listOf(
        Chat(
            id = "11",
            userImage = R.drawable.asset_antman,
            userOne = "Hulk",
            userTwo = "Antman",
            userOneId = "2",
            userTwoId = "1"
        ),
        Chat(
            id = "44",
            userImage = R.drawable.asset_thor,
            userOne = "Hulk",
            userTwo = "Thor",
            userOneId = "2",
            userTwoId = "3"
        ),
        Chat(
            id = "55",
            userImage = R.drawable.asset_wanda,
            userOne = "Hulk",
            userTwo = "Wanda",
            userOneId = "2",
            userTwoId = "4"
        ),
    )

    val thorChatList = listOf(
        Chat(
            id = "44",
            userImage = R.drawable.asset_hulk,
            userOne = "Thor",
            userTwo = "Hulk",
            userOneId = "3",
            userTwoId = "2"
        ),
        Chat(
            id = "22",
            userImage = R.drawable.asset_antman,
            userOne = "Thor",
            userTwo = "Antman",
            userOneId = "3",
            userTwoId = "1"
        ),
        Chat(
            id = "66",
            userImage = R.drawable.asset_wanda,
            userOne = "Thor",
            userTwo = "Wanda",
            userOneId = "3",
            userTwoId = "4"
        ),
    )

    val wandaChatList = listOf(
        Chat(
            id = "33",
            userImage = R.drawable.asset_antman,
            userOne = "Wanda",
            userTwo = "Antman",
            userOneId = "4",
            userTwoId = "1"
        ),
        Chat(
            id = "55",
            userImage = R.drawable.asset_hulk,
            userOne = "Wanda",
            userTwo = "Hulk",
            userOneId = "4",
            userTwoId = "2"
        ),
        Chat(
            id = "66",
            userImage = R.drawable.asset_thor,
            userOne = "Wanda",
            userTwo = "Thor",
            userOneId = "4",
            userTwoId = "3"
        ),
    )

}