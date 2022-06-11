package com.gsnotes.services.impl;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.dao.IEtudiantDao;
import com.gsnotes.services.IInscriptioReinscriptionService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class InscriptionReinscriptionService implements IInscriptioReinscriptionService {
//    @Value("${C:.Users.21261.Documents.GI2.S4.JEE.mini Project.app_gs_notes_MiniProject")

    @Autowired
    IEtudiantDao etudiantDao;
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;


    @Value("${app.upload.file:${user.home}}")
    public String EXCEL_FILE_PATH;

    Workbook workbook;

    public void uploadFile(MultipartFile file){
        try{
            System.out.println("hello world");
            Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Could not store file \" + file.getOriginalFilename()\n" +
                    "                + \". Please try again!");
        }
    }

    public List<Etudiant> getExcelDataList(){
        List<String> list = new ArrayList<String>();

        DataFormatter dataFormatter = new DataFormatter();

        try{
             workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH + "/ennoukra.xlsx"));

        }catch(Exception e){
            e.printStackTrace();
        }

        Sheet sheet = workbook.getSheetAt(0);

        int noOfColumns = sheet.getRow(0).getLastCellNum();
        System.out.println("-------Sheet has '"+noOfColumns+"' columns------");

        for(Row row : sheet){
            for(Cell cell : row){
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }

//        List<Etudiant> etudiants = null;
        List<Etudiant> etudiants = createList(list, noOfColumns);


        // Closing the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return etudiants;

    }


    public List<Etudiant> createList(List<String> excelData, int noOfColumns){
        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();

        int i = noOfColumns;
        Long idNiveau = Long.valueOf(0);
        String typeInscription = "";
        do{
            System.out.println("hello world this");
            Etudiant etudiant = new Etudiant();
            etudiant.setCne(String.valueOf(excelData.get(i + 1)));
            etudiant.setNom(String.valueOf(excelData.get(i + 2)));
            etudiant.setPrenom(String.valueOf(excelData.get(i + 3)));
            idNiveau = Long.valueOf(String.valueOf(excelData.get(i + 4)));
            typeInscription = String.valueOf(excelData.get(i + 5));

            etudiants.add(etudiant);
            etudiantDao.save(etudiant);
            i = i + (noOfColumns);

        }while(i < excelData.size());
        return etudiants;
    }
}
