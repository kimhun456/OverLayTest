package com.github.overlaytest.data.repository

import com.github.overlaytest.domain.enitity.Member
import com.github.overlaytest.domain.repository.MemberRepository
import kotlin.random.Random

class MemberRepositoryImpl : MemberRepository {

    companion object {
        val imageSampleList = arrayOf(
            "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
            "https://homepages.cae.wisc.edu/~ece533/images/baboon.png",
            "https://homepages.cae.wisc.edu/~ece533/images/girl.png",
            "https://homepages.cae.wisc.edu/~ece533/images/monarch.png"
        )
    }
    
    override fun getDummyMembers(): List<Member> {
        val random = Random(System.currentTimeMillis())
        // range 3 ~ 8
        val number = random.nextInt(3, 9)
        return memberSequence.take(number).toList()
    }

    private val memberSequence = sequence<Member> {
        yieldAll(generateSequence {
            val random = Random.nextInt(1000)
            Member(name = "member-$random", uri = imageSampleList[random % imageSampleList.size])
        })
    }
}