/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devpaula.controller;

import com.devpaula.model.Tutor;
import com.devpaula.model.dao.ManagerDao;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
       
        ManagerDao.getCurrentInstance().insert(this.tutorCadastro);
        this.tutorCadastro= new Tutor();
    
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage ("Novo Tutor cadastrado com Sucesso!") );
          
        // Redirecionamento para a página menuTutor.xhtml
         FacesContext facesContext = FacesContext.getCurrentInstance();
         ExternalContext externalContext = facesContext.getExternalContext();
         
    
         try {
        externalContext.redirect("menuTutor.xhtml");
        } catch (IOException e) {
        e.printStackTrace();
        // Lidar com exceção, se necessário
    }
        if(!this.tutorCadastro.getSenha().equals(confirma)){
        
         FacesContext.getCurrentInstance().addMessage("formCadTutor:pswSenha", 
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro Severo","A senha e a confirmação não batem!"));
         
        return ;
        }
        
        
         
    }
     public List<Tutor> readTutores(){
        
        List<Tutor> Tutores = null;
        
        Tutores = ManagerDao.getCurrentInstance()
                .read("select t from tutor t", Tutor.class);
        
        return Tutores;
     }

    public void editarPerfil(){
        
        ManagerDao.getCurrentInstance().update(this.Selection);
        
        FacesContext.getCurrentInstance()
                .addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso!", "Tutor alterado com Sucesso"));
        
        
    }
    public void deletar(){
        
        ManagerDao.getCurrentInstance().delete(this.Selection);
        
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Sucesso!", " Você deletou seu usuario"));
        
    }
    
    public void alterarSenha(String senha, String novaSenha, String confirma){
        
        //código para recuperar qualquer atributo na sessão
        Tutor uLogado = ((loginController)((HttpSession)FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true))
                .getAttribute("loginController")).getTutorLogado();
        
    
        if(!uLogado.getSenha().equals(senha)){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("A senha digitada está incorreta. "
                            + "Por favor, digite novamente de forma correta"));
            return ;
        }
        
        if(!novaSenha.equals(confirma)){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("A nova senha não bate com a confirmação. "
                            + "Por favor, digite novamente de forma correta"));
            return ;
        }
        
        uLogado.setSenha(novaSenha);
        
        ManagerDao.getCurrentInstance().update(uLogado);
        
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Senha alterada com sucesso!"));
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
