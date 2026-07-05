package com.example.poultryfarm.data

object SeedData {
    val lighting = listOf(
        LightingRecord(ageDay = "19 روزگی", darkHours = "3", timeRange = "12 الی 3 صبح", date = "1404/12/15"),
        LightingRecord(ageDay = "20 روزگی", darkHours = "4", timeRange = "12 الی 4 صبح", date = "1404/12/16"),
        LightingRecord(ageDay = "21 روزگی", darkHours = "5", timeRange = "12 الی 5 صبح", date = "1404/12/17"),
        LightingRecord(ageDay = "22 روزگی", darkHours = "6", timeRange = "12 الی 6 صبح", date = "1404/12/18"),
        LightingRecord(ageDay = "29 روزگی", darkHours = "7", timeRange = "11 الی 6 صبح", date = "1404/12/25"),
        LightingRecord(ageDay = "36 روزگی", darkHours = "8 (شدت 8 لوکس)", timeRange = "10 الی 6 صبح", date = "1405/01/03"),
        LightingRecord(ageDay = "42 روزگی", darkHours = "10", timeRange = "8 الی 6 صبح", date = "1405/01/10"),
        LightingRecord(ageDay = "49 روزگی", darkHours = "12", timeRange = "6 الی 6 صبح", date = "1405/01/17"),
        LightingRecord(ageDay = "57 روزگی", darkHours = "13", timeRange = "5 الی 6 صبح", date = "1405/01/24"),
        LightingRecord(ageDay = "64 روزگی", darkHours = "14", timeRange = "4 الی 6 صبح", date = "1405/01/31")
    )

    val vaccines = listOf(
        VaccineRecord(vaccineType = "نیوکاسل و برونشیت", method = "اسپری هنگام ورود جوجه به سالن", brand = "ویتا برون و ایبرن", date = "1404/11/26", dose = "1 دوز", halls = "سالن 1، 2، 3", chickAge = "1"),
        VaccineRecord(vaccineType = "نیوکاسل و برونشیت", method = "اسپری هنگام ورود جوجه به سالن", brand = "ویتا برون و ایبرن", date = "1404/11/27", dose = "1 دوز", halls = "سالن 4، 5", chickAge = "1"),
        VaccineRecord(vaccineType = "نیوکاسل و برونشیت", method = "اسپری هنگام ورود جوجه به سالن", brand = "ویتا برون و ایبرن", date = "1404/11/28", dose = "1 دوز", halls = "سالن 6، 7", chickAge = "1"),
        VaccineRecord(vaccineType = "نیوکاسل + آنفولانزا H9 + نیوکاسل قطره چشمی", method = "تزریقی در عضله سینه و قطره چشمی", brand = "QYH و Cevac", date = "1404/12/03", dose = "0.2 تزریق / 1 دوز چشمی", halls = "سالن 1", chickAge = "7"),
        VaccineRecord(vaccineType = "نیوکاسل + آنفولانزا H9 + نیوکاسل قطره چشمی", method = "تزریقی در عضله سینه و قطره چشمی", brand = "QYH و Cevac", date = "1404/12/04", dose = "0.2 تزریق / 1 دوز چشمی", halls = "سالن 2، 3، 4", chickAge = "8"),
        VaccineRecord(vaccineType = "نیوکاسل + آنفولانزا H9 + نیوکاسل قطره چشمی", method = "تزریقی در عضله سینه و قطره چشمی", brand = "QYH و Cevac", date = "1404/12/05", dose = "0.2 تزریق / 1 دوز چشمی", halls = "سالن 5، 6، 7", chickAge = "7"),
        VaccineRecord(vaccineType = "برونشیت", method = "خاموشی 2 ساعته سپس اسپری", brand = "واریانت 2 - پارک وی", date = "1404/12/05", dose = "1 دوز", halls = "سالن 1، 2، 3، 4", chickAge = "10"),
        VaccineRecord(vaccineType = "برونشیت", method = "خاموشی 2 ساعته سپس اسپری", brand = "واریانت 2 - پارک وی", date = "1404/12/06", dose = "1 دوز", halls = "سالن 5، 6، 7", chickAge = "10"),
        VaccineRecord(vaccineType = "گامبرو", method = "آشامیدنی", brand = "HIPRAGUMBORO CH80", date = "1404/12/11", dose = "1 دوز", halls = "همه سالن‌ها", chickAge = "16")
    )
}
