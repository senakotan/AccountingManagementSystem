
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserPanel extends javax.swing.JFrame {

    private String username;

    public UserPanel(String username) {
        initComponents();
        this.username = username;
        setTitle("Hoş geldiniz: " + username + " - Muhasebe Paneli");

        String[] paraBirimleri = {"TL", "USD", "EURO"};
        fillComboBox(cmbCurrency, paraBirimleri);
        loadTransactions();

        table.getSelectionModel().addListSelectionListener(e -> {
            if (table.getSelectedRow() != -1) {
                seciliIslemiFormaYaz();
            }
        });

    }

    //GENERİC METHOD
    private <T> void fillComboBox(javax.swing.JComboBox<T> comboBox, T[] items) {
        comboBox.removeAllItems();
        for (T item : items) {
            comboBox.addItem(item);
        }
    }

    private void loadTransactions() {
        try (Connection conn = DBHelper.getConnection()) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            String selectQuery = "SELECT t.id, t.date, t.type, t.category, t.amount, t.description, t.currency "
                    + "FROM transactions t JOIN users u ON t.user_id = u.id WHERE u.username = ?";
            PreparedStatement ps = conn.prepareStatement(selectQuery);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("date"),
                    rs.getString("type"),
                    rs.getString("category"),
                    rs.getDouble("amount"),
                    rs.getString("description"),
                    rs.getString("currency")
                });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "İşlem gerçekleştirilemedi. Lütfen tekrar deneyin.");
        }
    }

    private void seciliIslemiFormaYaz() {
        int row = table.getSelectedRow();
        if (row == -1) {
            return;
        }

        //Seçilen satırdaki bilgileri alalım.
        String type = table.getValueAt(row, 2).toString();
        String category = table.getValueAt(row, 3).toString();
        String amountStr = table.getValueAt(row, 4).toString();
        String description = table.getValueAt(row, 5).toString();
        String currency = table.getValueAt(row, 6).toString();

        if (type.equals("Income")) {
            rdbIncome.setSelected(true);
        } else if (type.equals("Expense")) {
            rdbExpense.setSelected(true);
        }

        categoryList.setSelectedValue(category, true);

        try {
            double amount = Double.parseDouble(amountStr);
            spinnerAmount.setValue(amount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tutar değeri geçersiz.");
            spinnerAmount.setValue(0);
        }

        txtDescription.setText(description);
        cmbCurrency.setSelectedItem(currency);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rdbIncome = new javax.swing.JRadioButton();
        rdbExpense = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        categoryList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbCurrency = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        islemEkle = new javax.swing.JButton();
        islemSil = new javax.swing.JButton();
        islemGuncelle = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        analizGoruntule = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        spinnerAmount = new javax.swing.JSpinner();
        graphicsPanel = new javax.swing.JPanel();
        newPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        acikMod = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        koyuMod = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        varsayilanMod = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        cikis = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        giriseDon = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kullanıcı-İslemler");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1711, 687, -1, -1));

        jPanel1.setBackground(new java.awt.Color(202, 228, 218));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KULLANICI GELİR GİDER İSLEMLERİ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Tür:");

        buttonGroup1.add(rdbIncome);
        rdbIncome.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rdbIncome.setText("Income");

        buttonGroup1.add(rdbExpense);
        rdbExpense.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rdbExpense.setText("Expense");
        rdbExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbExpenseActionPerformed(evt);
            }
        });

        categoryList.setBackground(new java.awt.Color(252, 248, 247));
        categoryList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Kira", "Market", "Ulaşım", "Fatura", "Maaş", "Diğer" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(categoryList);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Kategori:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Tutar:");

        cmbCurrency.setBackground(new java.awt.Color(252, 247, 247));
        cmbCurrency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TL", "USD", "EURO" }));
        cmbCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCurrencyActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Para birimi:");

        txtDescription.setBackground(new java.awt.Color(252, 247, 247));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Açıklama:");

        islemEkle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        islemEkle.setText("İSLEM EKLE");
        islemEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                islemEkleActionPerformed(evt);
            }
        });

        islemSil.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        islemSil.setText("İSLEM SİL");
        islemSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                islemSilActionPerformed(evt);
            }
        });

        islemGuncelle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        islemGuncelle.setText("İSLEM GÜNCELLE");
        islemGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                islemGuncelleActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/twemoji--money-with-wings.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(233, 245, 248));

        table.setBackground(new java.awt.Color(255, 249, 249));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tarih", "Tür", "Kategori", "Tutar", "Aciklama", "Para Birimi"
            }
        ));
        table.setFocusable(false);
        table.setGridColor(new java.awt.Color(246, 242, 242));
        jScrollPane1.setViewportView(table);

        analizGoruntule.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        analizGoruntule.setText("ANALİZ GÖRÜNTÜLE");
        analizGoruntule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizGoruntuleActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconoir--reports.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(analizGoruntule, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(analizGoruntule, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(66, 66, 66))))
        );

        graphicsPanel.setLayout(null);

        newPanel.setBackground(new java.awt.Color(255, 255, 255));
        newPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(islemEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(islemSil, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdbIncome)
                        .addGap(49, 49, 49)
                        .addComponent(rdbExpense)
                        .addGap(198, 198, 198)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(newPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(graphicsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(1416, 1416, 1416))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(islemGuncelle)
                                    .addGap(1044, 1044, 1044))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(47, 47, 47)
                                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rdbIncome)
                    .addComponent(rdbExpense))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(spinnerAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(islemEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(islemSil, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(islemGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel9)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graphicsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 822));

        jMenu4.setText("Görünüm");

        acikMod.setText("Açık Mod");
        acikMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acikModActionPerformed(evt);
            }
        });
        jMenu4.add(acikMod);
        jMenu4.add(jSeparator2);

        koyuMod.setText("Koyu Mod");
        koyuMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koyuModActionPerformed(evt);
            }
        });
        jMenu4.add(koyuMod);
        jMenu4.add(jSeparator3);

        varsayilanMod.setText("Varsayılan Görünüm");
        varsayilanMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varsayilanModActionPerformed(evt);
            }
        });
        jMenu4.add(varsayilanMod);
        jMenu4.add(jSeparator1);

        jMenuBar1.add(jMenu4);

        jMenu1.setText("Çıkış");

        cikis.setText("Çıkış");
        cikis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisActionPerformed(evt);
            }
        });
        jMenu1.add(cikis);
        jMenu1.add(jSeparator4);

        giriseDon.setText("Giris Ekranına Dön");
        giriseDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giriseDonActionPerformed(evt);
            }
        });
        jMenu1.add(giriseDon);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdbExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbExpenseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbExpenseActionPerformed

    private void islemEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_islemEkleActionPerformed
        // TODO add your handling code here:
        String type = rdbIncome.isSelected() ? "Income" : rdbExpense.isSelected() ? "Expense" : null;
        String category = categoryList.getSelectedValue();
        String description = txtDescription.getText().trim();
        String currency = cmbCurrency.getSelectedItem().toString();
        Object spinnerValue = spinnerAmount.getValue();

        if (type == null || category == null || spinnerValue == null || currency == null || currency.isEmpty()) { //Herhangi bir tanesi bossa 
            JOptionPane.showMessageDialog(this, "Tüm alanlar doldurulmalı.");
            return;
        }

        if (!(spinnerValue instanceof Number)) {
            JOptionPane.showMessageDialog(this, "Geçerli bir tutar girin.");
            return;
        }

        double amount = ((Number) spinnerValue).doubleValue();

        try (Connection conn = DBHelper.getConnection()) {
            String query = "INSERT INTO transactions (type, category, amount, description, currency, user_id, date) "
                    + "VALUES (?, ?, ?, ?, ?, (SELECT id FROM users WHERE username = ?), CURRENT_DATE)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, type);
            ps.setString(2, category);
            ps.setDouble(3, amount);
            ps.setString(4, description);
            ps.setString(5, currency);
            ps.setString(6, username);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "İşlem eklendi.");

            loadTransactions();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "İşlem gerçekleştirilemedi. Lütfen tekrar deneyin.");
        }

        formTemizle();
    }//GEN-LAST:event_islemEkleActionPerformed

    private void islemSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_islemSilActionPerformed
        // TODO add your handling code here:
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silmek için bir işlem seçin.");
            return;
        }

        int id = (int) table.getModel().getValueAt(selectedRow, 0);

        try (Connection conn = DBHelper.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM transactions WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "İşlem silindi.");

            loadTransactions();

            formTemizle();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "İşlem gerçekleştirilemedi. Lütfen tekrar deneyin.");
        }
    }//GEN-LAST:event_islemSilActionPerformed

    private void islemGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_islemGuncelleActionPerformed
        // TODO add your handling code here:

        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Güncellemek için bir işlem seç.");
            return;
        }

        String type = rdbIncome.isSelected() ? "Income" : rdbExpense.isSelected() ? "Expense" : null;
        String category = categoryList.getSelectedValue();
        String description = txtDescription.getText().trim();
        String currency = cmbCurrency.getSelectedItem().toString();
        Object spinnerValue = spinnerAmount.getValue();

        if (type == null || category == null || spinnerValue == null) {
            JOptionPane.showMessageDialog(this, "Tüm alanlar doldurulmalı.");
            return;
        }

        if (!(spinnerValue instanceof Number)) {
            JOptionPane.showMessageDialog(this, "Geçerli bir tutar girin.");
            return;
        }

        double amount = ((Number) spinnerValue).doubleValue();


        int id = (int) table.getModel().getValueAt(selectedRow, 0);

        try (Connection conn = DBHelper.getConnection()) {
            String updateQuery = "UPDATE transactions SET type = ?, category = ?, amount = ?, description = ?, currency = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(updateQuery);
            ps.setString(1, type);
            ps.setString(2, category);
            ps.setDouble(3, amount);
            ps.setString(4, description);
            ps.setString(5, currency);
            ps.setInt(6, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "İşlem güncellendi.");

            loadTransactions();

            formTemizle();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_islemGuncelleActionPerformed

    private void analizGoruntuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizGoruntuleActionPerformed
        // TODO add your handling code here:
        new KullaniciRapor(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_analizGoruntuleActionPerformed

    private void cmbCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCurrencyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCurrencyActionPerformed

    private void giriseDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giriseDonActionPerformed
        // TODO add your handling code here:
        new GirisPanel().setVisible(true);
        dispose();
    }//GEN-LAST:event_giriseDonActionPerformed

    private void cikisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_cikisActionPerformed

    private void acikModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acikModActionPerformed

        getContentPane().setBackground(Color.WHITE);
        jPanel1.setBackground(Color.WHITE);
        jPanel2.setBackground(Color.WHITE);
        table.setBackground(Color.WHITE);

        table.setForeground(Color.BLACK);
        spinnerAmount.setForeground(Color.BLACK);
        txtDescription.setForeground(Color.BLACK);
        cmbCurrency.setForeground(Color.BLACK);

        jLabel1.setForeground(Color.BLACK);


    }//GEN-LAST:event_acikModActionPerformed

    private void koyuModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koyuModActionPerformed
        // TODO add your handling code here:
        Color koyuArka = new Color(40, 44, 52);
        Color koyuForm = new Color(60, 63, 65);
        Color yazıRenk = Color.WHITE;

        getContentPane().setBackground(koyuArka);
        jPanel1.setBackground(koyuForm);
        jPanel2.setBackground(koyuForm);
        table.setBackground(new Color(70, 70, 70));
        jLabel1.setForeground(yazıRenk);
        jLabel2.setForeground(yazıRenk);
        jLabel3.setForeground(yazıRenk);
        jLabel4.setForeground(yazıRenk);
        jLabel5.setForeground(yazıRenk);
        jLabel6.setForeground(yazıRenk);
        jLabel7.setForeground(yazıRenk);
        jLabel8.setForeground(yazıRenk);

        rdbIncome.setForeground(Color.WHITE);
        rdbExpense.setForeground(Color.WHITE);

        table.setForeground(Color.WHITE);
        table.setSelectionForeground(Color.BLACK);
        table.setSelectionBackground(new Color(100, 100, 100));

        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(60, 63, 65));


    }//GEN-LAST:event_koyuModActionPerformed

    private void varsayilanModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varsayilanModActionPerformed
        // TODO add your handling code here:
        dispose();
        new UserPanel(username).setVisible(true);

    }//GEN-LAST:event_varsayilanModActionPerformed

    private void formTemizle() {
        rdbIncome.setSelected(false);
        rdbExpense.setSelected(false);
        categoryList.clearSelection();
        spinnerAmount.setValue(0);
        txtDescription.setText("");
        cmbCurrency.setSelectedIndex(0);
        buttonGroup1.clearSelection();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem acikMod;
    private javax.swing.JButton analizGoruntule;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JList<String> categoryList;
    private javax.swing.JMenuItem cikis;
    private javax.swing.JComboBox<String> cmbCurrency;
    private javax.swing.JMenuItem giriseDon;
    private javax.swing.JPanel graphicsPanel;
    private javax.swing.JButton islemEkle;
    private javax.swing.JButton islemGuncelle;
    private javax.swing.JButton islemSil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem koyuMod;
    private javax.swing.JPanel newPanel;
    private javax.swing.JRadioButton rdbExpense;
    private javax.swing.JRadioButton rdbIncome;
    private javax.swing.JSpinner spinnerAmount;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JMenuItem varsayilanMod;
    // End of variables declaration//GEN-END:variables
}
