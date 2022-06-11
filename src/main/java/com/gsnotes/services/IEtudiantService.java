package com.gsnotes.services;

import com.gsnotes.bo.Etudiant;

import java.util.List;

public interface IEtudiantService {

//    return all the etudiant in the data base.
    public List<Etudiant> getAllEtudiant();

//    return all the etudaint with the same niveau.
    public List<Etudiant> getEtudiantByNiveau();

//    return etudents by id
    public Etudiant getEtudiantById(Long idEtudiant);

    public void deletEtudiantByid(Long id);
}
