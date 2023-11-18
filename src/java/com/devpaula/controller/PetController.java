/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devpaula.controller;

import com.devpaula.model.Pet;
import com.devpaula.model.Tutor;
import com.devpaula.model.dao.ManagerDao;
import static com.sun.faces.facelets.util.Path.context;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Peu Souza
 */

@ManagedBean(name="petController")
@SessionScoped // salva os objetos na sessão
public class PetController {
    private Pet petCadastro;
    private Pet Selection;
    private String codigoCompartilhamento;

    public Pet getPetCadastro() {
        return petCadastro;
    }

    public void setPetCadastro(Pet petcadastro) {
        this.petCadastro = petcadastro;
    }

    public Pet getSelection() {
        return Selection;
    }

    public void setSelection(Pet Selection) {
        this.Selection = Selection;
    }

    public String getCodigoCompartilhamento() {
        return codigoCompartilhamento;
    }

    public void setCodigoCompartilhamento(String codigoCompartilhamento) {
        this.codigoCompartilhamento = codigoCompartilhamento;
    }
    
@PostConstruct
public void init(){
        this.petCadastro = new Pet();
    }

public String inserir() {
      Tutor tutorLogado = ((loginController)((HttpSession)FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true))
                .getAttribute("loginController")).getTutorLogado();  
    
                this.petCadastro.setTutor1(tutorLogado); // Associar o tutor ao pet
                ManagerDao.getCurrentInstance().insert(this.petCadastro); // Inserir no banco

                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage ("Novo pet cadastrado com Sucesso!") );
 
                  return "menuTutor";
}
public String inserircodigo( String codpartilhar){
     Tutor tutorLogado = ((loginController)((HttpSession)FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true))
                .getAttribute("loginController")).getTutorLogado();  
 
     Pet pet = (Pet)ManagerDao.getCurrentInstance()
             .read("select p from Pet p where p.codigoPartilhar = '"+ codpartilhar +"'",  Pet.class).get(0);

     this.petCadastro.setTutor2(tutorLogado); // Associar o tutor ao pet
                ManagerDao.getCurrentInstance().update(this.petCadastro); // Inserir no banco

                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage ("pet compartilhado com Sucesso!") );
 
     
     return "menuTutor";
}
// parte relacionada ao codigo de compartilhar 

//    private boolean checkboxValue;
//    private String shareCode;
//
//    public boolean isCheckboxValue() {
//        return checkboxValue;
//    }
//
//    public void setCheckboxValue(boolean checkboxValue) {
//        this.checkboxValue = checkboxValue;
//    }
//
//    public String getShareCode() {
//        return shareCode;
//    }
//
//   public void generateShareCode() {
//    if (checkboxValue && Selection != null) {
//        int petId = Selection.getId(); // Obtendo o ID do pet selecionado
//        String codigoCompartilhamento = "love_" + petId; // Gerando o código com base no ID
//
//        // Defina o código gerado para a variável shareCode
//        shareCode = codigoCompartilhamento;
//
//        System.out.println("Código de compartilhamento gerado: " + shareCode);
//    } else {
//        shareCode = ""; // Se não houver pet selecionado ou checkbox desmarcado, define o código como vazio
//    }
//   }
}

 
