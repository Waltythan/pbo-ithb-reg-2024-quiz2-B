package View;

import Controller.ArtworkController;
import Model.Users;

import javax.swing.*;
import java.awt.*;

public class AddArtworkView extends JFrame {
    private Users currentUser;
    private ArtworkController artworkController;
    private JTextField titleField;
    private JTextArea descriptionArea;

    public AddArtworkView(Users user) {
        this.currentUser = user;
        this.artworkController = new ArtworkController();

        setTitle("Museum Ambazing - Tambah Karya");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(new JLabel("Judul Karya:"));
        titleField = new JTextField();
        mainPanel.add(titleField);

        mainPanel.add(new JLabel("Deskripsi:"));
        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        mainPanel.add(new JScrollPane(descriptionArea));

        JButton uploadButton = new JButton("Upload Gambar");
        uploadButton.addActionListener(e -> uploadImage());
        mainPanel.add(uploadButton);

        JButton submitButton = new JButton("Simpan Karya");
        submitButton.addActionListener(e -> submitArtwork());
        mainPanel.add(submitButton);

        JButton backButton = new JButton("Kembali ke Daftar Karya");
        backButton.addActionListener(e -> backToArtworkList());
        mainPanel.add(backButton);

        add(mainPanel);
    }

    private void uploadImage() {
        // how kooo T_T
    }

    private void submitArtwork() {
        String title = titleField.getText();
        String description = descriptionArea.getText();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Judulnya diisi dong!", 
                "Gagal", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = artworkController.addArtwork(
            title, 
            description,
            currentUser.getId()
        );

        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Karya kamu berhasil ditambahkan mas", 
                "Berhasil", 
                JOptionPane.INFORMATION_MESSAGE);
            
            new ArtworkListView(currentUser).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Gagal maneh wak", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void backToArtworkList() {
        new ArtworkListView(currentUser).setVisible(true);
        dispose();
    }
}