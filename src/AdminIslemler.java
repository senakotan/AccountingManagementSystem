
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminIslemler extends javax.swing.JFrame {

    private String username;

    public AdminIslemler(String username) {
        initComponents();
        this.username = username;
        loadTransactions();
        loadUsersToCombo();

        transactionTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && transactionTable.getSelectedRow() != -1) {
                seciliIslemiFormaYaz();
            }
        });

        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                int selectedIndex = jTabbedPane1.getSelectedIndex();
                String selectedTitle = jTabbedPane1.getTitleAt(selectedIndex);

                if (selectedTitle.equals("Kullanıcılar")) {
                    new AdminPanel(username).setVisible(true);
                    dispose();
                } else if (selectedTitle.equals("İslemler")) {
                    new AdminIslemler(username).setVisible(true);
                    dispose();
                } else if (selectedTitle.equals("Raporlar")) {
                    new AdminRaporlar(username).setVisible(true);
                    dispose();
                }
            }
        });

    }

    private void seciliIslemiFormaYaz() {
        int row = transactionTable.getSelectedRow();
        if (row == -1) {
            return;
        }

        //Secilen satırdaki ilgili sütunlardaki 2-3-4.. elemanları değişkenlere atarız 
        String type = transactionTable.getValueAt(row, 2).toString();
        String category = transactionTable.getValueAt(row, 3).toString();
        String amount = transactionTable.getValueAt(row, 4).toString();
        String description = transactionTable.getValueAt(row, 5).toString();
        String user = transactionTable.getValueAt(row, 6).toString();
        String currency = transactionTable.getValueAt(row, 7).toString();

        //Secilen islemdeki değerler ilgili kısımlara getirilir.
        cmbUser.setSelectedItem(user);
        cmbType.setSelectedItem(type.trim());
        cmbCategory.setSelectedItem(category);
        txtAmount.setText(amount);
        txtDescription.setText(description);
        cmbCurrency.setSelectedItem(currency);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenuBar7 = new javax.swing.JMenuBar();
        jMenu13 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jMenuBar8 = new javax.swing.JMenuBar();
        jMenu15 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        transactionTable = new javax.swing.JTable();
        cmbUser = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<>();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbCurrency = new javax.swing.JComboBox<>();
        txtDescription = new javax.swing.JTextField();
        islemEkle = new javax.swing.JButton();
        islemSil = new javax.swing.JButton();
        islemGuncelle = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        acikMod = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        koyuMod = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        varsayilanMod = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        giriseDon = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        cikis = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu9.setText("File");
        jMenuBar5.add(jMenu9);

        jMenu10.setText("Edit");
        jMenuBar5.add(jMenu10);

        jMenu11.setText("File");
        jMenuBar6.add(jMenu11);

        jMenu12.setText("Edit");
        jMenuBar6.add(jMenu12);

        jMenu13.setText("File");
        jMenuBar7.add(jMenu13);

        jMenu14.setText("Edit");
        jMenuBar7.add(jMenu14);

        jMenu15.setText("File");
        jMenuBar8.add(jMenu15);

        jMenu16.setText("Edit");
        jMenuBar8.add(jMenu16);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AdminPanel-İslemEkleme");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(232, 242, 242));

        transactionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tarih", "Tür", "Kategori", "Tutar", "Açıklama", "Kullanıcı", "Para Birimi"
            }
        ));
        jScrollPane1.setViewportView(transactionTable);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Açıklama:");

        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income", "Expense" }));
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kira", "Fatura", "Ulaşım", "Yemek", "Eğlence", "Market", "Maaş", "Diğer", " " }));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("Kullanıcı:");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Tür:");

        cmbCurrency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TL", "USD", "EURO" }));

        islemEkle.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        islemEkle.setForeground(new java.awt.Color(0, 0, 102));
        islemEkle.setText("İSLEM EKLE");
        islemEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                islemEkleActionPerformed(evt);
            }
        });

        islemSil.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        islemSil.setForeground(new java.awt.Color(0, 0, 102));
        islemSil.setText("İSLEM SİL");
        islemSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                islemSilActionPerformed(evt);
            }
        });

        islemGuncelle.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        islemGuncelle.setForeground(new java.awt.Color(0, 0, 102));
        islemGuncelle.setText("İSLEM GÜNCELLE");
        islemGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                islemGuncelleActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("Tutar:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("Kategori:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("Para Birimi:");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1369, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Kullanıcılar", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1369, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("İslemler", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1369, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Raporlar", jPanel3);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon-park--analysis.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/carbon--text-link-analysis.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 122));
        jLabel10.setText("KAYITLI İSLEMLER");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 153));
        jLabel11.setText("Para Birimi:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(1031, 1031, 1031))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel7)
                        .addGap(188, 188, 188)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addComponent(jLabel3))
                            .addComponent(jLabel11))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbCategory, javax.swing.GroupLayout.Alignment.LEADING, 0, 249, Short.MAX_VALUE)
                                    .addComponent(cmbType, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbUser, javax.swing.GroupLayout.Alignment.LEADING, 0, 249, Short.MAX_VALUE)
                                    .addComponent(cmbCurrency, javax.swing.GroupLayout.Alignment.LEADING, 0, 249, Short.MAX_VALUE)
                                    .addComponent(txtAmount, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(207, 207, 207)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(197, 197, 197)
                                        .addComponent(jLabel9))))
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(552, 552, 552)
                        .addComponent(jLabel10))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(islemEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(islemSil, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(islemGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(517, 517, 517)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(islemEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(islemSil, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(islemGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(783, 783, 783)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 944));

        acikMod.setText(" Görünüm");

        jMenuItem1.setText("Açık Mod");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        acikMod.add(jMenuItem1);
        acikMod.add(jSeparator1);

        koyuMod.setText("Koyu Mod");
        koyuMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koyuModActionPerformed(evt);
            }
        });
        acikMod.add(koyuMod);
        acikMod.add(jSeparator2);

        varsayilanMod.setText("Varsayılan Mod");
        varsayilanMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varsayilanModActionPerformed(evt);
            }
        });
        acikMod.add(varsayilanMod);

        jMenuBar1.add(acikMod);

        jMenu2.setText("Çıkış");

        giriseDon.setText("Giris Sayfasın Dön");
        giriseDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giriseDonActionPerformed(evt);
            }
        });
        jMenu2.add(giriseDon);
        jMenu2.add(jSeparator3);

        cikis.setText("Sistemden Çıkış");
        cikis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisActionPerformed(evt);
            }
        });
        jMenu2.add(cikis);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadTransactions() {
        DefaultTableModel model = (DefaultTableModel) transactionTable.getModel();
        model.setRowCount(0);

        try (Connection conn = DBHelper.getConnection()) {
            String query = """
            SELECT t.id, t.date, t.type, t.category, t.amount, t.description, 
                   u.username, t.currency 
            FROM transactions t 
            JOIN users u ON t.user_id = u.id
        """;  // 
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("date"),
                    rs.getString("type"),
                    rs.getString("category"),
                    rs.getDouble("amount"),
                    rs.getString("description"),
                    rs.getString("username"),
                    rs.getString("currency")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Bir hata oluştu. Lütfen daha sonra tekrar deneyin.");
        }
    }

    private void loadUsersToCombo() {
        cmbUser.removeAllItems();

        try (Connection conn = DBHelper.getConnection()) {
            String sql = "SELECT username FROM users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cmbUser.addItem(rs.getString("username"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Kullanıcılar yüklenirken hata oluştu.");
        }
    }

    private void clearForm() {
        cmbType.setSelectedIndex(0);
        cmbCategory.setSelectedIndex(0);
        txtDescription.setText("");
        txtAmount.setText("");
        cmbCurrency.setSelectedIndex(0);

        if (cmbUser.getItemCount() > 0) {
            cmbUser.setSelectedIndex(0);
        }

        transactionTable.clearSelection();
    }


    private void islemEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_islemEkleActionPerformed
        // TODO add your handling code here:

        String type = cmbType.getSelectedItem().toString();
        String category = cmbCategory.getSelectedItem().toString();
        String description = txtDescription.getText().trim();
        String amountText = txtAmount.getText().trim();
        String currency = cmbCurrency.getSelectedItem().toString();
        String user = cmbUser.getSelectedItem().toString();

        if (category.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kategori ve tutar boş olamaz.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            try (Connection conn = DBHelper.getConnection()) {
                PreparedStatement psUser = conn.prepareStatement("SELECT id FROM users WHERE username = ?");
                psUser.setString(1, user);
                ResultSet rs = psUser.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Kullanıcı bulunamadı.");
                    return;
                }
                int userId = rs.getInt("id");

                String query = "INSERT INTO transactions (type, category, amount, description, user_id, currency, date) "
                        + "VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, type);
                ps.setString(2, category);
                ps.setDouble(3, amount);
                ps.setString(4, description);
                ps.setInt(5, userId);
                ps.setString(6, currency);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "İşlem eklendi.");
                loadTransactions();
                clearForm();

            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Geçerli bir tutar girin.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Bir hata oluştu. Lütfen daha sonra tekrar deneyin.");
        }
    }//GEN-LAST:event_islemEkleActionPerformed

    private void islemSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_islemSilActionPerformed
        // TODO add your handling code here:
        int selectedRow = transactionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Lütfen silinecek işlemi seçin.");
            return;
        }

        int id = (int) transactionTable.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bu işlemi silmek istediğinize emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try (Connection conn = DBHelper.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM transactions WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "İşlem silindi.");
            loadTransactions();
            clearForm();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Silme sırasında hata oluştu.");
        }
    }//GEN-LAST:event_islemSilActionPerformed

    private void islemGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_islemGuncelleActionPerformed
        // TODO add your handling code here:
        int selectedRow = transactionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Lütfen güncellenecek işlemi seçin.");
            return;
        }

        int id = (int) transactionTable.getValueAt(selectedRow, 0);
        String type = cmbType.getSelectedItem().toString();
        String category = cmbCategory.getSelectedItem().toString();
        String description = txtDescription.getText().trim();
        String amountText = txtAmount.getText().trim();
        String currency = cmbCurrency.getSelectedItem().toString();
        String user = cmbUser.getSelectedItem().toString();

        if (category.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kategori ve tutar boş olamaz.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            try (Connection conn = DBHelper.getConnection()) {
                PreparedStatement psUser = conn.prepareStatement("SELECT id FROM users WHERE username = ?");
                psUser.setString(1, user);
                ResultSet rs = psUser.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Kullanıcı bulunamadı.");
                    return;
                }
                int userId = rs.getInt("id");

                PreparedStatement ps = conn.prepareStatement("UPDATE transactions SET type = ?, category = ?, amount = ?, description = ?, user_id = ?, currency = ? WHERE id = ?");
                ps.setString(1, type);
                ps.setString(2, category);
                ps.setDouble(3, amount);
                ps.setString(4, description);
                ps.setInt(5, userId);
                ps.setString(6, currency);
                ps.setInt(7, id);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "İşlem güncellendi.");
                loadTransactions();
                clearForm();

            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Geçerli bir tutar girin.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Güncelleme sırasında hata oluştu.");
        }
    }//GEN-LAST:event_islemGuncelleActionPerformed
    private void jTextFieldHighlight(Color foreground, Color background) {
        txtAmount.setForeground(foreground);
        txtAmount.setBackground(background);
        txtDescription.setForeground(foreground);
        txtDescription.setBackground(background);
     
    }

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTypeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jPanel8.setBackground(new Color(255, 255, 255));
        transactionTable.setBackground(new Color(245, 245, 245));
        transactionTable.setForeground(Color.BLACK);

        jTextFieldHighlight(Color.BLACK, Color.WHITE);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void koyuModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koyuModActionPerformed
        // TODO add your handling code here:
        jPanel8.setBackground(new Color(50, 50, 50));
        transactionTable.setBackground(new Color(80, 80, 80));
        transactionTable.setForeground(Color.WHITE);

        jTextFieldHighlight(Color.WHITE, new Color(60, 60, 60));
        jLabel10.setForeground(Color.WHITE);
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setForeground(Color.WHITE);
        jLabel6.setForeground(Color.WHITE);
        jLabel4.setForeground(Color.WHITE);
        jLabel11.setForeground(Color.WHITE);


    }//GEN-LAST:event_koyuModActionPerformed

    private void varsayilanModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varsayilanModActionPerformed
        // TODO add your handling code here:
        new AdminIslemler(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_varsayilanModActionPerformed

    private void giriseDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giriseDonActionPerformed
        // TODO add your handling code here:
        new GirisPanel().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_giriseDonActionPerformed

    private void cikisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_cikisActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu acikMod;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem cikis;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbCurrency;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JComboBox<String> cmbUser;
    private javax.swing.JMenuItem giriseDon;
    private javax.swing.JButton islemEkle;
    private javax.swing.JButton islemGuncelle;
    private javax.swing.JButton islemSil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenuBar jMenuBar7;
    private javax.swing.JMenuBar jMenuBar8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem koyuMod;
    private javax.swing.JTable transactionTable;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JMenuItem varsayilanMod;
    // End of variables declaration//GEN-END:variables
}
