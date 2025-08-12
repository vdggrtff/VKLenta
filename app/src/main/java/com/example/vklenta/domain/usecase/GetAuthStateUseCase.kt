package com.example.vklenta.domain.usecase

import com.example.vklenta.domain.entity.AuthState
import com.example.vklenta.domain.repository.NewsfeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetAuthStateUseCase(
    private val repository: NewsfeedRepository,
) {

    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}