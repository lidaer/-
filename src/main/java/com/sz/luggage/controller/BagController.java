package com.sz.luggage.controller;

import com.sz.luggage.pojo.Bag;
import com.sz.luggage.service.LuggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bag")
public class BagController {
    @Autowired
    private LuggageService luggageService;


    @GetMapping
    public List<Bag> getAll() {
        List<Bag> list = luggageService.list();

        return list;
    }

    @PostMapping
    public int save(@RequestBody Bag bag) {
        if (bag.getWeight() > 32 || bag.getSize() > 203) {
            return 0;
        }
        luggageService.save(bag);
        return 1;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        System.out.println(id);
        luggageService.removeById(id);
    }

    @GetMapping("/calculator")
    public List<Double> calculator() {
        List<Bag> list = luggageService.list();
        System.out.println(list);
        Double rmb = 0.0;
        Double dollar = 0.0;
        for (Bag bag : list) {
            System.out.println(bag);
            if ("1".equals(bag.getDistrict())) {  //区域一的行李
                if (23 <= bag.getWeight() && bag.getWeight() <= 28 && 60 <= bag.getSize() && bag.getSize() <= 158) {  //超重量但不超尺寸
                    rmb += 380;
                    dollar += 60;
                } else if (28 <= bag.getWeight() && bag.getWeight() <= 32 && 60 <= bag.getSize() && bag.getSize() <= 158) {  //超重量但不超尺寸
                    rmb += 980;
                    dollar += 150;
                } else if (2 <= bag.getWeight() && bag.getWeight() <= 23 && 158 < bag.getSize() && bag.getSize() <= 203) {  //不超重量但超尺寸
                    rmb += 980;
                    dollar += 150;
                } else if (23 < bag.getWeight() && bag.getWeight() <= 32 && 158 < bag.getSize() && bag.getSize() <= 203) {  //超重量且超尺寸
                    rmb += 1400;
                    dollar += 220;
                }
            } else if ("2".equals(bag.getDistrict())) {  //区域二的行李
                if (23 <= bag.getWeight() && bag.getWeight() <= 28 && 60 <= bag.getSize() && bag.getSize() <= 158) {  //超重量但不超尺寸
                    rmb += 280;
                    dollar += 40;
                } else if (28 <= bag.getWeight() && bag.getWeight() <= 32 && 60 <= bag.getSize() && bag.getSize() <= 158) {  //超重量但不超尺寸
                    rmb += 690;
                    dollar += 100;
                } else if (2 <= bag.getWeight() && bag.getWeight() <= 23 && 158 < bag.getSize() && bag.getSize() <= 203) {  //不超重量但超尺寸
                    rmb += 690;
                    dollar += 100;
                } else if (23 < bag.getWeight() && bag.getWeight() <= 32 && 158 < bag.getSize() && bag.getSize() <= 203) {  //超重量且超尺寸
                    rmb += 1100;
                    dollar += 160;
                }
            } else if ("4".equals(bag.getDistrict())) {  //区域四的行李
                if (23 <= bag.getWeight() && bag.getWeight() <= 28 && 60 <= bag.getSize() && bag.getSize() <= 158) {  //超重量但不超尺寸
                    rmb += 690;
                    dollar += 100;
                } else if (28 <= bag.getWeight() && bag.getWeight() <= 32 && 60 <= bag.getSize() && bag.getSize() <= 158) {  //超重量但不超尺寸
                    rmb += 1040;
                    dollar += 150;
                } else if (2 <= bag.getWeight() && bag.getWeight() <= 23 && 158 < bag.getSize() && bag.getSize() <= 203) {  //不超重量但超尺寸
                    rmb += 1040;
                    dollar += 150;
                } else if (23 < bag.getWeight() && bag.getWeight() <= 32 && 158 < bag.getSize() && bag.getSize() <= 203) {  //超重量且超尺寸
                    rmb += 2050;
                    dollar += 300;
                }
            } else if ("5".equals(bag.getDistrict())) {  //区域五的行李
                if (23 <= bag.getWeight() && bag.getWeight() <= 28 && 60 <= bag.getSize() && bag.getSize() <= 158) {  //超重量但不超尺寸
                    rmb += 210;
                    dollar += 30;
                } else if (28 <= bag.getWeight() && bag.getWeight() <= 32 && 60 <= bag.getSize() && bag.getSize() <= 158) {  //超重量但不超尺寸
                    rmb += 520;
                    dollar += 75;
                } else if (2 <= bag.getWeight() && bag.getWeight() <= 23 && 158 < bag.getSize() && bag.getSize() <= 203) {  //不超重量但超尺寸
                    rmb += 520;
                    dollar += 75;
                } else if (23 < bag.getWeight() && bag.getWeight() <= 32 && 158 < bag.getSize() && bag.getSize() <= 203) {  //超重量且超尺寸
                    rmb += 830;
                    dollar += 120;
                } else if ("3".equals(bag.getDistrict())) {  //区域三的行李
                    if (23 <= bag.getWeight() && bag.getWeight() <= 32 || 158 <= bag.getSize() && bag.getSize() <= 203) {  //超重量但不超尺寸
                        rmb += 520;
                        dollar += 75;
                    } else if (23 <= bag.getWeight() && bag.getWeight() <= 32 && 158 <= bag.getSize() && bag.getSize() <= 203) {  //超重量但不超尺寸
                        rmb += 520;
                        dollar += 75;
                    }
                }
            }
        }
        List<Double> res = new ArrayList<>();
        res.add(rmb);
        res.add(dollar);
        return res;
    }
}
