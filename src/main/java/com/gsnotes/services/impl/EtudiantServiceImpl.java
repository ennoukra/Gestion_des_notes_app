package com.gsnotes.services.impl;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Utilisateur;
import com.gsnotes.dao.IEtudiantDao;
import com.gsnotes.services.IEtudiantService;
import com.gsnotes.utils.export.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiantService {

    @Autowired
    private IEtudiantDao etudiantDao;

    @Override
    public List<Etudiant> getAllEtudiant() {
        return etudiantDao.findAll();
    }

    @Override
    public List<Etudiant> getEtudiantByNiveau() {
        return null;
    }

    @Override
    public Etudiant getEtudiantById(Long idEtudaint) {

        return etudiantDao.getById(idEtudaint);
    }

    public void deletEtudiantByid(Long id){
        etudiantDao.deleteById(id);
    }

    public ExcelExporter prepareEtudiantExport(List<Etudiant> etudiants){
        String[] columnNames = new String[] { "CNE", "Prénom", "NOM" };
        String[][] data = new String[etudiants.size()][5];

        int i = 0;
        for (Etudiant e : etudiants) {
            data[i][0] = e.getCne();
            data[i][1] = e.getPrenom();
            data[i][2] = e.getNom();
            i++;
        }

        return new ExcelExporter(columnNames, data, "Etudiants");
    }

    public void updateEtudiant(Etudiant etudiant){
        etudiantDao.save(etudiant);
    }

}
