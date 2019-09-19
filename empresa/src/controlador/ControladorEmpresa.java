/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoEmpresa;
import javax.swing.JOptionPane;
import modelo.Empresa;
import tela.manutencao.ManutencaoEmpresa;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class ControladorEmpresa {

    public static void inserir(ManutencaoEmpresa man) {
        Empresa objeto = new Empresa();
        objeto.setNome_fantasia(man.jtfnomefantasia.getText());
        objeto.setRazao_social(man.jtfrazaosocial.getText());

        boolean resultado = DaoEmpresa.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
                atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
            }
            man.dispose();//fechar a tela da manutençãoF
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void alterar(ManutencaoEmpresa man) {
        Empresa objeto = new Empresa();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfcodigo.getText()));
        objeto.setNome_fantasia(man.jtfnomefantasia.getText());
        objeto.setRazao_social(man.jtfrazaosocial.getText());

        boolean resultado = DaoEmpresa.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
                atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
            }
            man.dispose();//fechar a tela da manutençãoF
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void excluir(ManutencaoEmpresa man) {
        Empresa objeto = new Empresa();
        objeto.setCodigo(Integer.parseInt(man.jtfcodigo.getText())); //só precisa definir a chave primeira

        boolean resultado = DaoEmpresa.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
                atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
            }
            man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Código");
        modelo.addColumn("Nome Fantasia");
        modelo.addColumn("Razão Social");
        List<Empresa> resultados = DaoEmpresa.consultar();
        for (Empresa objeto : resultados) {
            Vector linha = new Vector();

            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome_fantasia());
            linha.add(objeto.getRazao_social());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }

    public static void atualizaCampos(ManutencaoEmpresa man, int pk) {
        Empresa objeto = DaoEmpresa.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfcodigo.setText(objeto.getCodigo().toString());
        man.jtfnomefantasia.setText(objeto.getNome_fantasia());
        man.jtfrazaosocial.setText(objeto.getRazao_social());

        man.jtfcodigo.setEnabled(false); //desabilitando o campo código
        man.btninserir.setEnabled(false); //desabilitando o botão adicionar
    }

}
