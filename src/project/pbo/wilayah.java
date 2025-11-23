/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project.pbo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author destria
 */
public class wilayah extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(wilayah.class.getName());

    public wilayah() {
        initComponents();
        loadDataWilayah(); 
    }
    
    private void loadDataWilayah() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT id_wilayah, nama_wilayah, jarak, keterangan FROM wilayah";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_wilayah"),
                    rs.getString("nama_wilayah"),
                    rs.getString("jarak"),
                    rs.getString("keterangan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data wilayah!");
        }
    }
    
    private void tambahWilayah() {
        String id = JOptionPane.showInputDialog(this, "ID Wilayah:");
        String nama = JOptionPane.showInputDialog(this, "Nama Wilayah:");
        String jarak = JOptionPane.showInputDialog(this, "Jarak:");
        String ket = JOptionPane.showInputDialog(this, "Keterangan:");

        if (id == null || nama == null || jarak == null) {
            JOptionPane.showMessageDialog(this, "Input tidak boleh kosong!");
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO wilayah (id_wilayah, nama_wilayah, jarak, keterangan) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id);
            pst.setString(2, nama);
            pst.setString(3, jarak);
            pst.setString(4, ket);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Wilayah berhasil ditambahkan!");

            loadDataWilayah();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menambah wilayah!");
        }
    }
    
    private void hapusWilayah() {
        int row = jTable1.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
            return;
        }

        String id = jTable1.getValueAt(row, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Hapus wilayah dengan ID " + id + "?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = DatabaseConnection.getConnection();
                String sql = "DELETE FROM wilayah WHERE id_wilayah=?";
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, id);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Wilayah dihapus.");
                loadDataWilayah();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus wilayah!");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        ButtonTambahRiwayat = new javax.swing.JButton();
        ButtonHapusWilayah = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ButtonDashboard = new javax.swing.JButton();
        ButtonDataPelanggan = new javax.swing.JButton();
        ButtonPesan = new javax.swing.JButton();
        ButtonWilayah = new javax.swing.JButton();
        ButtonLogout = new javax.swing.JButton();
        ButtonLaporan = new javax.swing.JButton();
        ButtonCucian = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(200, 217, 230));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 648));

        jPanel3.setBackground(new java.awt.Color(245, 239, 235));
        jPanel3.setPreferredSize(new java.awt.Dimension(1070, 606));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID Wilayah", "Nama Wilayah", "Jarak", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); 
        jLabel1.setForeground(new java.awt.Color(47, 65, 86));
        jLabel1.setText("Wilayah");

        ButtonTambahRiwayat.setText("➕ Tambah");
        ButtonTambahRiwayat.addActionListener(evt -> ButtonTambahRiwayatActionPerformed(evt));

        ButtonHapusWilayah.setText("Hapus");
        ButtonHapusWilayah.addActionListener(evt -> ButtonHapusWilayahActionPerformed(evt));

        // ----- Layout panel wilayah -----
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonTambahRiwayat)
                    .addComponent(ButtonHapusWilayah))
                .addGap(57, 57, 57))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(481, 481, 481)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ButtonTambahRiwayat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonHapusWilayah)))
                .addGap(71, 71, 71))
        );

        // ----- Label Sidebar -----
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jLabel2.setForeground(new java.awt.Color(47, 65, 86));
        jLabel2.setText("BackBurn Laundry");

        // ----- Sidebar buttons -----
        ButtonDashboard.setBackground(new java.awt.Color(245, 239, 235));
        ButtonDashboard.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        ButtonDashboard.setForeground(new java.awt.Color(75, 111, 127));
        ButtonDashboard.setText("Dashboard");
        ButtonDashboard.setPreferredSize(new java.awt.Dimension(89, 30));
        ButtonDashboard.addActionListener(evt -> ButtonDashboardActionPerformed(evt));

        ButtonDataPelanggan.setBackground(new java.awt.Color(245, 239, 235));
        ButtonDataPelanggan.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        ButtonDataPelanggan.setForeground(new java.awt.Color(75, 111, 127));
        ButtonDataPelanggan.setText("Data Pelanggan");

        ButtonPesan.setBackground(new java.awt.Color(245, 239, 235));
        ButtonPesan.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        ButtonPesan.setForeground(new java.awt.Color(75, 111, 127));
        ButtonPesan.setText("Pesan");
        ButtonPesan.addActionListener(evt -> ButtonPesanActionPerformed(evt));

        ButtonWilayah.setBackground(new java.awt.Color(245, 239, 235));
        ButtonWilayah.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        ButtonWilayah.setForeground(new java.awt.Color(75, 111, 127));
        ButtonWilayah.setText("Wilayah");

        ButtonLogout.setBackground(new java.awt.Color(245, 239, 235));
        ButtonLogout.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        ButtonLogout.setForeground(new java.awt.Color(75, 111, 127));
        ButtonLogout.setText("Logout");
        ButtonLogout.addActionListener(evt -> ButtonLogoutActionPerformed(evt));

        ButtonLaporan.setBackground(new java.awt.Color(245, 239, 235));
        ButtonLaporan.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        ButtonLaporan.setForeground(new java.awt.Color(75, 111, 127));
        ButtonLaporan.setText("Laporan Pemasukan");
        ButtonLaporan.addActionListener(evt -> ButtonLaporanActionPerformed(evt));

        ButtonCucian.setBackground(new java.awt.Color(245, 239, 235));
        ButtonCucian.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        ButtonCucian.setForeground(new java.awt.Color(75, 111, 127));
        ButtonCucian.setText("Cucian & Jenis Cucian");
        ButtonCucian.addActionListener(evt -> ButtonCucianActionPerformed(evt));

        // ----- Layout seluruh form -----
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(ButtonDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonDataPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonCucian, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonWilayah, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(ButtonDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonPesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonDataPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonCucian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonWilayah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(261, 261, 261)
                .addComponent(ButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void ButtonPesanActionPerformed(java.awt.event.ActionEvent evt) {                                            
        new Offline().setVisible(true);
        this.dispose();
    }                                           

    private void ButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {                                             
        new login().setVisible(true);
        this.dispose();
    }                                            

    private void ButtonTambahRiwayatActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        tambahWilayah();
    }                                                   

    private void ButtonLaporanActionPerformed(java.awt.event.ActionEvent evt) {                                              
        new laporan().setVisible(true);
        this.dispose();
    }                                             

    private void ButtonDashboardActionPerformed(java.awt.event.ActionEvent evt) {                                                
        new homepage_admin().setVisible(true);
        this.dispose();
    }                                               

    private void ButtonCucianActionPerformed(java.awt.event.ActionEvent evt) {                                             
        new Cucian().setVisible(true);
        this.dispose();
    }                                            

    private void ButtonWilayahActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // tetap di halaman wilayah
    }                                             

    private void ButtonHapusWilayahActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        hapusWilayah();
    }                                                  

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {}

        java.awt.EventQueue.invokeLater(() -> new wilayah().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton ButtonCucian;
    private javax.swing.JButton ButtonDashboard;
    private javax.swing.JButton ButtonDataPelanggan;
    private javax.swing.JButton ButtonHapusWilayah;
    private javax.swing.JButton ButtonLaporan;
    private javax.swing.JButton ButtonLogout;
    private javax.swing.JButton ButtonPesan;
    private javax.swing.JButton ButtonTambahRiwayat;
    private javax.swing.JButton ButtonWilayah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
