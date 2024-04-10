package com.example.springbootweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootweb.model.dto.ParamDto;
import com.example.springbootweb.model.dto.ParamMixDto;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "adios") String message) {

        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message);

        return paramDto;
    }

    @GetMapping("/bar")
    public ParamMixDto bar(@RequestParam String message, @RequestParam Integer code) {

        return new ParamMixDto(message, code);
    }

    @GetMapping("/request")
    public ParamMixDto request(HttpServletRequest request) {

        ParamMixDto paramMixDto = new ParamMixDto();
        paramMixDto.setMessage(request.getParameter("message"));

        String codeParam = request.getParameter("code");
        Integer code = 0;

        try {
            code = Integer.parseInt(codeParam);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        paramMixDto.setCode(code);

        System.out.println(request.getCookies() + "-----" + request.getHeader("header"));

        return paramMixDto;
    }

}
