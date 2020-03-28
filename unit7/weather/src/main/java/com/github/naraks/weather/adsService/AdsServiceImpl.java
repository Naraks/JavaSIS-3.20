package com.github.naraks.weather.adsService;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AdsServiceImpl implements AdsService {

    List<String> ads;

    AdsServiceImpl() {
        ads = new ArrayList<>();
        ads.add("Самые горячие чебуреки в твоем городе! Звоните прямо сейчас: 222-22-22");
        ads.add("Продам гараж. Тел: 222-22-21");
        ads.add("Борзые щенки (222-22-20)");
        ads.add("Поставь модный рингтон на свой телефон! Отправь СМС по номеру: 222-22-19");
        ads.add("Хотите разместить рекламу в нашем приложении? Отправляйте заявку по адресу noreply@spam.ru");
    }

    @Override
    public String getRandomAd() {
        return ads.get(new Random().nextInt(ads.size()));
    }
}
