package com.unirfp.ropaapi.data.model


data class ApiResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val results: List<Producto>
)