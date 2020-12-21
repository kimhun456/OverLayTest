package com.github.overlaytest.domain.repository

import com.github.overlaytest.domain.enitity.Group

interface GroupRepository {
    fun getGroups(): List<Group>
    fun requestGroups(): List<Group>
}