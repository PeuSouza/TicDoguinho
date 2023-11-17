/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devpaula.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;

/**
 *
 * @author Peu Souza
 */
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
    @Column (length= 60, nullable= true)
    private String nome;
    private String mesAnoNascimento;
    private String porte;
    private String codigoPartilhar;
    private Tutor tutor1;
    private Tutor tutor2;

  
    public List<Tutor> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<Tutor> responsaveis) {
        this.responsaveis = responsaveis;
    }
   // @OneToMany
    private List<Tutor> responsaveis;
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMesAnoNascimento() {
        return mesAnoNascimento;
    }

    public void setMesAnoNascimento(String mesAnoNascimento) {
        this.mesAnoNascimento = mesAnoNascimento;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getCodigoPartilhar() {
        return codigoPartilhar;
    }

    public void setCodigoPartilhar(String codigoPartilhar) {
        this.codigoPartilhar = codigoPartilhar;
    }

    
    public Tutor getTutor1() {
        return tutor1;
    }

    public void setTutor1(Tutor tutor1) {
        this.tutor1 = tutor1;
    }

    public Tutor getTutor2() {
        return tutor2;
    }

    public void setTutor2(Tutor tutor2) {
        this.tutor2 = tutor2;
    }
  
    // Método para gerar um novo código usando o ID e a palavra "love"
    public String gerarCodigoCompartilhamento() {
        String codigo = "love_" + this.id; // Concatena "love_" com o ID do pet
        return codigo;
    }

    public void setTutor(Tutor tutor1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}

