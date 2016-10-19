package io.daio.wavetile.extensions


fun String?.removeLastWord(ifGreaterThan: Int): String? = if (this?.length?.compareTo(ifGreaterThan) === -1) this else this?.substringBeforeLast(" ")