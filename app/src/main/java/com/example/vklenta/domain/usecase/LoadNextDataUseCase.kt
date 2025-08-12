package com.example.vklenta.domain.usecase

import com.example.vklenta.domain.repository.NewsfeedRepository

class LoadNextDataUseCase(
    private val repository: NewsfeedRepository
) {

    suspend operator fun invoke() {
        repository.loadNextData()
    }
}