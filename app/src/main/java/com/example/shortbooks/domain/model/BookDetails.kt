package com.example.shortbooks.domain.model

class BookDetails(
    val bookName: String,
    val keyPoints: List<KeyPoint>,
) {
    fun getTotalSteps(): Int {
        return keyPoints.size
    }
}
