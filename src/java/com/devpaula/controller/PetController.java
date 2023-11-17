/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devpaula.controller;

import com.devpaula.model.Pet;
import com.devpaula.model.dao.ManagerDao;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

public void inserir(String confirma){
        
        if(!this.petCadastro.getTutor1().equals(confirma)){
        
         FacesContext.getCurrentInstance().addMessage("formCadPet:txttutor", 
           new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro Severo","TUTOR NÃO PODE CADASTRAR ESSE PET!"));
         
        return;
        
        }
        
        ManagerDao.getCurrentInstance().insert(this.petCadastro);
        this.petCadastro= new Pet();
    
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage ("Novo amiguinho PET cadastrado com Sucesso!") );
    
    }


// parte relacionada ao codigo de compartilhar 

    private boolean checkboxValue;
    private String shareCode;

    public boolean isCheckboxValue() {
        return checkboxValue;
    }

    public void setCheckboxValue(boolean checkboxValue) {
        this.checkboxValue = checkboxValue;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void generateShareCode() {
        if (checkboxValue) {
            // Verifica se o atributo id contém a palavra "love"
            String id = "seu_id_do_elemento"; // Substitua com o ID do seu elemento
            if (id.contains("love")) {
                shareCode = "Código de compartilhamento gerado!";
            } else {
                shareCode = ""; // Se o ID não contiver "love", não gera o código
            }
        } else {
            shareCode = ""; // Se o checkbox não estiver marcado, não gera o código
        }
    }
}

 
