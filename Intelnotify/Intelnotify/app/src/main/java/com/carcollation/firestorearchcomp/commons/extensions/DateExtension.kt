package com.carcollation.firestorearchcomp.commons.extensions

import java.text.SimpleDateFormat
import java.util.*


val SERVER_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
val LOCAL_DATE_FORMAT = SimpleDateFormat("dd MMMM yyyy")
val LOCAL_DATE_FORMAT_1 = SimpleDateFormat("dd MMM yyyy")
val YEAR_FORMAT = SimpleDateFormat("yyyy")
val MONTH_FORMAT = SimpleDateFormat("MM")
val DAY_FORMAT = SimpleDateFormat("dd")
val Date_Send_FORMAT = SimpleDateFormat("yyyyMMdd")
val NewLOCAL_DATE_FORMAT = SimpleDateFormat("dd MMM yyyy")
val NewLOCAL_Time_FORMAT = SimpleDateFormat("hh:mm")
val DisplayMonth_FORMAT = SimpleDateFormat("dd-MM-yyyy")
val Convert_Month_FORMAT = SimpleDateFormat("MMMM-yyyy")
val Time_FORMAT = SimpleDateFormat("hh:mm aa",Locale.ENGLISH)
val Convert_Time_FORMAT = SimpleDateFormat("HH:mm",Locale.ENGLISH)



@Throws(Exception::class)
fun changeToServerFormat(year: Int, month: Int, day: Int): String =
        LOCAL_DATE_FORMAT.parse("$year-$month-$day").changeToServerDate()

@Throws(Exception::class)
fun Date.getLocalYear(): Int = YEAR_FORMAT.format(this).toInt()

@Throws(Exception::class)
fun Date.getLocalMonth(): Int = MONTH_FORMAT.format(this).toInt()

@Throws(Exception::class)
fun Date.getLocalDay(): Int = DAY_FORMAT.format(this).toInt()

@Throws(Exception::class)
fun Date.changeToServerDate() = SERVER_DATE_FORMAT.format(this.updateMonth(reduce = false))

@Throws(Exception::class)
fun String.changeToLocalDateFormat() = LOCAL_DATE_FORMAT.format(SERVER_DATE_FORMAT.parse(this))

@Throws(Exception::class)
fun String.changeToLocalDateFormat1() = LOCAL_DATE_FORMAT_1.format(
    SERVER_DATE_FORMAT.parse(this))


@Throws(Exception::class)
fun String.newchangeToLocalDateFormate() = NewLOCAL_DATE_FORMAT.format(
    SERVER_DATE_FORMAT.parse(this))

@Throws(Exception::class)
fun String.newchangeToLocalTimeFormate() = NewLOCAL_Time_FORMAT.format(
    SERVER_DATE_FORMAT.parse(this))


@Throws(Exception::class)
fun String.convertmonthFormate() = Convert_Month_FORMAT.format(DisplayMonth_FORMAT.parse(this))

@Throws(Exception::class)
fun String.convertTimeFormate() = Convert_Time_FORMAT.format(Time_FORMAT.parse(this))


@Throws(Exception::class)
fun String.changedatewisenews() = Date_Send_FORMAT.format(SERVER_DATE_FORMAT.parse(this))

fun Date.updateMonth(reduce: Boolean) : Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.add(Calendar.MONTH, if(reduce) -1 else 1)
    return cal.time
}