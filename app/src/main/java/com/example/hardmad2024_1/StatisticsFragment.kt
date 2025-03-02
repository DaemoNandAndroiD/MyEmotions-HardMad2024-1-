package com.example.hardmad2024_1

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.StatisticsFragmentBinding
import com.example.hardmad2024_1.utilities.DuringDayStatisticsData
import com.example.hardmad2024_1.utilities.GeneralStatisticsData
import com.example.hardmad2024_1.utilities.HorizontalViewPagerAdapter
import com.example.hardmad2024_1.utilities.OftenStatisticsData
import com.example.hardmad2024_1.utilities.VerticalFragmentData
import com.example.hardmad2024_1.utilities.WeekStatisticsData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class StatisticsFragment: Fragment(R.layout.statistics_fragment) {
    private lateinit var binding:StatisticsFragmentBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsFragmentBinding.bind(view)

        binding.horizontalTabLayout.setPadding(
            0,getStatusBarHeight(),0,0
        )

        val adapter = HorizontalViewPagerAdapter(this,
            listOf(
                VerticalFragmentData(
                    GeneralStatisticsData(5,listOf(80f,20f)),
                    arrayOf(WeekStatisticsData(
                        date = "17 февраля",
                        dayOfWeek = getString(R.string.first_day),
                        emotionsTexts = listOf(
                            "Спокойствие",
                            "Продуктивность",
                            "Счастье",
                            "Радость"
                        ),
                        icons = listOf(
                            R.drawable.ic_green_emote,
                            R.drawable.ic_yellow_emote,
                            R.drawable.ic_yellow_emote_2,
                            R.drawable.ic_red_emote
                        )
                    ),
                        WeekStatisticsData(
                            date = "18 февраля",
                            dayOfWeek = getString(R.string.second_day),
                            emotionsTexts = listOf(
                                "Выгорание",
                                "Усталость",
                            ),
                            icons = listOf(
                                R.drawable.ic_blue_emote,
                                R.drawable.ic_blue_emote_2,
                            )
                        ),
                        WeekStatisticsData(
                            date = "19 февраля",
                            dayOfWeek = getString(R.string.third_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "20 февраля",
                            dayOfWeek = getString(R.string.fourth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "21 февраля",
                            dayOfWeek = getString(R.string.fifth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "22 февраля",
                            dayOfWeek = getString(R.string.sixth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "23 февраля",
                            dayOfWeek = getString(R.string.seventh_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        )
                ),
                    arrayOf(OftenStatisticsData("Спокойствие", 1f,4, R.drawable.ic_green_emote, R.drawable.green_often_stat),
                        OftenStatisticsData("Продуктивность", 0.75f, 3, R.drawable.ic_yellow_emote, R.drawable.yellow_often_stat),
                        OftenStatisticsData("Счастье", 0.5f, 2, R.drawable.ic_yellow_emote_2, R.drawable.yellow_often_stat),
                        OftenStatisticsData("Выгорание", 1f, 4, R.drawable.ic_blue_emote, R.drawable.blue_often_stat),
                        OftenStatisticsData("Усталость", 0.25f, 1, R.drawable.ic_blue_emote_2, R.drawable.blue_often_stat)),
                    arrayOf(DuringDayStatisticsData(1, listOf(1), listOf(R.drawable.green_vertical_stat)),
                        DuringDayStatisticsData(3, listOf(1,1,1), listOf(R.drawable.yellow_vertical_stat, R.drawable.green_vertical_stat, R.drawable.red_vertical_stat)),
                        DuringDayStatisticsData(10, listOf(1,2,3,4), listOf(R.drawable.yellow_vertical_stat, R.drawable.green_vertical_stat, R.drawable.red_vertical_stat, R.drawable.blue_vertical_stat)),
                        DuringDayStatisticsData(8, listOf(2,2,2,2), listOf(R.drawable.red_vertical_stat, R.drawable.blue_vertical_stat, R.drawable.yellow_vertical_stat, R.drawable.green_vertical_stat)),
                        DuringDayStatisticsData(2, listOf(1,1), listOf(R.drawable.red_vertical_stat, R.drawable.yellow_vertical_stat)))
                ),
                VerticalFragmentData(GeneralStatisticsData(10,listOf(20f,40f, 20f, 20f)),
                    arrayOf(WeekStatisticsData(
                        date = "24 февраля",
                        dayOfWeek = getString(R.string.first_day),
                        emotionsTexts = listOf(
                            "Спокойствие",
                            "Продуктивность",
                            "Счастье",
                            "Радость",
                            "Напряжение",
                            "Зависть",
                            "Апатия"
                        ),
                        icons = listOf(
                            R.drawable.ic_green_emote,
                            R.drawable.ic_yellow_emote,
                            R.drawable.ic_yellow_emote_2,
                            R.drawable.ic_red_emote,
                            R.drawable.ic_red_emote,
                            R.drawable.ic_red_emote,
                            R.drawable.ic_blue_emote
                        )
                    ),
                        WeekStatisticsData(
                            date = "25 февраля",
                            dayOfWeek = getString(R.string.second_day),
                            emotionsTexts = listOf(
                                "Выгорание",
                                "Усталость",
                                "Депрессия",
                                "Благодарность",
                                "Спокойствие",
                                "Продуктивность",
                                "Счастье",
                                "Радость",
                                "Напряжение",
                                "Зависть",
                                "Апатия"
                            ),
                            icons = listOf(
                                R.drawable.ic_blue_emote,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_yellow_emote,
                                R.drawable.ic_yellow_emote_2,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_blue_emote
                            )
                        ),
                        WeekStatisticsData(
                            date = "26 февраля",
                            dayOfWeek = getString(R.string.third_day),
                            emotionsTexts = listOf(
                                "Выгорание",
                                "Усталость",
                                "Депрессия",
                                "Благодарность",
                                "Спокойствие",
                                "Продуктивность",
                                "Счастье",
                                "Радость",
                                "Напряжение",
                                "Зависть",
                                "Апатия"
                            ),
                            icons = listOf(
                                R.drawable.ic_blue_emote,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_yellow_emote,
                                R.drawable.ic_yellow_emote_2,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_blue_emote
                            )
                        ),
                        WeekStatisticsData(
                            date = "27 февраля",
                            dayOfWeek = getString(R.string.fourth_day),
                            emotionsTexts = listOf(
                                "Выгорание",
                                "Усталость",
                                "Депрессия",
                                "Благодарность",
                                "Спокойствие",
                                "Продуктивность",
                                "Счастье",
                                "Радость",
                                "Напряжение",
                                "Зависть",
                                "Апатия"
                            ),
                            icons = listOf(
                                R.drawable.ic_blue_emote,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_yellow_emote,
                                R.drawable.ic_yellow_emote_2,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_blue_emote
                            )
                        ),
                        WeekStatisticsData(
                            date = "28 февраля",
                            dayOfWeek = getString(R.string.fifth_day),
                            emotionsTexts = listOf(
                                "Выгорание",
                                "Усталость",
                                "Депрессия",
                                "Благодарность",
                                "Спокойствие",
                                "Продуктивность",
                                "Счастье",
                                "Радость",
                                "Напряжение",
                                "Зависть",
                                "Апатия"
                            ),
                            icons = listOf(
                                R.drawable.ic_blue_emote,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_yellow_emote,
                                R.drawable.ic_yellow_emote_2,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_blue_emote
                            )
                        ),
                        WeekStatisticsData(
                            date = "1 марта",
                            dayOfWeek = getString(R.string.sixth_day),
                            emotionsTexts = listOf(
                                "Выгорание",
                                "Усталость",
                                "Депрессия",
                                "Благодарность",
                                "Спокойствие",
                                "Продуктивность",
                                "Счастье",
                                "Радость",
                                "Напряжение",
                                "Зависть",
                                "Апатия"
                            ),
                            icons = listOf(
                                R.drawable.ic_blue_emote,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_yellow_emote,
                                R.drawable.ic_yellow_emote_2,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_blue_emote
                            )
                        ),
                        WeekStatisticsData(
                            date = "2 марта",
                            dayOfWeek = getString(R.string.seventh_day),
                            emotionsTexts = listOf(
                                "Выгорание",
                                "Усталость",
                                "Депрессия",
                                "Благодарность",
                                "Спокойствие",
                                "Продуктивность",
                                "Счастье",
                                "Радость",
                                "Напряжение",
                                "Зависть",
                                "Апатия"
                            ),
                            icons = listOf(
                                R.drawable.ic_blue_emote,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_blue_emote_2,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_green_emote,
                                R.drawable.ic_yellow_emote,
                                R.drawable.ic_yellow_emote_2,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_red_emote,
                                R.drawable.ic_blue_emote
                            )
                        )
                    ), arrayOf(OftenStatisticsData("Беспокойство", 1f,10, R.drawable.ic_red_emote, R.drawable.red_often_stat),
                        OftenStatisticsData("Зависть", 1f, 10, R.drawable.ic_red_emote, R.drawable.red_often_stat)),
                    arrayOf(DuringDayStatisticsData(10, listOf(1,9), listOf(R.drawable.green_vertical_stat, R.drawable.red_vertical_stat)),
                        DuringDayStatisticsData(3, listOf(1,1,1), listOf(R.drawable.yellow_vertical_stat, R.drawable.green_vertical_stat, R.drawable.red_vertical_stat)),
                        DuringDayStatisticsData(40, listOf(21,17,2), listOf(R.drawable.blue_vertical_stat, R.drawable.green_vertical_stat, R.drawable.red_vertical_stat)),
                        DuringDayStatisticsData(12, listOf(2,5,1,4), listOf(R.drawable.red_vertical_stat, R.drawable.blue_vertical_stat, R.drawable.yellow_vertical_stat, R.drawable.green_vertical_stat)),
                        DuringDayStatisticsData(0, listOf(), listOf()))
                ),
                VerticalFragmentData(
                    GeneralStatisticsData(),
                    arrayOf(WeekStatisticsData(
                        date = "3 марта",
                        dayOfWeek = getString(R.string.first_day),
                        emotionsTexts = listOf(),
                        icons = listOf()
                    ),
                        WeekStatisticsData(
                            date = "4 марта",
                            dayOfWeek = getString(R.string.second_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "5 марта",
                            dayOfWeek = getString(R.string.third_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "6 марта",
                            dayOfWeek = getString(R.string.fourth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "7 марта",
                            dayOfWeek = getString(R.string.fifth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "8 марта",
                            dayOfWeek = getString(R.string.sixth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "9 марта",
                            dayOfWeek = getString(R.string.seventh_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        )
                    ),
                    duringDayStatisticsData = arrayOf(DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()))
                ),
                VerticalFragmentData(
                    GeneralStatisticsData(),
                    arrayOf(WeekStatisticsData(
                        date = "10 марта",
                        dayOfWeek = getString(R.string.first_day),
                        emotionsTexts = listOf(),
                        icons = listOf()
                    ),
                        WeekStatisticsData(
                            date = "11 марта",
                            dayOfWeek = getString(R.string.second_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "12 марта",
                            dayOfWeek = getString(R.string.third_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "13 марта",
                            dayOfWeek = getString(R.string.fourth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "14 марта",
                            dayOfWeek = getString(R.string.fifth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "15 марта",
                            dayOfWeek = getString(R.string.sixth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "16 марта",
                            dayOfWeek = getString(R.string.seventh_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        )
                    ),
                    duringDayStatisticsData = arrayOf(DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()))),
                VerticalFragmentData(GeneralStatisticsData(),
                    arrayOf(WeekStatisticsData(
                        date = "17 марта",
                        dayOfWeek = getString(R.string.first_day),
                        emotionsTexts = listOf(),
                        icons = listOf()
                    ),
                        WeekStatisticsData(
                            date = "18 марта",
                            dayOfWeek = getString(R.string.second_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "19 марта",
                            dayOfWeek = getString(R.string.third_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "20 марта",
                            dayOfWeek = getString(R.string.fourth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "21 марта",
                            dayOfWeek = getString(R.string.fifth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "22 марта",
                            dayOfWeek = getString(R.string.sixth_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        ),
                        WeekStatisticsData(
                            date = "23 марта",
                            dayOfWeek = getString(R.string.seventh_day),
                            emotionsTexts = listOf(),
                            icons = listOf()
                        )
                    ),
                    duringDayStatisticsData = arrayOf(DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()),
                        DuringDayStatisticsData(0, listOf(), listOf()))),
            ),
            5)
        binding.viewPagerHorizontal.adapter = adapter

        val tabTexts = listOf<String>("17-23 фев", "24 фев – 2 март", "3–9 март", "10-16 март","17-23 март")

        TabLayoutMediator(
            binding.horizontalTabLayout,binding.viewPagerHorizontal){ tab: TabLayout.Tab, i: Int ->
            tab.text = tabTexts[i]
        }.attach()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getStatusBarHeight():Int{
        val windowInsets: WindowInsets = requireActivity().window.decorView.rootWindowInsets
        return windowInsets.getInsets(WindowInsets.Type.statusBars()).top
    }
}