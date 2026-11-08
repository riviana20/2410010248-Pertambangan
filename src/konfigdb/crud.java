package konfigdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

public class crud {

    private Connection koneksiDB;
    private final String username = "root";
    private final String password = "";
    private final String jdbcurl = "jdbc:mysql://localhost:3306/pertambangan_2410010248";

    public crud() {
        try {
            this.koneksiDB = DriverManager.getConnection(jdbcurl, username, password);
            System.out.println("database terkoneksi");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
        }
    }

    // ================= CHECK LIST UNIT =================
    public void simpanChecklist(String listUnit) {
        try {
            String sql = "INSERT INTO check_list_unit (list_unit) VALUES (?)";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setString(1, listUnit);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubahChecklist(int id, String listUnit) {
        try {
            String sql = "UPDATE check_list_unit SET list_unit=? WHERE id=?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setString(1, listUnit);
            ps.setInt(2, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapusChecklist(int id) {
        try {
            String sql = "DELETE FROM check_list_unit WHERE id=?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String cariChecklist(int id) {
        try {
            String sql = "SELECT list_unit FROM check_list_unit WHERE id=?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("list_unit");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    // ================= HAULING =================
    // format waktu: yyyy-MM-dd HH:mm:ss  (contoh 2026-11-05 08:30:00)
    public void simpanHauling(String loading, String dumping, String lokasiLoading,
                              String lokasiHauling, String waktu) {
        try {
            String sql = "INSERT INTO hauling (loading, dumping, lokasi_loading, lokasi_hauling, waktu) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setString(1, loading);
            ps.setString(2, dumping);
            ps.setString(3, lokasiLoading);
            ps.setString(4, lokasiHauling);
            ps.setTimestamp(5, Timestamp.valueOf(waktu));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Format waktu harus yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubahHauling(int id, String loading, String dumping, String lokasiLoading,
                            String lokasiHauling, String waktu) {
        try {
            String sql = "UPDATE hauling SET loading=?, dumping=?, lokasi_loading=?, "
                    + "lokasi_hauling=?, waktu=? WHERE id=?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setString(1, loading);
            ps.setString(2, dumping);
            ps.setString(3, lokasiLoading);
            ps.setString(4, lokasiHauling);
            ps.setTimestamp(5, Timestamp.valueOf(waktu));
            ps.setInt(6, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Format waktu harus yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapusHauling(int id) {
        try {
            String sql = "DELETE FROM hauling WHERE id=?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // hasil: {loading, dumping, lokasi_loading, lokasi_hauling, waktu}
    public String[] cariHauling(int id) {
        try {
            String sql = "SELECT * FROM hauling WHERE id=?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[]{
                    rs.getString("loading"),
                    rs.getString("dumping"),
                    rs.getString("lokasi_loading"),
                    rs.getString("lokasi_hauling"),
                    rs.getString("waktu")
                };
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    // ================= TROUBLE UNIT =================
    public void simpanTrouble(String keterangan) {
        try {
            String sql = "INSERT INTO trouble_unit (keterangan_unit) VALUES (?)";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setString(1, keterangan);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubahTrouble(int id, String keterangan) {
        try {
            String sql = "UPDATE trouble_unit SET keterangan_unit=? WHERE id=?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setString(1, keterangan);
            ps.setInt(2, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapusTrouble(int id) {
        try {
            String sql = "DELETE FROM trouble_unit WHERE id=?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String cariTrouble(int id) {
        try {
            String sql = "SELECT keterangan_unit FROM trouble_unit WHERE id=?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("keterangan_unit");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
}