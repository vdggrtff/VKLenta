package com.example.vklenta.domain.usecase

import com.example.vklenta.domain.repository.NewsfeedRepository
import javax.inject.Inject

class LoadNextDataUseCase @Inject constructor(
    private val repository: NewsfeedRepository
) {

    suspend operator fun invoke() {
        repository.loadNextData()
    }
}