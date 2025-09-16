package com.mycompany.happyfarm;

import javax.swing.JOptionPane;

public class Principal {
 public static void main(String[] args) {
 String menu = "1-Cadastrar\n2-Atualizar\n3-Apagar\n4-Listar\n0-Sair";
  int op;
    do{
          op = Integer.parseInt(JOptionPane.showInputDialog(menu));
              switch (op){
            case 1:
                 String cnpj = JOptionPane.showInputDialog("CNPJ?");
                 String emailCorporativo = JOptionPane.showInputDialog("E-mail Corporativo?");
                 String fone = JOptionPane.showInputDialog("Telefone?");
                 String endereco = JOptionPane.showInputDialog("Endereço?");
                 String bairro = JOptionPane.showInputDialog("Bairro?");
                 String numeroEndereco = JOptionPane.showInputDialog("Numero do Endereço?");
                 int numeroEnderecoInt = Integer.parseInt(numeroEndereco);
                 String cidade = JOptionPane.showInputDialog("Cidade?");
                 String estado = JOptionPane.showInputDialog("Estado?");
                 String cep = JOptionPane.showInputDialog("Cep?");
                 String senha = JOptionPane.showInputDialog("Senha?");
          
                 Fazendeiro f = new Fazendeiro (); 
                 f.setCnpj(cnpj);  
                 f.setEmailCorporativo(emailCorporativo); 
                 f.setFone(fone);  
                 f.setEndereco(endereco);  
                 f.setBairro(bairro); 
                 f.setNumeroEndereco(numeroEnderecoInt); 
                 f.setCidade(cidade);  
                 f.setEstado(estado); 
                 f.setCep(cep); 
                 f.setSenha(senha);  
                 f.inserir();
            break;

            case 2:
                //inserir aqui o ATUALIZAR
            break;
            case 3:
                {  int codigo =  
                Integer.parseInt(JOptionPane.showInputDialog("Codigo?")); 
                Fazendeiro fap = new Fazendeiro();
                fap.setCodigo(codigo); 
                fap.apagar();     
                break;              
            }    
            case 4:
                {
 Fazendeiro fl = new Fazendeiro();
 fl.listar();

                
            break;
          }
            case 0:
            break;           
            default:   
               JOptionPane.showMessageDialog(null, "Opção inválida"); }
              
   }while (op != 0);  
}
}

