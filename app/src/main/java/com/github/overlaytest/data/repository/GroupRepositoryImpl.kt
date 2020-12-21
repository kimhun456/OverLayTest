package com.github.overlaytest.data.repository

import com.github.overlaytest.domain.enitity.Group
import com.github.overlaytest.domain.repository.GroupRepository
import kotlin.random.Random

class GroupRepositoryImpl : GroupRepository {

    override fun getGroups(): List<Group> = getDummyGroups()

    override fun requestGroups(): List<Group> = getDummyGroups()

    private fun getDummyGroups(): List<Group> {
        val random = Random(System.currentTimeMillis())
        // range 10 ~ 19
        val number = random.nextInt(10, 20)
        return groupSequence.take(number).toList()
    }

    private val groupSequence = sequence<Group> {
        yieldAll(generateSequence {
            val random = Random.nextInt(1000)
            Group(name = "group-$random")
        })
    }
}