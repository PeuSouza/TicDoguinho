/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devpaula.controller;

import com.devpaula.model.Tutor;
import com.devpaula.model.dao.ManagerDao;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Peu Souza
 */

@ManagedBean(name="tutorController")
@SessionScoped // salva os objetos na sessão
public class TutorController {
    
    private Tutor tutorCadastro; 
    private Tutor Selection;

    public Tutor getTutorCadastro() {
        return tutorCadastro;
    }

    public void setTutorCadastro(Tutor tutorCadastro) {
        this.tutorCadastro = tutorCadastro;
    }

    
    public Tutor getSelection() {
        return Selection;
    }

    public void setSelection(Tutor Selection) {
        this.Selection = Selection;
    }
    
    
    @PostConstruct 
    public void init(){
        this.tutorCadastro = new Tutor();
    }
    
    public void inserir(String confirma){
        
        if(!this.tutorCadastro.getSenha().equals(confirma)){
        
         FacesContext.getCurrentInstance().addMessage("formCadTutor:pswSenha", 
           new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro Severo","A senha e a confirmação não batem!"));
         
        return;
        
        }
        
        ManagerDao.getCurrentInstance().insert(this.tutorCadastro);
        this.tutorCadastro= new Tutor();
    
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage ("Novo Tutor cadastrado com Sucesso!") );
    
    }
    
//     public List<Tutor> responsaveis(){
//        
//        List<Tutor> responsaveis= null;
//        
//        Tutor = ManagerDao.getCurrentInstance()
//                .read("select t from Tutor t", Tutor.class);
//        
//        return Tutor;
//        
//    }
}
