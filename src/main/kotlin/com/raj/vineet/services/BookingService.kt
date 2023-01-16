package com.raj.vineet.services

import com.raj.vineet.domain.Seat
import org.springframework.stereotype.Service

@Service
class BookingService {
    fun isSeatAvailable(seat: Seat):Boolean{
        return true
    }
}