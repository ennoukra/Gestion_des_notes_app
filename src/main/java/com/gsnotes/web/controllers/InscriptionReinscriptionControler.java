package com.gsnotes.web.controllers;


import com.gsnotes.services.IInscriptioReinscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cadreadmin")
public class InscriptionReinscriptionControler {

    @Autowired
    IInscriptioReinscriptionService inscriptioReinscriptionService;

    @RequestMapping("/inscriptionReinscription")
    public String inscriptionReinscription(){
        System.out.println("hello world ");
        return "/cadreadmin/inscriptionReinscription";
    }

    @RequestMapping("/upLoadFile")
    public String upLoadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file){

        inscriptioReinscriptionService.uploadFile(file);
        inscriptioReinscriptionService.getExcelDataList();
      int annee = Integer.parseInt(request.getParameter("annee"));
      String filename = "";
      System.out.println(request.getParameter("file"));
        return "/cadreadmin/inscriptionReinscription";
    }
}
