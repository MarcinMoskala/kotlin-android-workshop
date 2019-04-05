package com.workshop.universityannouncementsboard.extra

// TODO Implement this function to return list with element added at `index` position.
// Check out unit tests at PlusAtTest class.
fun <T> List<T>.plusAt(index: Int?, element: T?): List<T> {
    require(index != null && element != null)
    require(index in 0..this.size)
    return take(index) + element + drop(index)
}

