/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.gui;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import mpes.sorteio.calendário.jornadas.liga.portugal.genetic.algorithm.generation.GenerationLauncher;
import mpes.sorteio.calendário.jornadas.liga.portugal.model.Championship;
import mpes.sorteio.calendário.jornadas.liga.portugal.model.Team;

/**
 *
 * @author vitorsantos
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * A championship.
     */
    private Championship c;
    private DefaultListModel dlm;
    private String algorithmType = "";

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        c = new Championship();
        initComponents();
    }

    public MainWindow(Championship newC) {
        c = newC;
        initComponents();
    }

    //TODO: Implement MainWindow Constructor with the complete set of teams as a parameter.
    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        mainPanel = new javax.swing.JPanel();
        teamManagementPanel = new javax.swing.JPanel();
        addTeamButton = new javax.swing.JButton();
        editTeamButton = new javax.swing.JButton();
        deleteTeamButton = new javax.swing.JButton();
        teamListScrollpane = new javax.swing.JScrollPane();
        teamList = new javax.swing.JList();
        dlm = new DefaultListModel();

        for(Team t : c.getTeams()){
            dlm.addElement(t.getTeamName());
        }

        teamList = new JList(dlm);
        loadSaveTeamsButton = new javax.swing.JButton();
        calendarPanel = new javax.swing.JPanel();
        calendarScrollPane = new javax.swing.JScrollPane();
        calendarTable = new javax.swing.JTable();
        optionsButton = new javax.swing.JButton();
        generateCalendarButton = new javax.swing.JButton();

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addTeamButton.setText("Adicionar Equipa");
        addTeamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addTeamAction(evt);
            }
        });

        editTeamButton.setText("Editar Equipa");
        editTeamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTeamAction(evt);
            }
        });

        deleteTeamButton.setText("Apagar Equipa");
        deleteTeamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteTeamAction(evt);
            }
        });

        teamListScrollpane.setViewportView(teamList);

        loadSaveTeamsButton.setText("Gravar/Carregar");
        loadSaveTeamsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadSaveTeamsAction(evt);
            }
        });

        org.jdesktop.layout.GroupLayout teamManagementPanelLayout = new org.jdesktop.layout.GroupLayout(teamManagementPanel);
        teamManagementPanel.setLayout(teamManagementPanelLayout);
        teamManagementPanelLayout.setHorizontalGroup(
            teamManagementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(teamManagementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(teamManagementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(addTeamButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(editTeamButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(deleteTeamButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(loadSaveTeamsButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(teamListScrollpane))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        teamManagementPanelLayout.setVerticalGroup(
            teamManagementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(teamManagementPanelLayout.createSequentialGroup()
                .add(14, 14, 14)
                .add(addTeamButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(teamListScrollpane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(editTeamButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(deleteTeamButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(loadSaveTeamsButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        calendarTable.setAutoCreateColumnsFromModel(false);
        calendarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        calendarScrollPane.setViewportView(calendarTable);

        optionsButton.setText("Opções");
        optionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optionsAction(evt);
            }
        });

        generateCalendarButton.setText("Gerar Calendário");
        generateCalendarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generateCalendarAction(evt);
            }
        });

        org.jdesktop.layout.GroupLayout calendarPanelLayout = new org.jdesktop.layout.GroupLayout(calendarPanel);
        calendarPanel.setLayout(calendarPanelLayout);
        calendarPanelLayout.setHorizontalGroup(
            calendarPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(calendarPanelLayout.createSequentialGroup()
                .add(calendarPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(calendarScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .add(calendarPanelLayout.createSequentialGroup()
                        .add(optionsButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(generateCalendarButton)))
                .addContainerGap())
        );
        calendarPanelLayout.setVerticalGroup(
            calendarPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(calendarPanelLayout.createSequentialGroup()
                .add(14, 14, 14)
                .add(calendarPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(optionsButton)
                    .add(generateCalendarButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(calendarScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 345, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .add(teamManagementPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(calendarPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(teamManagementPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(calendarPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(mainPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(mainPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addTeamAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTeamAction
        SingleTeamWindow stw = new SingleTeamWindow(c);
        this.setVisible(false);
        stw.setVisible(true);
    }//GEN-LAST:event_addTeamAction

    private void editTeamAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTeamAction
        String teamNameToSearch = (String) teamList.getSelectedValue();
        Team teamToEdit = new Team();

        for (Team t : c.getTeams()) {
            if (t.getTeamName().equalsIgnoreCase(teamNameToSearch)) {
                teamToEdit = t;
                break;
            }
        }

        if (!teamToEdit.getTeamName().equals("")) {
            SingleTeamWindow stw = new SingleTeamWindow(c, teamToEdit);
            this.setVisible(false);
            stw.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Deverá seleccionar uma equipa para edição.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editTeamAction

    private void deleteTeamAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteTeamAction
        Object[] options = {"Sim", "Não"};
        int optionIndex = JOptionPane.showOptionDialog(this,
                "Tem a certeza desta operação?",
                "Confirmação de Acção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (optionIndex == JOptionPane.YES_OPTION) {
            String teamNameToSearch = (String) teamList.getSelectedValue();
            ArrayList<Team> teams = c.getTeams();

            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getTeamName().equalsIgnoreCase(teamNameToSearch)) {
                    teams.remove(i);
                    dlm.removeElementAt(i);
                    break;
                }
            }
        }
    }//GEN-LAST:event_deleteTeamAction

    private void loadSaveTeamsAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadSaveTeamsAction
        new LoadSaveTeams(c).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_loadSaveTeamsAction

    private void optionsAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsAction
        Object[] possibilities = {"Algoritmo genético orientado a equipas visitadas",
            "Algoritmo genético orientado a jornadas",
            "Pesquisa Tabu"};

        algorithmType = (String) JOptionPane.showInputDialog(this,
                "Escolha o algoritmo que pretende utilizar para gerar o calendário:",
                "Opção de geração",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Seleccionar...");

        if (algorithmType != null) {
            if (algorithmType.equals(possibilities[0])) {
                algorithmType = "GA-HT";
            } else if (algorithmType.equals(possibilities[1])) {
                algorithmType = "GA-M";
            } else if (algorithmType.equals(possibilities[2])) {
                algorithmType = "TS";
            }
        }
    }//GEN-LAST:event_optionsAction

    private void generateCalendarAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateCalendarAction
        GenerationLauncher gl = new GenerationLauncher(c, algorithmType);

        //At the end of generation, the main window should be able to print the results into the jTable.
        //Also, printing metadata like number os generations and time consumed must be useful to show...
    }//GEN-LAST:event_generateCalendarAction

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTeamButton;
    private javax.swing.JPanel calendarPanel;
    private javax.swing.JScrollPane calendarScrollPane;
    private javax.swing.JTable calendarTable;
    private javax.swing.JButton deleteTeamButton;
    private javax.swing.JButton editTeamButton;
    private javax.swing.JButton generateCalendarButton;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JButton loadSaveTeamsButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton optionsButton;
    private javax.swing.JList teamList;
    private javax.swing.JScrollPane teamListScrollpane;
    private javax.swing.JPanel teamManagementPanel;
    // End of variables declaration//GEN-END:variables
}
