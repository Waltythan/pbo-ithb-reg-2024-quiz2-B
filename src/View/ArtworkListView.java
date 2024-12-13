package View;

import Controller.ArtworkController;
import Model.Artworks;
import Model.Users;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ArtworkListView extends JFrame {
    private Users currentUser;
    private ArtworkController artworkController;

    public ArtworkListView(Users user) {
        this.currentUser = user;
        this.artworkController = new ArtworkController();

        setTitle("Museum Ambazinggg - Daftar Karya");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addArtworkButton = new JButton("Tambah Karya");
        addArtworkButton.addActionListener(e -> openAddArtworkView());
        topPanel.add(new JLabel("Halo dimas kanjeng " + currentUser.getName()));
        topPanel.add(addArtworkButton);

        JPanel artworkPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        artworkPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<Artworks> artworks = artworkController.getAllArtworks();
        for (Artworks artwork : artworks) {
            artworkPanel.add(createArtworkPanel(artwork));
        }

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(artworkPanel), BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createArtworkPanel(Artworks artwork) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel titleLabel = new JLabel(artwork.getTitle());
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel detailPanel = new JPanel(new GridLayout(2, 1));
        detailPanel.add(new JLabel("Mas Pelukis: " + artwork.getArtistName()));
        
        JLabel descLabel = new JLabel(artwork.getDescription());
        descLabel.setHorizontalAlignment(JLabel.CENTER);
        detailPanel.add(new JScrollPane(descLabel));

        panel.add(detailPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void openAddArtworkView() {
        new AddArtworkView(currentUser).setVisible(true);
        dispose();
    }
}