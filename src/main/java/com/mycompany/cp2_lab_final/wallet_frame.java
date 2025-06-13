/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cp2_lab_final;

import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1234
 */
public class wallet_frame extends javax.swing.JFrame {
    DefaultTableModel table_model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // tüm hücreler düzenlenemez
        }
    };

    DefaultTableModel table_model2 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /**
     * Creates new form wallet_frame
     */
    public wallet_frame() {
    initComponents();
    
    add_val_bttn.setVisible(false);
    
    if(userManager.getUsername().equals("admin"))
        add_val_bttn.setVisible(true);

    // Tek satır seçimi ve çakışmayı engelle
    table_users_valuables.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    table_all_valuables.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    table_users_valuables.setRowSelectionAllowed(true);
    table_users_valuables.setColumnSelectionAllowed(false);
    table_all_valuables.setRowSelectionAllowed(true);
    table_all_valuables.setColumnSelectionAllowed(false);

    table_users_valuables.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            table_all_valuables.clearSelection();
        }
    });

    table_all_valuables.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            table_users_valuables.clearSelection();
        }
    });

    
    DefaultTableModel table_model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    DefaultTableModel table_model2 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    String[] identifiers = {"id", "Name", "Unit Price", "Piece", "Total($)"};
    String[] identifiers2 = {"id", "Name", "Unit Price"};

    table_model.setColumnIdentifiers(identifiers);
    table_model2.setColumnIdentifiers(identifiers2);

    table_users_valuables.setModel(table_model);
    table_all_valuables.setModel(table_model2);

    String[] table_array = new String[5];
    String[] table_array2 = new String[3];
    double all_total = 0;
    int i = 1;

    for (int j = 1; j < walletManager.how_many_different() + 1; j++) {
        table_array[0] = String.valueOf(walletManager.nth_val_of_user(j));
        table_array[1] = valuableManager.name_of(Integer.parseInt(table_array[0]));
        table_array[2] = String.valueOf(valuableManager.price_of(Integer.parseInt(table_array[0])));


        table_array[3] = String.valueOf(walletManager.how_many(j));
table_array[4] = String.format(Locale.ENGLISH, "%.3f",
    (double) valuableManager.price_of(Integer.parseInt(table_array[0])) 
    * walletManager.how_many(j));


        all_total += valuableManager.price_of(Integer.parseInt(table_array[0])) * walletManager.how_many(j);
        table_model.addRow(table_array);
    }

   total_lbl.setText(String.format(Locale.ENGLISH, "%.3f", all_total));
balance_lbl.setText(String.valueOf(userManager.getBalance()));

    while (true) {
        if (valuableManager.name_of(i) != null && userManager.does_have(i) == -1) {
            table_array2[0] = String.valueOf(i);
            table_array2[1] = valuableManager.name_of(i);
table_array2[2] = String.valueOf(valuableManager.price_of(i));

            table_model2.addRow(table_array2);
        } else if (valuableManager.name_of(i) == null)
            break;

        i++;
    }
}



    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        total_lbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_all_valuables = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_users_valuables = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        buy_bttn = new javax.swing.JButton();
        sell_bttn = new javax.swing.JButton();
        refresh_bttn = new javax.swing.JButton();
        balance_lbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        add_val_bttn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Your Wallet");

        total_lbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setText("In your wallet:");

        table_all_valuables.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(table_all_valuables);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Wallet Balance:");

        table_users_valuables.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_users_valuables);

        jLabel4.setText("Other valuables:");

        buy_bttn.setText("Buy");
        buy_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buy_bttnActionPerformed(evt);
            }
        });

        sell_bttn.setText("Sell");
        sell_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sell_bttnActionPerformed(evt);
            }
        });

        refresh_bttn.setText("Refresh");
        refresh_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_bttnActionPerformed(evt);
            }
        });

        balance_lbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Total Valuables:");

        add_val_bttn.setText("Add Valuable");
        add_val_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_val_bttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(balance_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(buy_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(sell_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(add_val_bttn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refresh_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(total_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(balance_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sell_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(refresh_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(add_val_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buy_bttn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buy_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buy_bttnActionPerformed
       
        String selectedName = null;

    if (table_users_valuables.getSelectedRow() != -1) {
        selectedName = table_users_valuables.getValueAt(table_users_valuables.getSelectedRow(), 1).toString();
    } else if (table_all_valuables.getSelectedRow() != -1) {
        selectedName = table_all_valuables.getValueAt(table_all_valuables.getSelectedRow(), 1).toString();
    }

    buy_loadBalance_frame bframe;
    if (selectedName != null) {
        bframe = new buy_loadBalance_frame(selectedName);
    } else {
        bframe = new buy_loadBalance_frame(); // Seçim yapılmamışsa
    }

    bframe.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_buy_bttnActionPerformed

    private void sell_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sell_bttnActionPerformed
        String selectedName = null;

    if (table_users_valuables.getSelectedRow() != -1) {
        selectedName = table_users_valuables.getValueAt(table_users_valuables.getSelectedRow(), 1).toString();
    } else if (table_all_valuables.getSelectedRow() != -1) {
        selectedName = table_all_valuables.getValueAt(table_all_valuables.getSelectedRow(), 1).toString();
    }

    sell_frame sframe;
    if (selectedName != null) {
        sframe = new sell_frame(selectedName);
    } else {
        sframe = new sell_frame(); // Seçim yapılmamışsa
    }

    sframe.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_sell_bttnActionPerformed

    private void refresh_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_bttnActionPerformed
        valuableManager.change_values();
        this.dispose();
        wallet_frame frame = new wallet_frame();
        frame.setVisible(true);
    }//GEN-LAST:event_refresh_bttnActionPerformed

    private void add_val_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_val_bttnActionPerformed
        this.dispose();
        add_valuable_frame frame = new add_valuable_frame();
        frame.setVisible(true);
    }//GEN-LAST:event_add_val_bttnActionPerformed

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
            java.util.logging.Logger.getLogger(wallet_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(wallet_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(wallet_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(wallet_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new wallet_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_val_bttn;
    private javax.swing.JLabel balance_lbl;
    private javax.swing.JButton buy_bttn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refresh_bttn;
    private javax.swing.JButton sell_bttn;
    private javax.swing.JTable table_all_valuables;
    private javax.swing.JTable table_users_valuables;
    private javax.swing.JLabel total_lbl;
    // End of variables declaration//GEN-END:variables
}
