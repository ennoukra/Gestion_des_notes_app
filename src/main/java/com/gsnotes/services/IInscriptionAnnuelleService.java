package com.gsnotes.services;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;

import java.util.List;

public interface IInscriptionAnnuelleService {

//    select all the inscriptionAnnuelle by annee
    public List<InscriptionAnnuelle> getInscriptionAnnuelleByAnneeAndNiveau(int annee, Niveau niveau);
    public List<Etudiant> getEtudentsByNiveauAndInscriptionAnnuelle(int annee, Long niveau);

}
