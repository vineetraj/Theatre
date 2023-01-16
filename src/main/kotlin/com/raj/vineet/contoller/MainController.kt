package com.raj.vineet.contoller

import com.raj.vineet.services.BookingService
import com.raj.vineet.services.TheaterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {
    @Autowired
    lateinit var theatreService: TheaterService

    @Autowired
    lateinit var bookingService: BookingService

    @RequestMapping("/")
    fun homePage(): ModelAndView = ModelAndView("seatBooking", "bean", CheckAvailabilityBackingBeans())

    @RequestMapping(value = arrayOf("/checkAvailability"), method = arrayOf(RequestMethod.POST))
    fun checkAvailability(bean: CheckAvailabilityBackingBeans): ModelAndView {
        val selectedSeat = theatreService.find(bean.selectedSeatNum, bean.selectedSeatRow)
        val result = bookingService.isSeatAvailable(selectedSeat)
        bean.result = "Selected $selectedSeat is " + if (result) "available" else "booked"
        return ModelAndView("seatBooking", "bean", bean)
    }
}

class CheckAvailabilityBackingBeans() {
    val seatNums = 1..36
    val seatRows = 'A'..'O'
    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var result: String = ""
}