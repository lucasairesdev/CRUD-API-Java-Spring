package com.example.crud.view.model;

public class UsuarioResponse {
      //#region Atributos
      private Integer id;
 
      private String nome;
  
      private String email;
  
      private Integer idade;
      //#endregion
  
      
      
      //#region Metodos Getters e Setters
      public Integer getId() {
          return id;
      }
      
      public void setId(Integer id) {
          this.id = id;
      }
      
      public String getNome() {
          return nome;
      }
      
      public void setNome(String nome) {
          this.nome = nome;
      }
      
      public String getEmail() {
          return email;
      }
      
      public void setEmail(String email) {
          this.email = email;
      }
      
      public Integer getIdade() {
          return idade;
      }
      
      public void setIdade(Integer idade) {
          this.idade = idade;
      }
      //#endregion
      
}
