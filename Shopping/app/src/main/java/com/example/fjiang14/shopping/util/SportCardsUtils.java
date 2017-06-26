package com.example.fjiang14.shopping.util;

import com.example.fjiang14.shopping.R;
import com.example.fjiang14.shopping.home.SportCardModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SportCardsUtils {

    public static Collection<SportCardModel> generateSportCards() {
        List<SportCardModel> sportCardModels = new ArrayList<>(5);

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Table tennis")
                    .withSportSubtitle("Woman's singles")
                    .withSportRound("Preliminaries")
                    .withImageResId(R.drawable.ic_home_black_36dp)
                    .withTime("3:00")
                    .withDayPart("PM")
                    .withBackgroundColorResId(R.color.colorWhite)
                    .build());

        }

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Shooting")
                    .withSportSubtitle("Woman's 10m air rifle")
                    .withSportRound("Qualification")
                    .withImageResId(R.drawable.ic_home_black_36dp)
                    .withTime("2:30")
                    .withDayPart("PM")
                    .withBackgroundColorResId(R.color.colorGray)
                    .build());

        }

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Rowing")
                    .withSportSubtitle("Men's single sculls")
                    .withSportRound("Heats")
                    .withImageResId(R.drawable.ic_home_black_36dp)
                    .withTime("2:30")
                    .withDayPart("PM")
                    .withBackgroundColorResId(R.color.colorAccent)
                    .build());

        }

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Archery")
                    .withSportSubtitle("Men's team")
                    .withSportRound("Round of 16")
                    .withImageResId(R.drawable.ic_home_black_36dp)
                    .withTime("3:00")
                    .withDayPart("PM")
                    .withBackgroundColorResId(R.color.colorAccent)
                    .build());

        }

        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Water polo")
                    .withSportSubtitle("Men’s tournament")
                    .withSportRound("Group A")
                    .withImageResId(R.drawable.ic_home_black_36dp)
                    .withTime("3:00")
                    .withDayPart("PM")
                    .withBackgroundColorResId(R.color.colorAccent)
                    .build());

        }
        return sportCardModels;
    }
}
