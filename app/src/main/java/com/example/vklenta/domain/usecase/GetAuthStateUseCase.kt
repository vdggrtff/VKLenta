package com.example.vklenta.domain.usecase

import com.example.vklenta.domain.entity.AuthState
import com.example.vklenta.domain.repository.NewsfeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val repository: NewsfeedRepository
) {

    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}