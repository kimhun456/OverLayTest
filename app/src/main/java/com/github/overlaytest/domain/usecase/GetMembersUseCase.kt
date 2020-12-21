package com.github.overlaytest.domain.usecase

import com.github.overlaytest.data.repository.MemberRepositoryImpl
import com.github.overlaytest.domain.enitity.Member
import com.github.overlaytest.domain.repository.MemberRepository

class GetMembersUseCase(
    private val memberRepository: MemberRepository = MemberRepositoryImpl()
) {

    fun execute(): List<Member> {
        return memberRepository.getDummyMembers()
    }
}