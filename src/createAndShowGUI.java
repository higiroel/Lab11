import javax.swing.*;
import java.awt.*;
import java.io.File;

public class createAndShowGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(createAndShowGUI::new);
    }

    public createAndShowGUI() {
        JFrame frame = new JFrame("Recursive Lister");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton startButton = new JButton("Start");
        JButton quitButton = new JButton("Quit");
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JLabel titleLabel = new JLabel("Recursive Lister");

        startButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                listFiles(selectedDirectory, textArea);
            }
        });

        quitButton.addActionListener(e -> System.exit(0));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(startButton, BorderLayout.WEST);
        panel.add(quitButton, BorderLayout.EAST);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void listFiles(File directory, JTextArea textArea) {
        textArea.setText(""); // Clear previous content

        if (directory.isDirectory()) {
            listFilesRecursive(directory, textArea);
        } else {
            textArea.append("Selected path is not a directory.");
        }
    }

    private static void listFilesRecursive(File directory, JTextArea textArea) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesRecursive(file, textArea);
                } else {
                    textArea.append(file.getAbsolutePath() + "\n");
                }
            }
        }
    }
}
