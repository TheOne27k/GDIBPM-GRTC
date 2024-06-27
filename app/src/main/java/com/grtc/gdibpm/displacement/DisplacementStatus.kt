package com.grtc.gdibpm.displacement

enum class DisplacementStatus(val displayName: String) {
    SUCCESS ("Entregado"),
    IN_PROCESS ("En Proceso de Entrega"),
    CANCEL("Rechazado"),
}