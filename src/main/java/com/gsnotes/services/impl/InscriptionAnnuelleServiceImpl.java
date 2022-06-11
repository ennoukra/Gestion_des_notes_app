package com.gsnotes.services.impl;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IEtudiantDao;
import com.gsnotes.dao.IInscriptionAnnuelleDao;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.services.IInscriptionAnnuelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InscriptionAnnuelleServiceImpl implements IInscriptionAnnuelleService {

    @Autowired
    private INiveauDao niveauDao;
    @Autowired
    private IInscriptionAnnuelleDao inscriptionAnnuelleDao;
    @Autowired
    private IEtudiantDao etudiantDao;

    @Override
    public List<InscriptionAnnuelle> getInscriptionAnnuelleByAnneeAndNiveau(int annee, Niveau niveau){
      return  inscriptionAnnuelleDao.getInscriptionAnnuelleByAnneeAndNiveau(annee, niveau);
    }


//    this method return list of etudents
//    take in parameter the annee and niveau
//    first get all the inscriptionAnnuell by annee and niveau
//    than get all the etudents with the same inscriptionAnnuell and niveau
    public List<Etudiant> getEtudentsByNiveauAndInscriptionAnnuelle(int annee, Long niveau){
        List<Etudiant> etudiantsList = new ArrayList<Etudiant>();
        List<InscriptionAnnuelle> inscriptionAnnuelleList = new ArrayList<InscriptionAnnuelle>();

        inscriptionAnnuelleList = getInscriptionAnnuelleByAnneeAndNiveau(annee,  niveauDao.getById(niveau));


        for ( InscriptionAnnuelle inscriptionAnnuelle: inscriptionAnnuelleList ) {
//            System.out.println(inscriptionAnnuelle.getEtudiant().getNom());
            Etudiant etudiant = inscriptionAnnuelle.getEtudiant();

            System.out.println(etudiant.getNom());
            etudiantsList.add(etudiant);
        }
//        System.out.println(etudiantsList);
        return etudiantsList;
    }

}
