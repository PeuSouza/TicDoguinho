/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devpaula.controller;

import com.devpaula.model.Tutor;
import com.devpaula.model.dao.ManagerDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Peu Souza
 */
@ManagedBean
@SessionScoped
public class loginController {
    
    private Tutor tutorLogado;
    
    public String realizarLogin(String usuario, String senha) {

        try {
                    
            Tutor tLogin = (Tutor) ManagerDao.getCurrentInstance()
                    .read("select t from Tutor t"
                            + " where t.usuario= '" + usuario
                            + "' and t.senha = '" + senha+"'", Tutor.class).get(0);
            
            
            this.tutorLogado = tLogin;
            
            return "menuTutor.xhtml";
            
        } catch (Exception e) {
            
            e.printStackTrace();
            FacesContext.getCurrentInstance()
                    .addMessage(null, 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao Logar","Usuário e/ou senha estão incorretos"));
            return null;
        }

    }
    
    public String logout(){
        this.tutorLogado = null;
        
        return "index.xhtml";
    }

    public Tutor getTutorLogado() {
        return tutorLogado;
    }

    public void setTutorLogado(Tutor tutorLogado) {
        this.tutorLogado = tutorLogado;
    }

    
    
}
