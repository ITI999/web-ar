package com.practic.controller;

import com.practic.entity.Model;
import com.practic.entity.ModelSet;
import com.practic.repos.ModelRepo;
import com.practic.repos.SetRepo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ModelRepo modelRepo;

    @Autowired
    private SetRepo setRepo;

    @GetMapping("/models")
    public String models(
            org.springframework.ui.Model model
    ){
        model.addAttribute("sets", setRepo.findAll());
        return "models";
    }

    @PostMapping("/models")
    public String addSet(
            @RequestParam("title") String title,
            @RequestParam("description") String descriprion,
            org.springframework.ui.Model model
    ){
        ModelSet modelSet = new ModelSet(title,descriprion);
        setRepo.save(modelSet);
        File uploadDir = new File(uploadPath + "/" + modelSet.getId() + "/");
        if(!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        model.addAttribute("sets", setRepo.findAll());
        return "models";
    }

    @PostMapping("/set/{set}")
    public String addModel(
        @RequestParam("modelFile") MultipartFile modelFile,
        @RequestParam("letter") String letter,
        @RequestParam("type") String type,
        @RequestParam("scale") String scale,
        @RequestParam("mtlFile") MultipartFile mtlFile,
        @RequestParam(name = "uploadingFiles", required = false) MultipartFile[] uploadingFiles,
        org.springframework.ui.Model model,
        @PathVariable ModelSet set
    ) throws IOException {
        if(modelFile != null && !modelFile.isEmpty()){

            Model arModel = new Model();
            modelRepo.save(arModel);
            arModel.setLetter(letter);
            arModel.setType(type);
            arModel.setOriginalModelFileName(modelFile.getOriginalFilename());
            arModel.setScale(scale);
            arModel.setSet_id(set);

            File uploadDir = new File(uploadPath + "/" + set.getId() + "/" + arModel.getId() );

            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            modelFile.transferTo(new File(uploadDir + "/" + modelFile.getOriginalFilename()));


            if(mtlFile != null && !mtlFile.isEmpty()){
                arModel.setOriginalMtlFileName(mtlFile.getOriginalFilename());
                mtlFile.transferTo(new File(uploadDir + "/" + mtlFile.getOriginalFilename()));
            }

            if(uploadingFiles != null ) {
                for (MultipartFile uploadedFile : uploadingFiles) {
                    if(uploadedFile != null && !uploadedFile.isEmpty()) {
                        uploadedFile.transferTo(new File(uploadDir + "/" + uploadedFile.getOriginalFilename()));
                    }
                }
            }

            modelRepo.save(arModel);

        }
        model.addAttribute("models", modelRepo.findBySet_id(set.getId()));
        return "set";
    }

    @GetMapping("/set/{set_id}")
    public String getSetAr(
            org.springframework.ui.Model model,
            @PathVariable Long set_id
            ){
        model.addAttribute("models", modelRepo.findBySet_id(set_id));
        return "set";
    }

    @GetMapping("/models/delete/{set}")
    public String deleteSet(
            @PathVariable ModelSet set
    ) throws IOException {
        modelRepo.deleteAll(modelRepo.findBySet_id(set.getId()));
        setRepo.delete(set);
        FileUtils.deleteDirectory(new File(uploadPath + "/" + set.getId() ));

        return "redirect:/models/";
    }

    @GetMapping("/set/delete/{model}")
    public String deleteSet(
            @PathVariable Model model
    ) throws IOException {
        modelRepo.delete(model);
        FileUtils.deleteDirectory(new File(uploadPath + "/" + model.getSet_id().getId() + "/" + model.getId() ));

        return "redirect:/set/" + model.getId();
    }


    @GetMapping("/set/edit/{arModel}")
    public String editModel(
            @PathVariable Model arModel,
            org.springframework.ui.Model model
    ){
        model.addAttribute("model",arModel);
        return "editModel";
    }

    @PostMapping("/set/edit/{arModel}")
    public String updateModel(
            @RequestParam("letter") String letter,
            @RequestParam("scale") String scale,
            @PathVariable Model arModel,
            org.springframework.ui.Model model
    ){
        arModel.setScale(scale);
        arModel.setLetter(letter);
        modelRepo.save(arModel);

        model.addAttribute("model",arModel);
        return "redirect:/set/" + arModel.getSet_id().getId();
    }
}
