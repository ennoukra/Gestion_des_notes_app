package com.gsnotes.services;

import com.gsnotes.bo.Etudiant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IInscriptioReinscriptionService {

    public void uploadFile(MultipartFile file);

    public List<Etudiant> getExcelDataList();
}
