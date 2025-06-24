
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminPanel extends javax.swing.JFrame {

    private String username;

    public AdminPanel(String username) {
        initComponents();

        loadUsers();
        this.username = username;

        userTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && userTable.getSelectedRow() != -1) {
                seciliKullaniciyiFormaYaz();
            }
        });

        jTabbedPane1.setSelectedIndex(0);

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

    private void loadUsers() {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);

        try (Connection conn = DBHelper.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("role")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void seciliKullaniciyiFormaYaz() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }

        // Tablo üzerinden alıyoruz
        String username = userTable.getValueAt(selectedRow, 1).toString();
        String role = userTable.getValueAt(selectedRow, 2).toString();

        txtUsername.setText(username);
        cmbRole.setSelectedItem(role);

        // Şifreyi veritabanından çekiyoruz cünkü güvenlik gerekcesiyle tabloya yazmadim.
        try (Connection conn = DBHelper.getConnection()) {
            String sql = "SELECT password FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtPassword.setText(rs.getString("password"));
            } else {
                txtPassword.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Şifre alınırken hata oluştu.");
        }
    }

    private void clearForm() {
        txtUsername.setText("");
        txtPassword.setText("");
        cmbRole.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrolpane = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        cmbRole = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        kullaniciGuncelle = new javax.swing.JButton();
        kullaniciSil = new javax.swing.JButton();
        kullaniciEkle = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        acikMod = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        koyuMod = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        varsayilanMod = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        giriseDon = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        cikis = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin-Kullanıcılar");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(232, 242, 242));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("KAYITLI KULLANICILAR");

        scrolpane.setBackground(new java.awt.Color(243, 238, 238));

        userTable.setBackground(new java.awt.Color(250, 235, 235));
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Kullanıcı Adı", "Rol"
            }
        ));
        scrolpane.setViewportView(userTable);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Kullanıcı adı:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Sifre:");

        cmbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "user", "admin" }));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Rol:");

        kullaniciGuncelle.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        kullaniciGuncelle.setForeground(new java.awt.Color(0, 0, 153));
        kullaniciGuncelle.setText("Kullanıcı Güncelle");
        kullaniciGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kullaniciGuncelleActionPerformed(evt);
            }
        });

        kullaniciSil.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        kullaniciSil.setForeground(new java.awt.Color(0, 0, 102));
        kullaniciSil.setText("Kullanıcı Sil");
        kullaniciSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kullaniciSilActionPerformed(evt);
            }
        });

        kullaniciEkle.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        kullaniciEkle.setForeground(new java.awt.Color(0, 51, 153));
        kullaniciEkle.setText("Kullanıcı Ekle");
        kullaniciEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kullaniciEkleActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanel1.setBackground(new java.awt.Color(232, 242, 242));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1561, Short.MAX_VALUE)
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
            .addGap(0, 1561, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("İslemler", jPanel2);

        jPanel3.setBackground(new java.awt.Color(232, 242, 242));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1561, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Raporlar", jPanel3);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fa6-solid--user (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jLabel5)
                        .addGap(52, 52, 52)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(472, 472, 472)
                        .addComponent(kullaniciEkle)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(kullaniciSil, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(kullaniciGuncelle))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(637, 637, 637)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrolpane, javax.swing.GroupLayout.PREFERRED_SIZE, 1325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrolpane, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kullaniciSil, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kullaniciGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kullaniciEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenu3.setText("Görünüm");

        acikMod.setText("Açık Mod");
        acikMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acikModActionPerformed(evt);
            }
        });
        jMenu3.add(acikMod);
        jMenu3.add(jSeparator1);

        koyuMod.setText("Koyu Mod");
        koyuMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koyuModActionPerformed(evt);
            }
        });
        jMenu3.add(koyuMod);
        jMenu3.add(jSeparator2);

        varsayilanMod.setText("Varsayılan Mod");
        varsayilanMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varsayilanModActionPerformed(evt);
            }
        });
        jMenu3.add(varsayilanMod);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Çıkış");

        giriseDon.setText("Giris Syfasına Dön");
        giriseDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giriseDonActionPerformed(evt);
            }
        });
        jMenu4.add(giriseDon);
        jMenu4.add(jSeparator3);

        cikis.setText("Sistemden Çıkış");
        cikis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisActionPerformed(evt);
            }
        });
        jMenu4.add(cikis);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void kullaniciEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kullaniciEkleActionPerformed

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String role = cmbRole.getSelectedItem().toString();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kullanıcı adı ve şifre boş olamaz.");
            return;
        }

        try (Connection conn = DBHelper.getConnection()) {
            PreparedStatement check = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            check.setString(1, username);
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Bu kullanıcı zaten mevcut.");
                return;
            }

            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Kullanıcı eklendi.");
            loadUsers();
            clearForm();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "İşlem gerçekleştirilemedi. Lütfen tekrar deneyin.");
        }
    }

    {
    }//GEN-LAST:event_kullaniciEkleActionPerformed

    private void kullaniciSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kullaniciSilActionPerformed
        // TODO add your handling code here:
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {  
            JOptionPane.showMessageDialog(this, "Lütfen silinecek kullanıcıyı seçin.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bu kullanıcıyı ve tüm işlemlerini silmek istediğinize emin misiniz?",
                "Onay", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int userId = Integer.parseInt(userTable.getValueAt(selectedRow, 0).toString());

        try (Connection conn = DBHelper.getConnection()) {
            conn.setAutoCommit(false);

            // Önce bu kullanıcıya ait tüm işlemleri silelim
            PreparedStatement psDeleteTransactions = conn.prepareStatement(
                    "DELETE FROM transactions WHERE user_id = ?"
            );
            psDeleteTransactions.setInt(1, userId);
            psDeleteTransactions.executeUpdate();

            // Sonra kullanıcıyı silelim
            PreparedStatement psDeleteUser = conn.prepareStatement(
                    "DELETE FROM users WHERE id = ?"
            );
            psDeleteUser.setInt(1, userId);
            psDeleteUser.executeUpdate();

            conn.commit();

            JOptionPane.showMessageDialog(this, "Kullanıcı ve işlemleri silindi.");
            loadUsers();
            clearForm();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "İşlem gerçekleştirilemedi. Lütfen tekrar deneyin.");
        }
    }//GEN-LAST:event_kullaniciSilActionPerformed

    private void kullaniciGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kullaniciGuncelleActionPerformed
        // TODO add your handling code here:
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Güncellenecek kullanıcıyı seçin.");
            return;
        }

        int userId = Integer.parseInt(userTable.getValueAt(selectedRow, 0).toString());
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String role = cmbRole.getSelectedItem().toString();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kullanıcı adı ve şifre boş bırakılamaz.");
            return;
        }

        try (Connection conn = DBHelper.getConnection()) {

            PreparedStatement check = conn.prepareStatement(
                    "SELECT id FROM users WHERE username = ? AND id != ?"
            );
            check.setString(1, username);
            check.setInt(2, userId);
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Bu kullanıcı adı zaten kullanımda!");
                return;
            }

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?"
            );
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setInt(4, userId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Kullanıcı güncellendi.");
            loadUsers();
            clearForm();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "İşlem gerçekleştirilemedi. Lütfen tekrar deneyin.");
        }
    }//GEN-LAST:event_kullaniciGuncelleActionPerformed

    private void acikModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acikModActionPerformed

        jPanel4.setBackground(new Color(255, 255, 255));
        userTable.setBackground(new Color(245, 245, 245));
        userTable.setForeground(Color.BLACK);
        txtUsername.setBackground(Color.WHITE);
        txtUsername.setForeground(Color.BLACK);
        txtPassword.setBackground(Color.WHITE);
        txtPassword.setForeground(Color.BLACK);
    }//GEN-LAST:event_acikModActionPerformed

    private void koyuModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koyuModActionPerformed
        jPanel4.setBackground(new Color(50, 50, 50));
        userTable.setBackground(new Color(70, 70, 70));
        userTable.setForeground(Color.WHITE);

        txtUsername.setBackground(new Color(90, 90, 90));
        txtUsername.setForeground(Color.WHITE);

        txtPassword.setBackground(new Color(90, 90, 90));
        txtPassword.setForeground(Color.WHITE);

        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setForeground(Color.WHITE);
    }//GEN-LAST:event_koyuModActionPerformed

    private void varsayilanModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varsayilanModActionPerformed
        // TODO add your handling code here:
        new AdminPanel(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_varsayilanModActionPerformed

    private void giriseDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giriseDonActionPerformed
        // TODO add your handling code here:
        new GirisPanel().setVisible(true);
        dispose();
    }//GEN-LAST:event_giriseDonActionPerformed

    private void cikisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_cikisActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem acikMod;
    private javax.swing.JMenuItem cikis;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JMenuItem giriseDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem koyuMod;
    private javax.swing.JButton kullaniciEkle;
    private javax.swing.JButton kullaniciGuncelle;
    private javax.swing.JButton kullaniciSil;
    private javax.swing.JScrollPane scrolpane;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTable userTable;
    private javax.swing.JMenuItem varsayilanMod;
    // End of variables declaration//GEN-END:variables
}
