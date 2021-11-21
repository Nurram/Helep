package com.rex.project.helep.model

data class Review(
    val username: String,
    val date: String,
    val review: String
) {
    companion object {
        fun getDummy() = listOf(
            Review("budi_anduk90", "31/10/2021", "Pelayanan yang diberikan sangat memuaskan. Task diselesaikan dengan sempurna."),
            Review("abigailzzzz", "29/10/2021", "Task saya diselesaikan dengan cepat dan tepat. Auto follow." ),
            Review("andreassss_sss", "29/10/2021", "Berkat abang aku sekarang dah Radiant. Terima kasih bang."),
            Review("michaelyt", "28/10/2021", "Terima kasih mas John, saya jadi mudah mengerti mengenai pelajaran tadi."),
            Review("cindyzhou", "27/10/2021", "Jemputannya cepat, saya jadi tidak telat untuk pergi ke kantor. Saya follow, semoga anda bisa menerima Give Task dari saya.")
        )
    }
}