/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.gui;

import javax.swing.JOptionPane;
import mpes.sorteio.calendário.jornadas.liga.portugal.model.Championship;
import mpes.sorteio.calendário.jornadas.liga.portugal.support.LoadXML;
import mpes.sorteio.calendário.jornadas.liga.portugal.support.SaveToXML;

/**
 *
 * @author vitorsantos
 */
public class LoadSaveTeams extends javax.swing.JFrame {

    private Championship c;
    
    /**
     * Creates new form LoadSaveTeams
     */
    public LoadSaveTeams() {
        initComponents();
    }

    public LoadSaveTeams(Championship newC){
        c = newC;
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadSaveTeamsPanel = new javax.swing.JPanel();
        saveTeamsLabel = new javax.swing.JLabel();
        saveTeamsField = new javax.swing.JTextField();
        saveTeamsSearchLocationButton = new javax.swing.JButton();
        loadTeamsLabel = new javax.swing.JLabel();
        loadTeamsField = new javax.swing.JTextField();
        loadTeamsSearchLocationButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        loadTeamsButton = new javax.swing.JButton();
        saveTeamsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        saveTeamsLabel.setText("Gravar Equipas");

        saveTeamsSearchLocationButton.setText("Procurar...");

        loadTeamsLabel.setText("Carregar Equipas");

        loadTeamsSearchLocationButton.setText("Procurar...");

        cancelButton.setText("Cancelar");

        loadTeamsButton.setText("Carregar");
        loadTeamsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadTeamsAction(evt);
            }
        });

        saveTeamsButton.setText("Gravar");
        saveTeamsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveTeamsAction(evt);
            }
        });

        org.jdesktop.layout.GroupLayout loadSaveTeamsPanelLayout = new org.jdesktop.layout.GroupLayout(loadSaveTeamsPanel);
        loadSaveTeamsPanel.setLayout(loadSaveTeamsPanelLayout);
        loadSaveTeamsPanelLayout.setHorizontalGroup(
            loadSaveTeamsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(loadSaveTeamsPanelLayout.createSequentialGroup()
                .add(26, 26, 26)
                .add(loadSaveTeamsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(loadSaveTeamsPanelLayout.createSequentialGroup()
                        .add(saveTeamsLabel)
                        .add(18, 18, 18)
                        .add(saveTeamsField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 294, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(loadSaveTeamsPanelLayout.createSequentialGroup()
                        .add(loadTeamsLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(loadSaveTeamsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(loadSaveTeamsPanelLayout.createSequentialGroup()
                                .add(loadTeamsButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(saveTeamsButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(cancelButton))
                            .add(loadTeamsField))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(loadSaveTeamsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(saveTeamsSearchLocationButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(loadTeamsSearchLocationButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        loadSaveTeamsPanelLayout.setVerticalGroup(
            loadSaveTeamsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(loadSaveTeamsPanelLayout.createSequentialGroup()
                .add(22, 22, 22)
                .add(loadSaveTeamsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(saveTeamsLabel)
                    .add(saveTeamsField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(saveTeamsSearchLocationButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(loadSaveTeamsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(loadTeamsLabel)
                    .add(loadTeamsField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(loadTeamsSearchLocationButton))
                .add(18, 18, 18)
                .add(loadSaveTeamsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cancelButton)
                    .add(loadTeamsButton)
                    .add(saveTeamsButton))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(loadSaveTeamsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(loadSaveTeamsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadTeamsAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadTeamsAction
        LoadXML loaderEngine = new LoadXML(c, loadTeamsField.getText());
        
        if(loaderEngine.start()){
            //View operations.
        }
        else{
            //View operations.
        }
    }//GEN-LAST:event_loadTeamsAction

    private void saveTeamsAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveTeamsAction
        SaveToXML saverEngine = new SaveToXML(c, loadTeamsField.getText());
        
        if(saverEngine.start()){
            JOptionPane.showMessageDialog(this, "Gravação efectuada com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
            new MainWindow(c).setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(this, 
                    "Gravação cancelada!" + "\n" + "Motivo: " + saverEngine.getErrorMsg(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveTeamsAction

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
            java.util.logging.Logger.getLogger(LoadSaveTeams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadSaveTeams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadSaveTeams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadSaveTeams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoadSaveTeams().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel loadSaveTeamsPanel;
    private javax.swing.JButton loadTeamsButton;
    private javax.swing.JTextField loadTeamsField;
    private javax.swing.JLabel loadTeamsLabel;
    private javax.swing.JButton loadTeamsSearchLocationButton;
    private javax.swing.JButton saveTeamsButton;
    private javax.swing.JTextField saveTeamsField;
    private javax.swing.JLabel saveTeamsLabel;
    private javax.swing.JButton saveTeamsSearchLocationButton;
    // End of variables declaration//GEN-END:variables
}
