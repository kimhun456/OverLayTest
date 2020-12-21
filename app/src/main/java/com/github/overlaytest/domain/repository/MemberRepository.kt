package com.github.overlaytest.domain.repository

import com.github.overlaytest.domain.enitity.Member

interface MemberRepository {
    fun getDummyMembers(): List<Member>
}