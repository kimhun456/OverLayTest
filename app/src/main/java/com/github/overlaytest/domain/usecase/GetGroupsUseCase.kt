package com.github.overlaytest.domain.usecase

import com.github.overlaytest.data.repository.GroupRepositoryImpl
import com.github.overlaytest.data.repository.MemberRepositoryImpl
import com.github.overlaytest.domain.enitity.Group
import com.github.overlaytest.domain.repository.GroupRepository
import com.github.overlaytest.domain.repository.MemberRepository

class GetGroupsUseCase(
    private val memberRepository: MemberRepository = MemberRepositoryImpl(),
    private val groupRepository: GroupRepository = GroupRepositoryImpl()
) {

    fun execute(): List<Group> {
        return groupRepository.getGroups()
            .onEach { it.memberList.addAll(memberRepository.getDummyMembers()) }
    }
}