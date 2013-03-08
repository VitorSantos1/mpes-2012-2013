/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.gui;

import javax.swing.JOptionPane;
import mpes.sorteio.calendário.jornadas.liga.portugal.model.Championship;
import mpes.sorteio.calendário.jornadas.liga.portugal.model.Team;

/**
 *
 * @author vitorsantos
 */
public class SingleTeamWindow extends javax.swing.JFrame {

    private Championship c;
    private Team editableTeam;
    
    /**
     * Creates new form SingleTeamWindow
     */
    public SingleTeamWindow(Championship newC) {
        c = newC;
        initComponents();
    }
    
    public SingleTeamWindow(Championship newC, Team newEditableTeam){
        c = newC;
        editableTeam = newEditableTeam;
        initComponents();
        
        teamNameTextField.setText(editableTeam.getTeamName());
        teamDistrictComboBox.setSelectedItem((String) editableTeam.getTeamDistrict());
        teamTypeComboBox.setSelectedItem((String) editableTeam.getTeamType());
    }
    
    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        singleTeamPanel = new javax.swing.JPanel();
        teamNameLabel = new javax.swing.JLabel();
        teamNameTextField = new javax.swing.JTextField();
        teamDistrictLabel = new javax.swing.JLabel();
        teamDistrictComboBox = new javax.swing.JComboBox();
        teamTypeLabel = new javax.swing.JLabel();
        teamTypeComboBox = new javax.swing.JComboBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        teamNameLabel.setText("Nome:");

        teamDistrictLabel.setText("Distrito:");

        teamDistrictComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Aveiro", "Beja", "Braga", "Bragança", "Castelo Branco", "Coimbra", "Évora", "Faro", "Guarda", "Leiria", "Lisboa", "Porto", "Portalegre", "Santarém", "Setúbal", "Viana do Castelo", "Vila Real", "Viseu", "Açores", "Madeira" }));

        teamTypeLabel.setText("Tipo:");

        teamTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Equipa Grande", "Equipa Pequena", "Equipa \"B\" Grande", "Equipa \"B\"" }));

        okButton.setText("OK");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acceptChangesAction(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelAction(evt);
            }
        });

        org.jdesktop.layout.GroupLayout singleTeamPanelLayout = new org.jdesktop.layout.GroupLayout(singleTeamPanel);
        singleTeamPanel.setLayout(singleTeamPanelLayout);
        singleTeamPanelLayout.setHorizontalGroup(
            singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, singleTeamPanelLayout.createSequentialGroup()
                .add(35, 35, 35)
                .add(singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(singleTeamPanelLayout.createSequentialGroup()
                        .add(singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(teamNameLabel)
                            .add(teamDistrictLabel)
                            .add(teamTypeLabel))
                        .add(41, 41, 41)
                        .add(singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(singleTeamPanelLayout.createSequentialGroup()
                                .add(singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, teamTypeComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, teamDistrictComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE))
                            .add(teamNameTextField)))
                    .add(singleTeamPanelLayout.createSequentialGroup()
                        .add(cancelButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(okButton)))
                .add(41, 41, 41))
        );
        singleTeamPanelLayout.setVerticalGroup(
            singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(singleTeamPanelLayout.createSequentialGroup()
                .add(37, 37, 37)
                .add(singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(teamNameLabel)
                    .add(teamNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(teamDistrictLabel)
                    .add(teamDistrictComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(teamTypeLabel)
                    .add(teamTypeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(singleTeamPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cancelButton)
                    .add(okButton))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, singleTeamPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(singleTeamPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acceptChangesAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acceptChangesAction
        if(!((String) teamDistrictComboBox.getSelectedItem()).equals("Seleccionar...") &&
                !((String) teamTypeComboBox.getSelectedItem()).equals("Seleccionar...")){
            if(editableTeam == null){
                if(newEntryValidation()){
                    this.setVisible(false);
                    new MainWindow(c).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(this,
                    "Deverá incluir uma equipa que não tenha as mesmas características que uma já presente.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                }
            } 
            else if(editableTeam != null){
                if(editEntryValidation()){
                    this.setVisible(false);
                    new MainWindow(c).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(this,
                    "Deverá alterar uma equipa já existente.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this,
            "Deverá seleccionar um distrito e/ou um tipo para a equipa.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_acceptChangesAction

    private void cancelAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelAction
        this.setVisible(false);
        new MainWindow(c).setVisible(true);
    }//GEN-LAST:event_cancelAction

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel singleTeamPanel;
    private javax.swing.JComboBox teamDistrictComboBox;
    private javax.swing.JLabel teamDistrictLabel;
    private javax.swing.JLabel teamNameLabel;
    private javax.swing.JTextField teamNameTextField;
    private javax.swing.JComboBox teamTypeComboBox;
    private javax.swing.JLabel teamTypeLabel;
    // End of variables declaration//GEN-END:variables

    private boolean newEntryValidation() {
        String newTeamName = teamNameTextField.getText();
        
        for(Team t : c.getTeams()){
            if(t.getTeamName().equalsIgnoreCase(newTeamName)){
                return false;
            }
        }
        
        c.getTeams().add(new Team(newTeamName, (String) teamDistrictComboBox.getSelectedItem(), (String) teamTypeComboBox.getSelectedItem()));
        return true;
    }

    private boolean editEntryValidation() {
        String editTeamName = teamNameTextField.getText();
        
        for(Team t : c.getTeams()){
            if(t.getTeamName().equalsIgnoreCase(editTeamName)){
                t.setTeamName(editTeamName);
                t.setTeamDistrict((String) teamDistrictComboBox.getSelectedItem());
                t.setTeamType((String) teamTypeComboBox.getSelectedItem());
                
                return true;
            }
        }
        
        return false;
    }
}
