package com.gsnotes.web.controllers;


import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.services.IInscriptionAnnuelleService;
import com.gsnotes.services.INiveauService;
import com.gsnotes.services.impl.EtudiantServiceImpl;
import com.gsnotes.utils.export.ExcelExporter;
import com.gsnotes.web.models.PersonModel;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/cadreadmin")
public class EtudentsControler {

    @Autowired
    private INiveauService niveauService;
    @Autowired
    private IInscriptionAnnuelleService inscriptionAnnuelleService;
    @Autowired
    private EtudiantServiceImpl etudiantService;
    @Autowired
    private HttpSession session;

    @RequestMapping("/showAllEtudents")
    public String showAllEtudents(Model model){
        List<Niveau> niveauList = niveauService.getAllNiveau();
        model.addAttribute("niveauList", niveauList);
        return "/cadreadmin/showAllEtudents";
    }


//    this method get the use input from the form in the showAllEtudents ( niveau & annee )
//    what this method do :
//    select the id of etudent from inscriptionAnneulle table then select the etudent by id from etudents table.
//    put the list of etudents in the model
//    return the jsp file to show etudents to use .
    @RequestMapping("/showEtudentsByNiveau")
    public String showEtudentsByNiveau(Model model, HttpServletRequest request){


//      i need to pass the form parameter to de model.
//      get the niveau.
        Long idNiveau = Long.valueOf(request.getParameter("idNiveau"));
//      get the annee.
        int annee = Integer.parseInt(request.getParameter("annee"));
        session.setAttribute("courentAnneeSelected", annee);
        session.setAttribute("courentIdNiveau", idNiveau);
        System.out.println(idNiveau + " " + annee);
        List<Etudiant> etudiantsList;
        etudiantsList = inscriptionAnnuelleService.getEtudentsByNiveauAndInscriptionAnnuelle(annee, idNiveau);
//        System.out.println(etudiantsList);
//        add etudiantsList to the model
        model.addAttribute("etudiantsList", etudiantsList);
        return "/cadreadmin/showEtudentsByNiveau";
    }

//    this method return a form with value of the etudent that need to update

    @RequestMapping(value= "/updateEtudiant/{id}", method = RequestMethod.GET)
    public String updateEtudant(Model model, @PathVariable("id") Long id){
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        model.addAttribute("etudiant", etudiant);
        return "/cadreadmin/updateEtudiantForm";
    }

    @RequestMapping("/updateEtudiantById")
    public String updateEtudantById(Model model, HttpServletRequest request) throws ParseException {
//        todo: complete this shit
//        first thing get the id of the use we need to up date.
            System.out.println("hello world");
//          todo: i hava a fuckin problem here !
            Long id             = Long.valueOf(request.getParameter("idEtudiant"));
            String nom          = (String) request.getParameter("nom");
            String prenom       = (String) request.getParameter("prenom");
            String nomArab      = (String) request.getParameter("nomArab");
            String prenomArab   = (String) request.getParameter("prenomArab");
            String email        = (String) request.getParameter("email");
            String telephone    = (String) request.getParameter("telephone");
            String cne          = (String) request.getParameter("cne");

            Etudiant e = etudiantService.getEtudiantById(id);

            Etudiant etudiant = new Etudiant();
            etudiant.setIdUtilisateur(id);
            etudiant.setNom(nom);
            etudiant.setPrenom(prenom);
            etudiant.setNomArabe(nomArab);
            etudiant.setPrenomArabe(prenomArab);
            etudiant.setEmail(email);
            etudiant.setTelephone(telephone);
            etudiant.setDateNaissance(e.getDateNaissance());
            etudiant.setCne(cne);

            etudiantService.updateEtudiant(etudiant);
//            System.out.println(dateNaissance);
            System.out.println(id);
//            System.out.println(id);
            System.out.println("hello world this is a test that can help me debug this shit!");

//        save the old information to log file with the name of the person who update it.
//        update the etudiant information.
        List<Etudiant> etudiantList;
//
//        get the selected Niveau and annee from the session.
//        and return to the list of etudiant with a valid message.
        Long idNiveau = (Long) session.getAttribute("courentIdNiveau");
        int annee = (int) session.getAttribute("courentAnneeSelected");
        etudiantList = inscriptionAnnuelleService.getEtudentsByNiveauAndInscriptionAnnuelle(annee, idNiveau);
        model.addAttribute("etudiantsList", etudiantList);
        return "/cadreadmin/showEtudentsByNiveau";
    }


    @RequestMapping(value = "/deletEtudiant/{id}", method = RequestMethod.GET)
    public String DeletEtudiant(Model model, @PathVariable("id") Long id, HttpServletRequest request){
        System.out.println("hello world");
//        get the etudiant by id
        Etudiant etudiant = etudiantService.getEtudiantById(id);
//      enregester the etudiant info befor delete it

//        delete the etudiant by id
        etudiantService.deletEtudiantByid(id);

        request.setAttribute("message", "etudiant sepremmer avec success.");



        //get the selected Niveau and annee from the session.
        //and return to the list of etudiant with a valid message.
        Long idNiveau = (Long) session.getAttribute("courentIdNiveau");
        int annee = (int) session.getAttribute("courentAnneeSelected");
        List<Etudiant> etudiantList = inscriptionAnnuelleService.getEtudentsByNiveauAndInscriptionAnnuelle(annee, idNiveau);
        model.addAttribute("etudiantsList", etudiantList);
        return "cadreadmin/showEtudentsByNiveau";
    }
    @GetMapping("/exportEtudiant")
    public void ExportEtudiant(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        Long idNiveau = (Long) session.getAttribute("courentIdNiveau");
        int annee = (int) session.getAttribute("courentAnneeSelected");
        List<Etudiant> etudiantList = null;
        etudiantList = inscriptionAnnuelleService.getEtudentsByNiveauAndInscriptionAnnuelle(annee, idNiveau);
        ExcelExporter exceleExporter = etudiantService.prepareEtudiantExport(etudiantList);
        System.out.println("hello");
        exceleExporter.export(response);

    }
}
