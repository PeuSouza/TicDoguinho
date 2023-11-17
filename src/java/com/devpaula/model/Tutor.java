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
//import javax.persistence.OneToMany;

/**
 *
 * @author Peu Souza
 */

@Entity
public class Tutor {

    
    @Id //toda entidade precisa de um id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // cria uma chave primaria para a coluna onde se encontra o id
    private int id;
    
    @Column(length= 80, nullable= true) // definindo o tamanho da coluna e que não pode ser nula;
    private String nome;
    private String usuario; // nickname
    private String senha;
    private String email;
    
    //@ManyToMany
    private Pet Pet;
  //  @OneToMany(mappedBy = "tutor")
    private List<Pet> pets;
    
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMamae() {
        return Mamae;
    }

    public void setMamae(boolean Mamae) {
        this.Mamae = Mamae;
    }
    boolean Mamae = true;
    
    public Pet getPet() {
        return Pet;
    }

    public void setPet(Pet Pet) {
        this.Pet = Pet;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
