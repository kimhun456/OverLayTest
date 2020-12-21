package com.github.overlaytest.domain.enitity

data class Group(
    val memberList: MutableList<Member> = mutableListOf(),
    val name: String = ""
)
