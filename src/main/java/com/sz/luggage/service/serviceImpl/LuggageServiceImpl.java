package com.sz.luggage.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sz.luggage.mapper.BagMapper;
import com.sz.luggage.pojo.Bag;
import com.sz.luggage.service.LuggageService;
import org.springframework.stereotype.Service;

@Service
public class LuggageServiceImpl extends ServiceImpl<BagMapper, Bag> implements LuggageService {
}
